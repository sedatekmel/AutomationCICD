package rahulshettyacademy.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        //properties class

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            //Chrome
            WebDriverManager.chromedriver().setup(); //setProperty ile de driver pathi verilebilirdi.
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            //Firefox
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            //Edge
            System.setProperty("webdriver.edge.driver", "edge.exe");
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goToLandingPage();
        return landingPage;
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
