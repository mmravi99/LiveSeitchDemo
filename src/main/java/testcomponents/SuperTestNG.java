package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.android.AndroidDriver;

public class SuperTestNG {
	
	
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String[] DOMAINS = {"example.com", "mail.com", "test.com", "random.org", "email.net"};
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
	public static int WAIT_MIN = 3000;
	public static int WAIT_AVG = 5000;
	public static int WAIT_MAX = 12000;
	
	static ExtentSparkReporter report = null;
	public static ExtentReports extent = null;
	Properties properties = new Properties();
    FileInputStream fileInputStream = null;
	public SuperTestNG() throws IOException {
		fileInputStream = new FileInputStream(System.getProperty("user.dir") +"/src/test/resources/globalsettings.properties");
        properties.load(fileInputStream);
	}
    public String getContactsURL() {
    	return properties.getProperty("conatctsURL");
	}
    public String getConciergeURL() {
    	return properties.getProperty("conciergeURL");
	}
    public String getBrowser() {
    	return properties.getProperty("browser");
	}
    public String getUsername() {
    	return properties.getProperty("username");
	}
    public String getPassword() {
    	return properties.getProperty("password");
	}
    
    public static String generateRandomEmail() {
        String username = generateRandomString(8);  // Generate a random username with 8 characters
        String domain = DOMAINS[new Random().nextInt(DOMAINS.length)];
        return username + "@" + domain;
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        String characters = CHARACTERS + NUMERIC;

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
    
    public static String generateRandomName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();

        // Generate pattern "XAXAAXAX"
        for (int i = 0; i < 8; i++) {
            // Append a random uppercase letter for each position
            name.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return name.toString();
    }
    
    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        
        // Generate random numbers for each part of the phone number
        int firstPart = random.nextInt(900) + 100;   // First part: 100-999
        int secondPart = random.nextInt(900) + 100;  // Second part: 100-999
        int thirdPart = random.nextInt(10000);       // Third part: 0000-9999

        // Format the third part to ensure it has 4 digits (e.g., 123 becomes 0123)
        String formattedThirdPart = String.format("%04d", thirdPart);

        // Combine parts to form the complete phone number
        return firstPart + "-" + secondPart + "-" + formattedThirdPart;
    }
    public static ExtentReports setUp() throws IOException {
//		folderName = typeOfTest+"_"+env.toUpperCase()+"_" + setFolderName();
//		fileName = "Run_" + getTimeStamp();
//		String reportLocation = System.getProperty("user.dir") + "\\"  +"AutomationReports"+"\\"+folderName+ "\\" + "ExtentReport" + ".html";
		String reportLocation = System.getProperty("user.dir") + "/"  +"AutomationReports" + "/"  + "ExtentReport" + ".html";
		report = new ExtentSparkReporter(reportLocation);	
		report.config().setEncoding("utf-8");
		System.out.println("Extent Report location initialized . . .");


		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", "LiveSwitch");
		extent.setSystemInfo("Environment URL", "https://www.liveswitch.com");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		System.out.println("System Info. set in Extent Report");
		return extent;
	}
    public String captureScreenShot(WebDriver driver) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "/"+"AutomationReports"+"/"+"ScreenShots"+"/"+ getcurrentdateandtime() + ".jpg";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}
    public static String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}
		return str;
	}
    public void reportScreenshot(ExtentTest test,String status, String msg, WebDriver driver) throws IOException {

		if (status.equals("PASS"))
			test.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		else if (status.equals("FAIL"))
			test.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		else if (status.equals("INFO"))
			test.info(msg, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
	}
    
    public void reportScreenshot(ExtentTest test,String status, String msg, AndroidDriver driver) throws IOException {

		if (status.equals("PASS"))
			test.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		else if (status.equals("FAIL"))
			test.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
		else if (status.equals("INFO"))
			test.info(msg, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
	}
    public void reportPass(ExtentTest test,String msg) throws IOException {
			test.pass(msg);
	}
    public void reportFail(ExtentTest test,String msg) throws IOException {
		test.fail(msg);
    }
    public void reportInfo(ExtentTest test,String msg) throws IOException {
		test.info(msg);
    }

    public void waitforElement(WebElement ele,WebDriver dr) {
    	WebDriverWait wait = new WebDriverWait(dr, Duration.ofMillis(20000));
    	wait.until(ExpectedConditions.visibilityOf(ele));
    }
    public void waitforElement(List<WebElement> ele,WebDriver dr) {
    	WebDriverWait wait = new WebDriverWait(dr, Duration.ofMillis(20000));
    	wait.until(ExpectedConditions.visibilityOfAllElements(ele));
    }
    public static void waitForEmenet(int w) throws InterruptedException {
		Thread.sleep(w);
	}
	public static void waitForPage(int w) throws InterruptedException {
		Thread.sleep(w);
	}
    
    
}
