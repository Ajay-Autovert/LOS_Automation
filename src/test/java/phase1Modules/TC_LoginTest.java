package phase1Modules;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import genericLibrary.Base_Class;
import pomRepository.HomePage;
import pomRepository.LoginPage;
import pomRepository.RoleSelectionPage;

public class TC_LoginTest extends Base_Class {

    @Test
    public void verifyValidLogin() throws InterruptedException {
    	
        // Read credentials from properties
        String email = genericLibrary.PropertyFileReader.getValueProperty("email_Sales");
        String password = genericLibrary.PropertyFileReader.getValueProperty("password_Sales");

        //Creating Login Page object
        LoginPage loginPage = new LoginPage(driver);
        
        // Verify we're on Sign In page
        Assert.assertTrue(loginPage.getSignInHeader().isDisplayed(), "Not on Sign In page");
        Reporter.log("Sucessfully landed on Sign In page", true);
        
        //Verify we're able to see the Password
        loginPage.clickShowPassword();
        Assert.assertTrue(loginPage.isPasswordVisible(), "Password is not visible after clicking show");

        //Login page fields
        loginPage.login(email, password);
        loginPage.clickHidePassword();
        loginPage.clickRememberMe();
        loginPage.clickSignInBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getTwoWheelerButton()));	
        
        // Assertion after login - assuming Role-Selection page has some unique element
        String expectedUrl = "https://los.dev.autovert.in/app/role-selection"; // replace with your actual home URL
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login failed or User is not redirected to Role-Selection page");
        Reporter.log("Sucessfully landed on Role-Selection page", true);
        
        //Creating Role-Selection Page object
        RoleSelectionPage roleSelectionpage = new RoleSelectionPage(driver);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(roleSelectionpage.selectSuperAdminRole()));	
        roleSelectionpage.selectProductByText("2W");
        
     // Clicking OK on the Chrome Password pop-up
        //RobotClassUtils.dismissPasswordPopup();
        
//        //Scroll to Bottom of the page
//        JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
//        jsUtil.jsScrollToBottomOfThePage();
        
        //This is compulsory, both Implicit wait and 
        //Explicit wait not working on this for now
        Thread.sleep(3000);
        roleSelectionpage.clickProceed();
        
        // Validate home page
        HomePage homePage = new HomePage(driver);
        wait.until(ExpectedConditions.visibilityOf(homePage.getDashboardBtn()));
        Assert.assertEquals(driver.getCurrentUrl(), "https://los.dev.autovert.in/app", "Not redirected to home page");
        Reporter.log("Login successful and Home Page loaded correctly", true);
        
    }
}
