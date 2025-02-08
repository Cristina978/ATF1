package demoqa.project.api.actions;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.enums.ObjectKey;
import org.apache.logging.log4j.LogManager;
import static demoqa.project.enums.Endpoints.GET_USER;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;


public class DeleteUserActions {
    public void deleteAccount() {
        String token = ScenarioContext.getInstance().getData(ObjectKey.TOKEN);
        String user_id = ScenarioContext.getInstance().getData(ObjectKey.USER_ID);
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .delete(PropertiesManager.getProperty("BASE_URL_API") + GET_USER.getEndPoint() + "/" + user_id )
                .then().statusCode(SC_NO_CONTENT);
        LogManager.getLogger().info("User was deleted with ID: {} ", user_id);
    }
}
