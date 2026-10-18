package baseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import driverFactory.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigHandler;
import utilities.JSONFileManager;
import utilities.TakeScreenShot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BaseTest {
    private static final Logger LOG = LogManager.getLogger(BaseTest.class);

    public WebDriver driver;
    public ConfigHandler configHandler;
    public JSONFileManager  jsonFileManager;

    @BeforeMethod
    public void setup() {
        LOG.info("Starting setup");
        configHandler = new ConfigHandler("src/main/resources/Config.properties");
        jsonFileManager = new JSONFileManager("src/main/resources/Products.json");

        LOG.info("Initializing browser: {}", configHandler.getValue("browser"));
        driver = DriverFactory.getWebDriver(configHandler.getValue("browser"));
        LOG.info("Navigating to application URL: {}", configHandler.getValue("url"));
        driver.get(configHandler.getValue("url"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void failedTestCase(ITestResult result) throws FileNotFoundException {
        LOG.info("Starting failedTestCase");

        if (result.getStatus() == ITestResult.FAILURE) {
            LOG.error("Test failed: {}", result.getName(), result.getThrowable());
            LOG.info("Capturing screenshot for failed test: {}", result.getName());
            File image = TakeScreenShot.takeScreenShot(driver);
            Assert.assertNotNull(image);
            FileInputStream file = new FileInputStream(image);
            Allure.addAttachment("Screenshot", "image/png", file, "png");
        }
    }
    @AfterMethod
    public void tearDown() {
        LOG.info("Starting tearDown");
        LOG.info("Closing browser: {}", configHandler.getValue("browser"));
        DriverFactory.quitDriver(configHandler.getValue("browser"));
        driver = null;
    }
}
