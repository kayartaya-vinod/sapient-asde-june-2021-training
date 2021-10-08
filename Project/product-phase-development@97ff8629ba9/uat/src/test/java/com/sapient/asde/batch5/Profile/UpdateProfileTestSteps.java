package com.sapient.asde.batch5.Profile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;


/*
 * @Author Shubham Chaudhari shubham.chaudhari@publicissapient.com
 */

@Slf4j
public class UpdateProfileTestSteps {

    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "D:/Users/shuchaud1/Desktop/devtools/browserdriver/";
        String chromeDriver = driverLocation + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
         driver.quit();
    }

    @Given("I am on the profile page")
    public void i_am_on_the_profile_page() {
        driver.get("http://localhost:3000/customer/profile");
    }

    @When("I click the edit profile button")
    public void i_click_the_edit_profile_button() {
        driver.findElement(By.xpath("//*[@data-testid=\"btn-edit\"]")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    @Then("I should see a form where i can enter my details")
    public void i_should_see_a_form_where_i_can_enter_my_details() {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/h3")).getText();
        assertEquals("Update your details", actual);
    }




    @Given("I am on the update details page")
    public void i_am_on_the_update_details_page() {
        driver.get("http://localhost:3000/customer/profile/edit");
    }

    @When("I enter {string} and {string} as first name and last name and click update details button")
    public void i_pass_and_as_parameter(String string, String string2) {
        driver.findElement(By.xpath("//*[@data-testid=\"firstName\"]")).sendKeys(Keys.chord(Keys.CONTROL, "a"),string);
        driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys(Keys.chord(Keys.CONTROL, "a"),string2);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I should see {string} on the profile page")
    public void i_should_see_on_the_profile_page(String string) {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/h3")).getText();
        assertEquals(string, actual);
    }



    @When("I click the update details button without changing anything")
    public void i_click_the_update_details_button_without_changing_anything() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    @Then("I should rediect and see same full name {string} on the profile page")
    public void i_should_rediect_and_see_same_details_on_the_profile_page(String fullname) {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/h3")).getText();
        assertEquals(fullname, actual);
    }

    @When("I enter {string} as my email and click update details button")
    public void i_enter_as_my_email_and_click_update_details_button(String email) {
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(Keys.chord(Keys.CONTROL, "a"),email);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I should see {string} on my profile page")
    public void i_should_see_email_on_my_profile_page(String email) {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/table/tbody/tr[1]/td[2]")).getText();
        assertEquals(email, actual);
    }

    @When("I enter {string} as my alternate email and click update details button")
    public void i_enter_as_my_alternate_email_and_click_update_details_button(String string) {
        driver.findElement(By.xpath("//*[@id=\"alternateEmail\"]")).sendKeys(Keys.chord(Keys.CONTROL, "a"),string);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    @Then("I should see {string} as alternate email on my profile page")
    public void i_should_see_as_alternate_email_on_my_profile_page(String string) {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/table/tbody/tr[2]/td[2]")).getText();
        assertEquals(string, actual);
    }

    @When("I enter {string} as my contact number and click update details button")
    public void i_enter_as_my_contact_number_and_click_update_details_button(String string) {
        driver.findElement(By.xpath("//*[@id=\"contactNo\"]")).sendKeys(Keys.chord(Keys.CONTROL, "a"),string);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Then("I should see {string} as my contact on my profile page")
    public void i_should_see_as_alternate_contact_on_my_profile_page(String string) {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/table/tbody/tr[5]/td[2]")).getText();
        assertEquals(string, actual);
    }

    @When("I enter {string} as my alternate contact and click update details button")
    public void i_enter_phone_as_my_alternate_contact_and_click_update_details_button(String string) {
        driver.findElement(By.xpath("//*[@id=\"alternateContactNo\"]")).sendKeys(Keys.chord(Keys.CONTROL, "a"),string);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div/button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    @Then("I should see {string} as alternate contact on my profile page")
    public void i_should_see_phone_as_alternate_contact_on_my_profile_page(String string) {
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/table/tbody/tr[6]/td[2]")).getText();
        assertEquals(string, actual);
    }

            
}
