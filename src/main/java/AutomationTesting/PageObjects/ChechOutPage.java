package AutomationTesting.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationTesting.AbstractComponents.AbstractComponents;

public class ChechOutPage extends AbstractComponents {
WebDriver driver;
public ChechOutPage(WebDriver driver){
	//initalization
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
//WebElement userEmail=driver.findElement(By.id("userEmail"));
@FindBy(css="[placeholder='Select Country']")
WebElement country;

@FindBy(css=".ta-item:nth-of-type(2)")
WebElement SelectCounryName;

@FindBy(css=".action__submit")
WebElement placeOrder;

@FindBy
By results=By.cssSelector(".ta-results");

public void selectCountry(String countryName) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(7));
	Actions action=new Actions(driver);
	action.sendKeys(country,countryName).build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	SelectCounryName.click();
}
public ConfirmationPage submitOrder() throws InterruptedException {
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("arguments[0].scrollIntoView()", placeOrder);
	Thread.sleep(2000);
	placeOrder.click();
	return new ConfirmationPage(driver);
}




}
