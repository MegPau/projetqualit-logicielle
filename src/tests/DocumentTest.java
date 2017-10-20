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
	public void documentConstructeurTest() throws OperationImpossible{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Document doc2 = new DocumentStub("aaa", loca, "titre1", "auteur1", "2002", genr);
		assertEquals("Le code n'est pas introduit dans le document. ","aaa",doc2.getCode());
		assertEquals("La localisation n'est pas introduite dans le document.", loca, doc2.getLocalisation());
		assertEquals("Le titre n'est pas introduit dans le document.", "titre1", doc2.getTitre());
		assertEquals("L'auteur n'est pas introduit dans le document. ", "auteur1", doc2.getAuteur());
		assertEquals("L'année n'est pas introduite dans le document. ", "2002", doc2.getAnnee());
		assertEquals("Le genre n'est pas introduit dans le document", genr, doc2.getGenre());
		assertFalse("Le document est initialisé en tant qu'empruntable", doc2.estEmpruntable());
		assertFalse("Le document est initialisé en tant qu'emprunté.", doc2.estEmprunte());
		assertEquals("Le nombre d'emprunts n'est pas initialisé à 0", 0, doc2.getNbEmprunts());
	}
	
	@Test(expected = OperationImpossible.class)
	public void documentConstructeurImpossibleCodeNullTest() throws OperationImpossible{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Document doc2 = new DocumentStub(null, loca, "titre1", "auteur1", "2002", genr);
	}
	
	@Test(expected = OperationImpossible.class)
	public void documentConstructeurImpossibleLocaNullTest() throws OperationImpossible{
		Localisation loca = null;
		Genre genr = new Genre("genre1");
		Document doc2 = new DocumentStub("aaa", loca, "titre1", "auteur1", "2002", genr);
	}
	
	@Test(expected = OperationImpossible.class)
	public void documentConstructeurImpossibleTitreNullTest() throws OperationImpossible{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Document doc2 = new DocumentStub("aaa", loca, null, "auteur1", "2002", genr);
	}
	
	@Test(expected = OperationImpossible.class)
	public void documentConstructeurImpossibleAuteurNullTest() throws OperationImpossible{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Document doc2 = new DocumentStub("aaa", loca, "titre1", null, "2002", genr);
	}
	
	@Test(expected = OperationImpossible.class)
	public void documentConstructeurImpossibleAnneeNullTest() throws OperationImpossible{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Document doc2 = new DocumentStub("aaa", loca, "titre1", "auteur1", null, genr);
	}
	
	@Test(expected = OperationImpossible.class)
	public void documentConstructeurImpossibleGenreNullTest() throws OperationImpossible{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = null;
		Document doc2 = new DocumentStub("aaa", loca, "titre1", "auteur1", "2002", genr);
	}
	
	@Test
	public void metEmpruntableTest() throws OperationImpossible, InvariantBroken {
		document1.setEmpruntable(false);
		document1.setEmprunte(false);
		document1.metEmpruntable();
		assertTrue("L'emprunt du document n'a pas été autorisé.", document1.estEmpruntable());
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
		assertFalse("L'emprunt du document n'a pas été interdit.", document1.estEmpruntable());
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
		assertTrue("Le document n'a pas été emprunté", document1.emprunter());
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
		assertFalse("Le document est toujours considéré comme emprunté. ",document1.estEmprunte());
		assertTrue("Le document n'a pas été rétabli comme empruntable. ", document1.estEmpruntable());
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
