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
import mediatheque.LettreRappel;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Document;
import mediatheque.document.Livre;
import util.Datutil;
import util.InvariantBroken;;

/**
 * @author Megou
 *
 */
public class FicheEmpruntTest {

	Mediatheque media;
	Client client, client2;
	CategorieClient catClient;
	Document docu, docu2;
	Localisation endroit;
	Genre genre;
	FicheEmprunt fEmprunt;
	LettreRappel rappel;
	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp() throws Exception {
		
		media = new Mediatheque("Media1");
		catClient = new CategorieClient("cat", 8, 2, 2, 2, true);
		client = new Client("Haikal","Pierre", "25 rue du soleil", catClient, 25);
		client2 = new Client("Pau","Megane","35 rue de la lune", catClient, 41);
		endroit = new Localisation("salle404","rayon3");
		genre = new Genre("genre");
		docu = new DocumentStub("12345",endroit,"titre","auteur","annee",genre);
		docu2 = new DocumentStub("147", endroit,"titre", "auteur", "annee", genre);
		docu.setEmpruntable(true);
		docu2.setEmpruntable(true);
		fEmprunt = new FicheEmprunt(media, client, docu);
		
		//rappel = new LettreRappel("docu",fEmprunt);
		
	}
	
	@Test
	public void FicheEmpruntConstructeurTest() throws OperationImpossible, InvariantBroken
	{
		int duree = docu2.dureeEmprunt();
		int expectedNbTot = fEmprunt.getNbEmpruntTotal();
		FicheEmprunt fEmprunt = new FicheEmprunt(media, client2, docu2);
		
				
		assertEquals("La valeur mediatheque a mal été attribué",media,fEmprunt.getMediatheque() );
		assertEquals("La valeur client a mal été attribué",client2,fEmprunt.getClient());
		assertEquals("La valeur document a mal été attribué",docu2,fEmprunt.getDocument());
		assertEquals("La valeur date a mal été attribué",Datutil.dateDuJour(),fEmprunt.getDateEmprunt());
		assertEquals("La valeur date limite a mal été attribué",client2.dateRetour(fEmprunt.getDateEmprunt(), duree),fEmprunt.getDateLimite());
		assertEquals("La valeur depasse a mal été attribué",false,fEmprunt.getDepasse());
		assertEquals("La fiche n'est pas sur le client",client2.getLastEmprunt(),fEmprunt);
		assertEquals("Le nombre total ne s'est pas incrémenté",expectedNbTot +1 ,fEmprunt.getNbEmpruntTotal());
		
	}	
	/*
	 * Le document a bien été mis dans la fiche d'emprunt
	 */
	@Test(expected = OperationImpossible.class)
	public void FicheEmpruntConstructeurTest2() throws OperationImpossible, InvariantBroken
	{
		docu2.emprunter();

	}
	
	
	@Test
	public void correspondTest(){
		assertTrue("Erreur dans la correspondance entre client et document emprunté.",fEmprunt.correspond(client, docu));
	}
	@Test
	public void correspond2Test(){
		assertFalse("Document non emprunté par le client considéré comme tel. ", fEmprunt.correspond(client, docu2));
		assertFalse("Client ne correspondant pas à l'emprunt considéré comme tel. ", fEmprunt.correspond(client2, docu));
		assertFalse("Document et client non ne correspondant pas à l'emprunt considéré comme tel. ", fEmprunt.correspond(client2, docu2));
	}
	
	@Test
	public void restituerTest() throws InvariantBroken, OperationImpossible{
		//fEmprunt.restituer();
		// BESOIN DE CLIENT SNIF
		
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
