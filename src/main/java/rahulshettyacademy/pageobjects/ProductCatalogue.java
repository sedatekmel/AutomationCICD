package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(css = ".mb-3")
    List<WebElement> products;
    @FindBy(css = ".ngx-spinner-overlay")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.id("toast-container");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("div h5 b"))
                        .getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);// Add to chart butonuna tıklandıktan sonra ekran ortasında çıkan loading iconunun görünmesini bekler.
        waitForElementToDisappear(toastMessage);// Add to chart butonuna tıklandıktan sonra ekran ortasında çıkan loading iconunun kaybolmasını bekler.
        waitForElementToDisappear(spinner);
    }


}
