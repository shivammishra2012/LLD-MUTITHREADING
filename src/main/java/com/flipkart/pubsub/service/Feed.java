package com.flipkart.pubsub.service;

import com.flipkart.pubsub.util.strategy.AnswerSortingStrategy;
import com.flipkart.pubsub.models.Answer;
import com.flipkart.pubsub.models.Question;
import com.flipkart.pubsub.models.Topic;

import java.util.List;

public interface Feed {
    void addQuestion(Question question);
    List<Question> getFeed(List<Topic> filterTopics, Boolean answered);
    void setSortingStrategy(AnswerSortingStrategy sortingStrategy);
    List<Answer> getSortedAnswers(Question question);
}