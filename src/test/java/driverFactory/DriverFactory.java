package driverFactory;

import org.openqa.selenium.WebDriver;
public class DriverFactory
{
    public static WebDriver getWebDriver(String browser)
    {
        WebDriver driver;
        switch (browser.toLowerCase().trim())
        {
            case "chrome":
                driver = GetChromeDriver.getChromeDriver();
                break;
            case "firefox":
                    driver = GetFireFoxDriver.getFireFoxDriver();
                    break;
            case "edge":
                driver = GetEdgeDriver.getEdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not recognized: " + browser);
        }
        return driver;
    }
    public static void quitDriver(String browser)
    {
        switch (browser.toLowerCase().trim())
        {
            case "chrome":
                GetChromeDriver.quitDriver();
                break;
            case "firefox":
                    GetFireFoxDriver.quitDriver();
                    break;
            case "edge":
                    GetEdgeDriver.quitDriver();
                    break;
            default:
                throw new IllegalArgumentException("Browser not recognized: " + browser);

        }
    }
}
