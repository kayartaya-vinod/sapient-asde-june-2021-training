package com.sapient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FactorialGeneratorTestSteps {

    FactorialGenerator fg;
    long expected = 0;
    Exception expectedException = null;

    @Given("I have a factorial generator")
    public void i_have_a_factorial_generator() {
        fg = new FactorialGenerator();
    }

    @When("I pass {int} as parameter")
    public void i_pass_as_parameter(int num) {
        expected = fg.getFactorial(num);
    }

    @Then("I should get {long} as factorial")
    public void i_should_get_as_factorial(long actual) {
        assertEquals(expected, actual);
    }

    @When("I pass negative value {int} as argument")
    public void i_pass_negative_value_as_argument(int num) {
        try {
            fg.getFactorial(num);
        } catch (Exception ex) {
            this.expectedException = ex;
        }
    }

    @Then("I should get IllegalArgumentException")
    public void i_should_get_illegal_argument_exception() {
        assertNotNull("expectedException is null and should not be!", expectedException);
        assertTrue("Expecting IllegalArgumentException, but got: " + expectedException.getClass().getName(),
                expectedException instanceof IllegalArgumentException);
    }
}
