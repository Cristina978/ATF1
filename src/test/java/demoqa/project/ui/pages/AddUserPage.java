package demoqa.project.ui.pages;

import demoqa.project.ui.commonActions.BrowserAction;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import demoqa.project.utils.FakeDataGenerator;
import org.openqa.selenium.NoSuchElementException;


public class AddUserPage extends CommonPage{

    @FindBy(id = "addNewRecordButton")
    private WebElement addRecordButton;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@id = 'firstName']")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "age")
    private WebElement age;

    @FindBy(id = "salary")
    private WebElement salary;

    @FindBy(id = "department")
    private WebElement department;



    public void completeAddNewUserForm() throws InterruptedException {
        BrowserAction.clickButton(addRecordButton);
        Thread.sleep(2000);
        LogManager.getLogger().info("Add New User form is displayed");
        BrowserAction.populateField(firstName, FakeDataGenerator.generateRandomFirstName());
        BrowserAction.populateField(lastName, FakeDataGenerator.generateRandomLastName());
        BrowserAction.populateField(userEmail, FakeDataGenerator.generateRandomEmail());
        BrowserAction.populateField(age, FakeDataGenerator.generateRandomAge());
        BrowserAction.populateField(salary, FakeDataGenerator.generateRandomSalary());
        BrowserAction.populateField(department, FakeDataGenerator.generateRandomDepartment());
        Thread.sleep(3000);
    }


    public void checkNewUserIsAdded(WebElement element) {
        try {
            if (element.isDisplayed()) {
                Assert.fail("User was not added to the Web Table");
            }
        } catch (NoSuchElementException e) {
            LogManager.getLogger().info("New User is added to Web Table.");
        }
    }

    public void addNewUserInWebTable() throws InterruptedException {
        BrowserAction.clickButton(submitButton);
        Thread.sleep(2000);
        checkNewUserIsAdded(submitButton);
        //Thread.sleep(10000);
    }

}
