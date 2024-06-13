package pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testcomponents.BaseTest;

public class MeetingsPage extends BaseTest{
	
	private WebDriver driver;
	// Locators
    private By tabScheduleMeeting = By.xpath("//div[@class='ls-lg-device'][normalize-space()='Schedule a Meeting']");
    private By btnNewMeeting = By.xpath("//button[@id='new-meeting-button']");
    private By txtMeetingTitle = By.xpath("//input[@id='meeting-title-input']");
    private By txtStartDate = By.xpath("//input[@id='start-date-input']");
    private By txtStartTime = By.xpath("//input[@id='start-time-input']");
    private By txtEndDate = By.xpath("//input[@id='end-date-input']");
    private By txtEndTime = By.xpath("//input[@id='end-date-input']");
    private By btnSave = By.xpath("//button[normalize-space()='Save']");
    private By lstScheduleNames = By.xpath("//div[@class='upcoming-results container px-0']/div[@class='row no-gutters meeting-row my-3']/div/div[1]");
    
    private By btnOK = By.xpath("//button[@type='button'][normalize-space()='OK']");
    
    public MeetingsPage(WebDriver driver) throws IOException {
    	super();
        this.driver = driver;
    }
    
    public void fillMeetitmgTitle(String t) {
    	getDriver().findElement(txtMeetingTitle).sendKeys(t);
    }
    public void fillStartDate(String d1) {
    	getDriver().findElement(txtStartDate).sendKeys(d1);
    	getDriver().findElement(txtStartDate).sendKeys(Keys.TAB);
    }
    
    public void fillStartTime(String t1) {
    	getDriver().findElement(txtStartTime).sendKeys(t1);
    	getDriver().findElement(txtStartTime).sendKeys(Keys.TAB);
    }
    
    public void fillEndDate(String d2) {
    	getDriver().findElement(txtEndDate).sendKeys(d2);
    	getDriver().findElement(txtEndDate).sendKeys(Keys.TAB);
    }
    
    public void fillEndTime(String t2) {
    	getDriver().findElement(txtEndTime).sendKeys(t2);
    	getDriver().findElement(txtEndTime).sendKeys(Keys.TAB);
    }
    
    public void clickSaveSchedule() {
    	getDriver().findElement(btnSave).click();
    }
    public void clickScheduleMeeting() {
    	waitforElement(getDriver().findElement(tabScheduleMeeting), driver);
    	getDriver().findElement(tabScheduleMeeting).click();
    }
    public void clickNewMeeting() {
    	waitforElement(getDriver().findElement(btnNewMeeting), driver);
    	getDriver().findElement(btnNewMeeting).click();
    }
    public void btnOKClick() {
    	waitforElement(getDriver().findElement(btnOK), driver);
    	getDriver().findElement(btnOK).click();
    }
    
    public String printMeetingSchdules(String name ) {
    	
    	
    	waitforElement(getDriver().findElements(lstScheduleNames), driver);
       	List<WebElement> schedules =  getDriver().findElements(lstScheduleNames);
    	for(WebElement schedule :schedules) {
    		
    		if(schedule.getText().equals(name)) {
    			System.out.println("Schedule Name : "+schedule.getText());
    			return schedule.getText();
    		}
    			
    	}
    	return null;
    	
    }
    public String createNewMeeting(String title,String d1,String t1,String d2,String t2) throws InterruptedException {
    	clickScheduleMeeting();
    	clickNewMeeting();
    	fillMeetitmgTitle(title);
    	fillStartDate(d1);
    	fillStartTime(t1);
    	fillEndDate(d2);
    	fillEndTime(t2);
    	clickSaveSchedule();
    	Thread.sleep(10000);
    	btnOKClick();
    	Thread.sleep(3000);
    	return printMeetingSchdules(title);
    	
    }
    
    public boolean UpdateMeeting(String title) throws InterruptedException {
    	
    	driver.findElement(By.xpath(lstScheduleNames+"[contains(text(),'"+title+"')]/following::button[contains(text(),'Edit')]")).click();
    	Thread.sleep(4000);
    		
    	
    	
    	return true;
    	
    }

}
