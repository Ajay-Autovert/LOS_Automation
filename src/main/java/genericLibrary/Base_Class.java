package genericLibrary;
/***
 * Ajay A
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.time.Duration;
import java.util.HashMap;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomRepository.HomePage;

public class Base_Class implements FrameworkConstants {

	public static WebDriver driver;
	public WebDriverWait explicitWait;
	public PropertyFileReader PropertyFileReader;
	public HomePage homePage;

	@SuppressWarnings("serial")
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void openTheBrowser(@Optional("chrome") String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-save-password-bubble");
			options.addArguments("--disable-notifications");
			options.addArguments("--guest"); // Open in Guest mode
			options.addArguments("--no-default-browser-check");
			options.addArguments("--disable-infobars");
			options.addArguments("--incognito"); // Important: prevent Google profile influence
			options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
			    put("credentials_enable_service", false);
			    put("profile.password_manager_enabled", false);
			}});

			driver = new ChromeDriver(options);
			Reporter.log("Successfully Launched Chrome Browser", true);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Reporter.log("Successfully Launched Firefox Browser", true);
		} else {
			Reporter.log("Enter valid Browser name");
		}
		driver.manage().window().maximize();
		Reporter.log("Browser window is maximized successfully", true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_TIMEOUT));
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));		
	}

	@BeforeMethod(alwaysRun = true)
	public void navigateToApplication() {
		PropertyFileReader = new PropertyFileReader();
		@SuppressWarnings("static-access")
		String url = PropertyFileReader.getValueProperty("url");

		driver.get(url);
		homePage = new HomePage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void closeTheBrowser() {
		if (driver != null) {
	      //  driver.quit();
	    }
	}
}
