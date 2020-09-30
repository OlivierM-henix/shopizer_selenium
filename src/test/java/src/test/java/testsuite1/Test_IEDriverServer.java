package src.test.java.testsuite1;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import src.main.java.outilsTechniques.*;
import src.main.java.pageObject.*;


public class Test_IEDriverServer extends ElementsCommuns implements IConstantes {

	// Variables globales :
	ENavigateur nav = ENavigateur.ie;
	WebDriver driver;
	WebDriverWait wait;
	PageAccueil pageAccueil;
	// !!!!!!!! REMPLACER LE NOM DE LA CLASSE DU LOGGER !!!!!!!
	static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Test_IEDriverServer.class);
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	@Before
	public void miseEnConditionsInitiales() throws InterruptedException {
		logger.info("Deploiement des conditions initiales de test");
		driver = OutilsGeneraux.choisirNavigateur(nav);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Instanciation pageAuthentification
		logger.info("Accès à la page d'authentification");
		driver.get(URLSite);
		assertEquals("Importa", driver.getTitle());
		pageAccueil = PageFactory.initElements(driver, PageAccueil.class);
	}

	@After
	public void cloture() {
		logger.info("Test terminé ou interrompu - Fermeture du WebDriver");
		driver.quit();
	}

	@Ignore
	public void test1() throws InterruptedException {
		logger.info("*****Exécution du test : Ajout d'un produit et mise à jour du panier*****");
		// Ajout d'un item au panier
		pageAccueil.btn_addToCart_1.click();
		
		// Accès au panier et vérification de la présence des éléments
		PagePanier pagePanier = pageAccueil.goToPanier(driver);
		assertEquals("Passez votre commande", driver.getTitle());
		
		assertTrue(pagePanier.col_produit_image.isDisplayed());
		assertTrue(pagePanier.col_produit_nom.isDisplayed());
		assertTrue(pagePanier.col_champ_qte.isDisplayed());
		assertTrue(pagePanier.col_prix_unitaire.isDisplayed());
		assertTrue(pagePanier.col_prix_total.isDisplayed());		
		Thread.sleep(1000);
		
		// Doubler la quantité et vérifier la mise à jour du prix
		OutilsGeneraux.remplirChamp(pagePanier.col_champ_qte,"2");
		pagePanier.btn_recalculer.click();
		Thread.sleep(1000);		
		float prix_unitaire = pagePanier.extrairePrix(pagePanier.col_prix_unitaire);
		float prix_total = pagePanier.extrairePrix(pagePanier.col_prix_total);
		assertTrue(2*prix_unitaire==prix_total);
		
		// Ouverture de la page Paiement
		PagePaiement pagePaiement = pagePanier.goToPaiement();		
	}
	
	@Ignore
	public void test2() throws InterruptedException {
		logger.info("*****Exécution du test : Parcours de la catégorie table*****");
		// Se rendre sur la page table
		logger.info("Accès à la page : catégorie > tables");
		PageTables pageTables = pageAccueil.goToTables(driver);
		assertEquals("Wood Treasures - Tables", driver.getTitle());
		
		// Vérifier que chaque produit possède une photo, un nom, un prix normal, un prix réduit...
		int i = pageTables.liste_produits.size();
		logger.info("Nombre de produits de cette catégorie :" +i);
		for (int j=0 ; j<i ; j++)
		{
			WebElement produit = pageTables.liste_produits.get(j);
			String[] donneesProduit = pageTables.obtenirDonneesProduit(produit);
			
			assertNotNull(donneesProduit[0]);
			logger.info("Le produit possède une image à l'URL : " + donneesProduit[0]);
			
			assertNotNull(donneesProduit[1]);			
			logger.info("Le nom affiché du produit est : " + donneesProduit[1]);
			
			assertNotNull(donneesProduit[2]);
			logger.info(("Le prix normal du produit est :" + donneesProduit[2]));
			
			logger.info("Si le produit est en réduction, voici son prix réduit : " + donneesProduit[3]);
			
			assertNotNull(donneesProduit[4]);
			logger.info("Le prix possède le bouton d'ajout au panier :"+ donneesProduit[4]);
	
		}
		
		// Ajouter le premier produit au panier
		WebElement produit = pageTables.liste_produits.get(0);
		String[] donneesProduit = pageTables.obtenirDonneesProduit(produit);
		Thread.sleep(3000);
		
		//Accès à la page produit et vérification des données correspondent
		PageProduit pageProduit = pageTables.goToProduit(driver, produit.findElement(By.xpath("//h3[@itemprop=\"name\"]")));
		assertEquals("Wood Treasures - Natural root console", driver.getTitle());
		
		assertEquals(donneesProduit[1].toLowerCase(), driver.findElement(By.xpath("//div[@class=\"sinple-c-title\"]/h3")).getText().toLowerCase());
	}
	
	
}
