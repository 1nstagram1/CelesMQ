package io.hydrodevelopments.celesmq.message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * Fluent API builder for publishing messages to RabbitMQ channels.
 */
public class MessagePublisher {

    private final BiFunction<String, Map<String, Object>, CompletableFuture<Boolean>> sendFunction;
    private Map<String, Object> data;

    /**
     * Creates a new MessagePublisher with the specified send function.
     *
     * @param sendFunction A function that takes a channel name and message data,
     *                     and returns a CompletableFuture indicating success/failure
     */
    public MessagePublisher(BiFunction<String, Map<String, Object>, CompletableFuture<Boolean>> sendFunction) {
        this.sendFunction = sendFunction;
        this.data = new HashMap<>();
    }

    /**
     * Sets the data to be sent in the message.
     *
     * @param data The message data as a map
     * @return This builder for method chaining
     */
    public MessagePublisher data(Map<String, Object> data) {
        this.data = data != null ? new HashMap<>(data) : new HashMap<>();
        return this;
    }

    /**
     * Adds a single key-value pair to the message data.
     *
     * @param key   The key
     * @param value The value
     * @return This builder for method chaining
     */
    public MessagePublisher add(String key, Object value) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.put(key, value);
        return this;
    }

    /**
     * Sends the message to the specified channel.
     *
     * @param channel The channel to send the message to
     * @return A CompletableFuture that completes with true if the message was sent successfully
     */
    public CompletableFuture<Boolean> sendTo(String channel) {
        if (data == null || data.isEmpty()) {
            CompletableFuture<Boolean> future = new CompletableFuture<>();
            future.completeExceptionally(new IllegalStateException("No data provided. Use data() or add() before sendTo()"));
            return future;
        }

        return sendFunction.apply(channel, data);
    }
}