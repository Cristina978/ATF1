package demoqa.project.api.actions;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.enums.ObjectKey;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;

import java.util.*;
import java.util.stream.Collectors;

import static demoqa.project.enums.Endpoints.GET_BOOKS;
import static demoqa.project.enums.ObjectKey.RESPONSE;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;

public class BooksActions extends CommonActions {
    public List<String> bookIsbn = new ArrayList<>();

    public void getAllBooks() {
        String token = ScenarioContext.getInstance().getData(ObjectKey.TOKEN);
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .get(PropertiesManager.getProperty("BASE_URL_API") + GET_BOOKS.getEndPoint())
                .thenReturn();
        ScenarioContext.getInstance().saveData(RESPONSE, response);
        LogManager.getLogger().info("Response Body: \n{}", formatJson(response.getBody().asString()));
    }

    public void addBook (int randomIsbnCount) {
        String token = ScenarioContext.getInstance().getData(ObjectKey.TOKEN);
        String userId = ScenarioContext.getInstance().getData(ObjectKey.USER_ID);

        bookIsbn = getRandomBook(randomIsbnCount);
        ScenarioContext.getInstance().saveData(ObjectKey.BOOKS_ISBN, bookIsbn);

        List<Map<String, String>> isbnList = bookIsbn.stream()
                .map(isbn -> Map.of("isbn", isbn))
                .toList();

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(Map.of(
                        "userId", userId,
                        "collectionOfIsbns", isbnList
                ))
                .post(PropertiesManager.getProperty("BASE_URL_API") + GET_BOOKS.getEndPoint())
                .thenReturn();
        ScenarioContext.getInstance().saveData(RESPONSE, response);
        if ( response.statusCode() == SC_CREATED) {
            LogManager.getLogger().info("Successfully added books to user with ID: {}", userId);
            LogManager.getLogger().info("Added ISBNs: {}", isbnList.stream().map(m -> m.get("isbn")).toList());
        } else {
            String errorMessage = response.jsonPath().getString("message");
            LogManager.getLogger().info("Failed to add books to user ID: {}. Status Code: {}, Error: {}", userId, response.statusCode(), errorMessage);
        }
    }

    public void addMultipleBooksToProfile (int randomIsbnCount) {
        String token = ScenarioContext.getInstance().getData(ObjectKey.TOKEN);
        String userId = ScenarioContext.getInstance().getData(ObjectKey.USER_ID);

        bookIsbn = getRandomBook(randomIsbnCount);
        ScenarioContext.getInstance().saveData(ObjectKey.BOOKS_ISBN, bookIsbn);

        List<Map<String, String>> isbnList = bookIsbn.stream()
                .map(isbn -> Map.of("isbn", isbn))
                .toList();

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(Map.of(
                        "userId", userId,
                        "collectionOfIsbns", isbnList
                ))
                .post(PropertiesManager.getProperty("BASE_URL_API") + GET_BOOKS.getEndPoint())
                .thenReturn();
        ScenarioContext.getInstance().saveData(RESPONSE, response);
    }


}
