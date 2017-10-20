/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mediatheque.Localisation;

/**
 * @author Megou
 *
 */
public class LocalisationTest {

	/**
	 * @throws java.lang.Exception
	 */
	Localisation localisation;
	@Before
	public void setUp() throws Exception {

		localisation = new Localisation("salle", "rayon");
	
	}

	/**
	 * Test method for {@link mediatheque.Localisation#Localisation(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLocalisation() {
		Localisation localisation2 = new Localisation("salle", "rayon");
		assertEquals("La salle n'est pas introduite correctement.", "salle", localisation2.getSalle());
		assertEquals("Le rayon n'est pas introduit correctement.", "rayon", localisation2.getRayon());
	}

}
