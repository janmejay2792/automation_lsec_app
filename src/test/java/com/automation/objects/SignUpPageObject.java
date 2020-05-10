package com.automation.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.baseclass.BaseClass;
import com.automation.utlity.GlobalParameters;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author janmejay
 *
 */
public class SignUpPageObject {
	public SignUpPageObject() {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			new BaseClass();
			PageFactory.initElements(BaseClass.getDriver(), this);
		} else if (GlobalParameters.runType.equalsIgnoreCase("mobile")) {
			new BaseClass();
			PageFactory.initElements(new AppiumFieldDecorator(BaseClass.getDriverA()), this);
		}
	}

	@FindBy(id = "ctl00_OS2ContentPlaceHolder_linkButton")
	public WebElement signUpNow;

	@FindBy(id = "createAccount")
	public WebElement signupNow;

	@FindBy(xpath = "//*[text()='Sign Up LSAC Account']")
	public WebElement signUpLSACAccount;

	@FindBy(id = "newPassword")
	public WebElement newPassword;

	@FindBy(id = "reenterPassword")
	public WebElement confirmPassword;

	@FindBy(id = "email")
	public WebElement email;

	@FindBy(id = "givenName")
	public WebElement firstName;

	@FindBy(id = "surName")
	public WebElement lastName;

	@FindBy(id = "tosCheck")
	public WebElement tosCheck;

	@FindBy(id = "continueSignUp")
	public WebElement continueSignUp;

}
