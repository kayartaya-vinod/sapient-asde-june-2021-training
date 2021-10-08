package com.sapient;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/factorial.feature" }, glue = { "com.sapient" }, plugin = { "pretty",
        "json:target/reports/factorial-generator.json" })
public class FactorialGeneratorTest {

}
