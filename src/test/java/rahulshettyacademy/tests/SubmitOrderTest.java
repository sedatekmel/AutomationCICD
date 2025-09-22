package rahulshettyacademy.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

//new comment
    String productName = "ADIDAS ORIGINAL";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void SubmitOrder(HashMap<String, String> input) throws IOException {

        String expectedConfirmMessage = "Thankyou for the order.";
        ProductCatalogue productCatalogue = landingPage.login(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.selectCountry("Turkey");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        Assert.assertTrue(confirmationPage.getConfirmMessage().equalsIgnoreCase(expectedConfirmMessage));
        Assert.assertFalse(confirmationPage.getOrderNumber().isEmpty());
    }

    @Test(dependsOnMethods = {"SubmitOrder"})
    public void OrderHistoryTest() {
        //ADIDAS ORIGINAL
        ProductCatalogue productCatalogue = landingPage.login("sedatekmel_91@hotmail.com", "Sedo.7534");
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") +
                "//src//test//java//rahulshettyacademy//Data/PurchaseOrder.json");

        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }




    /* Hashmap Yöntem 2*/
    /*@DataProvider
    public Object[][] getData() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "sedatekmel_91@hotmail.com");
        map.put("password", "Sedo.7534");
        map.put("product", "ADIDAS ORIGINAL");

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email", "sedatekmel1@gmail.com");
        map1.put("password", "Kartal-15");
        map1.put("product", "IPHONE 13 PRO");
        return new Object[][]{{map}, {map1}};
    } */

    /* Object Yöntem 1*/
    /*
    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
        {"sedatekmel_91@hotmail.com", "Sedo.7534", "ADIDAS ORIGINAL"},
        {"sedatekmel1@gmail.com", "Kartal-15", "IPHONE 13 PRO"}};
    }
    */

}

