/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mediatheque.client.CategorieClient;

/**
 * @author Megou
 *
 */
public class CategorieClientTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	CategorieClient cat, cat2;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void categorieClientConstructortest() {
		cat = new CategorieClient("catTest",20,2,8,3,true);
		assertEquals("Le nom n'a pas été initialisé correctement.","catTest",cat.getNom());
		assertEquals("Le nombre d'emprunts maximal n'a pas été initialisé correctement.",20,cat.getNbEmpruntMax());
		assertEquals("La cotisation n'a pas été initialisée correctement.",2,cat.getCotisation(),0.0001);
		assertEquals("Le coeff. durée n'a pas été initialisé correctement.",8,cat.getCoefDuree(),0.0001);
		assertEquals("Le coeff. tarif n'a pas été initialisé correctement.",3,cat.getCoefTarif(),0.0001);
		assertTrue("Le code de récuction n'a pas été initialisé correctement.",cat.getCodeReducUtilise());
	}
	
	@Test
	public void categorieClientConstructorVideTest(){
		cat = new CategorieClient("catTest");
		assertEquals("Le nom n'a pas été initialisé correctement.","catTest",cat.getNom());
		assertEquals("Le nombre d'emprunts maximal n'a pas été initialisé correctement.",0,cat.getNbEmpruntMax());
		assertEquals("La cotisation n'a pas été initialisée correctement.",0,cat.getCotisation(),0.0001);
		assertEquals("Le coeff. durée n'a pas été initialisé correctement.",0,cat.getCoefDuree(),0.0001);
		assertEquals("Le coeff. tarif n'a pas été initialisé correctement.",0,cat.getCoefTarif(),0.0001);
		assertFalse("Le code de récuction n'a pas été initialisé correctement.",cat.getCodeReducUtilise());
	}
	
	@Test
	public void modifierNomTest() {
		cat = new CategorieClient("catTest");
		cat2 = new CategorieClient("catTest2");
		cat.modifierNom("nouveauNom");
		cat2.modifierNom("nouveauNom2");
		assertEquals("Le nom n'a pas été correctement modifié (1).", "nouveauNom", cat.getNom());
		assertEquals("Le nom n'a pas été correctement modifié (2).", "nouveauNom2", cat2.getNom());
	}
	
	@Test
	public void modifierMaxTest() {
		cat = new CategorieClient("catTest",20,2,8,3,true);
		cat2 = new CategorieClient("catTest2",12,5,4,8,false);
		cat.modifierMax(14);
		cat2.modifierMax(80);
		assertEquals("Le nombre d'emprunts max. n'a pas été correctement modifié (1).",14,cat.getNbEmpruntMax());
		assertEquals("Le nombre d'emprunts max. n'a pas été correctement modifié (2).",80,cat2.getNbEmpruntMax());
	}
	
	@Test
	public void modifierCotisationTest() {
		cat = new CategorieClient("catTest",20,2,8,3,true);
		cat2 = new CategorieClient("catTest2",12,5,4,8,false);
		cat.modifierCotisation(14);
		cat2.modifierCotisation(80);
		assertEquals("La cotisation n'a pas été correctement modifiée (1).",14,cat.getCotisation(),0.0001);
		assertEquals("La cotisation n'a pas été correctement modifiée (2).",80,cat2.getCotisation(),0.0001);
	}

	@Test
	public void modifierCoefDureeTest() {
		cat = new CategorieClient("catTest",20,2,8,3,true);
		cat2 = new CategorieClient("catTest2",12,5,4,8,false);
		cat.modifierCoefDuree(14);
		cat2.modifierCoefDuree(80);
		assertEquals("Le coeff. durée n'a pas été correctement modifié (1).",14,cat.getCoefDuree(),0.0001);
		assertEquals("Le coeff. durée n'a pas été correctement modifié (2).",80,cat2.getCoefDuree(),0.0001);
	}
	
	@Test
	public void modifierCoefTarif() {
		cat = new CategorieClient("catTest",20,2,8,3,true);
		cat2 = new CategorieClient("catTest2",12,5,4,8,false);
		cat.modifierCoefTarif(14);
		cat2.modifierCoefTarif(80);
		assertEquals("Le coeff. Tarif n'a pas été correctement modifié (1).",14,cat.getCoefTarif(),0.0001);
		assertEquals("Le coeff. Tarif n'a pas été correctement modifié (2).",80,cat2.getCoefTarif(),0.0001);
	}

	public void modifierCodeReducActif(boolean codeReducActif) {
		cat = new CategorieClient("catTest",20,2,8,3,true);
		cat2 = new CategorieClient("catTest2",12,5,4,8,false);
		cat.modifierCodeReducActif(false);
		cat2.modifierCodeReducActif(true);
		assertFalse("Le coeff. Tarif n'a pas été correctement modifié (1).",cat.getCodeReducUtilise());
		assertTrue("Le coeff. Tarif n'a pas été correctement modifié (2).",cat2.getCodeReducUtilise());
	}

}
