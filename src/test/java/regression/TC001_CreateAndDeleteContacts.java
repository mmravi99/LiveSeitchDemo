package regression;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageobjects.Concierge_ContactsPage;
import pageobjects.Concierge_HomePage;
import pageobjects.Contacts_ContactsPage;
import pageobjects.Contacts_HomePage;
import testcomponents.BaseTest;
import testcomponents.Utilities;

public class TC001_CreateAndDeleteContacts extends BaseTest {

	public TC001_CreateAndDeleteContacts() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(dataProviderClass = Utilities.class, dataProvider = "jsondp")
	public void createAndDeleteContacts(String plt, String rm) throws IOException, URISyntaxException, InterruptedException {
		ExtentTest test = null;
		try {
			if (rm.equals("N")) {
				throw new SkipException("Skipping the test case as the Run mode for data set is N");
			}

			if (plt.equals("mobile")) {
				test = extent.createTest("TC_001 Create Schedule : " + plt.toUpperCase());
				initAndroidDriver("mobile");
				Contacts_HomePage contSiginPage = new Contacts_HomePage(adDriver);
				Contacts_ContactsPage contContactsPage = new Contacts_ContactsPage(adDriver);
				Concierge_HomePage consSignPage = new Concierge_HomePage(adDriver);
				Concierge_ContactsPage consContactPage = new Concierge_ContactsPage(adDriver);
				assertEquals(contSiginPage.navigateToSigninPage(getContactsURL()), "Sign In to LiveSwitch Contact");
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "Navigated to CONTACTS HomePage", adDriver);
				System.out.println(contSiginPage.signIn(getUsername(), getPassword()));
				waitForPage(WAIT_MAX);
				reportScreenshot(test, "PASS", "User logged in Successfully", adDriver);
				String fn = generateRandomName();
				contContactsPage.addConatacts(fn, generateRandomName(), "Creative Synegrgies", generateRandomEmail(),
						generateRandomPhoneNumber());
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "New Contact "+fn+" created successfully ", adDriver);
				consSignPage.navigateToHomePage(getConciergeURL());
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "Navigated to CONCIERGE Home Page", adDriver);
				consContactPage.clickContactsTab();
				waitForPage(WAIT_AVG);
				assertTrue(consContactPage.deleteContact(fn));
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "Contact "+fn+" deleted Successfully in CONCIERGE ", adDriver);
				contContactsPage.clickContactsTab();
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "Contact "+fn+" deleted Successfully in CONTACTS", adDriver);
				closeAdDriver();

			} else {
				test = extent.createTest("TC_001 Create Schedule : " + plt.toUpperCase());
				initBrowser(plt);
				Contacts_HomePage contSiginPage = new Contacts_HomePage(driver);
				Contacts_ContactsPage contContactsPage = new Contacts_ContactsPage(driver);
				Concierge_HomePage consSignPage = new Concierge_HomePage(driver);
				Concierge_ContactsPage consContactPage = new Concierge_ContactsPage(driver);
				assertEquals(contSiginPage.navigateToSigninPage(getContactsURL()), "Sign In to LiveSwitch Contact");
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "Navigated to HomePage", driver);
				System.out.println(contSiginPage.signIn(getUsername(), getPassword()));
				waitForPage(WAIT_MAX);
				reportScreenshot(test, "PASS", "User logged in Successfully", driver);
				String fn = generateRandomName();
				contContactsPage.addConatacts(fn, generateRandomName(), "Creative Synegrgies", generateRandomEmail(),
						generateRandomPhoneNumber());
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "New Contact "+fn+" created Successfully in CONTACTS ", driver);
				consSignPage.navigateToHomePage(getConciergeURL());
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "Navigated to Home Page", driver);
				consContactPage.clickContactsTab();
				waitForPage(WAIT_AVG);
				assertTrue(consContactPage.deleteContact(fn));
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "Contact "+fn+" deleted Successfully in CONCIERGE  ", driver);
				contContactsPage.clickContactsTab();
				waitForPage(WAIT_AVG);
				reportScreenshot(test, "PASS", "Contact "+fn+" deleted Successfully in CONTACTS ", driver);

				closeBrowser();

			}

		} catch (SkipException e) {

		} catch (AssertionError e) {

			reportScreenshot(test, "FAIL", e.toString(), driver);
		} catch (Exception e) {
			if (!rm.equals("N"))
				reportFail(test, e.toString());
		}

	}

}
