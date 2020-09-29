package src.main.java.outilsTechniques;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class OutilsGeneraux {

	static WebDriver driver;

	public static WebDriver choisirNavigateur(ENavigateur nav) {
		switch (nav) {
		case firefox:
			System.setProperty("webdriver.gecko.driver", "src/test/resources/webdrivers/geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			driver = new FirefoxDriver(firefoxOptions);
			return driver;
		case chrome:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1200x600");
			driver = new ChromeDriver(options);
			return driver;
		case ie:
			System.setProperty("webdriver.ie.driver", "src/test/resources/webdrivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			return driver;
//		case edge :
//			System.setProperty("webdriver.edge.driver", "src/test/resources/webdrivers/msedgedriver.exe");
//			driver = new InternetExplorerDriver();
//			EdgeOptions edgeOptions = new EdgeOptions();
//			edgeOptions.useChromium = true;
//			edgeOptions.addArguments("headless");
//			WebDriver driver= new EdgeDriver(edgeOptions);
		default:
			return null;
		}
	}
	
	public static void remplirChamp(WebElement e, String s) {
		e.clear();
		e.sendKeys(s);
	}


	//Getter et Setter WebDriver
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		OutilsGeneraux.driver = driver;
	}

}
