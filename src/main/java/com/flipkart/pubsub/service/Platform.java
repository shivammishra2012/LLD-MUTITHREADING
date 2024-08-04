package com.flipkart.pubsub.service;

import com.flipkart.pubsub.exception.GlobalExceptionHandler;
import com.flipkart.pubsub.models.Topic;
import com.flipkart.pubsub.util.Factory;
import com.flipkart.pubsub.models.Answer;
import com.flipkart.pubsub.models.Question;
import com.flipkart.pubsub.models.User;
import com.flipkart.pubsub.util.validator.TopicValidator;
import com.flipkart.pubsub.util.validator.UserValidator;

import java.util.List;
import java.util.ArrayList;

public class Platform {
    private User loggedInUser;
    private Feed feed;

    public Platform() {
        this.feed = new FeedImpl();
    }

    public void userSignup(String name, String profession) {
        if (loggedInUser != null) {
            throw new IllegalStateException("A user is already logged in. Please logout first.");
        }
        UserValidator.validate(name, profession);
        loggedInUser = Factory.createUser(name, profession);
        System.out.println("User signed up: " + name + ", " + profession);
    }

    public void subscribe(String... topics) {
        if (loggedInUser == null) {
            throw new IllegalStateException("No user is logged in.");
        }
        if (topics == null) {
            throw new RuntimeException("Please enter valid topic name");
        }
        TopicValidator.validate(topics);
        for (String topicName : topics) {
            Topic topic = Factory.createTopic(topicName);
            loggedInUser.subscribeTopic(topic);
        }
        System.out.println("Subscribed to topics: " + String.join(", ", topics));
    }

    public void unsubscribe(String... topics) {
        if (loggedInUser == null) {
            throw new IllegalStateException("No user is logged in.");
        }
        TopicValidator.validate(topics);
        for (String topicName : topics) {
            Topic topic = Factory.createTopic(topicName);
            loggedInUser.unsubscribeTopic(topic);
        }
        System.out.println("Unsubscribed from topics: " + String.join(", ", topics));
    }

    public void addQuestion(String content, String[] topicNames) {
        try {
            if (loggedInUser == null) {
                throw new IllegalStateException("No user is logged in.");
            }
            if (topicNames == null) {
                throw new RuntimeException("Please enter valid topic name");
            }
            List<Topic> topics = new ArrayList<>();
            for (String topicName : topicNames) {
                topics.add(Factory.createTopic(topicName));
            }
            Question question = Factory.createQuestion(content, loggedInUser, topics);
            feed.addQuestion(question);
            loggedInUser.askQuestion(question);
            System.out.println("Question added: " + content);
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void showFeed(String[] filterTopics, Boolean answered) {
        try {
            List<Topic> topics = new ArrayList<>();
            if (filterTopics != null) {
                for (String topicName : filterTopics) {
                    topics.add(Factory.createTopic(topicName));
                }
            }
            if (topics.isEmpty()) return;
            List<Question> filteredFeed = feed.getFeed(topics, answered);
            for (Question q : filteredFeed) {
                System.out.println("Question: " + q.getContent() + " by " + q.getUser().getName() + " on " + q.getTimestamp());
                List<Answer> sortedAnswers = feed.getSortedAnswers(q);
                for (Answer a : sortedAnswers) {
                    System.out.println("Answer: " + a.getContent() + " by " + a.getUser().getName() + " Votes: " + a.getVotes());
                }
            }
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void logout() {
        if (loggedInUser == null) {
            throw new IllegalStateException("No user is logged in.");
        }
        System.out.println("User logged out: " + loggedInUser.getName());
        loggedInUser = null;
    }
}