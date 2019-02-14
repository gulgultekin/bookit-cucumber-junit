package com.bookit.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	private static WebDriver driver;
	private static String os = System.getProperty("os.name").toLowerCase();

	public static void setDriver(WebDriver lunchdriver) {
		driver = lunchdriver;

	}

	public static WebDriver getDriver() {
		return driver;
	}

	private Driver() {

	}

	public static WebDriver initializeDriver() {
		if (driver == null) {
			switch (ConfigurationReader.getProperty("browser").toLowerCase()) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "chrome":
				System.out.println("----Chrome----");
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-popup-blocking");
				options.addArguments("start-maximized");
				options.addArguments("test-type");
				options.addArguments("allow-running-insecure-content");
				options.addArguments("disable-extensions");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("test-type=browser");
				options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);
				break;
			case "phantomjs":
				WebDriverManager.phantomjs().setup();
				driver = new PhantomJSDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			}
		}
		System.out.println("----Initialize Driver----");
		setDriver(driver);
		return driver;
	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static boolean isWindows() {
		return (os.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (os.indexOf("mac") >= 0);
	}
}
