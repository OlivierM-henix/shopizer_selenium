package src.main.java.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class PageCategory extends ElementsCommuns {

	@FindBy(xpath = "//section[@class=\"products-grid\"]/div[1]/div")
	public List<WebElement> liste_produits;

	public String[] obtenirDonneesProduit(WebElement produit) {

		logger.info(">>>>> Analyse d'un produit");
		WebElement e1 = produit.findElement(By.xpath("//div//img"));
		String produit_photo = e1.getAttribute("src");

		WebElement e2 = produit.findElement(By.xpath("//h3[@itemprop=\"name\"]"));
		String produit_nom = e2.getText();

		String produit_prix;
		String produit_prix_reduit;

		try {
			WebElement e4 = produit.findElement(By.xpath("//span[@itemprop=\"price\"]"));
			produit_prix_reduit = e4.getText();
			produit_prix = produit.findElement(By.xpath("//del")).getText();
		} catch (Exception e) {
			WebElement e3 = produit.findElement(By.xpath("//span[@itemprop=\"price\"]"));
			produit_prix = e3.getText();
			produit_prix_reduit=null;
		}

		;
		WebElement e5 = produit.findElement(By.xpath("//a[@class=\"addToCart\"]"));
		boolean produit_btn_addToCart = false;
		if (e5.isDisplayed()) {
			produit_btn_addToCart = true;
		}

		String[] donneesProduit = new String[] { produit_photo, produit_nom, produit_prix, produit_prix_reduit,
				Boolean.toString(produit_btn_addToCart) };

		return donneesProduit;
	}
}
