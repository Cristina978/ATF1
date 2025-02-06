package demoqa.project.api.steps;

import demoqa.project.api.actions.CreateUserActions;
import demoqa.project.api.actions.GenerateToken;
import demoqa.project.configurations.scenario.ScenarioContext;
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
    CreateUserActions createUserActions = new CreateUserActions();

    @Then("response has status code {}")
    public void validateStatusCode(int expectedStatusCode) {
        assertEquals("The status code is incorrect", expectedStatusCode, response.getStatusCode());
        LogManager.getLogger().info("The {} status code is received", response.getStatusCode());
    }

    @And("authorization token is generated")
    public void validateAuthorizationToken() {
        GenerateToken.generateToken(ScenarioContext.getInstance().getData(ObjectKey.USER_CREDENTIALS));
    }

}