package src.main.java.outilsTechniques;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ReadXMLFile implements IConstantes {

	static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ReadXMLFile.class);

	public static Map<String, String> creerMapUtilisateursPermanents(File JDD) {

		logger.info("Construction de la map des utilisateurs permanents");

		Map<String, String> map_utilisateursPermanents = new HashMap<>();

		/*
		 * Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
		 */
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			/*
			 * Etape 2 : création d'un parseur DOM (analyseur syntaxique du DOM du XML)
			 */
			final DocumentBuilder builder = factory.newDocumentBuilder();

			/*
			 * Etape 3 : création d'un Document
			 */
			final Document document = builder.parse(JDD);
//					.parse(new File(repertoire_racine_projet + "/src/test/resources/jdd_identifiants.xml"));

			// Affichage du prologue
//			System.out.println("************* PROLOGUE ************");
//			System.out.println("version : " + document.getXmlVersion());
//			System.out.println("encodage : " + document.getXmlEncoding());
//			System.out.println("standalone : " + document.getXmlStandalone());

			/*
			 * Etape 4 : récupération de l'Element racine
			 */
			final Element racine = document.getDocumentElement();

			// Affichage de l'élément racine
//			System.out.println("\n************* RACINE ************");
//			System.out.println(racine.getNodeName());

			/*
			 * Etape 5 : récupération des identifiants
			 */
			final NodeList racineNoeuds = racine.getChildNodes();
			final int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) {
				if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element testID = (Element) racineNoeuds.item(i);

					// Affichage d'un identifiant testID
//					System.out.println("\n************* testID ************");
//					System.out.println("Numéro ID : " + testID.getAttribute("id"));

					/*
					 * Etape 6 : récupération de l'identifiant et du mdp
					 */
					final Element type = (Element) testID.getElementsByTagName("type").item(0);
					final Element identifiant = (Element) testID.getElementsByTagName("identifiant").item(0);
					final Element mdp = (Element) testID.getElementsByTagName("mdp").item(0);

					// Affichage de l'identifiant et du mot de passe
//					System.out.println("Type d'utilisateur : " + type.getTextContent());
//					System.out.println("identifiant : " + identifiant.getTextContent());
//					System.out.println("mot de passe : " + mdp.getTextContent());

					// Insertion du type et de l'identifiant dans la map
					map_utilisateursPermanents.put(type.getTextContent(), identifiant.getTextContent());
				}
			}
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		logger.info("Map des utilisateurs permanents créée !");
		return map_utilisateursPermanents;
	}
}