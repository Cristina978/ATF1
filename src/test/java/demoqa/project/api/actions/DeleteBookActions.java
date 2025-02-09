package demoqa.project.api.actions;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.enums.ObjectKey;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Map;

import static demoqa.project.enums.Endpoints.GET_BOOK;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

public class DeleteBookActions extends CommonActions {

    public void deleteBook() {
        String token = ScenarioContext.getInstance().getData(ObjectKey.TOKEN);
        String userId = ScenarioContext.getInstance().getData(ObjectKey.USER_ID);
        List<String> booksIsbn = ScenarioContext.getInstance().getData(ObjectKey.BOOKS_ISBN);

      given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(Map.of(
                        "userId", userId,
                        "isbn", booksIsbn.getFirst()
                ))
                .delete(PropertiesManager.getProperty("BASE_URL_API") + GET_BOOK.getEndPoint())
                .then().statusCode(SC_NO_CONTENT);
        LogManager.getLogger().info("Book was deleted with Isbn: {} ", booksIsbn.getFirst());
    }
}
