package demoqa.project.ui.steps;

import demoqa.project.ui.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @When("User logs in with the following credentials:")
    public void loginWithCredentials(DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMap(String.class, String.class);
        loginPage.loginWithCredentials(credentials);
    }

    @Then("{string} message is displayed")
    public void validateErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorLabel();
        assertThat("The error message is incorrect", actualMessage, is(expectedMessage));
        LogManager.getLogger().info("The error message is: {}", actualMessage);
    }

    @Then("Fields are highlighted in red color")
    public void checkFieldIsHighlighted() {
        loginPage.verifyFieldValidation();
    }
}
