package SiteBuy;


import java.time.Duration;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
//@CucumberOptions(publish = true)
public class BuyStepDefinations {
	private WebDriver driver;
	static ExtentReports extent;
	@Before
	public void setup() {
		
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Automation");
		 extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ayan Ghosh");
		extent.createTest("Initial Test");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ayanghosh\\Documents\\BrowserDrivers\\chromedriver.exe");
		//WebDriverManager.chromiumdriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
	}

	@Given("^go to website (.+) (.+)$")
	public void go_to_website(String email, String password) {
		

		driver.findElement(By.xpath("//a[@class='ico-login']")).click();

		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);

		driver.findElement(
				By.xpath("//input[@class='button-1 login-button']"))
				.click();
	}

	@When("^add to cart (.+)$")
	public void add_to_cart(String product) {
		driver.findElement(By.xpath("//input[@id='small-searchterms']")).click();

		driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(product);
		driver.findElement(By.xpath("//input[@class='button-1 search-box-button']")).click();// search for
																										// laptop

		driver.findElement(By.xpath(
				"//input[@class='button-2 product-box-add-to-cart-button']"))
				.click();

		driver.findElement(By.xpath("//li[@id='topcartlink']/a/span[1]")).click();
		String actual = driver
				.findElement(By.xpath(
						"//div[@class='page-title']/h1"))
				.getText();// assert for shopping cart
		String expected = "Shopping cart";
		Assert.assertEquals(actual, expected);

		driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
		driver.findElement(By.id("checkout")).click();
	}

	@And("^give the adress (.+)(.+) (.+) (.+) (.+)$")
	public void give_the_adress(String comname, String city, String adress, String pin, String num) {
		
		driver.findElement(By.id("BillingNewAddress_Company")).sendKeys(comname);
		WebElement staticDropdown = driver.findElement(By.id("BillingNewAddress_CountryId"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText("India");
		driver.findElement(By.id("BillingNewAddress_City")).sendKeys(city);
		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys(adress);
		driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys(pin);
		driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys(num);
		driver.findElement(By.xpath("//div[@id='billing-buttons-container']/input")).click();
		
		driver.findElement(By.cssSelector("#shipping-buttons-container > input")).click();
		
		driver.findElement(By.xpath("//input[@id='shippingoption_1']")).click();
		driver.findElement(By.cssSelector("#shipping-method-buttons-container > input")).click();
		driver.findElement(By.cssSelector("#paymentmethod_0")).click();
		driver.findElement(By.cssSelector("#payment-method-buttons-container > input")).click();// pay via cod
		driver.findElement(By.cssSelector("#payment-method-buttons-container > input")).click();// click
	
		driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();// click payment
																									// information
		
		
		driver.findElement(By.xpath("//div[@id='confirm-order-buttons-container']/input")).click();// order confirm
																									// button
	
																								
	}

}


