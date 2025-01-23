package demoqa.project.ui.pages;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Map;
import static demoqa.project.configurations.driver.DriverManager.getDriver;


public class LoginPage extends CommonPage {
    
      WebElement newUserButton = getDriver().findElement(By.xpath("//button[@id='newUser']"));

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id= "name")
    private WebElement errorLabel;

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
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
        BrowserAction.removeAds();
        browserAction.clickButton(loginButton);
    }

    public void loginWithCredentials(Map<String, String> credentials) throws InterruptedException {
        Thread.sleep(3000);
        browserAction.populateField(getUserNameField(), credentials.get("userName"));
        browserAction.populateField(getPasswordField(), credentials.get("password"));
        clickLoginButton();
    }

}
