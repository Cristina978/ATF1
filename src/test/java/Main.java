import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static demoqa.project.configurations.driver.DriverManager.*;


public class Main {

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
            //driver.get("https://demoqa.com/login");
            openBasePage();

            // 5. Maximizarea ferestrei browserului (opțional)
            //driver.manage().window().maximize();
            MaximizeBrowser();

            // 6. Localizarea câmpului "Username" și introducerea textului
            WebElement usernameField = getDriver().findElement(By.xpath("//input[@id='userName']"));
            usernameField.sendKeys("TestUser");

            // 7. Localizarea câmpului "Password" și introducerea textului
            WebElement passwordField = getDriver().findElement(By.xpath("//input[@id='password']"));
            passwordField.sendKeys("TestPassword");

            // 8. Localizarea butonului "Login" și clic pe el
            WebElement loginButton = getDriver().findElement(By.xpath("//button[@id='login']"));
            loginButton.click();

            // 9. Afișarea unui mesaj pentru confirmarea rulării
            LogManager.getLogger().info("Login page is displayed");
            //System.out.println("Testul s-a rulat cu succes!");

        } catch (Exception e) {
            e.printStackTrace(); // Afișează detalii despre eroare dacă apare una
        } finally {
            // 10. Închiderea browserului
            //driver.quit();
        }
    }
}
