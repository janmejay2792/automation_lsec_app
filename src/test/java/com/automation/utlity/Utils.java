/**
 * 
 */
package com.automation.utlity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.automation.baseclass.BaseClass;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;

/**
 * @author Janmejay
 *
 */
public class Utils extends BaseClass {
	private static JavascriptExecutor executor;
	private static WebDriverWait wait;
	private static JavascriptExecutor js;
	private static SimpleDateFormat sdf;
	private static Actions actions;
	private static TouchActions touchActions;
	private static Logger Log = Logger.getLogger(Utils.class);

//	******************************************************************************************************
	
	/**
	 * @param Click the element with webelement
	 */
//	****************************************************************************************************
	public static void click(WebElement ele) {
		try {
			if (GlobalParameters.runType.equalsIgnoreCase("web")) {
//				new WebDriverWait(getDriver(), 20).ignoring(StaleElementReferenceException.class)
//						.until(ExpectedConditions.elementToBeClickable(ele));
				new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class)
						.until((WebDriver driver) -> {
							executor = (JavascriptExecutor) getDriver();
							executor.executeScript("arguments[0].click();", ele);
							return true;
						});

				Log.info("Click the element");
			} else if (GlobalParameters.runType.equalsIgnoreCase("mobile"))
				executor = (JavascriptExecutor) getDriverA();
			executor.executeScript("arguments[0].click();", ele);
			Log.info("Click the element");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Log.info("NoSuchElementException found");
		} catch (IllegalStateException e) {
			e.printStackTrace();
			Log.info("IllegalStateException found");
		} catch (ElementNotInteractableException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
//	******************************************************************************************************
	
	/**
	 * @param Scrooling b text for Android 
	 */
//	****************************************************************************************************

	@SuppressWarnings("unchecked")
	public static void scrollByTexts(String containedText) {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			((FindsByAndroidUIAutomator<MobileElement>) getDriver()).findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + containedText + "\"));");
		} else if (GlobalParameters.runType.equalsIgnoreCase("mobile")) {
			((FindsByAndroidUIAutomator<MobileElement>) getDriverA()).findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + containedText + "\"));");
		}
	}
	
//	******************************************************************************************************
	
	/**
	 * @param Send the text to the element with webelement
	 */
//	****************************************************************************************************

	public static void sendkeys(WebElement ele, String text) {
		try {
			if (GlobalParameters.runType.equalsIgnoreCase("web")) {
				new WebDriverWait(getDriver(), 20).ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.visibilityOf(ele));
				executor = (JavascriptExecutor) getDriver();
				executor.executeScript("arguments[0].value='" + text + "';", ele);
				Log.info("Element Found");
			} else if (GlobalParameters.runType.equalsIgnoreCase("mobile"))
				executor = (JavascriptExecutor) getDriverA();
			executor.executeScript("arguments[0].value='" + text + "';", ele);
			Log.info("Element Found");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Log.info("NoSuchElementException found");
		} catch (IllegalStateException e) {
			e.printStackTrace();
			Log.info("IllegalStateException found");
		}
	}
	
//	******************************************************************************************************
	
	/**
	 * @param Scroll the browsr up
	 */
//	****************************************************************************************************

	public static void scrollBy() {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy(0,350)", "");
		} else if (GlobalParameters.runType.equalsIgnoreCase("mobile"))
			js = (JavascriptExecutor) getDriverA();
		js.executeScript("window.scrollBy(0,250)", "");
	}

//	******************************************************************************************************
	
	/**
	 * @param Taking Screen Shot
	 */
