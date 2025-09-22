package genericLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrayNavigationUtility {

    @SuppressWarnings("unused")
	private WebDriver driver;
    private WebDriverWait wait;

    public TrayNavigationUtility(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Clicks a Main Tab (App Creation, Risk Check, etc.)
     * @param mainTabName - Name of the main tab
     */
    public void clickMainTab(String mainTabName) {
        String xpath = "//span[normalize-space(text())='" + mainTabName + "']";
        WebElement mainTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        mainTab.click();
    }

    /**
     * Clicks a Sub Tab under the currently expanded Main Tab
     * @param subTabName - Name of the sub tab (e.g., KYC, QDE)
     */
    public void clickSubTab(String subTabName) {
    	
        String xpath = "//span[normalize-space(text())='" + subTabName + "']";
        WebElement subTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        subTab.click();
    }

    /**
     * Verify page navigation by checking for a unique element
     * @param locator - Unique element locator of the page
     */
    public void verifyPage(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
