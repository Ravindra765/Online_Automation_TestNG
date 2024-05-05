package AutomationTesting.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

import AutomationTesting.PageObjects.ProductCatalouge;
import AutomationTesting.TestCompoents.BaseTest;
import AutomationTesting.TestCompoents.Retry;

public class ErrorValidationsTest extends BaseTest{
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation()throws InterruptedException, IOException {
	String productName="ZARA COAT 3";
	landingPage.loginApplication("test12@gamil.com", "Test@1231");
	
    Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
	
	}
}