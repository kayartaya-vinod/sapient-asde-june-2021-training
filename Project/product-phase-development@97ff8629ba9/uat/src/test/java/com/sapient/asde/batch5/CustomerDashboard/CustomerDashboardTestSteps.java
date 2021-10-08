package com.sapient.asde.batch5.CustomerDashboard;

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

public class CustomerDashboardTestSteps {
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
        driver.findElement(By.name("email")).sendKeys("customer@domain.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/form/div[3]/button")).click();
    }

    @Given("A customer is logged in")
    public void i_am_on_the_customer_dashboard_page() {
        login();
    }

    @When("The customer is on dashboard page")
    public void the_page_loads() {
        driver.get("http://localhost:3000/customer/dashboard");
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

    @Then("The customer should see their profile details")
    public void the_customer_should_see_their_profile_details() {
        String expected = "customer@domain.com";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String actual = driver
                .findElement(
                        By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[3]/div/div[1]/div/table/tbody/tr[1]/td[2]"))
                .getText();
        assertEquals(expected, actual);
    }

    // --------------------------------------------------

    @Given("A customer is logged in and customer is on dashboard page")
    public void a_customer_is_logged_in_and_customer_is_on_dashboard_page() {
        login();
        driver.get("http://localhost:3000/customer/dashboard");
    }

    @When("Customer clicks on Edit Profile")
    public void customer_clicks_on_edit_profile() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[2]/td/h6")).click();
    }

    @Then("Page should update with edit options")
    public void page_should_update_with_edit_options() {
        String expected = "Update your details";
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[3]/div/h3")).getText();
        assertEquals(expected, actual);
    }

    @When("Customer clicks on change password")
    public void customer_clicks_on_change_password() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[3]/td/h6")).click();
    }

    @Then("Page should update with change password option")
    public void page_should_update_with_change_password_options() {
        String expected = "New Password:";
        String actual = driver.findElement(By.xpath("//*[@id=\"form\"]/div[2]/label")).getText();
        assertEquals(expected, actual);
    }

    @When("Customer clicks on Saved Comparisons")
    public void customer_clicks_on_Saved_Comparison() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[4]/td/h6")).click();
    }

    @Then("Page should update with Saved Comparisons")
    public void page_should_update_with_Saved_Comparisons() {
        String expected = "Saved Comparisons";
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[3]/div[1]/div[2]/h2")).getText();
        assertEquals(expected, actual);
    }


    @When("Customer clicks on Favorite Vehicles")
    public void customer_clicks_on_Favorite_Vehicles() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[1]/div/table/tbody/tr[5]/td/h6")).click();
    }

    @Then("Page should update with their Favorite Vehicles")
    public void page_should_update_with_their_Favorite_Vehicles() {
        String expected = "Favorite Vehicles";
        String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div[3]/div/h2")).getText();
        assertEquals(expected, actual);
    }

}
