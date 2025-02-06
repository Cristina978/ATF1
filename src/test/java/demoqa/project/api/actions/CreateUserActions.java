package demoqa.project.api.actions;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.configurations.scenario.ScenarioContext;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import static demoqa.project.enums.Endpoints.CREATE_USER;
import static demoqa.project.enums.ObjectKey.*;
import static io.restassured.RestAssured.given;


public class CreateUserActions {

    public void createUser(Object userData) {
        Response response = given()
                .contentType("application/json")
                .body(userData)
                .post(PropertiesManager.getProperty("BASE_URL_API") + CREATE_USER.getEndPoint())
                .thenReturn();
        ScenarioContext.getInstance().saveData(RESPONSE, response);
        String userId = response.jsonPath().getString("userID");
        ScenarioContext.getInstance().saveData(USER_ID, userId);

        if ( userId != null && response.statusCode() == 201) {
            LogManager.getLogger().info("Registered user with ID: {}", userId);
        } else {
            String errorMessage = response.jsonPath().getString("message");
            LogManager.getLogger().info("Failed to register user. Error: {}", errorMessage);
        }
    }
}
