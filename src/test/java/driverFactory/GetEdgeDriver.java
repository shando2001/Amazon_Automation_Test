package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class GetEdgeDriver
{
    private static WebDriver driver =  null;
    public static WebDriver getEdgeDriver() {
        if (driver == null) {
            EdgeOptions edgeDriver = new EdgeOptions();
            edgeDriver.addArguments("--incognito");
            driver = new EdgeDriver(edgeDriver);
        }
        return driver;

    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
