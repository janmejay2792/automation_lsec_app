/**
 * 
 */
package com.automation.testscripts;

import org.testng.annotations.Test;

import com.automation.baseclass.BaseClass;
import com.automation.pages.LoginPage;

/**
 * @author Janmejay ----Login the lsac application - with Valid and Invalid
 *         credentials
 */
public class Test_LoginTest extends BaseClass {
	

	@Test
	public void Test_LoginTest_() throws InterruptedException {
		// Scenario for Creating Form Login - InValid Scenario
		new LoginPage().registerNow();
	}

}
