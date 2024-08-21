package io.materialplus.producer.config;

import io.materialplus.producer.records.ProducerCreditCardTransactionRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerKafkaConfiguration {

    @Bean
    public ProducerFactory<String, ProducerCreditCardTransactionRecord> producerFactory() {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
       /* props.put("security.protocol", "SSL");
        props.put("ssl.truststore.location", "C:\\softwares\\kafka\\ssl2\\kafka.server.truststore.jks");
        props.put("ssl.truststore.password", "123456");
        props.put("ssl.keystore.location", "C:\\softwares\\kafka\\ssl2\\kafka.server.keystore.jks");
        props.put("ssl.keystore.password", "123456");
        props.put("ssl.key.password", "123456");*/

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, ProducerCreditCardTransactionRecord> kafkaTemplate()
    {
        return new KafkaTemplate<>(producerFactory());
    }
}