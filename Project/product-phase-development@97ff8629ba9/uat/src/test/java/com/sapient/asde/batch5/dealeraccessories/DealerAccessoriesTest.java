package com.sapient.asde.batch5.dealeraccessories;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/dealerAccessories.feature" }, glue = {
        "com.sapient.asde.batch5.dealeraccessories" }, plugin = { "pretty",
                "json:target/reports/dealeraccessories.json" })
public class DealerAccessoriesTest {

}
