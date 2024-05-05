package AutomationTesting.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponents {
WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		driver=this.driver;
		PageFactory.initElements(driver, this);		
		// TODO Auto-generated constructor stub
	}
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean verifyOrderDispaly(String productName) {

		Boolean match=productNames.stream().anyMatch(cartProducat->cartProducat.getText().equalsIgnoreCase(productName));
		return match;
	}

}
