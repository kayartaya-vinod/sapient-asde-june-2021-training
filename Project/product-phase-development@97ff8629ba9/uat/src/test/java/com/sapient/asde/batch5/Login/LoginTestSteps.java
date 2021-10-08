/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
package com.sapient.asde.batch5.Login;

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

public class LoginTestSteps {
    WebDriver driver;
    
    @Before
    public void setup() {
        String driverLocation = "C:/Users/rdrl/Downloads/chromeDriver/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("start-maximized");
        options.addArguments("--headless");
        options.addArguments("--window-size=1440x900");
        driver = new ChromeDriver(options);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Given("I have a login page")
    public void i_have_a_login_page() {
        driver.get("http://localhost:3000/login");
    }

    @When("I enter correct email {string} and password {string} and click Submit")
    public void i_enter_correct_email_and_password_and_click_submit(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/form/div[3]/button")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @Then("I should see welcome message on dashboard")
    public void i_should_see_welcome_message_on_dashboard() {
        String actual = driver.findElement(By.xpath("/html/body/div/div/div[2]/h1")).getText();
        assertEquals("Welcome to My Cars Solutions !", actual);
    }
    
    @Given("I am on home page")
    public void i_am_on_home_page() {
        driver.get("http://localhost:3000");
    }

    @When("I click on Login button")
    public void i_click_on_login_button() {
        driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[1]/a")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I should see Login Page")
    public void i_should_see_Login_Page() {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/form/label[1]")).getText();
        assertEquals("Email", actual);
    }

    @When("I enter incorrect email {string} or password {string} and click Submit")
    public void i_enter_incorrect_email_or_password_and_click_submit(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@class=\"btn btn-primary\"]")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    
    @Then("I should see error message on dashboard")
    public void i_should_see_error_message_on_dashboard() {
        String actual = driver.findElement(By.xpath("//*[@class='form-error']")).getText();
        assertEquals("either email or password is incorrect", actual);
    }

    @When("I enter only password {string} and click Submit")
    public void i_enter_only_password_and_click_submit(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/form/div[3]/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
   
    @Then("I should see email error message")
    public void i_should_see_email_error_message() {
        String actual = driver.findElement(By.xpath("//*[@class='form-error text-danger']")).getText();
        assertEquals("email cannot be empty", actual);
    }
    
    @When("I enter only email {string} and click Submit")
    public void i_enter_only_email_and_click_submit(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/form/div[3]/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I should see password error message")
    public void i_should_see_password_error_message(){
        String actual = driver.findElement(By.xpath("//*[@class='form-error text-danger']")).getText();
        assertEquals("password cannot be empty", actual);
    }

    @When("I do not give email and password and click Submit")
    public void i_do_not_give_email_and_password_and_click_submit() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/form/div[3]/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    
    @Then("I should see email and password error message")
    public void i_should_see_email_and_password_error_message() {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/form/ul/li[1]")).getText();
        String actual2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/form/ul/li[2]")).getText();
        assertEquals("email cannot be empty", actual);
        assertEquals("password cannot be empty", actual2);
    }

}