package demoqa.project.ui.steps;

import demoqa.project.ui.pages.LoginPage;
import demoqa.project.ui.pages.RegisterPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;

public class RegisterStep {
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @Given("user is on Registration page")
    public void userIsOnRegistrationPage() {
        registerPage.clickNewUserButton();
        LogManager.getLogger().info("Registration page is displayed");
    }

    @When("user cancel the registration form")
    public void registerWithValidData () {
        registerPage.loginWithCredentials();
    }

    @Then("user is on {string} page")
    public void goBackToLoginPage(String urlName) {
        loginPage.validatePageURL(urlName);
    }
}
