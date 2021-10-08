package com.sapient.asde.batch5.dealervehicles;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/dealerVehicles.feature" }, glue = {
    "com.sapient.asde.batch5.dealervehicles" }, plugin = { "pretty", "json:target/reports/dealervehicles.json" })
public class DealerVehiclesTest {

}