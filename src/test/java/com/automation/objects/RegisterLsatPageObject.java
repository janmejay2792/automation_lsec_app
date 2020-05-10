/**
 * 
 */
package com.automation.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.baseclass.BaseClass;
import com.automation.utlity.GlobalParameters;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Janmejay
 *
 */
public class RegisterLsatPageObject extends BaseClass{
	public RegisterLsatPageObject() {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			new BaseClass();
			PageFactory.initElements(BaseClass.getDriver(), this);
		} else {
			new BaseClass();
			PageFactory.initElements(new AppiumFieldDecorator(BaseClass.getDriverA()), this);
		}
	}

	@FindAll({@FindBy(xpath = "//button")})
	public List<WebElement> menuButton;
	
	@FindBy(xpath = "//*[@text='Register Now']")
	public WebElement registerNow;
	
	@FindBy(xpath = "//span[contains(text(),'Registering for the LSAT')]")
	public WebElement errorRegisteringLSAT;
	
	@FindBy(xpath = "//*[text()='Sign up now!']")
	public WebElement signUpNow;


	
	
	


	
}
