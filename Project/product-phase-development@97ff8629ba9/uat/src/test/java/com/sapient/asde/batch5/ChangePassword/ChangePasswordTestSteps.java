/**
@Author <Hrishant> <hrishant.raj@publicissapient.com>
*/

package com.sapient.asde.batch5.ChangePassword;

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

public class ChangePasswordTestSteps {
    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "D:/Users/hriraj1/Documents/ChromeDriver/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1200x600");
        driver = new ChromeDriver(options);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am on the change password page")
    public void i_am_on_the_change_password_page() {
        driver.get("http://localhost:3000");
        driver.findElement(By.xpath("//*[@data-testid=\"navbarDropdown\"]")).click();
        driver.findElement(By.cssSelector("#navbarColor01 > ul > li > ul > div:nth-child(2) > li > a")).click();
    }

    @When("I do not enter anything in some or all field and click change password button")
    public void i_do_not_enter_anything_in_some_or_all_field_and_click_change_password_button() {
        driver.findElement(By.xpath("//*[@id=\"form\"]/input")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @When("I type right old password {string} and different new password {string} and confirm password {string}")
    public void i_type_right_old_password_and_different_new_password_and_confirm_password_welcome(String string,
            String string2, String string3) {
        driver.findElement(By.xpath("//*[@id=\"old_password\"]")).sendKeys(string);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(string2);
        driver.findElement(By.xpath("//*[@id=\"confirm_password\"]")).sendKeys(string3);
        driver.findElement(By.xpath("//*[@id=\"form\"]/input")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @When("I type right old password old and only enter the new password {int}")
    public void i_type_right_old_password_old_and_only_enter_the_new_password(Integer int1) {
        driver.findElement(By.xpath("//*[@id=\"old_password\"]")).sendKeys("old");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("new");
        driver.findElement(By.xpath("//*[@id=\"form\"]/input")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @When("I type right old password old and only enter the confirm password {int}")
    public void i_type_right_old_password_old_and_only_enter_the_confirm_password(Integer int1) {
        driver.findElement(By.xpath("//*[@id=\"old_password\"]")).sendKeys("old");
        driver.findElement(By.xpath("//*[@id=\"confirm_password\"]")).sendKeys("new");
        driver.findElement(By.xpath("//*[@id=\"form\"]/input")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @When("I type same new password new and confirm password new only")
    public void i_type_same_new_password_new_and_confirm_password_new_only() {
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("new");
        driver.findElement(By.xpath("//*[@id=\"confirm_password\"]")).sendKeys("new");
        driver.findElement(By.xpath("//*[@id=\"form\"]/input")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @When("I type right old password {string} and same new password {string} and confirm password {string}")
    public void i_type_right_old_password_and_same_new_password_and_confirm_password(String string, String string2,
            String string3) {
        driver.findElement(By.xpath("//*[@id=\"old_password\"]")).sendKeys(string);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(string2);
        driver.findElement(By.xpath("//*[@id=\"confirm_password\"]")).sendKeys(string3);
        driver.findElement(By.xpath("//*[@id=\"form\"]/input")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @When("I type right old password {string} and new password {string} and confirm password {string} without following pattern")
    public void i_type_right_old_password_and_new_password_and_confirm_password_without_following_pattern(String string,
            String string2, String string3) {
        driver.findElement(By.xpath("//*[@id=\"old_password\"]")).sendKeys(string);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(string2);
        driver.findElement(By.xpath("//*[@id=\"confirm_password\"]")).sendKeys(string3);
        driver.findElement(By.xpath("//*[@id=\"form\"]/input")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I will see an Passwords don't match. error message")
    public void i_will_see_an_passwords_don_t_match_error_message() {
        String actual = driver
                .findElement(By.cssSelector(
                        "#root > div > div:nth-child(2) > div > div.col-12.col-md-8.col-lg-4 > div > div > div"))
                .getText();
        assertEquals("Passwords don't match.", actual);
    }

    @Then("I will see an Password has been changed message")
    public void i_will_see_an_password_has_been_changed_message() {
        String actual = driver
                .findElement(By.cssSelector(
                        "#root > div > div:nth-child(2) > div > div.col-12.col-md-8.col-lg-4 > div > div > div"))
                .getText();
        assertEquals("Password has been changed", actual);
    }

    @Then("I will see an Please enter all details. message")
    public void i_will_see_an_please_enter_all_details_message() {
        String actual = driver
                .findElement(By.cssSelector(
                        "#root > div > div:nth-child(2) > div > div.col-12.col-md-8.col-lg-4 > div > div > div"))
                .getText();
        assertEquals("Please enter all details.", actual);
    }

    @Then("I will see an {string} message")
    public void i_will_see_an_message(String string) {
        String actual = driver
                .findElement(By.cssSelector(
                        "#root > div > div:nth-child(2) > div > div.col-12.col-md-8.col-lg-4 > div > div > div"))
                .getText();
        assertEquals(string, actual);
    }

    @Then("I will see an Please enter all details. error message")
    public void i_will_see_an_please_enter_all_details_error_message() {
        String actual = driver
                .findElement(By.cssSelector(
                        "#root > div > div:nth-child(2) > div > div.col-12.col-md-8.col-lg-4 > div > div > div"))
                .getText();
        assertEquals("Please enter all details.", actual);
    }

}
