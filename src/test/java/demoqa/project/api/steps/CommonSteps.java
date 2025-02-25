package demoqa.project.api.steps;

import demoqa.project.api.actions.CreateUserActions;
import demoqa.project.api.dtos.requests.RequestUser;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.enums.ObjectKey;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import java.util.Map;

public class CommonSteps {

    @Given("the user provides the following credentials:")
    public void prepareCredentials(Map<String, String> userDataValue) {
        LogManager.getLogger("Starting process to register new user.");

        RequestUser userCreationRequest = new RequestUser(userDataValue);
        ScenarioContext.getInstance().saveData(ObjectKey.USER_CREDENTIALS, userCreationRequest);
        LogManager.getLogger().info("The Body for user creation is following: {}", userCreationRequest);
    }

    @When("the user attempts to registers an account")
    public void registerUser() {
        CreateUserActions.createUser(ScenarioContext.getInstance().getData(ObjectKey.USER_CREDENTIALS));
    }
}