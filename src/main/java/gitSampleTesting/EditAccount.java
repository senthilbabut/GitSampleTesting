package gitSampleTesting;

import org.junit.Test;

import wrappers.Login;

public class EditAccount extends Login {

	@Test
	public void editAccount() throws Exception {
		// TODO Auto-generated constructor stub -updated
		
		String userName= "DemoSalesManager";
		String password = "crmsfa";
		String accountName = "TestLeaf_Account9_1";
		String accountNameToUpdate = accountName+ "_Updated";
		//String accountId="17940";

		//login(userName,password);
		//clickByLink("CRM/SFA");
		clickByLink("Accounts");
		clickByLink("Find Accounts");
		
		enterByXpath("(//input[@name='accountName'])[2]",accountName);		
		enterByName("id",accountId);			
		
		clickByXpath("//button[contains(text(),'Find Accounts')]");		
		clickByXpath("(//a[@class='linktext'])[4]");
		verifyTitle("Account Details | opentaps CRM");
		clickByClassName("subMenuButton");
		enterById("accountName",accountNameToUpdate);
		clickByClassName("smallSubmit");
		verifyTextContainsByXpath("(//span[@class='tabletext'])[3]",accountNameToUpdate);
		closeBrowser();
	
	}

}

	