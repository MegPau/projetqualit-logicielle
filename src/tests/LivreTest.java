package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import mediatheque.document.Livre;
import util.InvariantBroken;

public class LivreTest {

	Livre livre;

	
	@Before
	public void setUp() throws Exception {
		Localisation localisation = new Localisation("salle1", "rayon 1");
		Genre genre = new Genre("genre1");
		livre = new Livre("aaa", localisation, "titre1", "auteur1", "2002", genre, 200);
	}
	
	@Test
	public void constructeurLivreTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Livre livre2 = new Livre("aaa", loca, "titre1", "auteur1", "2002", genr, 200);
		assertEquals("Le code n'est pas introduit dans le document. ","aaa",livre2.getCode());
		assertEquals("La localisation n'est pas introduite dans le document.", loca, livre2.getLocalisation());
		assertEquals("Le titre n'est pas introduit dans le document.", "titre1", livre2.getTitre());
		assertEquals("L'auteur n'est pas introduit dans le document. ", "auteur1", livre2.getAuteur());
		assertEquals("L'année n'est pas introduite dans le document. ", "2002", livre2.getAnnee());
		assertEquals("Le genre n'est pas introduit dans le document", genr, livre2.getGenre());
		assertFalse("Le document est initialisé en tant qu'empruntable", livre2.estEmpruntable());
		assertFalse("Le document est initialisé en tant qu'emprunté.", livre2.estEmprunte());
		assertEquals("Le nombre d'emprunts n'est pas initialisé à 0", 0, livre2.getNbEmprunts());
		assertEquals("Le nombre de pages n'est pas correctement introduit.", 200, livre2.getNbPages());
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurLivrePagesNegTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Livre livre2 = new Livre("aaa", loca, "titre1", "auteur1", "2002", genr, -10);
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurLivreDocNullTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = null;
		Livre livre2 = new Livre("aaa", loca, "titre1", "auteur1", "2002", genr, 100);
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurLivreDocNullPageNegTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Livre livre2 = new Livre(null, loca, "titre1", "auteur1", "2002", genr, -10);
	}
	
	@Test
	public void emprunterTest1() throws InvariantBroken, OperationImpossible {
		livre.setEmpruntable(true);
		livre.setEmprunte(false);
		int expected = livre.getStat() + 1;
		livre.emprunter();
		assertTrue("Le livre n'est pas emprunté. ",livre.estEmprunte());
		assertEquals("Le nombre d'emprunts n'est pas correct. ", expected, livre.getStat());
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest2() throws InvariantBroken, OperationImpossible {
		livre.setEmpruntable(true);
		livre.setEmprunte(true);
		livre.emprunter();
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest3() throws InvariantBroken, OperationImpossible {
		livre.setEmpruntable(false);
		livre.setEmprunte(true);
		livre.emprunter();
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest4() throws InvariantBroken, OperationImpossible {
		livre.setEmpruntable(false);
		livre.setEmprunte(false);
		livre.emprunter();
	}
	
	@Test
	public void invariantLivreTest(){
		livre.setEmpruntable(false);
		livre.setEmprunte(true);
		livre.setNbPages(10);
		assertFalse("L'invariant de Document n'est pas correct.",livre.invariantLivre());
	}

	@Test
	public void invariantLivre2Test(){
		livre.setEmpruntable(true);
		livre.setEmprunte(false);
		livre.setNbPages(10);
		assertTrue("L'invariant n'est pas correct.", livre.invariantLivre());
	}
	
	@Test
	public void invariantLivre3Test(){
		livre.setEmpruntable(false);
		livre.setEmprunte(false);
		livre.setNbPages(-10);
		assertFalse("L'invariant de Livre n'est pas correct.", livre.invariantLivre());
	}
	
	@Test
	public void invariantLivre4Test(){
		livre.setEmpruntable(true);
		livre.setEmprunte(true);
		livre.setNbPages(-10);
		assertFalse("L'invariant de Livre n'est pas correct.", livre.invariantLivre());
	}
	
}
