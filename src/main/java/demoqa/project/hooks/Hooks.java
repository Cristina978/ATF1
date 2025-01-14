package demoqa.project.hooks;

//import demoqa.project.api.actions.DeleteUserActions;
import demoqa.project.configurations.driver.DriverManager;
//import demoqa.project.configurations.logger.LoggerHelper;
//import demoqa.project.configurations.scenario.context.ScenarioContext;
//import demoqa.project.configurations.screenshots.ScreenshotConfiguration;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;

public class Hooks {

    @BeforeAll
    public static void launchTests() {
        //ScenarioContext.getInstance().clearData();
    }

    @Before("@API")
    public void setUpAPI(Scenario scenario) {
       // LoggerHelper.setLogFileName(scenario);
        LogManager.getLogger().info("Starting API test: {}", scenario.getName());
    }

    @Before("@UI")
    public void setUpUI(Scenario scenario) {
       // LoggerHelper.setLogFileName(scenario);
        LogManager.getLogger().info("Starting UI test: {}", scenario.getName());
       // DriverManager.openAndMaximizeBrowser();
        DriverManager.openBasePage();
    }

    @After(value = "@DeleteAccount", order = 2)
    public void clearUserData() {
    //    DeleteUserActions deleteUserActions = new DeleteUserActions();
     //   deleteUserActions.deleteAccount();
    }

    @After(value = "@API", order = 1)
    public void clearDataAPI() {
        /*ScenarioContext.getInstance().clearData();*/
    }

    @After("@UI")
    public void clearCache() {
        DriverManager.clearBrowserCache();
    }

    @AfterStep("@UI")
    public void takeScreenshot(Scenario scenario) {
      //  ScreenshotConfiguration.captureScreenshot(scenario);
    }

    @AfterAll
    public static void closeTests() {
        DriverManager.tearDown();
       // ScenarioContext.getInstance().clearData();
        LogManager.getLogger().info("Test is finished. Browser closed");
    }
}
