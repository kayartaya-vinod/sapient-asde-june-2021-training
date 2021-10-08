package com.sapient.asde.batch5.DealerFeedback;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/dealerFeedbacks.feature" }, glue = {
        "com.sapient.asde.batch5.DealerFeedback" }, plugin = { "pretty",
                "json:target/reports/dealerFeedbacks.json" })
public class DealerFeedbackTest {
    
}
