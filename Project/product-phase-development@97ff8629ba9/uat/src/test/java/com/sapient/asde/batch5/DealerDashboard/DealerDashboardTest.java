package com.sapient.asde.batch5.DealerDashboard;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/DealerDashboard.feature" }, glue = {
        "com.sapient.asde.batch5.DealerDashboard" }, plugin = { "pretty",
                "json:target/reports/DealerDashboard.json" })
public class DealerDashboardTest {
    
}
