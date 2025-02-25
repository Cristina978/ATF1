package demoqa.project.configurations.driver;

import io.github.bonigarcia.wdm.WebDriverManager;

import demoqa.project.configurations.properties.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserProperty {
    private static WebDriver driver;
    private static final String BROWSER = PropertiesManager
            .getBrowser()
            .toLowerCase()
            .trim();


    public static WebDriver getBrowserProperty() {
        switch (BROWSER) {
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox" :
                WebDriverManager.chromedriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari" :
                WebDriverManager.chromedriver().setup();
                driver = new SafariDriver();
                break;
            default :
                WebDriverManager.chromedriver().setup();
                driver = new EdgeDriver();
        }
        LogManager.getLogger().info("Current browser is: {}", BROWSER);
        return driver;
    }
}
