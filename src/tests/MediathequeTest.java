/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mediatheque.Mediatheque;

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
	}


	@Test
	public void chercherGenreTest() {
		fail("Not yet implemented");
	}
	@Test
	public void supprimerGenreTest() {
		fail("Not yet implemented");
	}
	@Test
	public void ajouterGenreTest() {
		fail("Not yet implemented");
	}
	@Test
	public void modifierGenreTest() {
		fail("Not yet implemented");
	}
	@Test
	public void listerGenresTest(){
		
	}
	
	@Test
	public void supprimerLocalisationTest(){
		
	}
	
	@Test
	public void chercherLocalisationTest(){
		
	}
	@Test
	public void ajouterLocalisationTest(){
		
	}
	
	@Test
	public void modifierLocalisationTest(){
		
	}
	@Test
	public void listerLocalisationsTest(){
		
	}
	
	/*
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
