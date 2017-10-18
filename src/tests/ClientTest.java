package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mediatheque.OperationImpossible;
import mediatheque.client.Client;

public class ClientTest {
	
	Client client1, client2;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void aDesEmpruntsEnCoursTest() {
		fail("Not yet implemented");
	}

	@Test
	public void peutEmprunterTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void emprunterTest(){
		fail("Not yes implemented");
	}
	@Test(expected = AssertionError.class)
	public void nePeutPasEmprunterTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void marquerTest(){
		fail("Not yes implemented");
	}
	
	@Test(expected = OperationImpossible.class)
	public void marquerErreurTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void restituerTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void restituerEnRetardTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void metAJourEmpruntsTest(){
		fail("Not yes implemented");
	}
	
	@Test
	public void dateRetourTest(){
		
	}
	
	@Test
	public void sommeDueTest(){
		
	}
}
