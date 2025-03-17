package demoqa.project.ui.steps;

import demoqa.project.ui.pages.RegisterPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import java.util.Map;


public class RegisterSteps {
    RegisterPage registerPage = new RegisterPage();

    @When("User clicks on {string} button")
    public void clickNewUserButton(String buttonName) {
        registerPage.clickNewUserButton(buttonName);
    }

    @And("User fill in the registration form")
    public void completeTheRegistrationForm(DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMap(String.class, String.class);
        registerPage.loginWithCredentials(credentials);
    }

    @And("User cancel the registration form")
    public void registerWithValidData () {
        registerPage.clickCancelRegisterButton();
    }
}
