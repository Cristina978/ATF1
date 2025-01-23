package demoqa.project.ui.steps;

import demoqa.project.ui.pages.LoginPage;
import demoqa.project.ui.pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;

import java.util.Map;

public class RegisterStep {
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @Given("user is on Registration page")
    public void userIsOnRegistrationPage() {
        registerPage.clickNewUserButton();
        LogManager.getLogger().info("Registration page is displayed");
    }

    @When("user register with valid data")
    public void registerWithValidData () throws InterruptedException {
        registerPage.loginWithCredentials();
    }

    @And("user check the reCAPTCHA checkbox")
    public void checkCaptchaCheckbox() {
        RegisterPage.checkCaptchaCheckbox();

    }

    @Then("user is successful registered")
    public void userIsSuccessfulRegistered() {
        registerPage.clickRegisterButton();
        LogManager.getLogger().info("Register button is clicked");
    }

}
