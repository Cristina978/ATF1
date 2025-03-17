package demoqa.project.ui.pages;

import demoqa.project.configurations.driver.DriverManager;
import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import demoqa.project.utils.FakeDataGenerator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class AddUserPage {

    @FindBy(xpath = "//h1[text()='Web Tables']")
    private WebElement headerText;

    @FindBy(id = "addNewRecordButton")
    private WebElement addRecordButton;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='userEmail']")
    private WebElement userEmail;

    @FindBy(xpath = "//input[@id='age']")
    private WebElement age;

    @FindBy(xpath = "//input[@id='salary']")
    private WebElement salary;

    @FindBy(xpath = "//input[@id='department']")
    private WebElement department;

    @FindBy(xpath = ".//div[@class='rt-tr-group']/div[@role='row'][not(contains(@class, '-padRow'))]")
    private List<WebElement> tableRows;


    public WebElement getAddRecordButton() {
        return addRecordButton;
    }

    public AddUserPage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void verifyElementsOnWebTablePage() {
        LogManager.getLogger().info("Verifying that all elements on the Web Table page are displayed.");
        List<WebElement> elementsOnLoginPage = List.of(
                headerText,
                addRecordButton
        );
        for (WebElement element : elementsOnLoginPage) {
            Assert.assertTrue("Element " + element.getAttribute("id") + " is not displayed!", element.isDisplayed());
            LogManager.getLogger().debug("Element '{}' is displayed.", element.getAttribute("id"));
        }
        LogManager.getLogger().info("All elements on the Web Table page are displayed correctly.");
    }

    public void verifyElementsOnRegistrationForm() {
        LogManager.getLogger().info("Verifying that all elements on the Registration Form are displayed.");
        WaitUtils.waitForElementToBeDisplayed(submitButton, PropertiesManager.displayElementTimeout());
        List<WebElement> elementsOnLoginPage = List.of(
                firstName,
                lastName,
                userEmail,
                submitButton
        );
        for (WebElement element : elementsOnLoginPage) {
            Assert.assertTrue("Element " + element.getAttribute("id") + " is not displayed!", element.isDisplayed());
            LogManager.getLogger().debug("Element '{}' is displayed correctly.", element.getAttribute("id"));
        }
        LogManager.getLogger().info("All elements on the Registration Form are displayed correctly.");
    }

    public void completeAddNewUserForm() {
        LogManager.getLogger().info("'Registration Form' is displayed");
        BrowserAction.populateField(firstName, FakeDataGenerator.generateRandomFirstName());
        BrowserAction.populateField(lastName, FakeDataGenerator.generateRandomLastName());
        BrowserAction.populateField(userEmail, FakeDataGenerator.generateRandomEmail());
        BrowserAction.populateField(age, FakeDataGenerator.generateRandomAge());
        BrowserAction.populateField(salary, FakeDataGenerator.generateRandomSalary());
        BrowserAction.populateField(department, FakeDataGenerator.generateRandomDepartment());
    }

    public void addNewUserInWebTable() {
        BrowserAction.clickButton(submitButton);
        checkNewUserIsAdded();
    }

    public void checkNewUserIsAdded() {
        // Obține ultimul rând valid
        WebElement lastRow = tableRows.getLast();

        // Extrage datele din ultimul rând
        List<WebElement> lastRecord = lastRow.findElements(By.className("rt-td"));

        String rowContent = "The added record in Web Tables is: ";
        for (WebElement cell : lastRecord) {
            rowContent += cell.getText() + " | ";
        }
        LogManager.getLogger().info(rowContent);
    }
}
