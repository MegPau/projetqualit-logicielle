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
import mediatheque.document.Video;
import util.InvariantBroken;

public class VideoTest {

	Video video;
	@Before
	public void setUp() throws Exception {
        Localisation localisation = new Localisation("salle1", "rayon 1");
		Genre genre = new Genre("genre1");
		video = new Video("aaa", localisation, "titre1", "auteur1", "2002", genre, 200, "mention légale");
	}


	@Test
	public void emprunterTest1() throws InvariantBroken, OperationImpossible {
		video.setEmpruntable(true);
		video.setEmprunte(false);
		int expected = video.getStat() + 1;
		video.emprunter();
		assertTrue("La video n'est pas empruntée. ",video.estEmprunte());
		assertEquals("Le nombre d'emprunts n'est pas correct. ", expected, video.getStat());
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest2() throws InvariantBroken, OperationImpossible {
		video.setEmpruntable(true);
		video.setEmprunte(true);
		video.emprunter();
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest3() throws InvariantBroken, OperationImpossible {
		video.setEmpruntable(false);
		video.setEmprunte(true);
		video.emprunter();
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterTest4() throws InvariantBroken, OperationImpossible {
		video.setEmpruntable(false);
		video.setEmprunte(false);
		video.emprunter();
	}
	
	@Test
	public void invariantVideoTest(){
		video.setEmpruntable(false);
		video.setEmprunte(true);
		video.setDureeFilm(10);
		assertFalse("L'invariant de Document n'est pas correct.", video.invariantVideo());
	}

	@Test
	public void invariantVideo2Test(){
		video.setEmpruntable(true);
		video.setEmprunte(false);
		video.setDureeFilm(10);
		assertTrue("L'invariant n'est pas correct.", video.invariantVideo());
	}
	
	@Test
	public void invariantVideo3Test(){
		video.setEmpruntable(false);
		video.setEmprunte(false);
		video.setDureeFilm(-10);
		assertFalse("L'invariant de Video n'est pas correct.", video.invariantVideo());
	}
	
	@Test
	public void invariantVideo4Test(){
		video.setEmpruntable(true);
		video.setEmprunte(true);
		video.setDureeFilm(-10);
		assertFalse("L'invariant de Video n'est pas correct.", video.invariantVideo());
	}
}