//	****************************************************************************************************
	public static final void getscreenshot(ITestResult testResult) {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			try {
				if (testResult.getStatus() == ITestResult.FAILURE) {
					sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
					Date date = new Date();
					String fileName = sdf.format(date);
					File des = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(des,
							new File(System.getProperty("user.dir") + "//Screenshot//"
									+ (testResult.getName() + "-" + fileName) + "//" + testResult.getName() + "-"
									+ fileName + "-" + Arrays.toString(testResult.getParameters()) + ".png"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.info("Screen Shot");
			}
		} else
			try {
				if (testResult.getStatus() == ITestResult.FAILURE) {
					sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
					Date date = new Date();
					String fileName = sdf.format(date);
					File des = ((TakesScreenshot) getDriverA()).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(des,
							new File(System.getProperty("user.dir") + "//Screenshot//"
									+ (testResult.getName() + "-" + fileName) + "//" + testResult.getName() + "-"
									+ fileName + "-" + Arrays.toString(testResult.getParameters()) + ".png"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.info("Screen Shot Not found");
			}
	}
//	******************************************************************************************************
	
	/**
	 * @param clickWithLinkName the element with webelement
	 */
//	****************************************************************************************************
	public static void clickWithLinkName(String linkNme) {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			try {
				List<WebElement> allLinks = getDriver().findElements(By.tagName("a"));
				for (WebElement link : allLinks) {
//					System.out.println(link.getText());
					if (link.getText().equalsIgnoreCase(linkNme)) {
						new WebDriverWait(getDriver(), 20).ignoring(StaleElementReferenceException.class)
						.until((WebDriver driver) -> {
							executor = (JavascriptExecutor) getDriver();
							executor.executeScript("arguments[0].click();", link);
							return true;
						});
						
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (GlobalParameters.runType.equalsIgnoreCase("mobile")) {
			try {
				List<MobileElement> allLinks = getDriverA().findElements(By.tagName("a"));
				for (WebElement link : allLinks) {
					new WebDriverWait(getDriverA(), 20).ignoring(StaleElementReferenceException.class)
					.until((WebDriver driver) -> {
						executor = (JavascriptExecutor) getDriverA();
						executor.executeScript("arguments[0].click();", link);
						return true;
					});
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
//	******************************************************************************************************
	
	/**
	 * @param Tool Tip Actions the element with webelement
	 */
//	****************************************************************************************************
	
	public static void toolTipActions(String linkNme) {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			try {
				List<WebElement> allLinks = getDriver().findElements(By.tagName("a"));
				for (WebElement link : allLinks) {
//					System.out.println(link.getText());
					if (link.getText().equalsIgnoreCase(linkNme)) {
						actions = new Actions(getDriver());
						actions.moveToElement(link).build().perform();
					}
				}
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (GlobalParameters.runType.equalsIgnoreCase("mobile")) {
			try {
				List<MobileElement> allLinks = getDriverA().findElements(By.tagName("a"));
				for (MobileElement link : allLinks) {
//					System.out.println(link.getText());
					if (link.getText().equalsIgnoreCase(linkNme)) {
//						touchActions = new TouchActions(getDriverA());
						touchActions.singleTap(link).build().perform();
//						touchActions.moveToElement(link).click().build().perform();
//						touchActions.longPress((WebElement) LongPressOptions.longPressOptions()
//								.withElement(ElementOption.element(link))).release().perform();
//						touchActions.longPress(link).release().perform();

					}
				}
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
//	******************************************************************************************************
	
	/**
	 * @param clickByLinkIndex the element with webelement
	 */
//	****************************************************************************************************

	@SuppressWarnings("unchecked")
	public static void clickByLinkIndex(WebElement ele, int index) {
		if (GlobalParameters.runType.equalsIgnoreCase("web")) {
			try {
				List<WebElement> link = (List<WebElement>) ele;
				new WebDriverWait(getDriver(), 10).ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.elementToBeClickable(ele));
				link.get(index).click();
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (GlobalParameters.runType.equalsIgnoreCase("mobile")) {
			try {
				List<MobileElement> link = (List<MobileElement>) ele;
				link.get(index).click();
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

//	******************************************************************************************************
	
	/**
	 * @param Tool Tip Evenet the element with webelement
	 */
//	****************************************************************************************************
	public static void tooTipEvent(WebElement ele) {
		if (GlobalParameters.runType.equalsIgnoreCase("mobile")) {
			try {
				wait = new WebDriverWait(getDriver(), 20);
				wait.until(ExpectedConditions.visibilityOf(ele));
				actions = new Actions(getDriver());
				actions.moveToElement(ele).perform();
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (GlobalParameters.runType.equalsIgnoreCase("mobile")) {
			try {
				wait = new WebDriverWait(getDriverA(), 10);
				wait.until(ExpectedConditions.visibilityOf(ele));
				actions = new Actions(getDriverA());
				actions.moveToElement(ele).perform();
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}
}
