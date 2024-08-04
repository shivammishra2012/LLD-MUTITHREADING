package com.flipkart.pubsub.util.strategy;

import com.flipkart.pubsub.models.Answer;

import java.util.List;

public interface AnswerSortingStrategy {
    List<Answer> sort(List<Answer> answers);
}