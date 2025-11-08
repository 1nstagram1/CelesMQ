package com.rabbitmq.minecraft.connection;

import com.rabbitmq.client.*;
import com.rabbitmq.minecraft.config.RabbitMQConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages RabbitMQ connections with automatic recovery and reconnection logic
 */
public class RabbitMQConnectionManager {

    private final JavaPlugin plugin;
    private final RabbitMQConfig config;
    private final Logger logger;
    private Connection connection;
    private Channel channel;
    private boolean isShuttingDown = false;

    public RabbitMQConnectionManager(JavaPlugin plugin, RabbitMQConfig config) {
        this.plugin = plugin;
        this.config = config;
        this.logger = plugin.getLogger();
    }

    /**
     * Establishes connection to RabbitMQ server
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(config.getHost());
            factory.setPort(config.getPort());
            factory.setUsername(config.getUsername());
            factory.setPassword(config.getPassword());
            factory.setVirtualHost(config.getVirtualHost());
            factory.setConnectionTimeout(config.getConnectionTimeout());
            factory.setAutomaticRecoveryEnabled(config.isAutomaticRecoveryEnabled());
            factory.setNetworkRecoveryInterval(config.getNetworkRecoveryInterval());

            // Add connection recovery listeners
            factory.setRecoveryDelayHandler(new RecoveryDelayHandler() {
                @Override
                public long getDelay(int recoveryAttempts) {
                    return config.getNetworkRecoveryInterval();
                }
            });

            connection = factory.newConnection();
            channel = connection.createChannel();

            // Add connection listeners
            connection.addShutdownListener(new ShutdownListener() {
                @Override
                public void shutdownCompleted(ShutdownSignalException cause) {
                    if (!isShuttingDown && !cause.isInitiatedByApplication()) {
                        logger.log(Level.WARNING, "RabbitMQ connection lost: " + cause.getMessage());
                    }
                }
            });

            ((Recoverable) connection).addRecoveryListener(new RecoveryListener() {
                @Override
                public void handleRecovery(Recoverable recoverable) {
                    logger.info("RabbitMQ connection recovered successfully");
                }

                @Override
                public void handleRecoveryStarted(Recoverable recoverable) {
                    logger.info("RabbitMQ connection recovery started");
                }
            });

            logger.info("Successfully connected to RabbitMQ server at " + config.getHost() + ":" + config.getPort());
            return true;

        } catch (IOException | TimeoutException e) {
            logger.log(Level.SEVERE, "Failed to connect to RabbitMQ server", e);
            return false;
        }
    }

    /**
     * Gets the current channel, creating one if necessary
     * @return Channel instance
     * @throws IOException if channel creation fails
     */
    public Channel getChannel() throws IOException {
        if (channel == null || !channel.isOpen()) {
            if (connection != null && connection.isOpen()) {
                channel = connection.createChannel();
            } else {
                throw new IOException("Connection is not established");
            }
        }
        return channel;
    }

    /**
     * Creates a new channel
     * @return new Channel instance
     * @throws IOException if channel creation fails
     */
    public Channel createChannel() throws IOException {
        if (connection == null || !connection.isOpen()) {
            throw new IOException("Connection is not established");
        }
        return connection.createChannel();
    }

    /**
     * Checks if connection is active
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connection != null && connection.isOpen();
    }

    /**
     * Closes the connection and channel gracefully
     */
    public void disconnect() {
        isShuttingDown = true;

        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
                logger.info("RabbitMQ channel closed");
            }
        } catch (IOException | TimeoutException e) {
            logger.log(Level.WARNING, "Error closing RabbitMQ channel", e);
        }

        try {
            if (connection != null && connection.isOpen()) {
                connection.close();
                logger.info("RabbitMQ connection closed");
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error closing RabbitMQ connection", e);
        }
    }

    /**
     * Gets the plugin instance
     * @return JavaPlugin instance
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the configuration
     * @return RabbitMQConfig instance
     */
    public RabbitMQConfig getConfig() {
        return config;
    }
}
