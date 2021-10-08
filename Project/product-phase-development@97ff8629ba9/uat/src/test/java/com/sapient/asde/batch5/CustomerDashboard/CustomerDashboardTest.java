package com.sapient.asde.batch5.CustomerDashboard;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/CustomerDashboard.feature" }, glue = {
        "com.sapient.asde.batch5.CustomerDashboard" }, plugin = { "pretty",
                "json:target/reports/CustomerDashboard.json" })
public class CustomerDashboardTest {
    
}
