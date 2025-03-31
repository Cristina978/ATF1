package demoqa.project.configurations.driver;

import demoqa.project.configurations.properties.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;


public class DriverManager {

    private static WebDriver driver;
    public DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = BrowserProperty.getBrowserProperty();}
        return driver;
    }

    public static void maximizeBrowser() {
        getDriver().manage().window().maximize();
        LogManager.getLogger().info("Browser is launched and maximized");
    }

    public static void openBasePage() {
        getDriver().get(PropertiesManager.getProperty("BASE_URL"));
    }

    public static void clearBrowserCache() {
        getDriver().manage().deleteAllCookies();
        LogManager.getLogger().info("Cache was cleared. \n");
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
