package demoqa.project.hooks;


import demoqa.project.configurations.driver.DriverManager;
import demoqa.project.configurations.logger.LoggerHelper;
import demoqa.project.configurations.scenario.ScenarioContext;
import demoqa.project.ui.commonActions.BrowserAction;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;


public class ExecutionHooks {

    @BeforeAll
    public static void launchTests() {
        ScenarioContext.getInstance().clearData();
    }

    @Before("@UI")
    public void setUpUI(Scenario scenario) throws InterruptedException {
        LoggerHelper.setLogFileName(scenario);
        LogManager.getLogger().info("Starting UI test: {}", scenario.getName());
        DriverManager.MaximizeBrowser();
        DriverManager.openBasePage();
       /* //Așteaptă ca iframe-urile cu aria-label="Advertisement" să devină vizibile
        WebElement iframeAd = DriverManager.getDriver().findElement(By.cssSelector("iframe[aria-label='Advertisement']"));
        waitForElementToBeDisplayed(iframeAd, PropertiesManager.displayElementTimeout());

        // Așteaptă ca secțiunile cu id ce conțin "Advertisement" să devină vizibile
        WebElement sectionAd = DriverManager.getDriver().findElement(By.cssSelector("section[id*='Advertisement']"));
        waitForElementToBeDisplayed(sectionAd, PropertiesManager.displayElementTimeout()); */
        Thread.sleep(3000);
        BrowserAction.removeAds();
    }

    @After("@UI")
    public void clearCache() {
        DriverManager.clearBrowserCache();
    }


    @AfterAll
    public static void closeTests() {
        DriverManager.tearDown();
        ScenarioContext.getInstance().clearData();
        LogManager.getLogger().info("Test is finished. Browser closed");
    }
}
