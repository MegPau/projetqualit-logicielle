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
		livre.metEmpruntable();
		assertTrue(livre.emprunter());
	}
	
	/*@Test(expected = OperationImpossible.class)
	public void emprunterTest2() throws InvariantBroken, OperationImpossible {
		livreempruntenonempruntable.emprunter();
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest3() throws InvariantBroken, OperationImpossible {
		livreemprunteempruntable.emprunter();
	}*/
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest4() throws InvariantBroken, OperationImpossible {
		livre.emprunter();
	}

}
