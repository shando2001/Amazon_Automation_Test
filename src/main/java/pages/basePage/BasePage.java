
package pages.basePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public BasePage(WebDriver driver) {
        this.driver = driver;

    }
    public void explicitWaitElementLocated(By locator) {
        new WebDriverWait(driver,Duration.ofSeconds(30))
        .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void explicitWaitInvisibilityOf(By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public WebElement findElement(By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }
    public List<WebElement> findElements(By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }
    public  void returnHomePage(String url){
        driver.navigate().to(url);
    }
    public void navigationBackButton()
    {
        driver.navigate().back();
    }
}
