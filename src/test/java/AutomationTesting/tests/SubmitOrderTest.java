package AutomationTesting.tests;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationTesting.AbstractComponents.OrderPage;
import AutomationTesting.PageObjects.CartPage;
import AutomationTesting.PageObjects.ChechOutPage;
import AutomationTesting.PageObjects.ConfirmationPage;
import AutomationTesting.PageObjects.LandingPage;
import AutomationTesting.PageObjects.ProductCatalouge;
import AutomationTesting.TestCompoents.BaseTest;



public class SubmitOrderTest extends BaseTest{
String productName="ZARA COAT 3";
@Test
public void submitOrder() throws InterruptedException, IOException {

ProductCatalouge productCatalouge=landingPage.loginApplication("test12@gamil.com", "Test@123");

List<WebElement> products=productCatalouge.getProductList();
productCatalouge.addToCart(productName);
CartPage cartPage=productCatalouge.goToCartPage();

Boolean mach=cartPage.verifyProductDispaly(productName);
Assert.assertTrue(mach);
ChechOutPage checkoutPage=cartPage.goToCheckout();
checkoutPage.selectCountry("india");
ConfirmationPage confirmationPage=checkoutPage.submitOrder();
Thread.sleep(1000);
String confirmMessage=confirmationPage.getConfirmationMessage();
Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
}
@Test(dependsOnMethods= {"submitOrder"})
public void orderHistory() {
	ProductCatalouge productCatalouge=landingPage.loginApplication("test12@gamil.com", "Test@123");
	OrderPage ordersPage=productCatalouge.goToOrderPage();
	Assert.assertTrue(ordersPage.verifyOrderDispaly(productName));

}
@DataProvider
public Object[][] getData(){
	return new Object[][] {{"test12@gamil.com","Test@123","ZARA COAT 3"},{"test12@gamil.com","Test@12","ADIDAS ORIGINAL"}};
}


}
