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
import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Document;
import mediatheque.document.Livre;
import util.InvariantBroken;;

/**
 * @author Megou
 *
 */
public class FicheEmpruntTest {

	Mediatheque media;
	Client client;
	CategorieClient catClient;
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
		catClient = new CategorieClient("cat", 8, 2, 2, 2, true);
		client = new Client("Haikal","Pierre", "25 rue du soleil", catClient, 25);
		endroit = new Localisation("salle404","rayon3");
		genre = new Genre("genre");
		docu = new DocumentStub("12345",endroit,"titre","auteur","annee",genre);
		docu.setEmpruntable(true);
		
		fEmprunt = new FicheEmprunt(media, client, docu);
		
		
	}

	
	@Test
	public void correspondTest(){
		assertTrue("Erreur dans la correspondance entre client et document emprunté.",fEmprunt.correspond(client, docu));
	}
	
	
	
	
	@Test
	public void restituerTest() throws InvariantBroken, OperationImpossible{
		fEmprunt.restituer();
	}
	
	
	@Test
	public void changementCategorieTest() throws OperationImpossible{
		fEmprunt.setDepasse(true);
		assertTrue("L'emprunt n'est pas considéré comme dépassé. ", fEmprunt.changementCategorie());
	}
	
	@Test
	public void changementCategorie2Test() throws OperationImpossible{
		fEmprunt.setDepasse(false);
		assertFalse("L'emprunt est considéré comme dépassé. ",fEmprunt.changementCategorie());
	}
}
