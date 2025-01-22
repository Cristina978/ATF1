package demoqa.project.ui.pages;

import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.configurations.properties.PropertiesManager;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.awaitility.Awaitility;
import org.openqa.selenium.support.FindBy;
import static demoqa.project.configurations.driver.DriverManager.getDriver;
import static org.junit.Assert.assertEquals;


public abstract class CommonPage {
    BrowserAction browserAction = new BrowserAction();

    @FindBy(id = "userName")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;


    public WebElement getUserNameField(){
        return userNameField;
    }
    public WebElement getPasswordField(){
        return passwordField;
    }


    public String getCurrentPage() {
        String currentUrl = getDriver().getCurrentUrl();

        if (currentUrl.contains("/login")) {
            return "Login";
        } else if (currentUrl.contains("/profile")) {
            return "Profile";
        } else if (currentUrl.contains("/register")) {
            return "Register";
        } else if (currentUrl.contains("/books")) {
            return "Books";
        } else {
            return "Unknown";
        }
    }

    public void validatePageURL(String expectedURL) {
        Awaitility.await()
                .atMost(PropertiesManager.displayElementTimeout())
                .untilAsserted(() -> assertEquals("Page URL does not match", expectedURL, getCurrentPage()));
        LogManager.getLogger().info("The following page URL: \"{}\" is displayed", expectedURL);
    }
}