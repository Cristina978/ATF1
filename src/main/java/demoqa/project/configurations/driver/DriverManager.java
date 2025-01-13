package demoqa.project.configurations.driver;

import demoqa.project.configurations.properties.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = BrowserProperty.getBrowserProperty();
        }
        return driver;
    }

    public static void MaximizeBrowser() {
        getDriver().manage().window().maximize();
        LogManager.getLogger().info("Browser is launched and maximized");
    }

    public static void openBasePage() {
        getDriver().get(PropertiesManager.getProperty("BASE_URL"));
    }

    public static void clearBrowserCache() {
        getDriver().manage().deleteAllCookies();
        LogManager.getLogger().info("Cash was cleared");
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
