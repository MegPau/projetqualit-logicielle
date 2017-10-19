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
	
	DocumentStub document1, document2;
	
	@Before
	public void setUp() throws Exception {
		Localisation localisation = new Localisation("salle1", "rayon 1");
		Genre genre = new Genre("genre1");
		document1 = new DocumentStub("aaa", localisation, "titre1", "auteur1", "2002", genre);
	}

	@Test
	public void metEmpruntableTest() throws OperationImpossible, InvariantBroken {
		document1.setEmpruntable(false);
		document1.setEmprunte(false);
		document1.metEmpruntable();
		assertTrue(document1.estEmpruntable());
	}
	
	@Test(expected = OperationImpossible.class)
	public void metEmpruntableRefusEmpruntableTest() throws OperationImpossible, InvariantBroken{
		document1.setEmpruntable(true);
		document1.setEmprunte(false);
		document1.metEmpruntable();
	}
	
	@Test(expected = InvariantBroken.class)
	public void metEmpruntableRefusInvariantTest() throws OperationImpossible, InvariantBroken{
		document1.setEmpruntable(false);
		document1.setEmprunte(true);
		document1.metEmpruntable();
	}
	
	@Test
	public void metConsultableTest() throws OperationImpossible, InvariantBroken{
		document1.setEmpruntable(true);
		document1.setEmprunte(false);
		document1.metConsultable();
		assertFalse(document1.estEmpruntable());
	}
	
	@Test(expected = OperationImpossible.class)
	public void metConsultableRefusNonEmpruntableTest() throws OperationImpossible, InvariantBroken{
		document1.setEmpruntable(false);
		document1.setEmprunte(false);
		document1.metConsultable();
	}
	
	@Test(expected = OperationImpossible.class)
	public void metConsultableRefusEmprunteTest() throws OperationImpossible, InvariantBroken{
		document1.setEmpruntable(true);
		document1.setEmprunte(true);
		document1.metConsultable();
	}
	
	
	@Test
	public void emprunterTest() throws OperationImpossible, InvariantBroken{
		document1.setEmpruntable(true);
		document1.setEmprunte(false);
		assertTrue(document1.emprunter());
	}
	@Test(expected = OperationImpossible.class)
	public void emprunterRefusNonEmpruntableTest() throws OperationImpossible, InvariantBroken{
		document1.setEmpruntable(false);
		document1.emprunter();
	}
	@Test(expected = OperationImpossible.class)
	public void emprunterRefusEmprunteTest() throws OperationImpossible, InvariantBroken{
		document1.setEmpruntable(true);
		document1.setEmprunte(true);
		document1.emprunter();
	}
	
	@Test
	public void restituerTest() throws InvariantBroken, OperationImpossible{
		document1.setEmpruntable(true);
		document1.setEmprunte(true);
		document1.restituer();
		assertFalse(document1.estEmprunte());
		assertTrue(document1.estEmpruntable());
	}
	
	@Test(expected = OperationImpossible.class)
	public void restituerRefusNonEmpruntableTest() throws InvariantBroken, OperationImpossible{
		document1.setEmpruntable(false);
		document1.setEmprunte(true);
		document1.restituer();
	}
	
	@Test(expected = OperationImpossible.class)
	public void restituerRefusNonEmprunteTest() throws InvariantBroken, OperationImpossible{
		document1.setEmpruntable(true);
		document1.setEmprunte(false);
		document1.restituer();
	}
	
	@Test
	public void invariantTest(){
		document1.setEmpruntable(true);
		document1.setEmprunte(false);
		assertTrue(document1.invariant());
		document1.setEmpruntable(false);
		document1.setEmprunte(false);
		assertTrue(document1.invariant());
		document1.setEmpruntable(true);
		document1.setEmprunte(true);
		assertTrue(document1.invariant());
		
		document1.setEmpruntable(false);
		document1.setEmprunte(true);
		assertFalse(document1.invariant());
	}
}
