package com.flipkart.pubsub.models;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Question {
    private String content;
    private User user;
    private List<Topic> topics;
    private LocalDateTime timestamp;
    private List<Answer> answers;
    private int votes;

    public Question(String content, User user, List<Topic> topics) {
        this.content = content;
        this.user = user;
        this.topics = topics;
        this.timestamp = LocalDateTime.now();
        this.answers = new ArrayList<>();
        this.votes = 0;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void upvote() {
        votes++;
    }

    public int getVotes() {
        return votes;
    }

    public boolean isAnswered() {
        return !answers.isEmpty();
    }
}