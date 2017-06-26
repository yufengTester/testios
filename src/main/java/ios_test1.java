/**
 * Created by gloria on 17/1/22.
 */


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
public class ios_test1 {

    
    public static void main(String[] args) throws MalformedURLException {

//        WebDriver wd;
        AppiumDriver wd;
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","ios");
        capabilities.setCapability("platformVersion", "10.3");
        capabilities.setCapability("deviceName", "iPhone 5s");
        capabilities.setCapability("udid", "2b01b1d98986abcb703c2fd5916618bda25531e4");
        capabilities.setCapability("bundleId", "com.tqmall.Motormaster");
//        capabilities.setCapability("app", "/Users/test_ge/Documents/app/Motormaster.app");

//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        // 下面是为了消除打开app时的弹窗
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("waitForAppScript","$.delay(5000); $.acceptAlert(); true;");

        capabilities.setCapability("resetKeyboard", "True");
        wd = new IOSDriver(new URL("http://192.168.10.157:3456/wd/hub"), capabilities);
        System.out.print("1");
//        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        By account = By.xpath("//*[@name=\"淘汽云修\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]");
        By passwd = By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField[1]");
        wd.findElement(account).clear();
        wd.findElement(account).sendKeys("18668192462");
        wd.findElement(passwd).sendKeys("1234");
        if(wd.findElement(By.name("登  录")).isEnabled()){
            wd.findElement(By.name("登  录")).click();
        }
        wd.close();

    }
}
