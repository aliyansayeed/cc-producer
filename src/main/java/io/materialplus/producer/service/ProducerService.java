package io.materialplus.producer.service;

import io.materialplus.producer.records.ProducerCreditCardTransactionRecord;
import io.materialplus.producer.util.ProducerRecordGenerator;
import io.materialplus.producer.util.ProducerTopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ProducerService {
    private static final Logger log = LoggerFactory.getLogger(ProducerService.class);
    private final KafkaTemplate<String, ProducerCreditCardTransactionRecord> transactionDataKafkaTemplate;
    private final ProducerRecordGenerator recordGenerator;
    private final List<ProducerCreditCardTransactionRecord> producerCreditCardTransactionRecordList;

    public ProducerService(KafkaTemplate<String, ProducerCreditCardTransactionRecord> transactionDataKafkaTemplate,
                           ProducerRecordGenerator recordGenerator) {
        this.transactionDataKafkaTemplate = transactionDataKafkaTemplate;
        this.recordGenerator = recordGenerator;
        this.producerCreditCardTransactionRecordList = new CopyOnWriteArrayList<>();
    }

    @Scheduled(fixedRate = 5000)  // Publish every 5 second
    public void publishCreditCardTransactionEvents() {
       // for (int i = 0; i < 20000; i++) {
           // Thread.sleep();
         //   log.info("Publishing credit card transaction events - from sender {}");
            int anyRandomNumber = new Random().nextInt();
            // FIXME which id should be sent //
            ProducerCreditCardTransactionRecord transactionDetails = recordGenerator.generateRandomTransaction(anyRandomNumber);
            transactionDataKafkaTemplate.send(ProducerTopics.CREDIT_CARD_TRANSACTION_TOPIC.getTopicName(), transactionDetails);
            log.info("Published: id={}, amount={}, cardNumber={}",
           // log.info(" ZUBEDI ### ", + i);
                    transactionDetails.transactionId(),
                    transactionDetails.amount(),
                    transactionDetails.cardNumber());
            producerCreditCardTransactionRecordList.add(transactionDetails);
      //  }
    }

    public List<ProducerCreditCardTransactionRecord> getPublishedMessages() {
        return producerCreditCardTransactionRecordList;
    }
}