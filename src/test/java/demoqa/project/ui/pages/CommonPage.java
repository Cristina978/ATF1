package demoqa.project.ui.pages;

import demoqa.project.configurations.driver.DriverManager;
import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.utils.WaitUtils;
import org.awaitility.core.ConditionTimeoutException;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.awaitility.Awaitility;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import static demoqa.project.configurations.driver.DriverManager.getDriver;
import static org.junit.Assert.assertEquals;



public class CommonPage {
    LoginPage loginPage = new LoginPage();

    @FindBy(id = "userName")
    private static WebElement userNameField;

    @FindBy(id = "password")
    private static WebElement passwordField;

    public static WebElement getUserNameField() {
        return userNameField;
    }

    public static WebElement getPasswordField() {
        return passwordField;
    }

    public CommonPage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void verifyAllElementsAreDisplayed() {
        LogManager.getLogger().info("Verifying that all elements on the Login page are displayed.");
        WaitUtils.waitForElementToBeDisplayed(loginPage.getLoginButton(), PropertiesManager.displayElementTimeout());
        List<WebElement> elementsOnLoginPage = List.of(
                userNameField,
                passwordField,
                loginPage.getLoginButton(),
                loginPage.getNewUserButton()
        );
        for (WebElement element : elementsOnLoginPage) {
            Assert.assertTrue("Element " + element.getAttribute("id") + " is not displayed!", element.isDisplayed());
            LogManager.getLogger().debug("Element '{}' is displayed.", element.getAttribute("id"));
        }
        LogManager.getLogger().info("All elements on the Login page are displayed correctly.");
    }


    public static String getCurrentPage() {
        String currentUrl = getDriver().getCurrentUrl();

        assert currentUrl != null;
        if (currentUrl.contains("/login")) {
            return "Login";
        } else if (currentUrl.contains("/profile")) {
            return "Profile";
        } else if (currentUrl.contains("/webtables")) {
            return "WebTables";
        } else if (currentUrl.contains("/books")) {
            return "Books";
        } else {
            return "Unknown";
        }
    }

    public void validatePageURL(String expectedURL) {
        try{
            Awaitility.await()
                    .atMost(PropertiesManager.displayElementTimeout())
                    .untilAsserted(() -> assertEquals("Page URL does not match", expectedURL, getCurrentPage()));
        } catch (ConditionTimeoutException ex){
            LogManager.getLogger().info("Unable to validate page URL: condition was not fulfilled within the given time period. Exception details: {}\n", ex.getMessage());
        throw ex;
        }
        LogManager.getLogger().info("The following page URL: \"{}\" is displayed", getCurrentPage());
    }

    public void navigateToURL(String expectedURL) {
        String fullURL = PropertiesManager.getProperty(expectedURL);
        LogManager.getLogger().info("User navigates to: \"{}\" page with URL: {}", expectedURL, fullURL);
        getDriver().get(fullURL);
    }
}