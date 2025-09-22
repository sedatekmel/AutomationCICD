package rahulshettyacademy.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void landed_on_Ecommerce_Page() throws IOException {
        //code
        landingPage = launchApplication();
    }

    //Regex tanımlamadır. Dinamik parametre alma durumunda aşağıdaki gibi yazılır.
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_email_password(String username, String password) {
        productCatalogue = landingPage.login(username, password);
    }

    @When("^When I add product (.+) to Cart$")
    public void add_product_to_chart(String productName) {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_orders(String productName) {
        cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Turkey");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_on_confirmationPage(String string) {
        Assert.assertTrue(confirmationPage.getConfirmMessage().equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String string) {
        Assert.assertEquals(landingPage.getErrorMessage(), string);
        driver.close();
    }
}
