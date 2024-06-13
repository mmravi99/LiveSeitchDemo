package testcomponents;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends SuperTestNG {
	
	

	protected static WebDriver driver;
	protected static AndroidDriver adDriver;
	protected WebDriverWait wait;
	protected static ThreadLocal<WebDriver> dr = new ThreadLocal<>();
	protected static ThreadLocal<AndroidDriver> ad = new ThreadLocal<>();
	AppiumDriverLocalService service;

	public static WebDriver getDriver() {
		return dr.get();
	}

	public static AndroidDriver getadDriver() {
		return ad.get();
	}

	public static void setDriver(WebDriver ref) {
		dr.set(ref);
	}

	public static void setadDriver(AndroidDriver ref) {
		ad.set(ref);
	}

	public static void unload() {
		dr.remove();
	}

	public static void unloadAd() {
		ad.remove();
	}

	public BaseTest() throws IOException {
		super();
	}
	
	public void initBrowser(String browser) throws IOException {

		if (browser.equals("chrome")) {
			
			driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--remote-allow-origins=*");
			//driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		setDriver(driver);
		System.out.println(driver.toString().split(":")[0] + " Driver intialized");
		getDriver().manage().window().maximize();
		//getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		
	}

	public void initAndroidDriver(String browser) throws IOException, URISyntaxException {
		
		
		if (browser.equals("mobile")) {
			service = new AppiumServiceBuilder()
					.withAppiumJS(new File(
							"C:\\Users\\ravimann\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					.withIPAddress("127.0.0.1").usingPort(4723).build();
			service.start();

			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("Pixel 6 LiveSwitch");
			options.setChromedriverExecutable("D:\\chromedriver83\\chromedriver.exe");
			options.setCapability("browserName", "Chrome");
			
			options.setCapability("--disable-cookies", true);

			adDriver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

		}
		setadDriver(adDriver);
		
		System.out.println(adDriver.toString().split(":")[0] + " Driver intialized");
		
	}

	protected WebElement waitForElement(By locator) {
		try {
			// Wait until the element is present on the webpage
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			// Handle exception if element is not found within the specified timeout
			e.printStackTrace();
			return null;
		}
	}

	public void closeBrowser() {
		if (getDriver() != null) {
			getDriver().quit();
			System.out.println(getDriver().toString().split(":")[0] + " Closed");
			unloadAd();
		}
	}
	
	public void closeAdDriver() {
		if (getadDriver() != null) {
			getadDriver().quit();
			service.stop();
			System.out.println(getadDriver().toString().split(":")[0] + " Closed");
			unloadAd();
			
		}

	}

}
