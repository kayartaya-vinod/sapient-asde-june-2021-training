package com.sapient.reportingservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GenerateReportService {

    @KafkaListener(topics = { "gen-report" }, groupId = "report-group")
    public void startGeneratingReport(String message) {
        // prcess the incoming message (currently String, but could of any custom types)
        log.info("Got this message - {}", message);
        // publish messages to "send-email" topic (for example)
    }
}
