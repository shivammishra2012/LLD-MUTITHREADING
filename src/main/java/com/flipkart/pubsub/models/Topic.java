package com.flipkart.pubsub.models;

public class Topic {
    private String name;

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Topic topic = (Topic) obj;
        return name.equals(topic.name);
    }

    public int hashCode() {
        return name.hashCode();
    }
}
