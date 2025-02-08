package demoqa.project.api.steps;

import demoqa.project.api.actions.GetUserActions;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.api.dtos.requests.RequestUser;
import demoqa.project.enums.ObjectKey;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import demoqa.project.api.actions.CreateUserActions;
import org.apache.logging.log4j.LogManager;
import java.util.Map;


public class RegisterSteps {
    CreateUserActions createUserActions = new CreateUserActions();
    GetUserActions getUserActions = new GetUserActions();

    @Given("User provides the following credentials:")
    public void prepareCredentials(Map<String, String> userDataValue) {
        RequestUser userCreationRequest = new RequestUser(userDataValue);
        ScenarioContext.getInstance().saveData(ObjectKey.USER_CREDENTIALS, userCreationRequest);
        LogManager.getLogger().info("The Request body for user registration is following: {}", userCreationRequest);
    }

    @When("user registers an account")
    public void registerUser() {
        createUserActions.createUser(ScenarioContext.getInstance().getData(ObjectKey.USER_CREDENTIALS));
    }

    @And("the new user is created")
    public void getUser() {
        getUserActions.getUser();
    }

}


