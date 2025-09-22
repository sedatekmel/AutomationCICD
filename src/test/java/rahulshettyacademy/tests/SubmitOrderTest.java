package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void SubmitOrder() throws IOException {

        String productName = "ADIDAS ORIGINAL";
        String expectedConfirmMessage = "Thankyou for the order.";
        ProductCatalogue productCatalogue = landingPage.login("sedatekmel_91@hotmail.com", "Sedo.7534");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.selectCountry("Turkey");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        Assert.assertTrue(confirmationPage.getConfirmMessage().equalsIgnoreCase(expectedConfirmMessage));
        Assert.assertFalse(confirmationPage.getOrderNumber().isEmpty());
    }
}

