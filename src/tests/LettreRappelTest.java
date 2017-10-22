package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mediatheque.FicheEmprunt;
import mediatheque.Genre;
import mediatheque.LettreRappel;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Document;

public class LettreRappelTest {
	
	Mediatheque media;
	Client client;
	CategorieClient catClient;
	Document docu;
	Localisation endroit;
	Genre genre;
	FicheEmprunt fEmprunt;
	LettreRappel rappel;
	
	@Before
	public void setUp() throws Exception {
		
		media = new Mediatheque("Media1");
		endroit = new Localisation("salle","rayon");
		catClient = new CategorieClient("cat", 8, 2, 2, 2, true);
		client = new Client("H","P", "25 rue du soleil", catClient,56);
		genre = new Genre("genre5");
		docu = new DocumentStub("69",endroit,"titre13","auteur25","annee3",genre);
		docu.setEmpruntable(true);
	
		fEmprunt = new FicheEmprunt(media, client, docu);
		
	}
	
	@Test
	public void LettreRappelConstructeurTest()
	{
		rappel = new LettreRappel("MediaTest", fEmprunt);
		
		assertEquals("La valeur daterappel n'a pas été pris en compte",rappel.getDateRappel(),fEmprunt.getDateLimite());
		assertEquals("La valeur nomMedia n'a pas été pris en compte","MediaTest",rappel.getNomMedia());
		assertEquals("La valeur de l'emprunt en retard n'a pas été pris en compte",rappel.getEnretard(),fEmprunt);
		assertEquals("La valeur de l'emprunt en retard n'a pas été pris en compte",rappel.getEnretard(),fEmprunt);
		
	}
	
	

}
