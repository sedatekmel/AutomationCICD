package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent  {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactoryæ
    @FindBy(id = "userEmail") WebElement userEmail;
    @FindBy(id = "userPassword") WebElement userPassword;
    @FindBy(id = "login") WebElement submitBtn;
    @FindBy(css = "[class*='bottom-right']") WebElement errorMessage;



    public ProductCatalogue login(String username,String password)
    {
        userEmail.sendKeys(username);
        userPassword.sendKeys(password);
        submitBtn.click();
        return new ProductCatalogue(driver);  // SubmitOrderTest classında sürekli yeni object oluşturulmasını engellemek için returnde object döndürüldü.
    }

    public void goToLandingPage()
    {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getErrorMessage()
    {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }



}
