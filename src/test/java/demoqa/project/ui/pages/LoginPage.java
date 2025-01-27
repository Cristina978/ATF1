package demoqa.project.ui.pages;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Map;



public class LoginPage extends CommonPage {

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id= "name")
    private WebElement errorLabel;


    public String getErrorLabel() {
        WaitUtils.waitForElementToBeDisplayed(errorLabel, PropertiesManager.displayElementTimeout());
        if (errorLabel != null && errorLabel.isDisplayed()) {
            return errorLabel.getText();
        } else {
            LogManager.getLogger().error("Error label is not displayed or is null.");
            throw new RuntimeException("Error label is not displayed.");
        }
    }

    public void clickLoginButton() {
        browserAction.clickButton(loginButton);
    }

    public void loginWithCredentials(Map<String, String> credentials) {
        browserAction.populateField(getUserNameField(), credentials.get("userName"));
        browserAction.populateField(getPasswordField(), credentials.get("password"));
        clickLoginButton();
    }

}
