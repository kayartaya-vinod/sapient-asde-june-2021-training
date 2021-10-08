
package com.sapient.asde.batch5.DealerUploads;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/dealerUploads.feature" }, glue = {
        "com.sapient.asde.batch5.DealerUploads" }, plugin = { "pretty", "json:target/reports/DealerUploads.json" })
public class DealerUploadsTest {

}
