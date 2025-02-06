package demoqa.project.api.actions;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.configurations.scenario.ScenarioContext;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;

import static demoqa.project.enums.Endpoints.GENERATE_TOKEN;
import static demoqa.project.enums.ObjectKey.RESPONSE;
import static demoqa.project.enums.ObjectKey.TOKEN;
import static io.restassured.RestAssured.given;

public class GenerateToken {
    public static void generateToken(Object userData) {
        Response response = given()
                .contentType("application/json")
                .body(userData)
                .post(PropertiesManager.getProperty("BASE_URL_API") + GENERATE_TOKEN.getEndPoint())
                .thenReturn();
        ScenarioContext.getInstance().saveData(RESPONSE, response);
        ScenarioContext.getInstance().saveData(TOKEN, response.jsonPath().getString("token"));
        if (ScenarioContext.getInstance().getData(TOKEN) != null) {
            LogManager.getLogger().info("User authorized successfully with token: {}", ScenarioContext.getInstance().getData(TOKEN).toString());
        } else {
            String errorMessage = response.jsonPath().getString("message");
            LogManager.getLogger().info("No token generated. Failed with the following error: {}", errorMessage);
        }
    }
}
