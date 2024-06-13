package pageobjects;

import java.awt.dnd.DropTargetDragEvent;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import testcomponents.BaseTest;

public class SignInPage extends BaseTest {
	
	private WebDriver driver;
	// Locators
    private By txtUsername = By.xpath("//input[@id='email']");
    private By txtPassword = By.xpath("//input[@id='password']");
    private By btnSignIn = By.xpath("//button[normalize-space()='Sign In']");
    
    public SignInPage(WebDriver driver) throws IOException {
    	super();
        this.driver = driver;
    }
    
    public void fillUsername(String un) {
    	waitforElement(getDriver().findElement(txtUsername),driver);
    	getDriver().findElement(txtUsername).clear();
    	getDriver().findElement(txtUsername).sendKeys(un);
    }
    public void fillPassword(String pwd) {
    	getDriver().findElement(txtPassword).clear();
    	getDriver().findElement(txtPassword).sendKeys(pwd);
    }
    public void clickSigIn() {
    	getDriver().findElement(btnSignIn).click();
    }
    
    public String signIn(String un,String pwd) throws InterruptedException {
    	fillUsername(un);
    	fillPassword(pwd);
    	clickSigIn();
    	Thread.sleep(20000);
    	System.out.println(getDriver().getCurrentUrl());
    	return getDriver().getCurrentUrl();
    	
    }
    

}
