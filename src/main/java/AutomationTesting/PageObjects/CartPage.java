package AutomationTesting.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
WebDriver driver;
public CartPage(WebDriver driver){
	//initalization
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
//WebElement userEmail=driver.findElement(By.id("userEmail"));
@FindBy(css=".totalRow button")
WebElement checkoutEle;

@FindBy(css=".cartSection h3")
List<WebElement> cartProducts;

@FindBy(id="login")
WebElement submit;

public Boolean verifyProductDispaly(String productName) {

	Boolean match=cartProducts.stream().anyMatch(cartProducat->cartProducat.getText().equalsIgnoreCase(productName));
	return match;
}
public ChechOutPage goToCheckout() {
	checkoutEle.click();
	return new ChechOutPage(driver);
}


}
