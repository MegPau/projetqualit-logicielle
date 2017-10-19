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
import mediatheque.document.Document;
import mediatheque.document.Livre;
import util.InvariantBroken;

public class DocumentTest {
	
	Document document1, document2;
	
	@Before
	public void setUp() throws Exception {
		Localisation localisation = new Localisation("salle1", "rayon 1");
		Genre genre = new Genre("genre1");
		document1 = new Livre("aaa", localisation, "titre1", "auteur1", "2002", genre, 200);
	}

	@Test
	public void metEmpruntableTest() throws OperationImpossible, InvariantBroken {
		document1.metEmpruntable();
		assertTrue(document1.estEmpruntable());
	}
	
	@Test
	public void metConsultableTest() throws OperationImpossible, InvariantBroken{
		document1.metConsultable();
		assertFalse(document1.estEmpruntable());
	}
	
	@Test
	public void emprunterTest() throws OperationImpossible, InvariantBroken{
		document1.metEmpruntable();
		assertTrue(document1.emprunter());
		
	}
	@Test(expected = OperationImpossible.class)
	public void refusEmpruntTest() throws OperationImpossible, InvariantBroken{
		document1.metConsultable();
		document1.emprunter();
	}
}
