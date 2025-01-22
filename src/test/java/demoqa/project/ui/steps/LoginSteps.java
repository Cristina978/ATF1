package demoqa.project.ui.steps;

import demoqa.project.ui.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Given("user is on Login page")
    public void userIsOnLoginPage() {
        LogManager.getLogger().info("Login page is displayed");
    }

    @When("user logs in with the following credentials:")
    public void loginWithCredentials(Map<String, String> credentials) {
        loginPage.loginWithCredentials(credentials);
    }

    @Then("{string} message is displayed")
    public void validateErrorMessage(String expectedMessage) throws InterruptedException {
        String actualMessage = loginPage.getErrorLabel().getAccessibleName();
        assertThat("The error message is incorrect", actualMessage, is(expectedMessage));
    }

    @Then("{string} page is displayed")
    public void isLoginPageDisplayed(String urlName) {
        loginPage.validatePageURL(urlName);
    }

}
