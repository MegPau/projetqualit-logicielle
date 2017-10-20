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

public class ClientTest {
	
	Client client1, client2;
	CategorieClient cat1,cat2;
	FicheEmprunt emprunt;
	Mediatheque media;
	DocumentStub docu;
	Localisation local;
	Genre genre;
	
	@Before
	public void setUp() throws Exception {
		
		cat1 = new CategorieClient("cat1");//categorie par default
		
		cat2 = new CategorieClient("cat2");//categorie avec un max d'emprunt a 2
		cat2.modifierMax(3);
		
		client1 = new Client("Smith", "John","adresse1",cat1); 
		
		client2 = new Client("Chun","lee","adresse6",cat2);//peut emprunter
		
		genre = new Genre("genre");		
		local = new Localisation("salle1","rayon1");
		docu = new DocumentStub("1234",local,"titre","auteur","annee",genre);
		media = new Mediatheque("media");
		
		docu.setEmpruntable(true);
		client2.setnbEmpruntsDepasses(0);
		
		emprunt = new FicheEmprunt(media, client2, docu );
	}
	
	

	@Test
	public void aDesEmpruntsEnCoursTest() {
		client1.setnbEmpruntsEncours(1);		
		assertTrue("A des emprunts en cours",client1.aDesEmpruntsEnCours());
		client1.setnbEmpruntsEncours(0);	
		assertFalse("N'a pas d'emprunt en cours",client1.aDesEmpruntsEnCours());
	}

	@Test
	public void peutEmprunterTest(){
		
		client2.setnbEmpruntsDepasses(1);
		client2.setnbEmpruntsEncours(0);
		assertFalse("1||0 : nbEmpruntsDepasses > 0 et nbEmpruntEncours < nbMaxEmprunt",client2.peutEmprunter());
		
		client1.setnbEmpruntsDepasses(0);
		client1.setnbEmpruntsEncours(1);
		assertFalse("0||1 : nbEmpruntDepasses = 0 et nbEmpruntEncours > nbMaxEmprunt",client1.peutEmprunter());
		
		client1.setnbEmpruntsDepasses(1);
		client1.setnbEmpruntsEncours(1);
		assertFalse("1||1 : nbEmpruntsDepasses > 0 et nbEmpruntEncours > nbMaxEmprunt",client1.peutEmprunter());
		
		client2.setnbEmpruntsDepasses(0);
		client2.setnbEmpruntsEncours(0);
		assertTrue("0||0 : nbEmpruntDepasses = 0 et nbEmpruntEncours < nbMaxEmprunt", client2.peutEmprunter());
	}
	
	@Test
	public void emprunterTest1(){
		
		client2.setnbEmpruntsEncours(0);
		client2.setnbEmpruntsEffectues(0);
		client2.setnbEmpruntsDepasses(0);
		
		int expectedEff = client2.getNbEmpruntsEffectues() + 1;
		int expectedEnC = client2.getNbEmpruntsEnCours() + 1;
		
		client2.emprunter();
		
		assertEquals("Vérification de l'incrémentation de Emprunt effectues",expectedEff,client2.getNbEmpruntsEffectues());
		assertEquals("Vérification de l'incrémentation de Emprunt en cour ",expectedEnC,client2.getNbEmpruntsEnCours());

	}
	
	@Test
	public void emprunterTest2(){
		
		client2.setnbEmpruntsEncours(0);
		client2.setnbEmpruntsEffectues(0);
		client2.setnbEmpruntsDepasses(0);

		int expectedEff = client2.getNbEmpruntsEffectues() + 1;
		int expectedEnC = client2.getNbEmpruntsEnCours() + 1;
		
		client2.emprunter(emprunt);		
		
		assertEquals("L'emprunt a-t-il bien été ajouté",emprunt,client2.getLastEmprunt());
		assertEquals("Vérification de l'incrémentation de Emprunt effectues",expectedEff,client2.getNbEmpruntsEffectues());
		assertEquals("Vérification de l'incrémentation de Emprunt en cour ",expectedEnC,client2.getNbEmpruntsEnCours());

	}
	
	/*
	@Test
	public void marquerTest(){
		fail("Not yes implemented");
	}
	
	
	@Test
	public void restituerTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void restituerEnRetardTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void metAJourEmpruntsTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void dateRetourTest(){
		
	}
	
	@Test
	public void sommeDueTest(){
		
	}
	*/
}
