package demoqa.project.hooks;


import demoqa.project.configurations.driver.DriverManager;
import demoqa.project.configurations.logger.LoggerHelper;
import demoqa.project.configurations.scenario.ScenarioContext;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;


public class ExecutionHooks {

    @Before("@UI")
    public static void launchTests() {
        ScenarioContext.getInstance().clearData();
    }

    @Before("@UI")
    public void setUpUI(Scenario scenario) {
        LoggerHelper.setLogFileName(scenario);
        LogManager.getLogger().info("Starting UI test: {}", scenario.getName());
        DriverManager.MaximizeBrowser();
        DriverManager.openBasePage();
    }

    @After("@UI")
    public void clearCache() {
        DriverManager.clearBrowserCache();
    }


    @After("@UI")
    public static void closeTests() {
        DriverManager.tearDown();
        ScenarioContext.getInstance().clearData();
        LogManager.getLogger().info("Test is finished. Browser closed");
    }
}
