package Pages;

import org.openqa.selenium.By;

import GBLibrary.Base;
import ObjectMap.ObjectMap;

public class FailedLoginPage extends Base{
	public ObjectMap objmap;


	public void VerifyInvalidPass() {
		String workingDir = System.getProperty("user.dir");
		objmap = new ObjectMap(workingDir + "\\objectpropertiesfile\\objectmap.properties");
		try {
			libray.getAndAssertText(objmap.getLocator("InvalidPassMessage"), "The password you’ve entered is incorrect. Forgot Password?");
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}
	}
	
	public void VerifyNoCredentials() {
		String workingDir = System.getProperty("user.dir");
		objmap = new ObjectMap(workingDir + "\\objectpropertiesfile\\objectmap.properties");
		try {
			libray.getAndAssertText(objmap.getLocator("NoCredentialMessage"), "The email or phone number you’ve entered doesn’t match any account. Sign up for an account.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}
	}
	
}
