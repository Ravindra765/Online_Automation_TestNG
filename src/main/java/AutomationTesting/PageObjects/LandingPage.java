package AutomationTesting.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
WebDriver driver;
public LandingPage(WebDriver driver){
	//initalization
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
//WebElement userEmail=driver.findElement(By.id("userEmail"));
@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement passwordEle;

@FindBy(id="login")
WebElement submit;

@FindBy(css="[class*='flyInOut']")
WebElement errorMessage;


public ProductCatalouge loginApplication(String email,String password) {
	userEmail.sendKeys(email);
	passwordEle.sendKeys(password);
	submit.click();
	ProductCatalouge productCatalouge=new ProductCatalouge(driver);
	return productCatalouge;
}
public String getErrorMessage() {
	waitForWebElementToAppear(errorMessage);
	return errorMessage.getText();
}
public void launchBrowser() {
	driver.get("https://rahulshettyacademy.com/client");
}
}
