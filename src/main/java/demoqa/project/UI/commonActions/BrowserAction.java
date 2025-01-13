package demoqa.project.UI.commonActions;

import demoqa.project.configurations.properties.PropertiesManager;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import demoqa.project.utils.WaitUtils;

public class BrowserAction {

    public void clickButton(WebElement button) {
        WaitUtils.waitForElementToBeClickable(button, PropertiesManager.displayElementTimeout());
        String buttonName= button.getText();
        button.click();
        LogManager.getLogger().info("The [{}] button is clicked.",buttonName);
    }
}
