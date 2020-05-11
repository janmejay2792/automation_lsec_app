package com.automation.baseclass;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.data.Urls;
import com.automation.objects.LoginPageObject;
import com.automation.utlity.GlobalParameters;
import com.automation.utlity.Utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * @author Janmejay
 *
 */
public class BaseClass {

	public static WebDriver driver;
	public static AndroidDriver<MobileElement> driverA;
	public static IOSDriver<MobileElement> driverIOS;
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder server;
	DesiredCapabilities cap;
	String platformRunAs;

	final String myPlatform = Urls.MYPLATFORM;

	@Parameters({ "platform", "runOn" })
	@BeforeClass
	public void setup(@Optional(myPlatform) String platform, @Optional("chrome") String runOn)
			throws MalformedURLException {
		System.out.println(platform);
		GlobalParameters.runType = platform;

		switch (platform) {
		case "web":

			if (runOn.equalsIgnoreCase("chrome")) {
				System.out.println("Web Application is opening... ");
				ChromeDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.get(Urls.URL);
					Utils.click(new LoginPageObject().cookiesClick);
			} else if (runOn.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.get(Urls.URL);
				Utils.click(new LoginPageObject().cookiesClick);
			}
			break;

		case "mobile":
			String chrome = System.getProperty("user.dir") + "//drivers//chromedriver.exe";
			System.out.println("Mobile Application is opening... ");
			cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator1");
			cap.setCapability(MobileCapabilityType.NO_RESET, false);
			cap.setCapability("chromedriverExecutable", chrome);
			ChromeDriverManager.chromedriver().setup();
//			System.setProperty("webdriver.chrome.driver", chrome);
			if (runOn.equals("IOS")) {
				cap.setCapability("udid", "MI");
				driverA = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4729/wd/hub"), cap);
			} else if (runOn.equals("abc")) {
				cap.setCapability("udid", "Samsung");
				driverA = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4726/wd/hub"), cap);
			} else if (platformRunAs.equals("mobile")) {
				driverA = new AndroidDriver<MobileElement>(service.getUrl(), cap);
				driverA.get(Urls.URL);
				Utils.click(new LoginPageObject().cookiesClick);
				driverA.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driverA.manage().deleteAllCookies();
			}

			break;
		case "IOS":
			System.out.println("IOS Application is opening... ");
			cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ABCD");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
			cap.setCapability("platformVersion", "11.2.6");
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			cap.setCapability(MobileCapabilityType.NO_RESET, false);
			if (runOn.equals("A")) {
				cap.setCapability("udid", "IOS_A");
				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4729/wd/hub"), cap);
			} else if (platformRunAs.equals("IOS")) {
				driverIOS = new IOSDriver<MobileElement>(service.getUrl(), cap);
				driverIOS.get(Urls.URL);
				Utils.click(new LoginPageObject().cookiesClick);
				driverIOS.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//				driverIOS.manage().deleteAllCookies();
			}

			break;
		default:
			System.out.println("Incorrect Platform...");
			break;
		}

	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static AndroidDriver<MobileElement> getDriverA() {
		return driverA;
	}

	public static IOSDriver<MobileElement> getDriverIOS() {
		return driverIOS;
	}

	@Parameters({ "runAs" })
	@BeforeTest
	public void startAppoumserver(@Optional(myPlatform) String platform) {
		System.out.println(platform);
		if (platform.equalsIgnoreCase("mobile")) {
			platformRunAs = "mobile";
			server = new AppiumServiceBuilder();
			server.usingAnyFreePort();
			server.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			server.withArgument(GeneralServerFlag.LOG_LEVEL, "error:error");
			service = AppiumDriverLocalService.buildService(server);
			service.start();
			System.out.println("Appium Server is Started...");
		}

	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		Utils.getscreenshot(testResult);
	}

	@Parameters({ "runAs" })
	@AfterTest
	public void stopAppiumServer(@Optional(myPlatform) String platform) {
		if (platform.equalsIgnoreCase("mobile")) {
			service.stop();
			System.out.println("Appium Server is Stoped...");
		} else if (platform.equalsIgnoreCase("web")) {
			driver.quit();
		}
	}
}
