package com.flipkart.pubsub.exception;

public class GlobalExceptionHandler {
    public static void handleException(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        e.printStackTrace();  // For detailed stack trace
    }
}