package com.flipkart.pubsub.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String profession;
    private List<Topic> subscribedTopics;
    private List<Question> askedQuestions;
    private List<Question> answeredQuestions;

    public User(String name, String profession) {
        this.name = name;
        this.profession = profession;
        this.subscribedTopics = new ArrayList<>();
        this.askedQuestions = new ArrayList<>();
        this.answeredQuestions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void subscribeTopic(Topic topic) {
        if (!subscribedTopics.contains(topic)) {
            subscribedTopics.add(topic);
        }
    }

    public void unsubscribeTopic(Topic topic) {
        subscribedTopics.remove(topic);
    }

    public List<Topic> getSubscribedTopics() {
        return subscribedTopics;
    }

    public void askQuestion(Question question) {
        askedQuestions.add(question);
    }

    public void answerQuestion(Question question, String answer) {
        question.addAnswer(new Answer(this, answer));
        answeredQuestions.add(question);
    }

    public void upvote(Question question) {
        if (subscribedTopics.stream().anyMatch(topic -> question.getTopics().contains(topic))) {
            question.upvote();
        }
    }

    public void upvote(Answer answer) {
        if (subscribedTopics.stream().anyMatch(topic -> answer.getQuestion().getTopics().contains(topic))) {
            answer.upvote();
        }
    }

    public List<Question> getAskedQuestions() {
        return askedQuestions;
    }

    public List<Question> getAnsweredQuestions() {
        return answeredQuestions;
    }
}