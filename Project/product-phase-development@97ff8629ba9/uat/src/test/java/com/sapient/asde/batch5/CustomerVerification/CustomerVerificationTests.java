 /**
# @Author Rohit Bhatt rohit.bhatt1@publicissapient.com
# */

package com.sapient.asde.batch5.CustomerVerification;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/customerVerification.feature" }, glue = { "com.sapient.asde.batch5.CustomerVerification" }, plugin = { "pretty",
        "json:target/reports/customerVerification.json" })
public class CustomerVerificationTests {
    /**
     * Rigorous Test :-)
     */

}