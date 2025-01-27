package demoqa.project.utils;

import demoqa.project.configurations.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;


public class WaitUtils {

    private static WebDriverWait defineWebDriverWait(Duration timeoutInSeconds){
        return new WebDriverWait(DriverManager.getDriver(), timeoutInSeconds);
    }

    public static void waitForElementToBeDisplayed(WebElement element, Duration timeoutInSeconds){
        WebDriverWait wait = defineWebDriverWait(timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        LogManager.getLogger().info("{} field is displayed.", element.getAccessibleName());
    }


    public static void waitForElementToBeClickable(WebElement element, Duration timeoutInSeconds){
        WebDriverWait wait = defineWebDriverWait(timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        LogManager.getLogger().info("Button [{}] is clickable.", element.getText());
    }

}
