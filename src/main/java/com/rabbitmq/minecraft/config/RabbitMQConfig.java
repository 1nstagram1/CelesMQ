package com.rabbitmq.minecraft.config;

/**
 * Configuration class for RabbitMQ connection settings
 */
public class RabbitMQConfig {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String virtualHost;
    private final int connectionTimeout;
    private final int networkRecoveryInterval;
    private final boolean automaticRecoveryEnabled;

    private RabbitMQConfig(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
        this.virtualHost = builder.virtualHost;
        this.connectionTimeout = builder.connectionTimeout;
        this.networkRecoveryInterval = builder.networkRecoveryInterval;
        this.automaticRecoveryEnabled = builder.automaticRecoveryEnabled;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getNetworkRecoveryInterval() {
        return networkRecoveryInterval;
    }

    public boolean isAutomaticRecoveryEnabled() {
        return automaticRecoveryEnabled;
    }

    /**
     * Builder class for RabbitMQConfig
     */
    public static class Builder {
        private String host = "localhost";
        private int port = 5672;
        private String username = "guest";
        private String password = "guest";
        private String virtualHost = "/";
        private int connectionTimeout = 10000; // 10 seconds of timeout
        private int networkRecoveryInterval = 5000; // 5 seconds between recovery attempts
        private boolean automaticRecoveryEnabled = true;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder virtualHost(String virtualHost) {
            this.virtualHost = virtualHost;
            return this;
        }

        public Builder connectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public Builder networkRecoveryInterval(int networkRecoveryInterval) {
            this.networkRecoveryInterval = networkRecoveryInterval;
            return this;
        }

        public Builder automaticRecoveryEnabled(boolean automaticRecoveryEnabled) {
            this.automaticRecoveryEnabled = automaticRecoveryEnabled;
            return this;
        }

        public RabbitMQConfig build() {
            return new RabbitMQConfig(this);
        }
    }
}
