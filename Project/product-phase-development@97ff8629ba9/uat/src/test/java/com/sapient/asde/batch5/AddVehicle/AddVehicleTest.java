/**
@Author <Shubham Chaudhary> <shubham.chaudhary@publicissapient.com>
*/

package com.sapient.asde.batch5.AddVehicle;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/addVehicle.feature" }, glue = {
                "com.sapient.asde.batch5.AddVehicle" }, plugin = { "pretty", "json:target/reports/addVehicle.json" })
public class AddVehicleTest {

}
