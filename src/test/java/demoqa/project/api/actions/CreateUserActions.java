package demoqa.project.api.actions;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.configurations.scenario.ScenarioContext;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import static demoqa.project.enums.Endpoints.GET_USER;
import static demoqa.project.enums.ObjectKey.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;


public class CreateUserActions {


public static void createUser(Object userData) {
    ScenarioContext context = ScenarioContext.getInstance();
        Response response = given()
                .contentType("application/json")
                .body(userData)
                .post(PropertiesManager.getProperty("BASE_URL_API") + GET_USER.getEndPoint())
                .thenReturn();
        context.saveData(RESPONSE, response);
        String userId = response.jsonPath().getString("userID");
        context.saveData(USER_ID, userId);

        if ( userId != null && response.statusCode() == SC_CREATED) {
            LogManager.getLogger().info("New user registered with ID: {}", userId);
        } else {
            String errorMessage = response.jsonPath().getString("message");
            LogManager.getLogger().info("Failed to register user. Error: {}", errorMessage);
        }
    }
}
