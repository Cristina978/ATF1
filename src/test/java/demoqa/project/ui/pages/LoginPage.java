package demoqa.project.ui.pages;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.enums.LoginFields;
import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class LoginPage extends CommonPage {

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id= "name")
    private WebElement errorLabel;

    @FindBy(css = ".is-invalid")
    private List<WebElement> invalidFields;

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
            LogManager.getLogger().info("Field highlighted as invalid: {}", fieldIdentifier);
        }
    }

    public List<WebElement> getInvalidFields() {
        return invalidFields;
    }


}
