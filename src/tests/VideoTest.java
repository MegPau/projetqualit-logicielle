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
	public void constructeurVideoTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Video video2 = new Video("aaa", loca, "titre1", "auteur1", "2002", genr, 200, "mention");
		assertEquals("Le code n'est pas introduit dans le document. ","aaa",video2.getCode());
		assertEquals("La localisation n'est pas introduite dans le document.", loca, video2.getLocalisation());
		assertEquals("Le titre n'est pas introduit dans le document.", "titre1", video2.getTitre());
		assertEquals("L'auteur n'est pas introduit dans le document. ", "auteur1", video2.getAuteur());
		assertEquals("L'année n'est pas introduite dans le document. ", "2002", video2.getAnnee());
		assertEquals("Le genre n'est pas introduit dans le document", genr, video2.getGenre());
		assertFalse("Le document est initialisé en tant qu'empruntable", video2.estEmpruntable());
		assertFalse("Le document est initialisé en tant qu'emprunté.", video2.estEmprunte());
		assertEquals("Le nombre d'emprunts n'est pas initialisé à 0", 0, video2.getNbEmprunts());
		assertEquals("La durée n'est pas correctement introduite.", 200, video2.getDureeFilm());
		assertEquals("La mention légale n'est pas correctement introduite.", "mention", video2.getMentionLegale());
	}
	
	@Test(expected = InvariantBroken.class)
	public void constructeurVideoDureeNegTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Video video2 = new Video("aaa", loca, "titre1", "auteur1", "2002", genr, -10, "mention");
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurVideoMentionNullTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Video video2 = new Video("aaa", loca, "titre1", "auteur1", "2002", genr, 200, null);
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurVideoDocNullTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = null;
		Video video2 = new Video("aaa", loca, "titre1", "auteur1", "2002", genr, 200, "mention");
	}
	
	@Test(expected = OperationImpossible.class)
	public void constructeurVideoDocNullDureeNegTest() throws OperationImpossible, InvariantBroken{
		Localisation loca = new Localisation("salle1", "rayon 1");
		Genre genr = new Genre("genre1");
		Video video2 = new Video("aaa", loca, null, "auteur1", "2002", genr, -10, "mention");
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
