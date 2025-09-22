package rahulshettyacademy.pageobjects;

import com.sun.jna.platform.win32.Wevtapi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = ".form-group input")
    WebElement placeHolderCountry;

    @FindBy(css = ".ta-results button")
    List<WebElement> countriesList;

    By countriesListBox = By.cssSelector(".ta-results button");

    @FindBy(css = ".action__submit")
    WebElement submit;

    String countryInputText = "Turkey";

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private List<WebElement> getCountriesList(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(placeHolderCountry, countryName).build().perform();
        waitForElementToAppear(countriesListBox);
        return countriesList;
    }

    private WebElement getCountry(String countryName) {
        WebElement selectedCountry = getCountriesList(countryName).stream()
                .filter(country -> country.findElement(By.tagName("span"))
                        .getText().equals(countryInputText)).findFirst().orElse(null);
        return selectedCountry;
    }

    public void selectCountry(String countryName) {
        WebElement ele = getCountry(countryName);
        ele.click();
    }

    public ConfirmationPage submitOrder () {
        submit.click();
        return new ConfirmationPage(driver);
    }
}
