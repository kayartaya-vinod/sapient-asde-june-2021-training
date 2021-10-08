package com.sapient.asde.batch5.VehiclesGrid;

/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/vehiclesgrid.feature" }, glue = {
        "com.sapient.asde.batch5.VehiclesGrid" }, plugin = { "pretty", "json:target/reports/vehiclesgrid.json" })
public class VehiclesGridTest {
    /**
     * Rigorous Test :-)
     */

}