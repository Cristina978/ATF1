package demoqa.project.ui.pages;

import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import static demoqa.project.configurations.driver.DriverManager.getDriver;


public class RegisterPage extends CommonPage {

    @FindBy(id = "firstname")
    private WebElement firstNameField ;

    @FindBy(id = "lastname")
    private WebElement lastNameField ;


    @FindBy(id = "userName")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    public WebElement getUserNameField(){
        return userNameField;
    }
    public WebElement getPasswordField(){
        return passwordField;
    }



    @FindBy(id = "newUser")
    private WebElement newUserButton;

    @FindBy(id = "register")
    private WebElement registerButton;

//    @FindBy(css = "iframe[src*='recaptcha']")
    @FindBy(id = "rc-anchor-container")
    private static WebElement captchaIframe;

    @FindBy(css = ".recaptcha-checkbox-border")
    private static WebElement captchaCheckbox;

    public RegisterPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void clickRegisterButton() {
        BrowserAction.removeAds();
        browserAction.clickButton(registerButton);
    }

    public void clickNewUserButton() {
        BrowserAction.removeAds();
        browserAction.clickButton(newUserButton);
    }

    public void loginWithCredentials() throws InterruptedException {
        Thread.sleep(3000);
        browserAction.populateField(firstNameField, "test");
        browserAction.populateField(lastNameField, "test");
        browserAction.populateField(getUserNameField(), "user1");
        browserAction.populateField(getPasswordField(), "UserUser@1");
        clickRegisterButton();
    }

    public static void checkCaptchaCheckbox() {
        WaitUtils.waitForElementToBeDisplayed(captchaIframe, PropertiesManager.displayElementTimeout());
        getDriver().switchTo().frame(captchaIframe);
        // Click pe checkbox-ul reCAPTCHA
        WaitUtils.waitForElementToBeDisplayed(captchaCheckbox, PropertiesManager.displayElementTimeout());
        captchaCheckbox.click();
        // Revino la contextul principal
        getDriver().switchTo().defaultContent();
    }


}
