package com.sapient.restendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GenerateReportService {

    @Autowired
    KafkaTemplate<String, String> template;

    public void triggerReportGeneration(String input){
        template.send("gen-report", input);
    }
}
