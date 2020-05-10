/**
 * 
 */
package com.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import com.automation.data.LoginPageData;
import com.automation.objects.LoginPageObject;
import com.automation.utlity.GlobalParameters;
import com.automation.utlity.Utils;

/**
 * @author Janmejay
 *
 */
public class LoginPage {

	LoginPageObject loginPageObject = new LoginPageObject();
	JavascriptExecutor executor;

	public void enterPassword(String password) {
		Utils.sendkeys(loginPageObject.passWord, password);
	}

	public void clickToRegisterNow() {
		Utils.click(loginPageObject.registerNow);
	}

	public void registerNow() {
		try {

			Utils.click(loginPageObject.registerNow);
			Utils.click(loginPageObject.logInToYourAccount);
			if (GlobalParameters.runType.equalsIgnoreCase("web")) {
//				This Feature is coming for CHrome some times
				/*
				 * if ((loginPageObject.generalError.isDisplayed())) {
				 * Utils.click(loginPageObject.continueBr);
				 * Utils.click(loginPageObject.logInToYourAccount); }
				 */
			}
			Assert.assertEquals(loginPageObject.headerSignLSAC.getText(), LoginPageData.HEADERSIGNLSAC);
			Utils.sendkeys(LoginPageObject.userName, LoginPageData.USERNAME);
			Utils.sendkeys(loginPageObject.passWord, LoginPageData.PASSWORD);
			Utils.click(loginPageObject.signIn);
			Assert.assertEquals(loginPageObject.errorMessage.getText(), LoginPageData.ERRORMESSAGEE);
			Assert.assertEquals(loginPageObject.errorAlert.getText(), LoginPageData.ALERTMESSAGE);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
