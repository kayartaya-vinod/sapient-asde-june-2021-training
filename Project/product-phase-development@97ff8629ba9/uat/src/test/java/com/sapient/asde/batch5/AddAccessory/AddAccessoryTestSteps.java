package com.sapient.asde.batch5.AddAccessory;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddAccessoryTestSteps {
    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "C:/Devtools/chromedriver_win32/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1200x600");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // driver.quit();
    }

    @Given("I am on add accessory page")
    public void i_am_on_add_accessory_page() {
        driver.get("http://localhost:3000/login");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
                
        driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/div/div/form/div[1]/input"))
                .sendKeys("dealer@domain.com");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/div/div/form/div[2]/input")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/div/div/form/div[3]/button")).click();
        driver.get("http://localhost:3000/dealer/add-accessory");
    }

    @When("I add details of the vehicle accesory")
    public void i_add_details_of_the_vehicle_accessory() {
    }

    @Then("I should see updated details of the vehicle accessory")
    public void i_should_see_updated_details_of_the_accessory() {
        String actualName = driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/form/div[1]/div[1]/label"))
                .getText();
        assertEquals("Name:", actualName);
    }
}
