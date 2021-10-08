/**
@Author <Shubham Chaudhary> <shubham.chaudhary@publicissapient.com>
*/

package com.sapient.asde.batch5.AddVehicle;

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

public class AddVehicleTestSteps {
    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "C:/Development/chromeDriver/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        options.addArguments("--window-size=1200x600");
        driver = new ChromeDriver(options);

    }

    @After
    public void tearDown() {
        // driver.quit();
    }

    

    @Given("I am on the add vehicle page")
    public void i_am_on_the_add_vehicle_page() {
        driver.get("http://localhost:3000/login");
    }
    
    @When("I enter all fields {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void i_enter_all_fields(String brand, String description, String color, String model, String tankCapacity, String mediaInterface,  String tripMeter, String price, String year) {
        driver.findElement(By.id("email")).sendKeys("dealer@domain.com");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div/div/form/div[3]/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        driver.get("http://localhost:3000/vehicles/add");
        driver.findElement(By.id("brand")).sendKeys(brand);
        driver.findElement(By.id("description")).sendKeys(description);
        driver.findElement(By.id("model")).sendKeys(model);
        driver.findElement(By.id("tankCapacity")).sendKeys(tankCapacity);
        driver.findElement(By.id("mediaInterface")).sendKeys(mediaInterface);
        driver.findElement(By.id("tripMeter")).sendKeys(tripMeter);
        driver.findElement(By.id("price")).sendKeys(price);
        driver.findElement(By.id("year")).sendKeys(year);
        driver.findElement(By.id("color")).sendKeys(color);
        
        driver.findElement(By.xpath("//*[@data-testid='Testinput-0']")).sendKeys("url");
        driver.findElement(By.xpath("//button[@data-testid='submitBtn']")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I will see an {string} success message")
    public void i_will_see_an_success_message(String expected) {
        String actual = driver.findElement(By.xpath("//button[@data-testid='successMessage']")).getText();
        assertEquals(expected, actual);
    }


}
