package demoqa.project.enums;

import org.apache.logging.log4j.LogManager;

public enum Endpoints {
    GET_USER("Account/v1/User"),
    GENERATE_TOKEN("Account/v1/GenerateToken"),
    GET_BOOKS("BookStore/v1/Books"),
    GET_BOOK("BookStore/v1/Book");

    private final String ENDPOINT;

    Endpoints(String endPoint) {
        this.ENDPOINT = endPoint;
    }

    public String getEndPoint() {
        LogManager.getLogger().info("Retrieving the endpoint: {}", ENDPOINT);
        return ENDPOINT;
    }
}
