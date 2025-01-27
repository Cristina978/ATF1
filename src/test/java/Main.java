import demoqa.project.ui.pages.CommonPage;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;

import static demoqa.project.configurations.driver.DriverManager.*;


public class Main extends CommonPage {

    @FindBy(xpath = "//div[@class='menu-list']//a[text()='Elements']")
    public static WebElement elements;

    //*[@id="app"]/div/div/div/div[1]/div/div/div[1]/span/div/div[1]
    static Main object = new Main();
    public static void main(String[] args) {



        // 1. Configurarea Driver Manager
        //WebDriverManager.chromedriver().setup(); // Automatizează descărcarea și configurarea ChromeDriver

        // 2. Configurarea opțiunilor pentru browser
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*"); // Rezolvă erori legate de origini încrucișate

        // 3. Crearea instanței WebDriver
        //WebDriver driver = new ChromeDriver(options);
        getDriver();
        try {
            // 4. Accesarea site-ului demoqa
            //getDriver().get("https://demoqa.com/register");
            openBasePage();

            // 5. Maximizarea ferestrei browserului (opțional)
            //driver.manage().window().maximize();
            MaximizeBrowser();
            Thread.sleep(5000);
            //JavascriptExecutor js = (JavascriptExecutor) getDriver();
            //js.executeScript("arguments[0].setAttribute('class', 'element-list collapse show');", elements);
            object.navigateToURL("WEBTABLES_URL");
        } catch (Exception e) {
            e.printStackTrace(); // Afișează detalii despre eroare dacă apare una
        } finally {
            // 10. Închiderea browserului
            //driver.quit();
        }
    }
}
