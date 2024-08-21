package io.materialplus.producer.util;

import org.springframework.stereotype.Component;
import io.materialplus.producer.records.ProducerCreditCardTransactionRecord;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class ProducerRecordGenerator {

     // Random  Record Generator for depecting Credit Card Transactions //

    private final Random random = new Random();

    public ProducerCreditCardTransactionRecord generateRandomTransaction(int transactionId) {
        String cardNumber = generateRandomCardNumber();
        double amount = generateRandomAmount();
        LocalDateTime timestamp = LocalDateTime.now();
        String merchantId = "merchant" + random.nextInt(1000);

        return new ProducerCreditCardTransactionRecord(transactionId, cardNumber, amount, timestamp, merchantId);
    }

    private String generateRandomCardNumber() {
        // Generate a random 16-digit credit card number
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    private double generateRandomAmount() {
        // Generate a random transaction amount between 1.00 and 1000.00
        return 1 + (999 * random.nextDouble());
    }
}
