package com.sapient.asde.batch5.VehicleDetails;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/vehicleDetails.feature" }, glue = {
        "com.sapient.asde.batch5.VehicleDetails" }, plugin = { "pretty", "json:target/reports/VehicleDetails.json" })
public class VehicleDetailsTest {

}







