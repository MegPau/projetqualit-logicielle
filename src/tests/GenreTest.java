/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mediatheque.Genre;

/**
 * @author Megou
 *
 */
public class GenreTest {

	Genre genre1;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		genre1 = new Genre("fantasy");
	}
	
	@Test
	public void emprunterTest() {
		int expected;
		expected = genre1.getNbEmprunts() + 1;
		genre1.emprunter();
		assertEquals("Le nombre d'emprunts n'est pas correct.", expected, genre1.getNbEmprunts());
	}

}
