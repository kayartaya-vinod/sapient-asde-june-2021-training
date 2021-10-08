/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
package com.sapient.asde.batch5.VehicleFeedbacks;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/vehicleFeedbacks.feature" }, glue = { "com.sapient.asde.batch5.VehicleFeedbacks" }, plugin = { "pretty",
        "json:target/reports/vehicleFeedbacks.json" })
public class VehicleFeedbacksTest {
    /**
     * Rigorous Test :-)
     */

}