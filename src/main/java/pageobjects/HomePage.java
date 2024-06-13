package pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import testcomponents.BaseTest;

public class HomePage extends BaseTest {
private WebDriver driver;
    
    // Locators
    private By linkSigin = By.linkText("Sign in");
    private By linkSwitchVideo = By.xpath("//a[contains(@href,'https://connect.video.liveswitch.com/login')][normalize-space()='LiveSwitch Video']");
    
    public HomePage(WebDriver driver) throws IOException {
    	super();
        this.driver = driver;
    }
    
    public void clickSignIn() {
    	getDriver().findElement(linkSigin).click();
    }
    public void clickSwitchVideo() {
    	getDriver().findElement(linkSwitchVideo).click();
    }
    
//    public String  navigateToHomePage() throws InterruptedException {
////    	getDriver().get(getEnvironment());
////    	clickSignIn();
////    	System.out.println(getDriver().getTitle());
////    	return getDriver().getTitle();
////    	
//    }
    public String navigateToSwitchVideo() throws InterruptedException {
    	
    	clickSwitchVideo();
    	System.out.println( getDriver().getTitle());
    	//Thread.sleep(3000);
    	return getDriver().getTitle();
    }
    
    

}
