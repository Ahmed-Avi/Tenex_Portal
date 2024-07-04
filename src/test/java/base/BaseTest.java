package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static utils.ReadPropertyFile.readPropertyFile;

public class BaseTest {
    public  AndroidDriver driver;
    Properties CapProp = readPropertyFile("config/AppiumCapabilities.properties");
    @BeforeTest
    public void SetupDriver() throws MalformedURLException {
        DesiredCapabilities AppiumCaps = new DesiredCapabilities();
        AppiumCaps.setCapability("platformName",CapProp.getProperty("platformName"));
        AppiumCaps.setCapability("platformVersion",CapProp.getProperty("platformVersion"));
        AppiumCaps.setCapability("deviceName",CapProp.getProperty("deviceName"));
        AppiumCaps.setCapability("automationName",CapProp.getProperty("automationName"));
        AppiumCaps.setCapability("AppPackage",CapProp.getProperty("appPackage"));
        URL appiumServerUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(appiumServerUrl, AppiumCaps);
        driver.activateApp(CapProp.getProperty("appPackage"));
    }


    @AfterTest
    public void QuitSession(){
        if(driver != null)
        driver.quit();
    }

}
