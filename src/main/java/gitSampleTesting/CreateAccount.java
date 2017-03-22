
package gitSampleTesting;

import org.junit.Test;

import wrappers.Login;

public class CreateAccount extends Login {

	@Test
	public void createAccount() throws Exception {
		// TODO Auto-generated constructor stub
		
		String userName= "DemoSalesManager";
		String password = "crmsfa";
		String accountName = "TestLeaf_Account9_2";
		String industry = "Computer Software";
		String preferredCurrency ="INR - Indian Rupee";
		String marketCampaign = "Automobile";
		String source = "Other";
		String phoneNumber = "9999999991";
		String city = "Chennai";		
		String eMail = "testleaf_101@testleaf.com";		
		String state= "Indiana";
		
		login(userName,password);
		
		clickByLink("CRM/SFA");
		
		clickByLink("Create Account");
		enterById("accountName",accountName);
		selectVisibileTextById("industryEnumId",industry);
		selectVisibileTextById("currencyUomId",preferredCurrency);
		selectVisibileTextById("dataSourceId",source);
		selectVisibileTextById("marketingCampaignId",marketCampaign);
		enterById("primaryPhoneNumber",phoneNumber);
		enterById("generalCity",city);
		enterById("primaryEmail",eMail);
		selectVisibileTextById("generalStateProvinceGeoId",state);
		clickByXpath("//input[@class='smallSubmit']");
		
		String company = getTextByXpath("(//span[@class='tabletext'])[3]");
		
		
		String[] parts = company.split("[(]");
		String companyName = parts[0];
		String accountID = parts[1].replace(")", " ");
		
		System.out.println("The Company Name is :"+ companyName);
		System.out.println("The Account  ID is :"+ accountID);
		
		clickByLink("Find Accounts");
		
		enterByXpath("(//input[@name='accountName'])[2]",companyName);		
		enterByName("id",accountID);		
		
		clickByXpath("//button[contains(text(),'Find Accounts')]");		
		verifyTextContainsByXpath("(//a[@class='linktext'])[6]",accountName);
		closeBrowser();
	}
}

	
	