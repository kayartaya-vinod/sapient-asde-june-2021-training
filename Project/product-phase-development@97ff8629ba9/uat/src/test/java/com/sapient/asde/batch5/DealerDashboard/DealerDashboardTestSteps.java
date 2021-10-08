package com.sapient.asde.batch5.DealerDashboard;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DealerDashboardTestSteps {
    WebDriver driver;

    @Before
    public void setup() {
        String driverLocation = "D:\\SeleniumDrivers\\";
        String chromeDriver = driverLocation + "chromedriver_win32\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        // options.addArguments("--window-size=1200x600");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void login() {
        driver.get("http://localhost:3000/login");
        driver.findElement(By.name("email")).sendKeys("dealer@domain.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/form/div[3]/button")).click();
    }

    @Given("A dealer is logged in")
    public void i_am_on_the_dealer_dashboard_page() {
        login();
    }

    @When("The dealer is on dashboard page")
    public void the_page_loads() {
        driver.get("http://localhost:3000/dealer/dashboard");
    }

    @Then("There should be option for {}, {}, {}, {} and {}")
    public void there_should_be_option_for_favorite_vehicles(String expected1, String expected2, String expected3,
            String expected4, String expected5) {
        String actual1 = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[1]/td/h6"))
                .getText();
        assertEquals(expected1, actual1);
        String actual2 = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[2]/td/h6"))
                .getText();
        assertEquals(expected2, actual2);
        String actual3 = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[3]/td/h6"))
                .getText();
        assertEquals(expected3, actual3);
        String actual4 = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[4]/td/h6"))
                .getText();
        assertEquals(expected4, actual4);
        String actual5 = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[5]/td/h6"))
                .getText();
        assertEquals(expected5, actual5);
    }

    @Then("The dealer should see their profile details")
    public void the_dealer_should_see_their_profile_details() {
        String expected = "dealer@domain.com";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String actual = driver
                .findElement(
                        By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[3]/div/div[1]/div/table/tbody/tr[1]/td[2]"))
                .getText();
        assertEquals(expected, actual);
    }

    // --------------------------------------------------

    @Given("A dealer is logged in and dealer is on dashboard page")
    public void a_dealer_is_logged_in_and_dealer_is_on_dashboard_page() {
        login();
        driver.get("http://localhost:3000/dealer/dashboard");
    }

    @When("dealer clicks on Edit Profile")
    public void dealer_clicks_on_edit_profile() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[2]/td/h6")).click();
    }

    @Then("Page should update with edit options")
    public void page_should_update_with_edit_options() {
        String expected = "Update your details";
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[3]/div/h3")).getText();
        assertEquals(expected, actual);
    }

    @When("dealer clicks on change password")
    public void dealer_clicks_on_change_password() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[3]/td/h6")).click();
    }

    @Then("Page should update with change password option")
    public void page_should_update_with_change_password_options() {
        String expected = "New Password:";
        String actual = driver.findElement(By.xpath("//*[@id=\"form\"]/div[2]/label")).getText();
        assertEquals(expected, actual);
    }

    @When("dealer clicks on Add Vehicles")
    public void dealer_clicks_on_Add_Vehicles() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[4]/td/h6")).click();
    }

    @Then("Page should update with Add Vehicles")
    public void page_should_update_with_Add_Vehicless() {
        String expected = "Add New Vehicle";
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[3]/div/h2")).getText();
        assertEquals(expected, actual);
    }


    @When("dealer clicks on Upload Vehicles")
    public void dealer_clicks_on_Upload_Vehicles() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[5]/td/h6")).click();
    }

    @Then("Page should update with their Upload Vehicles")
    public void page_should_update_with_their_Upload_Vehicles() {
        String expected = "Upload CSV File";
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[3]/div/div/div[1]/label")).getText();
        assertEquals(expected, actual);
    }

}
