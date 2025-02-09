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
        tags = "@UI or @API",
        features = {"src/test/resources/features"},
        glue = {"demoqa.project.ui.steps", "demoqa.project.hooks", "demoqa.project.api.steps"}
)
public class TestRunner {
}
