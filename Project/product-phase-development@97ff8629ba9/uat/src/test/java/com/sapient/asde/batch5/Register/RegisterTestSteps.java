 /**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com
# */
package com.sapient.asde.batch5.Register;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterTestSteps {

    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "C:/Users/yogamber singh negi/Downloads/Compressed/chromedriver_win32_/";
        String chromeDriver = driverLocation + "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        

        driver.manage().window().maximize();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am not logged in")
    public void i_have_a_home_page() {
        driver.get("http://localhost:3000/");
    }

    @When("I click register")
    public void i_click_register() {
        driver.findElement(By.xpath("/html/body/div/div/div[1]/nav/div/div/ul/li[2]/a")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I should see registration page")
    public void i_should_see_registration_page() {
        String actual = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/div/h2")).getText();
        assertEquals("Create Account", actual);
    }

    @Given("I am not logged in and a registered user and on registration page")
    public void i_am_a_registered_user() {
        driver.get("http://localhost:3000/register");
    }

    @When("I click Login here link")
    public void i_click_login_link() {
        driver.findElement(By.xpath("/html/body/div/div/div[1]/nav/div/div/ul/li[1]/a")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I should see login page")
    public void i_should_see_login_page() {
        String actual = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/h2")).getText();
        assertEquals("Existing users, login here !", actual);
    }

    @Given("I am on register page")
    public void i_am_on_registered_page() {
        driver.get("http://localhost:3000/register");
    }

    @When("I pass {string} {string} {string} {string} {string} elements and click register")
    public void i_pass_elements_and_click_register(String string, String string2, String string4, String string5,
            String string6) {

        driver.findElement(By.id("firstname")).sendKeys(string);
        driver.findElement(By.id("lastname")).sendKeys(string2);
        driver.findElement(By.id("email")).sendKeys(string4);
        driver.findElement(By.id("password")).sendKeys(string5);
        driver.findElement(By.id("confirmPassword")).sendKeys(string6);
        // try {
        // Thread.sleep(3000);
        // } catch (Exception e) {
        // }
        driver.findElement(By.xpath("//button[@data-testid='btn-submit']")).sendKeys(Keys.CONTROL, Keys.END);
        // driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/label")).click();
        driver.findElement(By.xpath("//button[@data-testid='btn-submit']")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @Then("I should see password not matching message")
    public void i_should_see_password_not_matching() {
        String actual = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/form/div[5]/div/p"))
                .getText();
        assertEquals("Passwords do not match", actual);
    }

    @When("I pass {string} {string} {string} {string} elements and not first name and click register")
    public void no_name_passed(String string, String string3, String string4, String string5) {

        driver.findElement(By.id("lastname")).sendKeys(string);
        driver.findElement(By.id("email")).sendKeys(string3);
        driver.findElement(By.id("password")).sendKeys(string4);
        driver.findElement(By.id("confirmPassword")).sendKeys(string5);
        driver.findElement(By.xpath("//button[@data-testid='btn-submit']")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//button[@data-testid='btn-submit']")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @Then("I should see first name required message")
    public void i_should_see_first_name_error() {

        String actual = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/form/div[1]/div/div"))
                .getText();
        assertEquals("First name is required", actual);
    }

    @When("I pass {string} {string} {string} {string} elements and not email and click register")
    public void email_not_passed(String string, String string2, String string4, String string5) {

        driver.findElement(By.id("firstname")).sendKeys(string);

        driver.findElement(By.id("lastname")).sendKeys(string2);
        driver.findElement(By.id("password")).sendKeys(string4);
        driver.findElement(By.id("confirmPassword")).sendKeys(string5);

        driver.findElement(By.xpath("//button[@data-testid='btn-submit']")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//button[@data-testid='btn-submit']")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @Then("I should see email required message")
    public void i_should_see_email_required() {
        String actual = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/form/div[3]/div/p"))
                .getText();
        assertEquals("Email required", actual);
    }

    @When("I pass {string} {string} {string} {string} {string} no elements and click register")
    public void i_pass_no_elements_and_click_register(String string, String string2, String string4, String string5,
            String string6) {

        driver.findElement(By.id("firstname")).sendKeys(string);
        driver.findElement(By.id("lastname")).sendKeys(string2);
        driver.findElement(By.id("email")).sendKeys(string4);
        driver.findElement(By.id("password")).sendKeys(string5);
        driver.findElement(By.id("confirmPassword")).sendKeys(string6);
        driver.findElement(By.xpath("//button[@data-testid='btn-submit']")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//button[@data-testid='btn-submit']")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    @Then("I should see all error messages")
    public void i_should_see_all_error_messages() {
        String actual = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/form/div[1]/div/div"))
                .getText();
        assertEquals("First name is required", actual);
        String actual1 = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/form/div[3]/div/p"))
                .getText();
        assertEquals("Email required", actual1);
        String actual2 = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/form/div[4]/div/p"))
                .getText();
        assertEquals("Field is required", actual2);
        String actual3 = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/form/div[5]/div/p"))
                .getText();
        assertEquals("Field is required", actual3);

    }

   

}
