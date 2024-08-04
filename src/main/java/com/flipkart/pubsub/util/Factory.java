package com.flipkart.pubsub.util;

import
        com.flipkart.pubsub.models.Answer;
import com.flipkart.pubsub.models.Question;
import com.flipkart.pubsub.models.Topic;
import com.flipkart.pubsub.models.User;

import java.util.List;

public class Factory {
    public static User createUser(String name, String profession) {
        return new User(name, profession);
    }

    public static Topic createTopic(String name) {
        return new Topic(name);
    }

    public static Question createQuestion(String content, User user, List<Topic> topics) {
        return new Question(content, user, topics);
    }

    public static Answer createAnswer(User user, String content) {
        return new Answer(user, content);
    }



}