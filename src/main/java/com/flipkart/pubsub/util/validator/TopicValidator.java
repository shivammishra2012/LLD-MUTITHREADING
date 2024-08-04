package com.flipkart.pubsub.util.validator;

public class TopicValidator {
    public static void validate(String... topics) {
        for (String topic:topics) {
            if (topic == null || topic.isEmpty()) {
                throw new IllegalStateException("Please enter valid topic name");
            }
        }
    }
}
