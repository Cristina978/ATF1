package demoqa.project.configurations.driver;

import demoqa.project.configurations.properties.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserProperty {
    private static WebDriver driver;
    private static final String BROWSER_TYPE = PropertiesManager
            .getBrowser()
            .toLowerCase()
            .trim();

    public static WebDriver getBrowserProperty() {
        switch (BROWSER_TYPE) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "safari" -> driver = new SafariDriver();
            default -> driver = new EdgeDriver();
        }
        LogManager.getLogger().info("Current browser is: {}", BROWSER_TYPE);
        return driver;
    }
}
