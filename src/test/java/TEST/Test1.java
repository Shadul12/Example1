package TEST;

import org.testng.annotations.Test;

import GBLibrary.Base;
import Pages.FailedLoginPage;
import Pages.HomePage;

public class Test1 extends Base{
	HomePage hp = new HomePage();
	FailedLoginPage flp = new FailedLoginPage();
	
	

	@Test(enabled= true)
	public void Test0001(){
		hp.title();
		hp.InvalidCredentialLogin("123@email.com", "123456");
		hp.ClickLogIn();
		flp.VerifyInvalidPass();
		
	}
	@Test(enabled = true)
	public void Test0002() {
		hp.title();
		hp.ClickLogIn();
		flp.VerifyNoCredentials();
		
	}
	@Test(enabled = true)// Test will Fail
	public void Test0003() {
		hp.title();
		hp.ClickLogIn();
		flp.VerifyInvalidPass();
	}

}