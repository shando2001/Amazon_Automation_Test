package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot {
    private static final Logger LOG = LogManager.getLogger(TakeScreenShot.class);


    public static File takeScreenShot(WebDriver driver) {
        LOG.info("Starting takeScreenShot");

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            LOG.info("Screenshot captured at: {}", screenshot.getAbsolutePath());
            return screenshot;
        } catch (Exception e) {
            LOG.error("Unable to capture screenshot", e);
            return null;
        }
    }
}
