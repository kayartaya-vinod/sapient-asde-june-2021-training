package com.sapient.asde.batch5.EditAccessory;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditAccessoryTestSteps {
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

    @Given("I am on the edit accessory page")
    public void i_am_on_edit_accessory_page(){
      driver.get("http://localhost:3000/login");
    }

    @When("I enter all fields")
    public void i_enter_all_fields(){
      driver.findElement(By.id("email")).sendKeys("dealer@domain.com");
        driver.findElement(By.id("password")).sendKeys("password");
        WebElement login = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/div/div/form/div[3]/button"));
        login.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        
        driver.get("http://localhost:3000/vehicle-accessory/edit/1");        
        driver.findElement(By.xpath("//*[@data-testid='input-price']")).sendKeys("10000");
        
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0, 100000)");
        
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div/div[2]/button")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    @Then("I will see an success message")
    public void i_will_see_an_success_message() {
        String actual = driver.findElement(By.xpath("//button[@data-testid='message']")).getText();
        assertEquals("Success! redirecting...", actual);
    }
}
