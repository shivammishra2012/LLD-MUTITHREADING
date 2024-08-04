package com.flipkart.pubsub.util.validator;

public class UserValidator {
    public static void validate(String name, String profession) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Please enter valid name");
        }
        if (profession == null || profession.isEmpty()) {
            throw new RuntimeException("Please enter valid profession");
        }
    }
}
