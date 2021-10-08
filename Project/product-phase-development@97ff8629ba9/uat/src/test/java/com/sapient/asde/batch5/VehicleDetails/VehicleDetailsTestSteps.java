package com.sapient.asde.batch5.VehicleDetails;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VehicleDetailsTestSteps {

    WebDriver driver;

    List<WebElement> listBefore;
    WebElement checkbox;
    String link;
    String baseURL;

    @Before
    public void setup() {
        String driverLocation = "D:/Selenium Webdriver";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();

        baseURL = "http://localhost:3000";
        driver = new ChromeDriver(options);

    }

    public void tearDown() {
        // driver.quit();
    }

    @Given("I am on home page")
    public void i_am_on_home_page() {
        driver.get("http://localhost:3000");
    }

    @When("I click on vehicle card")
    public void i_click_on_vehicle_card() {
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div/div[1]/div[2]/img")).click();
        driver.get("http://localhost:3000/vehicles/vehicle123");
    }

    @Then("I see vehicle details page")
    public void i_see_vehicle_details_page(){
    
       String actualModel= driver.findElement("/html/body/div[1]/div/div[1]/div[2]/div[4]/div/div[2]/table/tbody/tr[1]/td[2]");
        assertEquals("R200", actualModel);
        String actualAtrr = driver.findElement("/html/body/div[1]/div/div[1]/div[2]/div[3]/div[2]/div/div/div[4]/div/table/tbody/tr[1]/td[1]");
        assertEquals("Model", actualAtrr);
        driver.findElement("/html/body/div[1]/div/div[1]/div[2]/div[4]/div/div[2]/h3");
        driver.findElement ("/html/body/div[1]/div/div[1]/div[2]/div[5]/div/div[2]");
        

    }
}
