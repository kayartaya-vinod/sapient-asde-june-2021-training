package com.sapient.asde.batch5.AccessorySearch;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/accessorySearch.feature" }, glue = {
        "com.sapient.asde.batch5.AccessorySearch" }, plugin = { "pretty", "json:target/reports/AccessorySearch.json" })
public class AccessorySearchTest {
    
}
