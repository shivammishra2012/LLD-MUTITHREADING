package com.flipkart.pubsub.service;

import com.flipkart.pubsub.models.Answer;
import com.flipkart.pubsub.models.Question;
import com.flipkart.pubsub.models.Topic;
import com.flipkart.pubsub.util.strategy.AnswerSortingStrategy;
import com.flipkart.pubsub.util.strategy.VoteBasedSortingStrategy;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FeedImpl implements Feed {
    private List<Question> questions;
    private AnswerSortingStrategy sortingStrategy;

    public FeedImpl() {
        this.questions = new ArrayList<>();
        this.sortingStrategy = new VoteBasedSortingStrategy(); // default strategy
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public List<Question> getFeed(List<Topic> filterTopics, Boolean answered) {
        return questions.stream()
                .filter(q -> filterTopics == null || filterTopics.isEmpty() || q.getTopics().stream().anyMatch(filterTopics::contains))
                .filter(q -> answered == null || q.isAnswered() == answered)
                .collect(Collectors.toList());
    }

    @Override
    public void setSortingStrategy(AnswerSortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    @Override
    public List<Answer> getSortedAnswers(Question question) {
        return sortingStrategy.sort(question.getAnswers());
    }
}