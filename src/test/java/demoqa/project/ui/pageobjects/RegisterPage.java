package demoqa.project.ui.pageobjects;

import demoqa.project.enums.LoginFields;
import demoqa.project.ui.commonActions.BrowserAction;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Map;


public class RegisterPage extends CommonPage {

    @FindBy(id = "firstname")
    private WebElement firstNameField ;

    @FindBy(id = "lastname")
    private WebElement lastNameField ;

    @FindBy(id = "gotologin")
    private WebElement backToLoginButton;

    public void clickCancelRegisterButton(){
        BrowserAction.clickButton(backToLoginButton);
    }

    public void clickNewUserButton(String buttonName) {
        BrowserAction.clickButton(loginPage.getNewUserButton());
    }

    public void loginWithCredentials(Map<String, String> credentials) {
        LogManager.getLogger().info("Attempting to register new user with provided credentials.");
        BrowserAction.populateField(firstNameField, credentials.get(LoginFields.FIRSTNAME.getFieldName()));
        BrowserAction.populateField(lastNameField, credentials.get(LoginFields.LASTNAME.getFieldName()));
        BrowserAction.populateField(getUserNameField(), credentials.get(LoginFields.USERNAME.getFieldName()));
        BrowserAction.populateField(getPasswordField(), credentials.get(LoginFields.PASSWORD.getFieldName()));
    }
}
