package wrappers;

//import org.junit.Test;

import wrappers.GenericWrappers;

public class Login extends GenericWrappers {

	
	public void login(String userName,String password) throws Exception {

		//Create Lead
		
		invokeApp("chrome", "http://leaftaps.com");
		enterById("username", userName);
		enterById("password", password);
		clickByClassName("decorativeSubmit");		
		
	}

}
