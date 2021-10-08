package com.sapient.restendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generate-report")
public class GenerateReportController {

    @Autowired
    GenerateReportService service;

    @PostMapping
    public String generateReport(@RequestBody String input) {
        service.triggerReportGeneration(input);
        return "Your report will be ready in sometime, and we will notify you via email/sms";
    }
}
