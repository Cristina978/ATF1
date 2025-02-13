package demoqa.project.api.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.enums.ObjectKey;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    public List<String> getRandomBook(int count) {
        Response response = ScenarioContext.getInstance().getData(ObjectKey.RESPONSE);
        JsonPath jsonPath = response.jsonPath();

        List<String> isbnList = jsonPath.getList("books.isbn");
        count = Math.min(count, isbnList.size());
        Collections.shuffle(isbnList, new Random());
        List<String> randomIsbn = isbnList.stream()
                .limit(count)
                .collect(Collectors.toList());
        LogManager.getLogger().info("The book with the following isbn is generated: " + randomIsbn);
        return randomIsbn;
    }
}
