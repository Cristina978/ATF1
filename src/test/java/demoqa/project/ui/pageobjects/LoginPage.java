package demoqa.project.ui.pageobjects;

import demoqa.project.configurations.driver.DriverManager;
import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.enums.LoginFields;
import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Map;
import static demoqa.project.ui.pageobjects.CommonPage.getPasswordField;
import static demoqa.project.ui.pageobjects.CommonPage.getUserNameField;


public class LoginPage {

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "newUser")
    private WebElement newUserButton;

    @FindBy(id= "name")
    private WebElement errorLabel;

    @FindBy(css = ".is-invalid")
    private List<WebElement> invalidFields;

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getNewUserButton() {
        return newUserButton;
    }

    public LoginPage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

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
        BrowserAction.clickButton(loginButton);
    }

    public void loginWithCredentials(Map<String, String> credentials) {
        LogManager.getLogger().info("Attempting to log in with provided credentials.");
        BrowserAction.populateField(getUserNameField(), credentials.get(LoginFields.USERNAME.getFieldName()));
        BrowserAction.populateField(getPasswordField(), credentials.get(LoginFields.PASSWORD.getFieldName()));
        WaitUtils.waitForElementToBeDisplayed(loginButton, PropertiesManager.displayElementTimeout());
        clickLoginButton();
    }

    public void verifyFieldValidation(){
        LogManager.getLogger().info("Verifying fields are highlighted in red.");

        List<WebElement> invalidFields = getInvalidFields();
        Assert.assertFalse("No fields are highlighted in red, validation failed.", invalidFields.isEmpty());

        for (WebElement field : invalidFields) {
            Assert.assertTrue("Field is not highlighted as invalid.",
                    field.getAttribute("class").contains("is-invalid"));
            String fieldIdentifier = field.getAttribute("placeholder");
            LogManager.getLogger().info("Validation failed: '{}' field is highlighted in red.", fieldIdentifier);
        }
    }

    public List<WebElement> getInvalidFields() {
        return invalidFields;
    }
}
