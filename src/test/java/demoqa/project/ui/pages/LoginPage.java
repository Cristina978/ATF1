package demoqa.project.ui.pages;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Map;


import static demoqa.project.configurations.driver.DriverManager.getDriver;


public class LoginPage extends CommonPage {
    
      WebElement newUserButton = getDriver().findElement(By.xpath("//button[@id='newUser']"));

      WebElement loginButton = getDriver().findElement(By.xpath("//button[@id='login']"));

//    @FindBy(xpath = "button[id='login']")
//    private WebElement loginButton;

    @FindBy(id="name")
    private WebElement errorLabel;

    public WebElement getErrorLabel() throws InterruptedException {
        Thread.sleep(5000);
        WaitUtils.waitForElementToBeDisplayed(errorLabel, PropertiesManager.displayElementTimeout());
        return errorLabel;
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
