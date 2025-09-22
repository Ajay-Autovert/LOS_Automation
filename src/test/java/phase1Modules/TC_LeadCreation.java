package phase1Modules;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import genericLibrary.Base_Class;
import genericLibrary.LoginUtility;
import pomRepository.HomePage;
import pomRepository.LeadCreationPage;
import pomRepository.QDE_Page;
import apiLibrary.APIResponces;

public class TC_LeadCreation extends Base_Class {

    @Test
    public void createDocumentAndAttachToTemplate() throws InterruptedException, AWTException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Common login + role selection
        LoginUtility loginUtil = new LoginUtility(driver);
        loginUtil.loginToApplication("email_Sales", "password_Sales");
        Reporter.log("", true);

        // Step 11: Validate home page
        HomePage homePage = new HomePage(driver);
        wait.until(ExpectedConditions.visibilityOf(homePage.getCollapseBarValidation()));
        homePage.getCollapseBar();
        
        wait.until(ExpectedConditions.visibilityOf(homePage.getCreateNewLeadBtn()));
        Assert.assertTrue(homePage.getCreateNewLeadBtn().isDisplayed(), "User is not on home page");
        homePage.getCreateNewLeadBtn().click();
        
        // Verify we're on Sign In page
        LeadCreationPage leadCreationPage = new LeadCreationPage(driver);
        wait.until(ExpectedConditions.visibilityOf(leadCreationPage.getleadCreationPageHeader()));
        Assert.assertTrue(leadCreationPage.getleadCreationPageHeader().isDisplayed(), "Not in Lead Creation page");
        Reporter.log("Sucessfully landed on Lead Creation page", true);
        leadCreationPage.fillLeadForm();
        
        //QDE 
        QDE_Page qdePage = new QDE_Page(driver);
        leadCreationPage.scrollBy(200);
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(normalize-space(.),'QDE')]")));
        driver.navigate().refresh();
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//h3[normalize-space(text())='Customer Details']")));
        
        // Enter OTP in the Phone field
        leadCreationPage.scrollBy(400);
        qdePage.PhoneNumberCheckBox();
        qdePage.SendOtpBtn().click();
        String custPhoneNumber = genericLibrary.PropertyFileReader.getValueProperty("cust_Phone");
        String custEmail = genericLibrary.PropertyFileReader.getValueProperty("cust_Email");
//        String custPan = genericLibrary.PropertyFileReader.getValueProperty("cust_Pan");
        String phoneOtp = APIResponces.fetchOtp(custPhoneNumber);
        String emailOtp = APIResponces.fetchOtp(custEmail);
        qdePage.getPhoneOtpInputField().sendKeys(phoneOtp);
        qdePage.getVerifyOtpBtn().click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions
        		.elementToBeClickable(By.xpath("//button[@title='Verify' and contains(@class, 'fi-icon-btn')]")));
//        qdePage.getTxtPanTextfield().sendKeys(custPan);
//        qdePage.getVerifyPanButton().click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(qdePage.getVerifyEmailButton())); 
        qdePage.getVerifyEmailButton().click();
        Thread.sleep(4000);
        qdePage.getEmailOtpInputField().sendKeys(emailOtp);
        qdePage.getEmailOtpVerifyBtn().click();
//        
//        RobotClassUtils.trackMousePosition();
        //driver.navigate().refresh();
    }
    }
