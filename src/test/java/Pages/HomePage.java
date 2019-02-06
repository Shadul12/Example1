package Pages;

import org.openqa.selenium.By;

import GBLibrary.Base;
import ObjectMap.ObjectMap;

public class HomePage extends Base {
	public ObjectMap objmap;

	public void title() {
		libray.GetTitle("Facebook - Log In or Sign Up");

	}

	public void InvalidCredentialLogin(String UserName, String Password) {
		String workingDir = System.getProperty("user.dir");
		objmap = new ObjectMap(workingDir + "\\objectpropertiesfile\\objectmap.properties");
		try {
			libray.findAndSendKeys(objmap.getLocator("EmailField"), UserName);
			libray.findAndSendKeys(objmap.getLocator("PasswordField"), Password);
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}

	}

	public void ClickLogIn() {
		String workingDir = System.getProperty("user.dir");
		objmap = new ObjectMap(workingDir + "\\objectpropertiesfile\\objectmap.properties");
		try {
			Thread.sleep(1000);
			libray.findAndClick(objmap.getLocator("LogInButton"));
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}

	}

}
