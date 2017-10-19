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
