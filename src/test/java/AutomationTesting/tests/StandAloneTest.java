package AutomationTesting.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {

public static void main(String[] args) throws InterruptedException {
WebDriver driver;
String productName="ZARA COAT 3";
WebDriverManager.chromedriver().setup();
ChromeOptions option = new ChromeOptions();
option.addArguments("--remote-allow-origins=*");
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver(option);
driver.manage().window().maximize();
driver.get("https://rahulshettyacademy.com/client");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//LandingPage load=new LandingPage(driver);
driver.findElement(By.id("userEmail")).sendKeys("test12@gamil.com");
driver.findElement(By.id("userPassword")).sendKeys("Test@123");
driver.findElement(By.id("login")).click();
WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(7));
wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
WebElement prod=products.stream().filter(product->
product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
List<WebElement> cartProducats=driver.findElements(By.cssSelector(".cartSection h3"));
Boolean match=cartProducats.stream().anyMatch(cartProducat->cartProducat.getText().equalsIgnoreCase(productName));
Assert.assertTrue(match);
driver.findElement(By.cssSelector(".totalRow button")).click();
Actions action=new Actions(driver);
action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();

wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item:nth-of-type(2)")));
//WebElement we=driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));
//action.moveToElement(we).perform();
driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".action__submit")));

WebElement placeOrder=driver.findElement(By.cssSelector(".action__submit"));
JavascriptExecutor jse = (JavascriptExecutor)driver;
jse.executeScript("arguments[0].scrollIntoView()", placeOrder); 
Thread.sleep(2000);
placeOrder.click();
Thread.sleep(1000);
String conformationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
Assert.assertTrue(conformationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
driver.close();
}
}
