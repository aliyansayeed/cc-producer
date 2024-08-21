package io.materialplus.producer.controller;
import io.materialplus.producer.records.ProducerCreditCardTransactionRecord;
import io.materialplus.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private ProducerService service;

   /* @PostMapping("/publish-cc-transaction")
    *//*public ResponseEntity<Void> publishCreditCardTransaction() {
        service.publishCreditCardTransactionEvents();
        return ResponseEntity.ok().build();
    }*//*
    public String publishCreditCardTransaction() {
        service.publishCreditCardTransactionEvents();
        return "request pushed successfully";
    }*/

    @GetMapping("/get-cc-transaction")
    public ResponseEntity<List<ProducerCreditCardTransactionRecord>> getCreditCardTransactionMessages() {
        List<ProducerCreditCardTransactionRecord> messages = service.getPublishedMessages();
        return ResponseEntity.ok(messages);
    }


}
