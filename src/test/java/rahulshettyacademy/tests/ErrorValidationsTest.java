package rahulshettyacademy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test
    public void LoginErrorValidation() throws IOException {
        landingPage.login("sedatekmel_91x@hotmail.com", "Sedo.7534");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ADIDAS ORIGINAL";
        String expectedConfirmMessage = "Thankyou for the order.";
        ProductCatalogue productCatalogue = landingPage.login("sedatekmel_91@hotmail.com", "Sedo.7534");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINAL 2");
        Assert.assertFalse(match);
    }
}

