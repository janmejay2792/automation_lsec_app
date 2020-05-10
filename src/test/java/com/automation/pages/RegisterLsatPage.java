/**
 * 
 */
package com.automation.pages;

import org.testng.Assert;

import com.automation.data.LoginPageData;
import com.automation.data.RegisterLsatPageData;
import com.automation.data.SignUpPageData;
import com.automation.objects.LoginPageObject;
import com.automation.objects.RegisterLsatPageObject;
import com.automation.objects.SignUpPageObject;
import com.automation.utlity.GlobalParameters;
import com.automation.utlity.Utils;

/**
 * @author janme
 *
 */
public class RegisterLsatPage {

	RegisterLsatPageObject registerLsatPageObject = new RegisterLsatPageObject();
	LoginPageObject loginPageObject = new LoginPageObject();
	SignUpPageObject signUpPageObject= new SignUpPageObject();

	public void registerForLsat() {
		if (GlobalParameters.runType.equalsIgnoreCase("mobile")) {
			// This feature is only for Mobile Menu
			registerLsatPageObject.menuButton.get(0).click();
			registerLsatPageObject.menuButton.get(1).click();
			Utils.scrollBy();
//			Utils.toolTipActions("The LSAT");
//			Utils.clickWithLinkName("Registering for the LSAT");
		} else if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			Utils.toolTipActions("The LSAT");
			Utils.clickWithLinkName("Registering for the LSAT");
			Utils.scrollBy();
			Assert.assertEquals(registerLsatPageObject.errorRegisteringLSAT.getText(),
					RegisterLsatPageData.REGISTERINGLSAT);
		}
		Utils.clickWithLinkName("Register Now");
		// Registering new Account
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utils.click(signUpPageObject.signUpNow);
		Assert.assertEquals(loginPageObject.headerSignLSAC.getText(), LoginPageData.HEADERSIGNLSAC);
		Utils.click(signUpPageObject.signupNow);
		Assert.assertEquals(signUpPageObject.signUpLSACAccount.getText(), SignUpPageData.SIGNUPLSACACCOUNT);
		Utils.sendkeys(signUpPageObject.newPassword, SignUpPageData.USERNAME);
		Utils.sendkeys(signUpPageObject.newPassword, SignUpPageData.NEWPASSWORD);
		Utils.sendkeys(signUpPageObject.newPassword, SignUpPageData.CONFIRMPASSWORD);
		Utils.sendkeys(signUpPageObject.newPassword, SignUpPageData.EMAIL);
		Utils.sendkeys(signUpPageObject.newPassword, SignUpPageData.FIRSTNAME);
		Utils.sendkeys(signUpPageObject.newPassword, SignUpPageData.LASTNAME);
		Utils.click(signUpPageObject.tosCheck);
		Utils.click(signUpPageObject.continueSignUp);

	}

}
