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
        tags = "@UI or @API or @Negative",
        features = {"src/test/resources/features"},
        glue = {"demoqa.project.ui.stepdefinitions", "demoqa.project.hooks", "demoqa.project.api.stepdefinitions"}
)
public class TestRunner {
}
