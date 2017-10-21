/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mediatheque.FicheEmprunt;
import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.client.HashClient;
import mediatheque.document.Document;

/**
 * @author Megou
 *
 */
public class MediathequeTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	Mediatheque media, media3;
	
	@Before
	public void setUp() throws Exception {
		media = new Mediatheque("media");
		media.setDebug(true);
		
		media3 = new Mediatheque("media3");
		media3.setDebug(true);
		media3.ajouterLocalisation("salleTest2", "rayonTest2");
		media3.ajouterGenre("genreTest2");
		DocumentStub doc = new DocumentStub("aaa", media3.chercherLocalisation("salleTest2", "rayonTest2"), "titre1", "auteur1", "2002", media3.chercherGenre("genreTest2"));
		media3.ajouterDocument(doc);
	}


	@Test
	public void constructeurMediaTest(){
		Mediatheque media2 = new Mediatheque("media");
		assertEquals("Le nom n'est pas introduit.", "media", media2.getNom());
		assertEquals("La liste des genres n'est pas vide", 0, media2.getGenresSize());
		assertEquals("La liste des Localisations n'est pas vide", 0, media2.getLocalisationsSize());
		assertEquals("La liste des Documents n'est pas vide", 0, media2.getDocumentsSize());
		assertEquals("La liste des Clients n'est pas vide", 0, media2.getClientsSize());
		assertEquals("La liste des Emprunts n'est pas vide", 0, media2.getFicheEmpruntsSize());
		assertEquals("La liste des CatsClient n'est pas vide", 0, media2.getCategoriesSize());
	}
	
	@Test
	public void chercherGenreTest() throws OperationImpossible {
		media.ajouterGenre("genreTest");
		media.ajouterGenre("genreTest2");
		assertEquals("Le genre n'est pas trouvé dans la liste", new Genre("genreTest"), media.chercherGenre("genreTest"));
		assertEquals("Le genre2 n'est pas trouvé dans la liste", new Genre("genreTest2"), media.chercherGenre("genreTest2"));
	}
	
	@Test
	public void chercherGenreInexistantTest() throws OperationImpossible{
		assertEquals("Le genre inexistant à été trouvé.",null,media.chercherGenre("genretest"));
	}
	
	@Test
	public void supprimerGenreTest() throws OperationImpossible {
		media.ajouterGenre("genreTest");
		media.supprimerGenre("genreTest");
		assertEquals("Le genre n'a pas été supprimé.", null, media.chercherGenre("genreTest"));	
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerGenreInexistantTest() throws OperationImpossible {
		media.supprimerGenre("genreTest");
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerGenreAvecDocumentTest() throws OperationImpossible {
		media3.supprimerGenre("genreTest2");
	}
	
	
	@Test
	public void ajouterGenreTest() throws OperationImpossible {
		int initialSize = media.getGenresSize();
		media.ajouterGenre("genreTest");
		assertEquals("Le genre n'est pas ajouté à la liste des genres : nombre de genres non incrémenté", initialSize+1,media.getGenresSize());
		assertEquals("Le genre n'est pas ajouté à la liste des genres : genre introuvable dans la liste", new Genre("genreTest"), media.chercherGenre("genreTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void ajouterGenreExistant() throws OperationImpossible{
		media.ajouterGenre("genreTest");
		media.ajouterGenre("genreTest");
	}
	
	
	@Test
	public void modifierGenreTest() throws OperationImpossible {
		media.ajouterGenre("genreTest");
		media.modifierGenre("genreTest", "nouveauGenreTest");
		assertEquals("Genre non changé", new Genre("nouveauGenreTest"), media.chercherGenre("nouveauGenreTest"));
		assertEquals("Ancien genre toujours existant", null, media.chercherGenre("genreTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void modifierGenreInexistantTest() throws OperationImpossible {
		media.modifierGenre("genreTest", "nouveauGenreTest");
	}
	
	
	
	@Test
	public void supprimerLocalisationTest() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.supprimerLocalisation("salleTest", "rayonTest");
		assertEquals("La localisation n'a pas été supprimée", null, media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerLocalisationInexistanteTest() throws OperationImpossible {
		media.supprimerLocalisation("salleTest", "rayonTest");
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerLocalisationAvecDocumentTest() throws OperationImpossible {
		media3.supprimerLocalisation("salleTest2", "rayonTest2");
	}
	
	@Test
	public void chercherLocalisationTest() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.ajouterLocalisation("salleTest2", "rayonTest2");
		assertEquals("La localisation localisationTest n'est pas trouvée dans la liste", new Localisation("salleTest","rayonTest"), media.chercherLocalisation("salleTest","rayonTest"));
		assertEquals("La localisation localisationTest2 n'est pas trouvée dans la liste", new Localisation("salleTest2","rayonTest2"), media.chercherLocalisation("salleTest2","rayonTest2"));
	}
	
	@Test
	public void chercherLocalisationInexistanteTest() throws OperationImpossible{
		assertEquals("La localisation inexistante à été trouvée.",null,media.chercherLocalisation("salleTest","rayonTest"));
	}
	
	@Test
	public void ajouterLocalisationTest() throws OperationImpossible{
		int initialSize = media.getLocalisationsSize();
		media.ajouterLocalisation("salleTest", "rayonTest");
		assertEquals("La localisation n'est pas ajoutée à la liste des locallisations : nombre de localisations non incrémenté", initialSize+1,media.getLocalisationsSize());
		assertEquals("La localisation n'est pas ajoutée à la liste des localisations : salle introuvable dans la liste", new Localisation("salleTest", "rayonTest"), media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void ajouterLocalisationExistante() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.ajouterLocalisation("salleTest", "rayonTest");
	}
	
	@Test
	public void modifierLocalisationTest() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.modifierLocalisation(media.chercherLocalisation("salleTest", "rayonTest"), "nouvelleSalle", "nouveauRayon");
		assertEquals("Localisation non changée", new Localisation("nouvelleSalle", "nouveauRayon"), media.chercherLocalisation("nouvelleSalle", "nouveauRayon"));
		assertEquals("Ancienne Localisation toujours existante", new Localisation("salleTest", "rayonTest"), media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void modifierLocalisationInexistanteTest() throws OperationImpossible {
		Localisation localisationInexistante = new Localisation("sT","rT");
		media.modifierLocalisation(localisationInexistante,"salleTest", "rayonTest");
	}
	
	
	/*
	
	chercherCatClient
	supprimerCatClient
	ajouterCatClient
	modifierCatClient
	@Test
	public void supprimerLocalisationTest() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.supprimerLocalisation("salleTest", "rayonTest");
		assertEquals("La localisation n'a pas été supprimée", null, media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerLocalisationInexistanteTest() throws OperationImpossible {
		media.supprimerLocalisation("salleTest", "rayonTest");
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerLocalisationAvecDocumentTest() throws OperationImpossible {
		media3.supprimerLocalisation("salleTest2", "rayonTest2");
	}
	*/
	@Test
	public void chercherCatClientTest() throws OperationImpossible{
		media.ajouterCatClient("catTest", 20, 8, 2, 3, true);
		media.ajouterCatClient("catTest2", 20, 8, 2, 3, true);
		assertEquals("La catégorie client catTest n'est pas trouvée dans la liste", new CategorieClient("catTest", 20, 8, 2, 3, true), media.chercherCatClient("catTest"));
		assertEquals("La catégorie client catTest2 n'est pas trouvée dans la liste", new CategorieClient("catTest2", 20, 8, 2, 3, true), media.chercherCatClient("catTest2"));
	}
	
	@Test
	public void chercherCatClientInexistanteTest() throws OperationImpossible{
		assertEquals("La catégorie client inexistante à été trouvée.",null,media.chercherCatClient("catTest"));
	}
	/*
	@Test
	public void ajouterLocalisationTest() throws OperationImpossible{
		int initialSize = media.getLocalisationsSize();
		media.ajouterLocalisation("salleTest", "rayonTest");
		assertEquals("La localisation n'est pas ajoutée à la liste des locallisations : nombre de localisations non incrémenté", initialSize+1,media.getLocalisationsSize());
		assertEquals("La localisation n'est pas ajoutée à la liste des localisations : salle introuvable dans la liste", new Localisation("salleTest", "rayonTest"), media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void ajouterLocalisationExistante() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.ajouterLocalisation("salleTest", "rayonTest");
	}
	
	@Test
	public void modifierLocalisationTest() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.modifierLocalisation(media.chercherLocalisation("salleTest", "rayonTest"), "nouvelleSalle", "nouveauRayon");
		assertEquals("Localisation non changée", new Localisation("nouvelleSalle", "nouveauRayon"), media.chercherLocalisation("nouvelleSalle", "nouveauRayon"));
		assertEquals("Ancienne Localisation toujours existante", new Localisation("salleTest", "rayonTest"), media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void modifierLocalisationInexistanteTest() throws OperationImpossible {
		Localisation localisationInexistante = new Localisation("sT","rT");
		media.modifierLocalisation(localisationInexistante,"salleTest", "rayonTest");
	}

	
	chercherDocument
	ajouterDocument
	retirerDocument
	metEmpruntable
	metConsultable
	listerDocuments
	existeDocument

	
	emprunter
	restituer
	verifier
	
	inscrire
	resilier
	modifierClient
	changerCategorie
	changerCodeReduction
	chercherClient
	existeClient
	
	initFromFile
	saveToFile
	*/
	
	
}
