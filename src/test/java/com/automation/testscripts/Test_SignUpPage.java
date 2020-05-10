package com.automation.testscripts;

import org.testng.annotations.Test;

import com.automation.baseclass.BaseClass;
import com.automation.pages.SignUpPage;

/**
 * @author janmejay ------This Function is for Creating the User
 */
public class Test_SignUpPage extends BaseClass {

	@Test
	public void Test_SignUpPage_() {

		new SignUpPage().createNewAccouynt();
	}

}
