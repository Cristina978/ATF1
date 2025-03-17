package demoqa.project.ui.steps;


import demoqa.project.ui.pages.AddUserPage;
import demoqa.project.ui.pages.CommonPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static demoqa.project.ui.commonActions.BrowserAction.clickButton;


public class AddUserSteps extends AddUserPage {
    CommonPage commonPage = new CommonPage();

    @When("User navigates to {string} page")
    public void redirectUserOnWebTablesPage(String urlName) {
        commonPage.navigateToURL("WEBTABLES");
        commonPage.validatePageURL(urlName);
        verifyElementsOnWebTablePage();
    }

    @And("User clicks on Add button")
    public void clickAddUserButton() {
        clickButton(getAddRecordButton());
        verifyElementsOnRegistrationForm();
    }

    @And("User complete the registration form")
    public void completeAddUserForm() {
        completeAddNewUserForm();
    }

    @Then("New user is successful added in table")
    public void newUserIsSuccessfulAdded() {
        addNewUserInWebTable();
    }
}
