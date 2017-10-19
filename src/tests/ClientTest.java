package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mediatheque.FicheEmprunt;
import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;

public class ClientTest {
	
	Client client1, client2, client3, client4, client5, client6;
	CategorieClient cat1,cat2;
	FicheEmprunt emprunt;

	@Before
	public void setUp() throws Exception {
		
		cat1 = new CategorieClient("cat1");//categorie par default
		
		cat2 = new CategorieClient("cat2");//categorie avec un max d'emprunt a 2
		cat2.modifierMax(2);
		
		client1 = new Client("Smith", "John","adresse1",cat1); //A des emprunts en cours 
		client1.setnbEmpruntsEncours(1);
		
		
		client2 = new Client("Doe", "Jane","adresse2",cat1);//N'a pas d'emprunt en cours
	
		client3 = new Client("Pom","Pim","adresse3",cat1); //Depasse le nb d'emprunt		
		client3.setnbEmpruntsDepasses(1);
		client3.setnbEmpruntsEncours(0);
		
		client4 = new Client("Yadi","Yada","adresse4",cat1); //A un emprunt en cours
		client4.setnbEmpruntsDepasses(0);
		client4.setnbEmpruntsEncours(1);
		
		client5 = new Client("Yo","lo","adresse5",cat1);//a des emprunts depassé et en cours
		client5.setnbEmpruntsDepasses(1);
		client5.setnbEmpruntsEncours(1);
		
		client6 = new Client("Chun","lee","adresse6",cat2);//peut emprunter
		client6.setnbEmpruntsDepasses(0);
		client6.setnbEmpruntsEncours(0);
	}
	
	

	@Test
	public void aDesEmpruntsEnCoursTest() {
		assertTrue(client1.aDesEmpruntsEnCours());
		assertFalse(client2.aDesEmpruntsEnCours());
	}

	@Test
	public void peutEmprunterTest(){
		assertFalse(client3.peutEmprunter());
		assertFalse(client4.peutEmprunter());
		assertFalse(client5.peutEmprunter());
		assertTrue(client6.peutEmprunter());
	}
	/*
	@Test
	public void emprunterTest(){
		fail("Not yes implemented");
	}
	@Test(expected = AssertionError.class)
	public void nePeutPasEmprunterTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void marquerTest(){
		fail("Not yes implemented");
	}
	
	@Test(expected = OperationImpossible.class)
	public void marquerErreurTest(){
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
