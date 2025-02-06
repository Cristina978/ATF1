package demoqa.project.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:reports/cucumber-report/cucumber-report-html"
        },
        tags = "@API",
        features = {"src/test/resources/features"},
        glue = {"demoqa.project.ui.steps", "demoqa.project.hooks"}
)
public class TestRunner {
}
