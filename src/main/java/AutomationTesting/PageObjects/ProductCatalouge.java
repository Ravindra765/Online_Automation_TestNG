package AutomationTesting.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AutomationTesting.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents {
WebDriver driver;
public ProductCatalouge(WebDriver driver){
	//initalization
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
//WebElement userEmail=driver.findElement(By.id("userEmail"));
@FindBy(css=".mb-3")
List<WebElement> products;

@FindBy(css=".ng-animating")
WebElement spinner;


By producyBy=By.cssSelector(".mb-3");
By addToCart=By.cssSelector(".card-body button:last-of-type");
By toastMessage=By.cssSelector("#toast-container");

public List<WebElement> getProductList() {
	waitForElementToAppear(producyBy);
	return products;
}
public WebElement getProductByName(String productName) {
	
	WebElement prod=getProductList().stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    return prod;
}

public void addToCart(String productName) throws InterruptedException {
	WebElement prod=getProductByName(productName);
	prod.findElement(addToCart).click();
	waitForElementToAppear(toastMessage);
	waitForElementToDisapper(spinner);
	
}



}
