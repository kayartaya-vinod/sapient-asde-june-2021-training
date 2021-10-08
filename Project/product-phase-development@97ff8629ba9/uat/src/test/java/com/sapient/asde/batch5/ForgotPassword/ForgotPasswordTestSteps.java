 /**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com
# */
package com.sapient.asde.batch5.ForgotPassword;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ForgotPasswordTestSteps {
    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "C:/Users/yogamber singh negi/Downloads/Compressed/chromedriver_win32_/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("start-maximized");
        // options.addArguments("--headless");
        options.addArguments("--window-size=1200x600");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Given("I am on Login Page")
    public void i_am_on_login_page() {
        driver.get("http://localhost:3000/login");
       

    }

    @When("I click on Forgot Password")
    public void i_click_on_forgot_password() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        driver.findElement(By.xpath("//*[@data-testid='forget-password-link']")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @Then("I should see Forgot Password Page")
    public void i_should_see_forgot_password_page() {
        String actual = driver.findElement(By.xpath("//*[@data-testid=\"submitbtn\"]")).getText();
        assertEquals("Submit", actual);
    }

    @Given("I am on Forgot Password Page")
    public void i_am_on_forgot_password_page() {
        driver.get("http://localhost:3000/reset-password-email");
    }
   
    @When("I enter empty email address")
public void i_enter_empty_email_address() {
    driver.findElement(By.xpath("//*[@data-testid='email']")).sendKeys("");
    driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/form/div[2]/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
}
    @Then("I should see empty email error message")
    public void i_should_see_email_sent_message() {
        String actual = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div")).getText();
        assertEquals("Please enter email", actual);
    }
    
    @When("I enter incorrect email address {string}")
    public void i_enter_incorrect_email_address(String email) {
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/form/div[1]/input")).sendKeys(email);
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/form/div[2]/button")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @Then("I should see error message")
    public void i_should_see_error_message() {
        String actual = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div")).getText();
        assertEquals("Email not registered", actual);
    }
}
