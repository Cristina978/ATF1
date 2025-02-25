package demoqa.project.ui.steps;

import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.ui.pages.LoginPage;
import demoqa.project.ui.pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class RegisterSteps {
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @Given("user is on Login page")
    public void userIsOnLoginPage() {
        LogManager.getLogger().info("User is on Login page");
    }

    @When("User clicks on {string} button")
    public void clickNewUserButton(String buttonName) {
        registerPage.clickNewUserButton(buttonName);
    }

    @And("User fill in the registration form")
    public void completeTheRegistrationForm(Map<String, String> credentials) {
        registerPage.loginWithCredentials(credentials);
    }

    @And("User cancel the registration form")
    public void registerWithValidData () {

    }

    @Then("User is redirected on {string} page")
    public void goBackToLoginPage(String urlName) {
        loginPage.validatePageURL(urlName);
    }
}
