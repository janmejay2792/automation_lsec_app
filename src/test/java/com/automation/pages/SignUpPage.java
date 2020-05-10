/**
 * 
 */
package com.automation.pages;

import org.testng.Assert;

import com.automation.data.LoginPageData;
import com.automation.data.SignUpPageData;
import com.automation.objects.LoginPageObject;
import com.automation.objects.SignUpPageObject;
import com.automation.utlity.GlobalParameters;
import com.automation.utlity.Utils;

/**
 * @author Janmejay
 *
 */
public class SignUpPage {

	SignUpPageObject signUpPageObject = new SignUpPageObject();
	LoginPageObject loginPageObject = new LoginPageObject();

	public void createNewAccouynt() {
//		Utils.scrollBy();
		Utils.click(loginPageObject.registerNow);
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
//			This Feature is coming for CHrome some times
			/*
			 * if ((loginPageObject.generalError.isDisplayed())) {
			 * Utils.click(loginPageObject.continueBr);
			 * Utils.click(loginPageObject.logInToYourAccount); }
			 */
		}
		Utils.click(signUpPageObject.signUpNow);
		// Sign Up Page
		fillApplicationForm();
	}

	public void fillApplicationForm() {
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
