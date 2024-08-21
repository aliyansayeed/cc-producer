package io.materialplus.producer.util;

public enum ProducerTopics {

    CREDIT_CARD_TRANSACTION_TOPIC("prod-msg");


    private final String topicName;

    ProducerTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

}
