package io.materialplus.producer.records;

import io.materialplus.producer.exception.ProducerInvalidAmountException;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

import static io.materialplus.producer.util.ProducerAppUtils.amountValidation;

@Slf4j
public record ProducerCreditCardTransactionRecord(
        int transactionId,
        String cardNumber,
        double amount,
        LocalDateTime timestamp,
        String merchantId
) implements Serializable {

    public ProducerCreditCardTransactionRecord {
        if (!amountValidation(amount)) {
            log.error("Invalid amount {} for transaction {}", amount, transactionId);
            throw new ProducerInvalidAmountException("Amount cannot be negative: " + amount);
        }
    }
}
