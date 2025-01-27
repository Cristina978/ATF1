package demoqa.project.ui.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
        browserAction.clickButton(cancelRegisterButton);
    }

    public void clickNewUserButton() {
        browserAction.clickButton(newUserButton);
    }

    public void loginWithCredentials() {
        browserAction.populateField(firstNameField, "test");
        browserAction.populateField(lastNameField, "test");
        browserAction.populateField(getUserNameField(), "user1");
        browserAction.populateField(getPasswordField(), "UserUser@1");
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", cancelRegisterButton);
        clickCancelRegisterButton();
    }



}
