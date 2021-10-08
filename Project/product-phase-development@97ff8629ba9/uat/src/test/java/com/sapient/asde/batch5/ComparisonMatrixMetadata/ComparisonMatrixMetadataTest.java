package com.sapient.asde.batch5.ComparisonMatrixMetadata;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/comparisonMatrixMetadata.feature" }, glue = {
        "com.sapient.asde.batch5.ComparisonMatrixMetadata" }, plugin = { "pretty", "json:target/reports/ComparisonMatrixMetadata.json" })
public class ComparisonMatrixMetadataTest {
    
}
