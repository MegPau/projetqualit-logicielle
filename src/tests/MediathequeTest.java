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
	
	Mediatheque media;
	
	@Before
	public void setUp() throws Exception {
		media = new Mediatheque("media");
		media.setDebug(true);
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
		assertEquals("Le genre n'est pas trouvé dans la liste", new Genre("genreTest"), media.chercherGenre("genreTest"));
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
		media.ajouterGenre("genreTest");
		Localisation localisation = new Localisation("a","b");
		DocumentStub doc = new DocumentStub("aaa", localisation, "titre1", "auteur1", "2002", media.chercherGenre("genreTest"));
		media.ajouterDocument(doc);
		media.supprimerGenre("genreTest");
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
	
	/*
	@Test
	public void modifierGenreTest() {
		fail("Not yet implemented");
	}
	@Test
	public void listerGenresTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void supprimerLocalisationTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void chercherLocalisationTest(){
		fail("Not yet implemented");
	}
	@Test
	public void ajouterLocalisationTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void modifierLocalisationTest(){
		fail("Not yet implemented");
	}
	@Test
	public void listerLocalisationsTest(){
		fail("Not yet implemented");
	}
	
	
	chercherCatClient
	supprimerCatClient
	ajouterCatClient
	modifierCatClient
	listerCatsClient

	
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
