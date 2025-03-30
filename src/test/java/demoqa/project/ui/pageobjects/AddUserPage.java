package demoqa.project.ui.pageobjects;

import demoqa.project.configurations.driver.DriverManager;
import demoqa.project.configurations.properties.PropertiesManager;
import demoqa.project.ui.commonActions.BrowserAction;
import demoqa.project.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import demoqa.project.utils.FakeDataGenerator;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


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
            if (element.isDisplayed()) {
                LogManager.getLogger().debug("Element '{}' is displayed.", element.getAttribute("id"));
            } else {
                LogManager.getLogger().debug("Element '{}' is not displayed.", element.getAttribute("id"));
            }
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
            if (element.isDisplayed()) {
                LogManager.getLogger().debug("Element '{}' is displayed correctly.", element.getAttribute("id"));
            } else {
                LogManager.getLogger().debug("Element '{}' is not displayed correctly.", element.getAttribute("id"));
            }
        }
        LogManager.getLogger().info("All elements on the Registration Form are displayed correctly.");
    }

    private String generatedEmail;
    public void completeAddNewUserForm() {
        LogManager.getLogger().info("'Registration Form' is displayed");
        BrowserAction.populateField(firstName, FakeDataGenerator.generateRandomFirstName());
        BrowserAction.populateField(lastName, FakeDataGenerator.generateRandomLastName());
        generatedEmail = FakeDataGenerator.generateRandomEmail();
        BrowserAction.populateField(userEmail, generatedEmail);
        BrowserAction.populateField(age, FakeDataGenerator.generateRandomAge());
        BrowserAction.populateField(salary, FakeDataGenerator.generateRandomSalary());
        BrowserAction.populateField(department, FakeDataGenerator.generateRandomDepartment());
    }

    public void addNewUserInWebTable() {
        BrowserAction.clickButton(submitButton);
        checkNewUserIsAdded();
    }

    public void checkNewUserIsAdded() {
        WebElement lastRow = tableRows.getLast();

        List<WebElement> lastRecord = lastRow.findElements(By.className("rt-td"));

        StringBuilder rowContent = new StringBuilder("The added record in Web Tables is: ");
        for (WebElement cell : lastRecord) {
            rowContent.append(cell.getText()).append(" | ");
        }

        boolean containsEmail = lastRecord.stream()
                .anyMatch(cell -> cell.getText().equals(generatedEmail));
        assertThat("The record was not added in the Web Tables", containsEmail, is(true));

        LogManager.getLogger().info(rowContent.toString());
    }
}
