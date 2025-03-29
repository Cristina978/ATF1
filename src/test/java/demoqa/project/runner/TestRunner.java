package demoqa.project.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber-report.json",
                "html:target/cucumber-report/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@UI or @API",
        features = {"src/test/resources/features"},
        glue = {"demoqa.project.ui.steps", "demoqa.project.hooks", "demoqa.project.api.steps"}
)
public class TestRunner {
}
