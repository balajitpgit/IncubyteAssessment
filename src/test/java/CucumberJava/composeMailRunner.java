package CucumberJava;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class composeMailRunner {

	WebDriver driver = null;

	@Given("^There is a user who visits yahoo login page$")
	public void given() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://in.mail.yahoo.com/");
		Thread.sleep(1000);

		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@And("^User login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void loginWithIdAndPassword(String Username, String Password) throws Throwable {

		// Enter mail id
		driver.findElement(By.id("login-username")).sendKeys(Username);
		driver.findElement(By.id("login-signin")).click();

		Thread.sleep(100);

		// Enter Password
		driver.findElement(By.id("login-passwd")).sendKeys(Password);

		// click Next
		driver.findElement(By.id("login-signin")).click();

	}

	@When("^User sends an email to \"([^\"]*)\" with subject \"Incubyte\" and body \"Automation QA test for Incubyte\"$")
	public void composeEmail(String toMailID) throws Throwable {

		// click Compose button
		driver.findElement(By.xpath("//a[@aria-label='Compose']")).click();

		// Enter To
		driver.findElement(By.id("message-to-field")).sendKeys(toMailID);

		// Enter subject
		driver.findElement(By.xpath("//input[@aria-label='Subject']")).sendKeys("Incubyte");

		// Enter body
		driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys("Automation QA test for Incubyte");

		// Click Send button
		driver.findElement(By.xpath("//span[text()='Send']")).click();

	}

	@Then("^The email appears in the sent folder of yahoo with subject \"Incubyte\"$")

	public void mailVerificationInSentFolder() throws Throwable {

		// Click Sent
		driver.findElement(By.xpath("//span[text()='Sent']")).click();

		// Checking whether the mail is in sent folder
		Assert.assertTrue(driver.findElements(By.xpath("//span[@title='Incubyte']")).size() > 0);

		driver.close();

	}

}