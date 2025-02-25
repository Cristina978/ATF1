package demoqa.project.ui.pages;

import demoqa.project.ui.commonActions.BrowserAction;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

import static demoqa.project.configurations.driver.DriverManager.getDriver;


public class RegisterPage extends CommonPage {

    @FindBy(id = "firstname")
    private WebElement firstNameField ;

    @FindBy(id = "lastname")
    private WebElement lastNameField ;


    @FindBy(id = "newUser")
    private WebElement newUserButton;

    @FindBy(id = "gotologin")
    private WebElement cancelRegisterButton;


    public void clickCancelRegisterButton(){
        BrowserAction.clickButton(cancelRegisterButton);
        LogManager.getLogger().info("[{}] button is clicked.", cancelRegisterButton );
    }

    public void clickNewUserButton(String buttonName) {
        BrowserAction.clickButton(newUserButton);
    }

    public void loginWithCredentials(Map<String, String> credentials) {
        LogManager.getLogger().info("Attempting to register new user with provided credentials.");
        BrowserAction.populateField(firstNameField, credentials.get("firstname"));
        BrowserAction.populateField(lastNameField, credentials.get("lastname"));
        BrowserAction.populateField(getUserNameField(), credentials.get("username"));
        BrowserAction.populateField(getPasswordField(), credentials.get("password"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", cancelRegisterButton);
        clickCancelRegisterButton();
    }



}
