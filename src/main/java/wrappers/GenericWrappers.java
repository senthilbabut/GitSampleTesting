package wrappers;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericWrappers implements Wrappers {
	WebDriverWait wait;
	RemoteWebDriver driver;
	int i=1;

	public void invokeApp(String browser, String url)  {		
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "D:/Test Leaf Softwares/Drivers/chromedriver_win32/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "D:/Test Leaf Softwares/Drivers/chromedriver_win32/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 30);
			System.out.println("The Browser " + browser + " launched");			
		}catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
	}

	//Enter By Id
	public void enterById(String idValue, String data){		
		try {
			WebElement expWaitCond = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idValue)));
			expWaitCond.clear();
			expWaitCond.sendKeys(data);
			/*driver.findElementById(idValue).clear();
			driver.findElementById(idValue).sendKeys(data);*/
			System.out.println("INFO: " + "The Text Field "+ idValue +" is entered with data "+ data );
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element id " + idValue + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
	}

	//Enter By Name
	public void enterByName(String nameValue, String data)  {
		try {
			WebElement expWaitCond = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(nameValue)));
			expWaitCond.clear();
			expWaitCond.sendKeys(data);		
			/*driver.findElementByName(nameValue).clear();
			driver.findElementByName(nameValue).sendKeys(data);*/
			System.out.println("INFO: " + "The Text Field "+ nameValue +" is entered with data "+ data );
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element " + nameValue + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
	}

	//Enter By Xpath
	public void enterByXpath(String xpathValue, String data)  {
		try {
			WebElement expWaitCond = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathValue)));
			expWaitCond.clear();
			expWaitCond.sendKeys(data);			
			/*driver.findElementByXPath(xpathValue).clear();
			driver.findElementByXPath(xpathValue).sendKeys(data);*/
			System.out.println("INFO: " + "The Text Field "+ xpathValue +" is entered with data "+ data );
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element: " + xpathValue + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}	
	}

	//Verify Title
	public boolean verifyTitle(String title) {	
		boolean bReturn=false;
		try{
			if(driver.getTitle().equals(title)){
				System.out.println("INFO: " + "Title is matched");
				bReturn= true;
			} else {
				System.err.println("WARNING: " + "The title does not match" );
			} 
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}	
		return bReturn;
	}

	//Verify Text By Id
	public void verifyTextById(String id, String text)
	{
		try {			
			//String strText = driver.findElementById(id).getText();
			String strText = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id))).getText();
			if (strText.equals(text)){
				System.out.println("INFO: " + "The given text " + text + " is matched");	
			}  else {
				System.err.println("WARNING: " + "The given text " + text + " does not match");
			}
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with ID: " + id + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
	}

	//Verify Text By Xpath
	public void verifyTextByXpath(String xpath, String text) {
		try {
			//String strText = driver.findElementByXPath(xpath).getText();
			String strText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText();
			if (strText.equalsIgnoreCase(text)) {
				System.out.println("INFO: " + "The given text " + text + " is  exist in: " + strText);
			} else {
				System.err.println("WARNING: " + "The given text " + text + " does not exist in: " + strText);
			}
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with xpath: " + xpath + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
	}

	//Verify Text Contains By Xpath
	public void verifyTextContainsByXpath(String xpath, String text) {
		try {
			String strText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText();
			//String strText = driver.findElementByXPath(xpath).getText();
			if (strText.contains(text)){		
				System.out.println("INFO: " + "The given text "+text+"is present in " + strText);
			} else {
				System.err.println("WARNING: " + "The given text "+text+"is not present in " + strText);
			}
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with xpath " + xpath + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
	}

	//Click By Id
	public void clickById(String id)  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
			//driver.findElementById(id).click();
			System.out.println("INFO: " + "The button/link "+ id + " is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with id: " + id + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();			
		}
	}

	//click By ClassName
	public void clickByClassName(String classVal)  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.className(classVal))).click();
			//driver.findElementByClassName(classVal).click();
			System.out.println("INFO: " + "The button/link " + classVal + " is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with class value " + classVal + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();			
		}
	}

	//Click By Name
	public void clickByName(String nameVal)  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.name(nameVal))).click();
			//driver.findElementByName(nameVal).click();
			System.out.println("INFO: " + "The create lead Button " + nameVal + " is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with name value " + nameVal + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();			
		}
	}

	//Click By Link
	public void clickByLink(String linkVal)  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkVal))).click();
			//driver.findElementByLinkText(linkVal).click();
			System.out.println("INFO: " + "The link " + linkVal + " is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with link value: " + linkVal + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}finally {
			takeSnap();			
		}
	}

	//Click By Link NoSnap
	public void clickByLinkNoSnap(String linkVal)  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkVal))).click();
			//driver.findElementByLinkText(linkVal).click();
			System.out.println("INFO: " + "The link with NoSnap " + linkVal + " is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with link value: " + linkVal + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}
	}

	//click By Xpath
	public void clickByXpath(String xpathVal)  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathVal))).click();
			//driver.findElementByXPath(xpathVal).click();
			System.out.println("INFO: " + "The Button/link with xpath: " + xpathVal + " is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with xpath value: " + xpathVal + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();			
		}
	}

	//click By Xpath NoSnap
	public void clickByXpathNoSnap(String xpathVal)  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathVal))).click();
			//driver.findElementByXPath(xpathVal).click();
			System.out.println("INFO: " + "The Button/link with NoSnap " + xpathVal + " is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with xpath value " + xpathVal + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}	
	}

	//Get Text By Id
	public String getTextById(String idVal) {
		String strId = null;
		try {
			strId = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idVal))).getText(); 
			//strId = driver.findElementById(idVal).getText();
			System.out.println("INFO: " + "The text is " + strId);
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with id value: " + idVal + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}
		return strId;
	}

	//Get Text By Xpath
	public String getTextByXpath(String xpathVal) {
		String strXpath = null;
		try {
			strXpath = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathVal))).getText();
			//strXpath = driver.findElementByXPath(xpathVal).getText();
			System.out.println("INFO: " + "The text is " + strXpath);			
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with xpath value: " + xpathVal + " not found" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}
		return strXpath;
	}
	
	//Get Text By link text
	public String getTextBylinktext(String linkTextVal) {
		String strlinktext = null;
			try {
				strlinktext = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkTextVal))).getText();
				//strlinktext = driver.findElementByLinkText(linkVal).getText();
				System.out.println("INFO: " + "The text is " + strlinktext);			
			} catch (NoSuchElementException e) {
				System.err.println("EXCEPTION: " + "The element with xpath value: " + linkTextVal + " not found" + e.getMessage());
			} catch (WebDriverException e) {
				System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
			}
			return strlinktext;
		}

	//Select VisibileText By Id
	public void selectVisibileTextById(String id, String value)
			 {		
		try {
			WebElement SecQuest = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(id)));
			//WebElement SecQuest = driver.findElementById(id);
			Select dd = new Select(SecQuest);
			dd.selectByVisibleText(value);
			System.out.println("INFO: " + "The Dropdown value selected by visibleText is: "+ value);
		} 
		catch (NoSuchElementException e) 
		{
			System.err.println("EXCEPTION: " + "The element with value: " + value + " not found " + e.getMessage());
		} 
		catch (ElementNotVisibleException e) 
		{
			System.err.println("EXCEPTION: " + "The given element "  + value + " not visible - " + e.getMessage());
		} 
		catch (ElementNotSelectableException e) 
		{
			System.err.println("EXCEPTION: " + "The given element "  + value + " is not selectable - " + e.getMessage());
		} 
		catch (WebDriverException e) 
		{
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} 
		catch (Exception e) 
		{
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} 
		finally 
		{
			takeSnap();			
		}
	}

	//select Index By Id
	public void selectIndexById(String id, int value) {		
		try {
			WebElement SecQuest = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			//WebElement SecQuest = driver.findElementById(id);
			Select dd = new Select(SecQuest);
			dd.selectByIndex(value);
			System.out.println("INFO: " + "The Dropdown value selected by Index: " + value);
		} catch (NoSuchElementException e) {
			System.err.println("EXCEPTION: " + "The element with value: " + value + " not found " + e.getMessage());
		} catch (ElementNotSelectableException e) {
			System.err.println("EXCEPTION: " + "The given element " + value + " is not selectable - " + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}  finally {
			takeSnap();			
		}
	}

	//Switch To Parent Window
	public void switchToParentWindow() {
		try {
			Set<String> strPrimWin1 = driver.getWindowHandles();
			for (String strEachWin : strPrimWin1) {
				driver.switchTo().window(strEachWin);
				break;
			}
			System.out.println("INFO: " + "Switched to parent window and the title is: " + driver.getTitle());
		} catch (NoSuchWindowException e) {
			System.err.println("EXCEPTION: " + "There is no such window available for the parent window" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
	}
	
	//Switch To Last Window
	public void switchToLastWindow() {		
		try {
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			Set<String> strAllWin1 = driver.getWindowHandles();
			for (String strEachWin : strAllWin1) {
				driver.switchTo().window(strEachWin);
			}
			System.out.println("INFO: " + "Switched to last window and the title is: " + driver.getTitle());
		} catch (NoSuchWindowException e) {
			System.err.println("EXCEPTION: " + "There is no such last window available - " + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
	}
	
	//Accept Alert
	public void acceptAlert() {
		try {
			driver.switchTo().alert().accept();
			System.out.println("INFO: " + "The Alert is accepted");
		} catch (NoAlertPresentException e) {
			System.err.println("EXCEPTION: " + "There is no such alert present to accept - " + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}
	}

	//Dismiss Alert
	public void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
			System.out.println("INFO: " + "The Alert is dismissed");
		} catch (NoAlertPresentException e) {
			System.err.println("EXCEPTION: " + "There is no such alert present to dismiss - " + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}
	}
	
	

	//Get AlertText
	public String getAlertText() {
		String strAlertTxt = null;
		try {
			strAlertTxt = driver.switchTo().alert().getText();
			System.out.println("INFO: " + "The Alert text is received" + strAlertTxt);
		} catch (NoAlertPresentException e) {
			System.err.println("EXCEPTION: " + "There is no such alert present to get the text - " + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		}  catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		} finally {
			takeSnap();
		}
		return strAlertTxt;
	}

	//Take Snap shot
	public long takeSnap() {
		try {
			File src = driver.getScreenshotAs(OutputType.FILE);
			File dec = new File("./snapshot/snap" + i + ".jpg");
				FileUtils.copyFile(src, dec);
			i++;
			System.out.println("INFO: " + "The snapshot taken as " + dec.getName());
		} catch (IOException e) {
			System.err.println("EXCEPTION: " + "IO Exception: " + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}
		return 0;
	}

	//Close the current focused browser
	public void closeBrowser() {
		try {
			driver.close();
			System.out.println("INFO: " + "The current focused browser closed");
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}
	}

	//Closes all the browsers opened by the test
	public void closeAllBrowsers() {
		try {
			driver.quit();
			System.out.println("INFO: " + "All the browser are closed");
		} catch (WebDriverException e) {
			System.err.println("EXCEPTION: " + "Browser doesn't exist - Driver exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("EXCEPTION: " + "Other exception occured: " + e.getMessage());
		}
	}

	public void switchToLastWindow(String windowTitle) {
		// TODO Auto-generated method stub
		
	}

}
