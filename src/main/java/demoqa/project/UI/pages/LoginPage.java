package demoqa.project.UI.pages;

import demoqa.project.UI.commonActions.BrowserAction;
import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;



public class LoginPage extends CommonPage {

    @FindBy(id = "newUser")
    private WebElement newUserButton;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "name")
    private WebElement errorLabel;

    public void clickLoginButton() {
        BrowserAction.clickButton(loginButton);
    }

    public void clickNewUserButton() {
        browserAction.clickButton(newUserButton);
    }

    public void loginWithCredentials(Map<String, String> credentials) {
        browserAction.populateField(getUserNameField(), credentials.get("UserName"));
        browserAction.populateField(getPasswordField(), credentials.get("Password"));
        clickLoginButton();
    }

    public WebElement getErrorLabel() {
        WaitUtils.waitForElementToBeDisplayed(errorLabel, PropertiesManager.displayElementTimeout());
        return errorLabel;
    }
}
