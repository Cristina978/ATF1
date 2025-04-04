package demoqa.project.ui.stepdefinitions;

import demoqa.project.ui.pageobjects.CommonPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;


public class CommonSteps extends CommonPage {

    @Given("User is on {string} page")
    public void userIsOnLoginPage(String urlName) {
        LogManager.getLogger().info("User is on Login page \n");
        validatePageURL(urlName);
        verifyAllElementsAreDisplayed();
    }

    @Then("User is redirected on {string} page")
    @Then("{string} page is displayed")
    public void isLoginPageDisplayed(String urlName) {
        validatePageURL(urlName);
    }
}
