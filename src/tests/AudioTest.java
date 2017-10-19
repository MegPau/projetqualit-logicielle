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
