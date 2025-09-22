package pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericLibrary.TrayNavigationUtility;

public class TrayPage {

    @SuppressWarnings("unused")
	private WebDriver driver;
    private TrayNavigationUtility trayUtil;

    public TrayPage(WebDriver driver) {
        this.driver = driver;
        this.trayUtil = new TrayNavigationUtility(driver);
    }

    // -------- MAIN TABS --------

    public void goToAppCreation() {
        trayUtil.clickMainTab("App Creation");
        trayUtil.verifyPage(By.xpath("//span[normalize-space(text())='Leads']")); // default first sub-tab page
    }

    public void goToRiskCheck() {
        trayUtil.clickMainTab("Risk Check");
        trayUtil.verifyPage(By.xpath("//span[normalize-space(text())='FCU']"));
    }

    public void goToUnderwriting() {
        trayUtil.clickMainTab("Underwriting");
        trayUtil.verifyPage(By.xpath("//h3[contains(text(),'Underwriting')]"));
    }

    // -------- SUB TABS (under App Creation) --------

    public void goToLeads() {
        trayUtil.clickSubTab("Leads");
        trayUtil.verifyPage(By.xpath("//button[contains(normalize-space(.),'Leads')]"));
    }

    public void goToQDE() {
        trayUtil.clickSubTab("QDE");
        trayUtil.verifyPage(By.xpath("//button[contains(normalize-space(.),'QDE')]"));
    }

    public void goToKYC() {
        trayUtil.clickSubTab("KYC");
        trayUtil.verifyPage(By.xpath("//button[contains(normalize-space(.),'KYC')]"));
    }

    public void goToAddressDetails() {
        trayUtil.clickSubTab("Address Details");
        trayUtil.verifyPage(By.xpath("//button[contains(normalize-space(.),'Address Details')]"));
    }

    // Add more sub-tabs as needed...
}
