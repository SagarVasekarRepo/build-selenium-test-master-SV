package com.build.qa.build.selenium.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Sagar Vasekar: Basic Framework for Appium tests.
 *
 * Note: This is working perfectly fine with Android.
 * This is not tested on IOS but this code should work
 * with correct settings.
 */
public abstract class AppiumBaseFramework {
    protected AppiumDriver driver;
    protected Wait<AppiumDriver> wait;

    private static final Logger LOG = LoggerFactory.getLogger(BaseFramework.class);
    private static final String CONFIG_FILE = "./conf/automation.properties";
    private static final String MOBILE_PLATFORM_ANDROID = "android";
    private static final String MOBILE_PLATFORM_IOS = "ios";
    private static Properties configuration;

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @BeforeClass
    public static void setUpClass() throws IOException {
        configuration = new Properties();
        FileInputStream input;

        LOG.info("Loading in configuration file.");
        input = new FileInputStream(new File(CONFIG_FILE));
        configuration.loadFromXML(input);
        input.close();
    }

    @Before
    public void setUpBefore() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (MOBILE_PLATFORM_ANDROID.equalsIgnoreCase(configuration.getProperty("MOBILE_PLATFORM"))) {
            File app = new File(configuration.getProperty("APK_PATH"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } else if (MOBILE_PLATFORM_IOS.equalsIgnoreCase(configuration.getProperty("MOBILE_PLATFORM"))) {
            File app = new File(configuration.getProperty("IOS_APP_PATH"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }

        // Define fluent wait
        wait = new FluentWait<AppiumDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected String getConfiguration(String config) {
        return configuration.getProperty(config);
    }

    @After
    public void tearDownAfter() {
        LOG.info("Quitting driver.");
        driver.quit();
        driver = null;
    }
}
