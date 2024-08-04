package com.flipkart.pubsub.models;

public class Answer {
    private User user;
    private String content;
    private Question question;
    private int votes;

    public Answer(User user, String content) {
        this.user = user;
        this.content = content;
        this.votes = 0;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void upvote() {
        votes++;
    }

    public int getVotes() {
        return votes;
    }
}