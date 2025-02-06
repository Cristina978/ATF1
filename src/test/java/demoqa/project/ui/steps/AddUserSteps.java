package demoqa.project.ui.steps;

import demoqa.project.ui.pages.AddUserPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AddUserSteps {
    AddUserPage addUserPage = new AddUserPage();


    @Given("user navigates to {string} page")
    public void userIsOnWebTablesPage(String urlName) {
        addUserPage.navigateToURL("WEBTABLES_URL");
        addUserPage.validatePageURL(urlName);
    }

    @When("user complete the form")
    public void userCompleteTheForm() throws InterruptedException {
        addUserPage.completeAddNewUserForm();
    }

    @Then("new user is successful added")
    public void newUserIsSuccessfulAdded() throws InterruptedException {
        addUserPage.addNewUserInWebTable();
    }
}
