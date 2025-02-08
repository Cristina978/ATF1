package demoqa.project.api.steps;

import demoqa.project.api.actions.CreateUserActions;
import demoqa.project.api.actions.GenerateTokenActions;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.enums.ErrorMessage;
import demoqa.project.enums.ObjectKey;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class CommonSteps {
    Response response = ScenarioContext.getInstance().getData(ObjectKey.RESPONSE);

    @Then("response has status code {}")
    public void validateStatusCode(int expectedStatusCode) {
        assertEquals("The status code is incorrect", expectedStatusCode, response.getStatusCode());
        LogManager.getLogger().info("The {} status code is received", response.getStatusCode());
    }

    @Then("response status code has {}")
    public void verifyStatusCode(String expectedStatusCode) {
        assertEquals("The status code is incorrect", Integer.parseInt(expectedStatusCode), response.getStatusCode());
        LogManager.getLogger().info("The response has status code {}", response.getStatusCode());
    }


    @And("authorization token is generated")
    public void validateAuthorizationToken() {
        GenerateTokenActions.generateToken(ScenarioContext.getInstance().getData(ObjectKey.USER_CREDENTIALS));
    }

    @And("{} error message is displayed")
    public void validateErrorMessage(ErrorMessage type) {
        String expectedErrorMessage = type.getErrorMessageName();
        assertThat("The error message is incorrect", response.jsonPath().getString("message"), is(expectedErrorMessage));
        LogManager.getLogger().info("The following error message is displayed: {}", expectedErrorMessage);
    }



}