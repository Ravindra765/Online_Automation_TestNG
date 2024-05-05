package AutomationTesting.tests;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
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



public class SubmitOrderTest3_HashMap extends BaseTest{
//String productName="ZARA COAT 3";
@Test(dataProvider="getData",groups= {"Purchase"})
public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

ProductCatalouge productCatalouge=landingPage.loginApplication(input.get("email"), input.get("pwd"));

List<WebElement> products=productCatalouge.getProductList();
productCatalouge.addToCart(input.get("productName"));
CartPage cartPage=productCatalouge.goToCartPage();

Boolean mach=cartPage.verifyProductDispaly(input.get("productName"));
Assert.assertTrue(mach);
ChechOutPage checkoutPage=cartPage.goToCheckout();
checkoutPage.selectCountry("india");
ConfirmationPage confirmationPage=checkoutPage.submitOrder();
Thread.sleep(1000);
String confirmMessage=confirmationPage.getConfirmationMessage();
Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
}

@DataProvider
public Object[][] getData() throws IOException{
	List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//AutomationTesting//data//PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
}
}
/*HashMap<String,String> map=new HashMap<String,String>();
map.put("email", "test12@gamil.com");
map.put("pwd", "Test@123");
map.put("productName", "ZARA COAT 3");

HashMap<String,String> map2=new HashMap<String,String>();
map2.put("email", "test12@gamil.com");
map2.put("pwd", "Test@123");
map2.put("productName", "ADIDAS ORIGINAL");*/
