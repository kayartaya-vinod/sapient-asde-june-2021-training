package com.sapient.asde.batch5.EditAccessory;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features/editAccessory.feature"}, glue = {"com.sapient.asde.batch5.EditAccessory" }, plugin = { "pretty", "json:target/reports/editAccessory.json" })
public class EditAccessoryTest {
  
}
