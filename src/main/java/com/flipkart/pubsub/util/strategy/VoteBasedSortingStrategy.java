package com.flipkart.pubsub.util.strategy;

import com.flipkart.pubsub.models.Answer;

import java.util.List;

public class VoteBasedSortingStrategy implements AnswerSortingStrategy {
    @Override
    public List<Answer> sort(List<Answer> answers) {
        answers.sort((a1, a2) -> Integer.compare(a2.getVotes(), a1.getVotes()));
        return answers;
    }
}