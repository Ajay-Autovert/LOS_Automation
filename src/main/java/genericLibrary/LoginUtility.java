package genericLibrary;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pomRepository.HomePage;
import pomRepository.LoginPage;
import pomRepository.RoleSelectionPage;

public class LoginUtility {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginUtility(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void loginToApplication(String emailKey, String passwordKey) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        
        // Validate Sign In Page
        if (!loginPage.getSignInHeader().isDisplayed()) {
            throw new RuntimeException("Not on Sign In page.");
        }

        // Perform login steps
        loginPage.clickShowPassword();
        if (!loginPage.isPasswordVisible()) {
            throw new RuntimeException("Password not visible after clicking show.");
        }

        loginPage.login(
        		PropertyFileReader.getValueProperty(emailKey),
        		PropertyFileReader.getValueProperty(passwordKey)
        );

        loginPage.clickHidePassword();
        loginPage.clickRememberMe();
        loginPage.clickSignInBtn();

        wait.until(ExpectedConditions.visibilityOf(loginPage.getTwoWheelerButton()));
        Reporter.log("Login Successful", true);

        // Role selection
        RoleSelectionPage roleSelectionPage = new RoleSelectionPage(driver);
        wait.until(ExpectedConditions.visibilityOf(roleSelectionPage.selectSalesRole()));
        roleSelectionPage.selectSalesRole();
        roleSelectionPage.selectTwoWheeler();

        new JavaScriptUtil(driver).jsScrollToBottomOfThePage();
        Thread.sleep(1000);
        roleSelectionPage.clickProceed();

        // Validate home page loaded
        HomePage homePage = new HomePage(driver);
        wait.until(ExpectedConditions.visibilityOf(homePage.getDashboardBtn()));
        if (!driver.getCurrentUrl().contains("/app")) {
            throw new RuntimeException("Login failed or not redirected to home.");
        }
        Reporter.log("Navigated to Home page", true);
    }
}
