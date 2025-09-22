package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = ".hero-primary")
    WebElement confirmMessage;

    @FindBy(css="label[class='ng-star-inserted']")
    WebElement orderNumber;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getConfirmMessage() {
        return confirmMessage.getText();
    }
    public String getOrderNumber() {
        return orderNumber.getText();
    }
}
