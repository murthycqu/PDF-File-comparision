package email;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class RequestRejectedByMember_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void RequestRejectedByMember() throws Exception{
		
		Reporter.log("Login to Application with Specific User One");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("member2142@amail.club");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Navigate to Request Page");
		Thread.sleep(3000);
		getWebElement("SideBarInventoryAndRequest").click();
		Thread.sleep(1000);
		getWebElement("SubModuleLabCatalog").click();
		Thread.sleep(5000);
		getWebElement("LabCatalogLineItemReqIcon").click();
		Thread.sleep(3000);
		getWebElement("LabCatalogRequestConfirmation").click();
		
		Reporter.log("Add Product Request and Reject the Product");
		Thread.sleep(10000);
		getWebElement("SideBarInventoryAndRequest").click();
		Thread.sleep(1000);
		getWebElement("SubModuleRequest").click();
		Thread.sleep(8000);
		getWebElement("RequestRejectLineItemLink").click();
		
		Reporter.log("Open Manager Email Inbox and Verify the Product Rejected mail");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys("manager2142");
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Product rejected"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Similarly Open Owner Email Inbox and Verify the Product Rejected mail");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(2));
		driver.get("https://www.mailinator.com/");
		Thread.sleep(5000);
		getWebElement("MailinatorEmailField").sendKeys("devtest2142@mailinator.com");
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(3000);
		driver.switchTo().frame("msg_body");
		
		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("Product rejected"));
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Reporter.log("Move to application Tab");
		Thread.sleep(2000);
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	    	 driver.switchTo().window(tabs1.get(0));
	     }
	   }
		
	    Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
