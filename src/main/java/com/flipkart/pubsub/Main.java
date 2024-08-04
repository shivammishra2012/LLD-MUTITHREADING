package com.flipkart.pubsub;

import com.flipkart.pubsub.exception.GlobalExceptionHandler;
import com.flipkart.pubsub.service.Platform;

public class Main {
    public static void main(String[] args) {
        try {
            Platform platform = new Platform();

            // Test case 1
            platform.userSignup("Sachin", "Developer");

            // Test case 2
            platform.subscribe("java", "hadoop", "jdk");

            // Test case 3
            platform.addQuestion("What are new open source jdks?", new String[]{"java", "jdk"});

            // Test case 4
            platform.addQuestion("Does Hadoop work on JDK 11?", new String[]{"hadoop", "jdk"});

            // Test case 5
            platform.showFeed(null, null);

            // Test case 6
            platform.showFeed(new String[]{"java"}, null);

            // Test case 7
            platform.showFeed(new String[]{"jdk"}, null);

            // Test case 8
            platform.showFeed(null, true);

            // Test case 9
            platform.logout();
        }
        catch (Exception ex) {
            GlobalExceptionHandler.handleException(ex);
        }
    }
}