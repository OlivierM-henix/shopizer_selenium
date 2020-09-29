package src.main.java.outilsTechniques;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class OutilsGeneraux {

	static WebDriver driver;

	public static WebDriver choisirNavigateur(ENavigateur nav) {
		switch (nav) {
		case firefox:
			System.setProperty("webdriver.gecko.driver", "src/test/resources/webdrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			return driver;
		case chrome:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
			driver = new ChromeDriver();
			return driver;
		case ie:
			System.setProperty("webdriver.ie.driver", "src/test/resources/webdrivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			return driver;
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
