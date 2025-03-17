package demoqa.project.ui.commonActions;

import demoqa.project.configurations.driver.DriverManager;
import demoqa.project.configurations.properties.PropertiesManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import demoqa.project.utils.WaitUtils;



public class BrowserAction {

    public static void clickButton(WebElement button) {
        scrollToElement(button);
        WaitUtils.waitForElementToBeClickable(button, PropertiesManager.displayElementTimeout());
        String buttonName = button.getText();
        button.click();
        LogManager.getLogger().info("The [{}] button is clicked.", buttonName);
    }

    public static void populateField(WebElement inputField, String value) {
        populateFieldInternal(inputField, value == null ? "" : value);
    }
    public static void populateField(WebElement inputField, Integer value) {
        populateFieldInternal(inputField, value == null ? "" : String.valueOf(value));
    }
    private static void populateFieldInternal(WebElement inputField, String value) {
        WaitUtils.waitForElementToBeDisplayed(inputField, PropertiesManager.displayElementTimeout());
        inputField.clear();
        LogManager.getLogger().debug("{} field is cleared", inputField.getAccessibleName());
        inputField.sendKeys(value);
        LogManager.getLogger().info("{} field is populated with the value: {}", inputField.getAccessibleName(), value);
    }

    public static void removeAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("document.querySelectorAll('iframe[aria-label=\"Advertisement\"], section[id*=\"Advertisement\"]').forEach(el => el.remove());\n");
            LogManager.getLogger().info("All Advertisements are removed.");
        } catch (Exception e) {
            LogManager.getLogger().warn("Failed to remove ads: {}", e.getMessage());
        }
    }
    public static void scrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            LogManager.getLogger().debug("Successfully scrolled to element: {}", element);
        } catch (Exception e) {
            LogManager.getLogger().error("Error while scrolling to the element: {}", element, e);
            throw e;
        }
    }

}
