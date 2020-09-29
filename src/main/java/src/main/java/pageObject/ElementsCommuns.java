package src.main.java.pageObject;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class ElementsCommuns {

	WebDriver driver;
	static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ElementsCommuns.class);

	/// WEBELEMENTS 
	@FindBy(xpath = "//*[text()=\"Panier d'achat \"]")
	WebElement btn_affiche_panier;
	
	@FindBy(xpath = "//*[text()=\"Paiement\"]")
	WebElement btn_goTo_panier;
	
	@FindBy(xpath = "//a[@href=\"/shop/category/tables.html/ref=c:1\"]")
	WebElement btn_menu_goTo_tables;
	

	/// METHODES COMMUNES

	
	public static float extrairePrix(WebElement e) {
		String prix_string_avec_devise = e.getText();
		logger.info("Prix de l'article :" + prix_string_avec_devise);
		String prix_string_sans_devise = prix_string_avec_devise.split("[$]")[1].replace(",",".");
		logger.info("Prix de l'article :" + prix_string_sans_devise);
		Float prix_double = Float.parseFloat(prix_string_sans_devise);
		return prix_double;
	}
	
	/// PAGES OBJECT
	
	public PagePanier goToPanier (WebDriver driver) throws InterruptedException {
		Actions a = new Actions(driver);
		a.moveToElement(btn_affiche_panier).build().perform();
		Thread.sleep(1500);
		btn_goTo_panier.click();
		return PageFactory.initElements(driver, PagePanier.class);
	}

	public PageTables goToTables (WebDriver driver) {
		btn_menu_goTo_tables.click();
		return PageFactory.initElements(driver, PageTables.class);
	}
		
		public PageProduit goToProduit (WebDriver driver, WebElement e) {
			e.click();
			return PageFactory.initElements(driver, PageProduit.class);
		}

}