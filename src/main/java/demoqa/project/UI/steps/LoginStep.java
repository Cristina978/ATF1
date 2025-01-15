package demoqa.project.UI.steps;

import demoqa.project.UI.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginStep {
    LoginPage loginPage = new LoginPage();

    @Given("User is on Login page")
    public void userIsOnLoginPage() {
        LogManager.getLogger().info("Login page is displayed");
    }
    @When("User enter the following credentials")
    public void enterTheFollowingCredentials(Map<String, String> userData) {
        loginPage.loginWithCredentials(userData);
    }
    @Then("{String} is displayed")
    public void isDisplayed(String urlName) {
        loginPage.validatePageURL(urlName);
    }
    @Then("{string} message is displayed")
    public void validateErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorLabel().getText();
        assertThat("The error message is incorrect", actualMessage, is(expectedMessage));
    }
}
