/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
package com.sapient.asde.batch5.UploadJson;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/uploadJson.feature" }, glue = {
        "com.sapient.asde.batch5.UploadJson" }, plugin = { "pretty", "json:target/reports/uploadJson.json" })
public class UploadJsonTest {
    /**
     * Rigorous Test :-)
     */

}