/**
# @Author Rohit Bhatt rohit.bhatt1@publicissapient.com
# */

package com.sapient.asde.batch5.CustomerVerification;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerVerificationTestSteps {
    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "D:/Users/rohbhatt5/Desktop/chromedriver_win32/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("start-maximized");
        // options.addArguments("--headless");
        options.addArguments("--window-size=1440x900");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        // driver.quit();
    }

    @Given("I got a valid token from email")
    public void iGotAValidToken() {
        driver.get("http://localhost:3000/verify-account/token");
    }

    @When("I process the token")
    public void iProcessToken() {

    }

    @Then("I should see a success message")
    public void iShouldSeeSuccessMessage() {
        String actual = driver.findElement(By.xpath("/html/body/div/div/div[3]/h3")).getText();
        String expected = "Congratulations Jon Snow, your account has been verified! Login to your account";
        assertEquals(expected, actual);
    }
}