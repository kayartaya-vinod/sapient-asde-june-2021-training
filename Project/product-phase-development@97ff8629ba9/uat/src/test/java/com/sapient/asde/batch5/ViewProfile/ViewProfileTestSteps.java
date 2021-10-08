/**
 @Author <Hrishant> <hrishant.raj@publicissapient.com>
 */

package com.sapient.asde.batch5.ViewProfile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewProfileTestSteps {
    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "D:/Users/hriraj1/Documents/ChromeDriver/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1300x700");
        driver = new ChromeDriver(options);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I have a view profile button")
    public void i_have_a_view_profile_button() {
        driver.get("http://localhost:3000");
        driver.findElement(By.cssSelector("#navbarColor01 > ul > li > span")).click();
    }

    @When("I click on the view profile button")
    public void i_click_on_the_view_profile_button() {

        driver.findElement(By.cssSelector("#navbarColor01 > ul > li > ul > div:nth-child(1) > li > a")).click();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

    }

    @Then("I should see Email field on customer profile")
    public void i_should_see_email_field_on_customer_profile() {
        String actual = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/table/tbody/tr[1]/td[1]")).getText();
        assertEquals("Email", actual);
    }

    @Then("I should see Alternate Email field on customer profile")
    public void i_should_see_alternate_email_field_on_customer_profile() {
        String actual = driver.findElement(By.cssSelector(
                "#root > div > div:nth-child(2) > div > div:nth-child(3) > div > table > tbody > tr:nth-child(2) > td:nth-child(1)"))
                .getText();
        assertEquals("Alternate Email", actual);
    }

    @Then("I should see Default Address field on customer profile")
    public void i_should_see_default_address_field_on_customer_profile() {
        String actual = driver.findElement(By.cssSelector(
                "#root > div > div:nth-child(2) > div > div:nth-child(3) > div > table > tbody > tr:nth-child(3) > td:nth-child(1)"))
                .getText();
        assertEquals("Default Address", actual);
    }

    @Then("I should see Alternate Address field on customer profile")
    public void i_should_see_alternate_address_field_on_customer_profile() {
        String actual = driver.findElement(By.cssSelector(
                "#root > div > div:nth-child(2) > div > div:nth-child(3) > div > table > tbody > tr:nth-child(4) > td:nth-child(1)"))
                .getText();
        assertEquals("Alternate Address", actual);
    }

    @Then("I should see Contact field on customer profile")
    public void i_should_see_contact_field_on_customer_profile() {
        String actual = driver.findElement(By.cssSelector(
                "#root > div > div:nth-child(2) > div > div:nth-child(3) > div > table > tbody > tr:nth-child(5) > td:nth-child(1)"))
                .getText();
        assertEquals("Contact", actual);
    }

    @Then("I should see Alternate Contact field on customer profile")
    public void i_should_see_alternate_contact_field_on_customer_profile() {
        String actual = driver.findElement(By.cssSelector(
                "#root > div > div:nth-child(2) > div > div:nth-child(3) > div > table > tbody > tr:nth-child(6) > td:nth-child(1)"))
                .getText();
        assertEquals("Alternate Contact", actual);
    }

    @Then("I should see Edit Profile field on customer profile")
    public void i_should_see_edit_profile_field_on_customer_profile() {
        String actual = driver
                .findElement(
                        By.cssSelector("#root > div > div:nth-child(2) > div > div:nth-child(4) > div > a > button"))
                .getText();
        assertEquals("Edit Profile", actual);
    }

}