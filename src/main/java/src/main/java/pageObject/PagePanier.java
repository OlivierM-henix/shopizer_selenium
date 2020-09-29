package src.main.java.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PagePanier extends ElementsCommuns{
	
	
	@FindBy (xpath = "//table/tbody/tr[1]/td[@data-th=\"Article\"]/div/div[1]/img")
	public 
	WebElement col_produit_image;
	
	@FindBy (xpath = "//table/tbody/tr[1]/td[@data-th=\"Article\"]/div/div[2]/span/strong")
	public
	WebElement col_produit_nom;
	
	
	@FindBy (xpath = "//table/tbody/tr[1]/td[@data-th=\"Quantité\"]/input")
	public
	WebElement col_champ_qte;
	
	@FindBy (xpath = "//table/tbody/tr[1]/td[@data-th=\"Prix\"]/strong")
	public
	WebElement col_prix_unitaire;
	
	@FindBy (xpath = "//table/tbody/tr[1]/td[@data-th=\"Total\"]/strong")
	public
	WebElement col_prix_total;
	
	@FindBy (xpath = "//*[text()=\"Recalculer\"]")
	public
	WebElement btn_recalculer;
	
	@FindBy (xpath = "//tr[@class=\"cart-subtotal\"]/td[1]/span")
	public
	WebElement info_sous_total;	

	@FindBy (xpath = "//tr[@class=\"cart-subtotal\"]/td[2]/span")
	public
	WebElement info_total;
	
	@FindBy (xpath = "//*[text()=\"Effectuer le paiement\"]")
	public
	WebElement btn_paiement;
	
	public PagePaiement goToPaiement() {
		btn_paiement.click();
		return PageFactory.initElements(driver, PagePaiement.class);
	}

	
}
