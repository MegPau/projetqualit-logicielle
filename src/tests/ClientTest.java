package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

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
import util.Datutil;
import util.InvariantBroken;

public class ClientTest {
	
	Client client1, client2, client3, client4;
	CategorieClient cat1,cat2, cat3;
	FicheEmprunt emprunt,emprunt1;
	Mediatheque media,media1;
	DocumentStub docu,docu1;
	Localisation local,local1;
	Genre genre,genre1;
	Date jour;
	Date expectedDate;
	
	
	@Before
	public void setUp() throws Exception {
		
		cat1 = new CategorieClient("cat1");//categorie par default
		
		cat2 = new CategorieClient("cat2");//categorie avec un max d'emprunt a 2
		cat2.modifierMax(3);
		
		cat3 = new CategorieClient("cat3");
		
		client1 = new Client("Smith", "John","adresse1",cat1); 
		
		client2 = new Client("Chun","lee","adresse6",cat2);//peut emprunter
		
		genre = new Genre("genre");		
		local = new Localisation("salle1","rayon1");
		docu = new DocumentStub("1234",local,"titre","auteur","annee",genre);
		media = new Mediatheque("media");
		
		docu.setEmpruntable(true);
		client2.setnbEmpruntsDepasses(0);
		
		client3 = new Client("Yadi","Yada","adresse",cat2);
		emprunt = new FicheEmprunt(media, client3, docu );
		
		client4 = new Client("Yo","lo","adresse4",cat2);
		genre1 = new Genre("genre1");
		local1 = new Localisation("salle2","rayon2");
		docu1 = new DocumentStub("12345",local1,"titre1","auteur1","annee1",genre1);
		docu1.setEmpruntable(true);
		media1 = new Mediatheque("media1");
		emprunt1 = new FicheEmprunt(media1, client4, docu1);
		
		jour = Calendar.getInstance().getTime();
	}
	
	
	
	@Test 
	public void clientConstructeurTest() throws OperationImpossible {
		Client client = new Client("Pierre","Haikal","rue albert",cat3);
		
		assertEquals("Le code n'est pas introduit dans le client. ","Pierre",client.getNom() );
		assertEquals("Le code n'est pas introduit dans le client. ","Haikal",client.getPrenom() );
		assertEquals("Le code n'est pas introduit dans le client. ","rue albert",client.getAdresse());
		assertEquals("Le code n'est pas introduit dans le client. ",cat3,client.getCategorie());
	}
	
	@Test(expected = OperationImpossible.class)
	public void clientConstructeurTest2() throws OperationImpossible {
		cat3.modifierCodeReducActif(false);
		Client client = new Client("Pierre","Haikal","rue albert",cat3,46);
		
	}
	

	@Test 
	public void clientConstructeurTest3() throws OperationImpossible {
		
		cat3.modifierCodeReducActif(true);
		Client client = new Client("Pierre","Haikal","rue albert",cat3,46);
		
		assertEquals("Le code n'est pas introduit dans le client. ","Pierre",client.getNom() );
		assertEquals("Le code n'est pas introduit dans le client. ","Haikal",client.getPrenom() );
		assertEquals("Le code n'est pas introduit dans le client. ","rue albert",client.getAdresse());
		assertEquals("Le code n'est pas introduit dans le client. ",cat3,client.getCategorie());
		assertEquals("Le code n'est pas introduit dans le client. ",46,client.getReduc());
		
	}
	@Test
	public void clientConstructeurTest4()
	{
		Client client = new Client("Pierre", "Haikal");
		assertEquals("Le code n'est pas introduit dans le client. ","Pierre",client.getNom() );
		assertEquals("Le code n'est pas introduit dans le client. ","Haikal",client.getPrenom() );
		
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
	public void emprunterTest2() throws OperationImpossible, InvariantBroken{
		

		int expectedEff = client3.getNbEmpruntsEffectues() + 1;
		int expectedEnC = client3.getNbEmpruntsEnCours() + 1;
		
		
		client3.emprunter(emprunt);		
		
		assertEquals("L'emprunt a-t-il bien été ajouté",emprunt,client3.getLastEmprunt());
		assertEquals("Vérification de l'incrémentation de Emprunt effectues",expectedEff,client3.getNbEmpruntsEffectues());
		assertEquals("Vérification de l'incrémentation de Emprunt en cour ",expectedEnC,client3.getNbEmpruntsEnCours());

	}
	
	
	@Test(expected = OperationImpossible.class)
	public void marquerTest() throws OperationImpossible{
		
		client1.setnbEmpruntsDepasses(1);
		client1.setnbEmpruntsEncours(1);
		
		client1.marquer();			
		
	}
	
	@Test
	public void marquerTest2() throws OperationImpossible
	{
		client1.setnbEmpruntsDepasses(0);
		client1.setnbEmpruntsEncours(1);
		
		int expectednbED = client1.getnbEmpruntsDepasses() + 1;
		
		client1.marquer();
				
		assertEquals("Vérification de l'incrementation de nbEmpruntsDepasses",expectednbED,client1.getnbEmpruntsDepasses());
		
		
	}
	
	
	@Test(expected = NoSuchElementException.class)
	public void restituerTest() throws OperationImpossible{
		
		client3.restituer(emprunt);		
		client3.getLastEmprunt();
		
	}
	
	//Expected "Restituer sans emprunt" operation impossible
	@Test(expected = OperationImpossible.class)
	public void restituerTest2() throws OperationImpossible{
				
		client3.setnbEmpruntsEncours(0);
		client3.restituer(true);
		
		
	}
	
	//Expected "Restituer en retard sans retard" operation impossible
	@Test(expected = OperationImpossible.class)
	public void restituerTest3() throws OperationImpossible{
				
		client3.setnbEmpruntsEncours(1);
		client3.setnbEmpruntsDepasses(0);
		client3.restituer(true);	
		
	}
	
	//Expected "Restituer en retard sans emprunt" operation impossible
		@Test(expected = OperationImpossible.class)
		public void restituerTest4() throws OperationImpossible{
					
			client3.setnbEmpruntsEncours(0);
			client3.setnbEmpruntsDepasses(1);
			client3.restituer(true);	
			
		}
	
	@Test
	public void restituerTest5() throws OperationImpossible{
				
		client3.setnbEmpruntsEncours(1);
		client3.setnbEmpruntsDepasses(1);
		
		int expectednbED = client3.getnbEmpruntsDepasses() - 1;
		
		client3.restituer(true);
		
		assertEquals("NbEmpruntsDepasses doit être decrémenter",expectednbED,client3.getnbEmpruntsDepasses());
		
	}
	
	@Test
	public void metAJourEmpruntsTest() throws OperationImpossible {
		
		emprunt.setDepasse(true);
		client4.setnbEmpruntsEncours(2);
		client4.setnbEmpruntsDepasses(1);
		
		client4.metAJourEmprunts();
		
		assertEquals("On enlève les emprunts depassé",0,client3.getnbEmpruntsDepasses());
	}
	
		
	@Test
	public void dateRetourTest(){
		
		int duree = 2;
		cat2.modifierCoefDuree(1.5);
		int expectedD =(int) ((double) duree * cat2.getCoefDuree());
		
		expectedDate =  client4.dateRetour(jour, duree);
		
		assertEquals("Les coefs et les duree sont les bonnes",expectedDate,Datutil.addDate(jour, expectedD) );
			
		
	}
	/*
	@Test
	public void sommeDueTest(){
		
		
	}*/
	
}
