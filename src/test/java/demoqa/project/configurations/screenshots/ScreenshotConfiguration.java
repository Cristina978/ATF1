package demoqa.project.configurations.screenshots;

import demoqa.project.configurations.driver.DriverManager;
import demoqa.project.configurations.properties.PropertiesManager;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ScreenshotConfiguration {

    private static String getCurrentDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static void captureScreenshot(Scenario scenario) {

        if (ThreadContext.get("scenarioName") == null) {
            String safeName = scenario.getName().replaceAll(" ", "_");
            ThreadContext.put("scenarioName", safeName);
        }

        //Casts the WebDriver instance to TakesScreenshot so it can capture screenshots from the browser
        TakesScreenshot screenshotTaker = (TakesScreenshot) DriverManager.getDriver();
        String formattedTimestamp = getCurrentDateTime(PropertiesManager.getProperty("TIME_PATTERN"));

        byte[] screenshotBytes = screenshotTaker.getScreenshotAs(OutputType.BYTES);
        String fileName = "Screenshot_" + formattedTimestamp;
        scenario.attach(screenshotBytes, PropertiesManager.getProperty("MEDIA_TYPE"), fileName);
        LogManager.getLogger().info("Screenshot attached to report: {} \n", fileName);

        File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
        File destinationFile = createScreenshotFile(scenario, formattedTimestamp);
        try {
            FileUtils.copyFile(screenshot, destinationFile);
        } catch (IOException e) {
            LogManager.getLogger().error(new ScreenshotException("Error capturing screenshot", e));
        }
    }

    private static File createScreenshotFile(Scenario scenario, String timestamp) {
        String directoryPath = "reports/evidence/" + getCurrentDateTime(PropertiesManager.getProperty("TIME_PATTERN_FOR_FOLDER"));
        File scenarioDirectory = new File(directoryPath, scenario.getName().trim().replaceAll(" ", "_"));
        if (!scenarioDirectory.exists()) {
            if (scenarioDirectory.mkdirs()) {
                LogManager.getLogger().info("Screenshot directory created successfully at: {}", scenarioDirectory.getAbsolutePath());
            }
        }
        String screenshotName = "Screenshot_" + timestamp + ".png";
        return new File(scenarioDirectory, screenshotName);
    }
}
