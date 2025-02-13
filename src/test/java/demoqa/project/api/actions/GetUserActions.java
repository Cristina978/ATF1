package demoqa.project.api.actions;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.enums.ObjectKey;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;

import java.util.List;

import static demoqa.project.enums.Endpoints.GET_USER;
import static demoqa.project.enums.ObjectKey.RESPONSE;
import static io.restassured.RestAssured.given;


public class GetUserActions extends CommonActions {
    public void getUser() {
        String user_id = ScenarioContext.getInstance().getData(ObjectKey.USER_ID);
        String token = ScenarioContext.getInstance().getData(ObjectKey.TOKEN);
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .get(PropertiesManager.getProperty("BASE_URL_API") + GET_USER.getEndPoint() + "/" + user_id )
                .thenReturn();
        ScenarioContext.getInstance().saveData(RESPONSE, response);
        LogManager.getLogger().info("Response Body: \n{}", formatJson(response.getBody().asString()));
    }

    public void verifyUserHasNoBooks() {
        String user_id = ScenarioContext.getInstance().getData(ObjectKey.USER_ID);
        String token = ScenarioContext.getInstance().getData(ObjectKey.TOKEN);
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .get(PropertiesManager.getProperty("BASE_URL_API") + GET_USER.getEndPoint() + "/" + user_id )
                .thenReturn();
        ScenarioContext.getInstance().saveData(RESPONSE, response);

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        List<Object> books = jsonPath.getList("books");

        if (books == null || books.isEmpty()) {
            LogManager.getLogger().info("All books have been successfully removed from the user's profile. \n{}", formatJson(responseBody));
        } else {
            LogManager.getLogger().warn("The user's profile still contains books. \n{}", formatJson(responseBody));
        }
    }
}
