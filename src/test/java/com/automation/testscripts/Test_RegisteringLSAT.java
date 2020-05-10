/**
 * 
 */
package com.automation.testscripts;

import org.testng.annotations.Test;

import com.automation.baseclass.BaseClass;
import com.automation.pages.RegisterLsatPage;

/**
 * @author janmejay ---This Function is for register the Account
 *
 */
public class Test_RegisteringLSAT extends BaseClass {

	@Test
	public void Test_RegisteringLSAT_() {

		new RegisterLsatPage().registerForLsat();

	}
}
