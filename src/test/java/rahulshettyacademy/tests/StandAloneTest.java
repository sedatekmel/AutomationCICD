package rahulshettyacademy.tests;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) {

	//new comment

        String productName = "ADIDAS ORIGINAL";

        WebDriverManager.chromedriver().setup(); //setProperty ile de driver pathi verilebilirdi.
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id("userEmail")).sendKeys("sedatekmel_91@hotmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Sedo.7534");
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("div h5 b")).getText().equals(productName)).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
        //ng-animating
        // Add to chart butonuna tıklandıktan sonra ekran ortasında çıkan loading iconunun kaybolmasını bekler.
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));

        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();


        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "Tur").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
        WebElement selectedCountry = countries.stream().filter(country -> country.findElement(By.tagName("span")).getText().equals("Turkey")).findFirst().orElse(null);
        selectedCountry.click();

        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        String orderNumber = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();

        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        Assert.assertFalse(orderNumber.isEmpty());
        System.out.println(driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText().split("\\|")[1].trim());
        driver.close();
    }
}