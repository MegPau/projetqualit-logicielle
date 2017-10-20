/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import mediatheque.document.Audio;
import mediatheque.document.Livre;
import mediatheque.document.Video;
import util.InvariantBroken;

/**
 * @author Megou
 *
 */
public class AudioTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	Audio audio;
	@Before
	public void setUp() throws Exception {
		
		Localisation localisation = new Localisation("salle1", "rayon 1");
		Genre genre = new Genre("genre1");
		audio = new Audio("aaa", localisation, "titre1", "auteur1", "2002", genre, "classification");
	}
	
	
	@Test
	public void constructeurAudioTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Audio audio2 = new Audio("aaa", loca, "titre1", "auteur1", "2002", genr, "classification");
		assertEquals("Le code n'est pas introduit dans le document. ","aaa",audio2.getCode());
		assertEquals("La localisation n'est pas introduite dans le document.", loca, audio2.getLocalisation());
		assertEquals("Le titre n'est pas introduit dans le document.", "titre1", audio2.getTitre());
		assertEquals("L'auteur n'est pas introduit dans le document. ", "auteur1", audio2.getAuteur());
		assertEquals("L'année n'est pas introduite dans le document. ", "2002", audio2.getAnnee());
		assertEquals("Le genre n'est pas introduit dans le document", genr, audio2.getGenre());
		assertFalse("Le document est initialisé en tant qu'empruntable", audio2.estEmpruntable());
		assertFalse("Le document est initialisé en tant qu'emprunté.", audio2.estEmprunte());
		assertEquals("Le nombre d'emprunts n'est pas initialisé à 0", 0, audio2.getNbEmprunts());
		assertEquals("La classification n'est pas correctement introduite.", "classification", audio2.getClassification());
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurAudioClassNullTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Audio audio2 = new Audio("aaa", loca, "titre1", "auteur1", "2002", genr, null);
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurAudioDocNullTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = null;
		Audio audio2 = new Audio("aaa", loca, "titre1", "auteur1", "2002", genr, "classification");
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurAudioDocNullClassNullTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Audio audio2 = new Audio(null, loca, "titre1", "auteur1", "2002", genr, null);
	}
	
	

	@Test
	public void emprunterTest1() throws InvariantBroken, OperationImpossible {
		audio.setEmpruntable(true);
		audio.setEmprunte(false);
		int expected = audio.getStat() + 1;
		audio.emprunter();
		assertTrue("Le disque n'est pas emprunté. ",audio.estEmprunte());
		assertEquals("Le nombre d'emprunts n'est pas correct. ", expected, audio.getStat());
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest2() throws InvariantBroken, OperationImpossible {
		audio.setEmpruntable(true);
		audio.setEmprunte(true);
		audio.emprunter();
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest3() throws InvariantBroken, OperationImpossible {
		audio.setEmpruntable(false);
		audio.setEmprunte(true);
		audio.emprunter();
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest4() throws InvariantBroken, OperationImpossible {
		audio.setEmpruntable(false);
		audio.setEmprunte(false);
		audio.emprunter();
	}
}
