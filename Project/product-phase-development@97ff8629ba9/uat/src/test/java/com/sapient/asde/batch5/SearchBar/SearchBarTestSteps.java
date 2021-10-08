/**
 @Author <Hrishant> <hrishant.raj@publicissapient.com>
 */

package com.sapient.asde.batch5.SearchBar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchBarTestSteps {
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

    @Given("I have a searchbar")
    public void i_have_a_searchbar() {
        driver.get("http://localhost:3000");
    }

    @When("I enter a string present in the database Honda and press enter")
    public void i_enter_a_string_present_in_the_database_honda_and_press_enter() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/form/input")).sendKeys("Honda");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/form/input")).sendKeys(Keys.RETURN);
    }
    @Then("I should get Your searched vehicles search result")
    public void i_should_get_your_searched_vehicles_search_result() {
        String actual = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/h1"))
                .getText();
        assertEquals("Your searched vehicles", actual);
    }
    
    @When("I enter a string not present in the database asd and press enter")
    public void i_enter_a_string_not_present_in_the_database_asd_and_press_enter() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/form/input")).sendKeys("asd");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/form/input")).sendKeys(Keys.RETURN);
    }
    @Then("I should get No Vehicles found!!! search result")
    public void i_should_get_no_vehicles_found_search_result() {
        String actual = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/h1"))
                .getText();
        assertEquals("No Vehicles found!!!", actual);
    }
}