/**
 * 
 */
package com.automation.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.baseclass.BaseClass;
import com.automation.utlity.GlobalParameters;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Janmejay
 *
 */
public class LoginPageObject {
	public LoginPageObject() {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			new BaseClass();
			PageFactory.initElements(BaseClass.getDriver(), this);
		} else {
			new BaseClass();
			PageFactory.initElements(new AppiumFieldDecorator(BaseClass.getDriverA()), this);
		}
	}

	@FindBy(xpath = "//a[contains(text(),'Log in to Your Account')]")
	public WebElement logInToYourAccount;
	
	@FindBy(xpath = "//input[@name='Cancel']")
	public WebElement continueBr;

	@FindBy(id = "CybotCookiebotDialogBodyLevelButtonAccept")
	public WebElement cookiesClick;

	@FindBy(xpath = "//a[contains(text(),'Register Now')]")
	public WebElement registerNow;


	@FindBy(id = "logonIdentifier")
	public static WebElement userName;

	@AndroidFindBy(id = "password")
	@FindBy(id = "password")
	public WebElement passWord;
	
	@FindBy(xpath = "//button[text()='Sign in']")
	public WebElement signIn;
	
	@FindBy(xpath = "//*[text()='Sign In to your LSAC Account']")
	public WebElement headerSignLSAC;
	
	@FindBy(xpath = "//p[contains(text(),'An error has occurred processing your request')]")
	public WebElement generalError;
	
	@FindBy(xpath = "//*[text()='Please check the form for errors and retry.']")
	public WebElement errorMessage;
	
	@FindBy(xpath = "//*[@role='alert']")
	public WebElement errorAlert;
}
