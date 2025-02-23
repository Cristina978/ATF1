package demoqa.project.api.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.enums.ObjectKey;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import static demoqa.project.enums.Endpoints.GET_USER;
import static demoqa.project.enums.ObjectKey.RESPONSE;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class CommonActions {

    public String formatJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (Exception e) {
            return "Invalid JSON: " + json;
        }
    }

    public void getUser() {
        LogManager.getLogger().info("Starting process to get user details.");
        String user_id = ScenarioContext.getInstance().getData(ObjectKey.USER_ID);
        String token = ScenarioContext.getInstance().getData(ObjectKey.TOKEN);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .get(PropertiesManager.getProperty("BASE_URL_API") + GET_USER.getEndPoint() + "/" + user_id )
                .thenReturn();
        ScenarioContext.getInstance().saveData(RESPONSE, response);
        LogManager.getLogger().info("Response Body: \n {}", formatJson(response.getBody().asString()));
        int statusCode = response.getStatusCode();
        if (statusCode != SC_OK) {
            String errorMessage = response.jsonPath().getString("message");
            LogManager.getLogger().warn("Response contains an error. Message: {}", errorMessage);
        }
    }

    public List<String> getRandomBook(int count) {
        LogManager.getLogger().info("User gets {} random books from the response data.", count);
        Response response = ScenarioContext.getInstance().getData(ObjectKey.RESPONSE);
        JsonPath jsonPath = response.jsonPath();

        List<String> isbnList = jsonPath.getList("books.isbn");
        LogManager.getLogger().info("Total available books in response: {}", isbnList.size());
        count = Math.min(count, isbnList.size());
        Collections.shuffle(isbnList, new Random());
        List<String> randomIsbn = isbnList.stream()
                .limit(count)
                .collect(Collectors.toList());
        LogManager.getLogger().info("Randomly selected {} books with ISBN: {} \n", count, randomIsbn);
        return randomIsbn;
    }
}
