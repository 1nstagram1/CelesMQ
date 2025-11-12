package io.hydrodevelopments.celesmq.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

  // SSL/TLS configuration
  private final boolean useSsl;
  private final String sslProtocol;
  private final String trustStorePath;
  private final String trustStorePassword;
  private final String keyStorePath;
  private final String keyStorePassword;
  private final boolean validateServerCertificate;

  // New fields for consumer and channels
  private final String consumerName;
  // Exchange-based channels (exchange name per logical channel)
  private final Map<String, String> channels;
  // Direct channels (queue-based routing)
  private final Set<String> directChannels;
  private final boolean autoSubscribe;

  // Queue configuration
  private final boolean queueDurable;
  private final boolean queueExclusive;
  private final boolean queueAutoDelete;
  private final Map<String, Object> queueArguments;

  // Exchange configuration
  private final boolean exchangeDurable;
  private final boolean exchangeAutoDelete;
  private final Map<String, Object> exchangeArguments;

  private RabbitMQConfig(Builder builder) {
    this.host = builder.host;
    this.port = builder.port;
    this.username = builder.username;
    this.password = builder.password;
    this.virtualHost = builder.virtualHost;
    this.connectionTimeout = builder.connectionTimeout;
    this.networkRecoveryInterval = builder.networkRecoveryInterval;
    this.automaticRecoveryEnabled = builder.automaticRecoveryEnabled;
    this.useSsl = builder.useSsl;
    this.sslProtocol = builder.sslProtocol;
    this.trustStorePath = builder.trustStorePath;
    this.trustStorePassword = builder.trustStorePassword;
    this.keyStorePath = builder.keyStorePath;
    this.keyStorePassword = builder.keyStorePassword;
    this.validateServerCertificate = builder.validateServerCertificate;
    this.consumerName = builder.consumerName;
    this.channels = new HashMap<>(builder.channels);
    this.directChannels = new HashSet<>(builder.directChannels);
    this.autoSubscribe = builder.autoSubscribe;
    this.queueDurable = builder.queueDurable;
    this.queueExclusive = builder.queueExclusive;
    this.queueAutoDelete = builder.queueAutoDelete;
    this.queueArguments = builder.queueArguments != null ? new HashMap<>(builder.queueArguments) : null;
    this.exchangeDurable = builder.exchangeDurable;
    this.exchangeAutoDelete = builder.exchangeAutoDelete;
    this.exchangeArguments = builder.exchangeArguments != null ? new HashMap<>(builder.exchangeArguments) : null;
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

  public String getConsumerName() {
    return consumerName;
  }

  public Map<String, String> getChannels() {
    return new HashMap<>(channels);
  }

  public Set<String> getDirectChannels() {
    return new HashSet<>(directChannels);
  }

  public boolean isAutoSubscribe() {
    return autoSubscribe;
  }

  public boolean isUseSsl() {
    return useSsl;
  }

  public String getSslProtocol() {
    return sslProtocol;
  }

  public String getTrustStorePath() {
    return trustStorePath;
  }

  public String getTrustStorePassword() {
    return trustStorePassword;
  }

  public String getKeyStorePath() {
    return keyStorePath;
  }

  public String getKeyStorePassword() {
    return keyStorePassword;
  }

  public boolean isValidateServerCertificate() {
    return validateServerCertificate;
  }

  public boolean isQueueDurable() {
    return queueDurable;
  }

  public boolean isQueueExclusive() {
    return queueExclusive;
  }

  public boolean isQueueAutoDelete() {
    return queueAutoDelete;
  }

  public Map<String, Object> getQueueArguments() {
    return queueArguments != null ? new HashMap<>(queueArguments) : null;
  }

  public boolean isExchangeDurable() {
    return exchangeDurable;
  }

  public boolean isExchangeAutoDelete() {
    return exchangeAutoDelete;
  }

  public Map<String, Object> getExchangeArguments() {
    return exchangeArguments != null ? new HashMap<>(exchangeArguments) : null;
  }

  /**
   * Builder class for RabbitMQConfig
   * All fields must be explicitly configured - no defaults
   */
  public static class Builder {
    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;
    private int connectionTimeout;
    private int networkRecoveryInterval;
    private boolean automaticRecoveryEnabled;
    private String consumerName;
    private Map<String, String> channels = new HashMap<>();
    private Set<String> directChannels = new HashSet<>();
    private boolean autoSubscribe;

    // SSL/TLS configuration (optional)
    private boolean useSsl = false;
    private String sslProtocol = "TLSv1.3";
    private String trustStorePath;
    private String trustStorePassword;
    private String keyStorePath;
    private String keyStorePassword;
    private boolean validateServerCertificate = true;
    private boolean queueDurable = true;
    private boolean queueExclusive = false;
    private boolean queueAutoDelete = false;
    private Map<String, Object> queueArguments;
    private boolean exchangeDurable = true;
    private boolean exchangeAutoDelete = false;
    private Map<String, Object> exchangeArguments;

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

    /**
     * Sets the consumer name (queue name for receiving messages)
     * If not set, a random name will be generated
     */
    public Builder consumerName(String consumerName) {
      this.consumerName = consumerName;
      return this;
    }

    /**
     * Adds a direct channel (uses direct queue routing - no exchange)
     * This is compatible with the old RabbitMQ pattern where messages go directly to queues.
     *
     * <p>Example:
     * <pre>{@code
     * .addChannel("auth-server")           // Direct queue routing
     * }</pre>
     *
     * @param queueName the name of the queue to send messages to directly
     * @return this builder
     */
    public Builder addChannel(String queueName) {
      this.directChannels.add(queueName);
      return this;
    }

    /**
     * Adds an exchange-based channel (uses exchange routing for broadcast/fanout)
     * This creates an exchange and is useful for pub/sub patterns.
     *
     * <p>Example:
     * <pre>{@code
     * .addChannel("auth-server", "auth-server-exchange")
     * }</pre>
     *
     * @param channelName the logical channel name
     * @param exchangeName the RabbitMQ exchange name
     * @return this builder
     */
    public Builder addChannel(String channelName, String exchangeName) {
      this.channels.put(channelName, exchangeName);
      return this;
    }

    /**
     * Sets multiple exchange-based channels at once
     */
    public Builder channels(Map<String, String> channels) {
      this.channels.putAll(channels);
      return this;
    }

    /**
     * Sets whether to automatically subscribe to configured channels
     * Default is true
     */
    public Builder autoSubscribe(boolean autoSubscribe) {
      this.autoSubscribe = autoSubscribe;
      return this;
    }

    /**
     * Enables SSL/TLS encryption for the RabbitMQ connection
     * Default is false (unencrypted)
     */
    public Builder useSsl(boolean useSsl) {
      this.useSsl = useSsl;
      return this;
    }

    /**
     * Sets the SSL/TLS protocol version
     * Default is "TLSv1.3"
     * Common values: "TLSv1.2", "TLSv1.3", "TLS"
     */
    public Builder sslProtocol(String sslProtocol) {
      this.sslProtocol = sslProtocol;
      return this;
    }

    /**
     * Sets the path to the trust store file for SSL/TLS
     * Used to verify server certificates
     * Optional - if not set, system default trust store is used
     */
    public Builder trustStorePath(String trustStorePath) {
      this.trustStorePath = trustStorePath;
      return this;
    }

    /**
     * Sets the password for the trust store
     */
    public Builder trustStorePassword(String trustStorePassword) {
      this.trustStorePassword = trustStorePassword;
      return this;
    }

    /**
     * Sets the path to the key store file for client certificate authentication (mTLS)
     * Optional - only needed for mutual TLS authentication
     */
    public Builder keyStorePath(String keyStorePath) {
      this.keyStorePath = keyStorePath;
      return this;
    }

    /**
     * Sets the password for the key store
     */
    public Builder keyStorePassword(String keyStorePassword) {
      this.keyStorePassword = keyStorePassword;
      return this;
    }

    /**
     * Sets whether to validate server certificates
     * Default is true (recommended for production)
     * Set to false only for testing with self-signed certificates
     */
    public Builder validateServerCertificate(boolean validateServerCertificate) {
      this.validateServerCertificate = validateServerCertificate;
      return this;
    }

    /**
     * Sets whether queues should be durable (survive broker restart)
     * Default is true
     *
     * WARNING: Changing this from an existing queue's setting will cause
     * PRECONDITION_FAILED errors. Delete the old queue first or use a new name.
     */
    public Builder queueDurable(boolean queueDurable) {
      this.queueDurable = queueDurable;
      return this;
    }

    /**
     * Sets whether queues should be exclusive (used by only one connection)
     * Default is false
     *
     * WARNING: Changing this from an existing queue's setting will cause
     * PRECONDITION_FAILED errors. Delete the old queue first or use a new name.
     */
    public Builder queueExclusive(boolean queueExclusive) {
      this.queueExclusive = queueExclusive;
      return this;
    }

    /**
     * Sets whether queues should auto-delete when no longer in use
     * Default is false
     *
     * WARNING: Changing this from an existing queue's setting will cause
     * PRECONDITION_FAILED errors. Delete the old queue first or use a new name.
     */
    public Builder queueAutoDelete(boolean queueAutoDelete) {
      this.queueAutoDelete = queueAutoDelete;
      return this;
    }

    /**
     * Sets queue arguments (e.g., x-message-ttl, x-max-length)
     *
     * Common arguments:
     * - "x-message-ttl": Message time-to-live in milliseconds (Integer)
     * - "x-max-length": Maximum queue length (Integer)
     * - "x-max-length-bytes": Maximum queue size in bytes (Integer)
     * - "x-dead-letter-exchange": Dead letter exchange name (String)
     * - "x-max-priority": Maximum priority value (Integer, 1-255)
     *
     * Example:
     * <pre>
     * Map<String, Object> args = new HashMap<>();
     * args.put("x-message-ttl", 5000);  // 5 second TTL
     * builder.queueArguments(args);
     * </pre>
     *
     * WARNING: Changing arguments from an existing queue's settings will cause
     * PRECONDITION_FAILED errors. Delete the old queue first or use a new name.
     */
    public Builder queueArguments(Map<String, Object> queueArguments) {
      this.queueArguments = queueArguments != null ? new HashMap<>(queueArguments) : null;
      return this;
    }

    /**
     * Adds a single queue argument
     *
     * @param key argument key (e.g., "x-message-ttl")
     * @param value argument value (e.g., 5000)
     * @return this builder
     */
    public Builder addQueueArgument(String key, Object value) {
      if (this.queueArguments == null) {
        this.queueArguments = new HashMap<>();
      }
      this.queueArguments.put(key, value);
      return this;
    }

    /**
     * Sets whether exchanges should be durable (survive broker restart)
     * Default is true
     *
     * WARNING: Changing this from an existing exchange's setting will cause
     * PRECONDITION_FAILED errors. Delete the old exchange first or use a new name.
     */
    public Builder exchangeDurable(boolean exchangeDurable) {
      this.exchangeDurable = exchangeDurable;
      return this;
    }

    /**
     * Sets whether exchanges should auto-delete when no longer in use
     * Default is false
     *
     * WARNING: Changing this from an existing exchange's setting will cause
     * PRECONDITION_FAILED errors. Delete the old exchange first or use a new name.
     */
    public Builder exchangeAutoDelete(boolean exchangeAutoDelete) {
      this.exchangeAutoDelete = exchangeAutoDelete;
      return this;
    }

    /**
     * Sets exchange arguments (advanced configuration)
     *
     * Common arguments:
     * - "alternate-exchange": Name of alternate exchange (String)
     * - "x-delayed-type": For delayed message exchange plugin (String)
     *
     * Example:
     * <pre>
     * Map<String, Object> args = new HashMap<>();
     * args.put("alternate-exchange", "fallback-exchange");
     * builder.exchangeArguments(args);
     * </pre>
     *
     * WARNING: Changing arguments from an existing exchange's settings will cause
     * PRECONDITION_FAILED errors. Delete the old exchange first or use a new name.
     */
    public Builder exchangeArguments(Map<String, Object> exchangeArguments) {
      this.exchangeArguments = exchangeArguments != null ? new HashMap<>(exchangeArguments) : null;
      return this;
    }

    /**
     * Adds a single exchange argument
     *
     * @param key argument key (e.g., "alternate-exchange")
     * @param value argument value
     * @return this builder
     */
    public Builder addExchangeArgument(String key, Object value) {
      if (this.exchangeArguments == null) {
        this.exchangeArguments = new HashMap<>();
      }
      this.exchangeArguments.put(key, value);
      return this;
    }

    public RabbitMQConfig build() {
      // Validate required fields
      if (host == null || host.isEmpty()) {
        throw new IllegalStateException("Host must be configured");
      }
      if (port <= 0) {
        throw new IllegalStateException("Port must be configured");
      }
      if (username == null || username.isEmpty()) {
        throw new IllegalStateException("Username must be configured");
      }
      if (password == null) {
        throw new IllegalStateException("Password must be configured");
      }
      if (virtualHost == null || virtualHost.isEmpty()) {
        throw new IllegalStateException("Virtual host must be configured");
      }
      if (connectionTimeout <= 0) {
        throw new IllegalStateException("Connection timeout must be configured");
      }
      if (networkRecoveryInterval <= 0) {
        throw new IllegalStateException("Network recovery interval must be configured");
      }

      return new RabbitMQConfig(this);
    }
  }
}