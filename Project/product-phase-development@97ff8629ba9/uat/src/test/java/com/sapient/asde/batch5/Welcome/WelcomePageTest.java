package com.sapient.asde.batch5.Welcome;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


/*
 * @Author Shubham Chaudhari shubham.chaudhari@publicissapient.com
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/welcome.feature" }, glue = { "com.sapient" }, plugin = { "pretty",
        "json:target/reports/welcome.json" })
public class WelcomePageTest {
    
}
