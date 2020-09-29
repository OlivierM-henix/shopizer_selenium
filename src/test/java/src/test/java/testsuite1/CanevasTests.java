package src.test.java.testsuite1;


import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import src.main.java.outilsTechniques.ENavigateur;
import src.main.java.outilsTechniques.IConstantes;
import src.main.java.outilsTechniques.OutilsGeneraux;
import src.main.java.pageObject.ElementsCommuns;
import src.main.java.pageObject.PageAccueil;


public class CanevasTests extends ElementsCommuns implements IConstantes {

	// Variables globales :
	ENavigateur nav = ENavigateur.chrome;
	WebDriver driver;
	WebDriverWait wait;
	PageAccueil pageAccueil;
	// !!!!!!!! REMPLACER LE NOM DE LA CLASSE DU LOGGER !!!!!!!
	static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CanevasTests.class);
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	@Before
	public void miseEnConditionsInitiales() throws InterruptedException {
		logger.info("Deploiement des conditions initiales de test");
		driver = OutilsGeneraux.choisirNavigateur(nav);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Instanciation pageAuthentification
		logger.info("Accès à la page d'authentification");
		driver.get(URLSite);
		assertEquals("ANTS - Mon compte", driver.getTitle());
		pageAccueil = PageFactory.initElements(driver, PageAccueil.class);
		Thread.sleep(3000);
	}

	@After
	public void cloture() {
		logger.info("Test terminé ou interrompu - Fermeture du WebDriver");
		driver.quit();
	}

	@Ignore
	public void test1() {
		logger.info("Exécution du test : ");

	}
}
