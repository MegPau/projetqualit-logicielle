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

import mediatheque.FicheEmprunt;
import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.client.Client;
import mediatheque.document.Document;;

/**
 * @author Megou
 *
 */
public class FicheEmpruntTest {

	Mediatheque media;
	Client client;
	Document docu;
	Localisation endroit;
	Genre genre;
	FicheEmprunt fEmprunt;
	
	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp() throws Exception {
		
		media = new Mediatheque("Media1");
		client = new Client("Haikal","Pierre");
		endroit = new Localisation("salle404","rayon3");
		genre = new Genre("genre");
		
		docu = new Document("12345",endroit,"titre","auteur","annee",genre);
		
		fEmprunt = new FicheEmprunt(media, client, docu);
		
		
	}


	@Test
	public void verifierTest() {
		//assertTrue()
		fail("Not yet implemented");
	}

	@Test
	public void premierRappelTest(){
		
	}
	
	@Test
	public void relancerTest(){
		
	}
	
	@Test
	public void correspondTest(){
		assertTrue("client haikalp docu",fEmprunt.correspond(client, docu));
	}
	
	@Test
	public void restituerTest(){
		
	}
	
	@Test
	public void changementCategorieTest(){
		
	}
}
