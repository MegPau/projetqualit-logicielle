/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mediatheque.FicheEmprunt;
import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.client.HashClient;
import mediatheque.document.Document;
import util.InvariantBroken;

/**
 * @author Megou
 *
 */
public class MediathequeTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	Mediatheque media, media3;
	
	@Before
	public void setUp() throws Exception {
		media = new Mediatheque("media");
		media.setDebug(true);
		
		media3 = new Mediatheque("media3");
		media3.setDebug(true);
		media3.ajouterLocalisation("salleTest2", "rayonTest2");
		media3.ajouterGenre("genreTest2");
		media3.ajouterCatClient("catTest2", 20, 8, 2, 3, true);
		media3.inscrire("Smith", "John", "57 rue du melon", "catTest2");
		DocumentStub doc = new DocumentStub("aaa", media3.chercherLocalisation("salleTest2", "rayonTest2"), "titre1", "auteur1", "2002", media3.chercherGenre("genreTest2"));
		media3.ajouterDocument(doc);
	}


	@Test
	public void constructeurMediaTest(){
		Mediatheque media2 = new Mediatheque("media");
		assertEquals("Le nom n'est pas introduit.", "media", media2.getNom());
		assertEquals("La liste des genres n'est pas vide", 0, media2.getGenresSize());
		assertEquals("La liste des Localisations n'est pas vide", 0, media2.getLocalisationsSize());
		assertEquals("La liste des Documents n'est pas vide", 0, media2.getDocumentsSize());
		assertEquals("La liste des Clients n'est pas vide", 0, media2.getClientsSize());
		assertEquals("La liste des Emprunts n'est pas vide", 0, media2.getFicheEmpruntsSize());
		assertEquals("La liste des CatsClient n'est pas vide", 0, media2.getCategoriesSize());
	}
	
	@Test
	public void chercherGenreTest() throws OperationImpossible {
		media.ajouterGenre("genreTest");
		media.ajouterGenre("genreTest2");
		assertEquals("Le genre n'est pas trouvé dans la liste", new Genre("genreTest"), media.chercherGenre("genreTest"));
		assertEquals("Le genre2 n'est pas trouvé dans la liste", new Genre("genreTest2"), media.chercherGenre("genreTest2"));
	}
	
	@Test
	public void chercherGenreInexistantTest() throws OperationImpossible{
		assertEquals("Le genre inexistant à été trouvé.",null,media.chercherGenre("genretest"));
	}
	
	@Test
	public void supprimerGenreTest() throws OperationImpossible {
		media.ajouterGenre("genreTest");
		media.supprimerGenre("genreTest");
		assertEquals("Le genre n'a pas été supprimé.", null, media.chercherGenre("genreTest"));	
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerGenreInexistantTest() throws OperationImpossible {
		media.supprimerGenre("genreTest");
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerGenreAvecDocumentTest() throws OperationImpossible {
		media3.supprimerGenre("genreTest2");
	}
	
	
	@Test
	public void ajouterGenreTest() throws OperationImpossible {
		int initialSize = media.getGenresSize();
		media.ajouterGenre("genreTest");
		assertEquals("Le genre n'est pas ajouté à la liste des genres : nombre de genres non incrémenté", initialSize+1,media.getGenresSize());
		assertEquals("Le genre n'est pas ajouté à la liste des genres : genre introuvable dans la liste", new Genre("genreTest"), media.chercherGenre("genreTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void ajouterGenreExistant() throws OperationImpossible{
		media.ajouterGenre("genreTest");
		media.ajouterGenre("genreTest");
	}
	
	
	@Test
	public void modifierGenreTest() throws OperationImpossible {
		media.ajouterGenre("genreTest");
		media.modifierGenre("genreTest", "nouveauGenreTest");
		assertEquals("Genre non changé", new Genre("nouveauGenreTest"), media.chercherGenre("nouveauGenreTest"));
		assertEquals("Ancien genre toujours existant", null, media.chercherGenre("genreTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void modifierGenreInexistantTest() throws OperationImpossible {
		media.modifierGenre("genreTest", "nouveauGenreTest");
	}
	
	
	
	@Test
	public void supprimerLocalisationTest() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.supprimerLocalisation("salleTest", "rayonTest");
		assertEquals("La localisation n'a pas été supprimée", null, media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerLocalisationInexistanteTest() throws OperationImpossible {
		media.supprimerLocalisation("salleTest", "rayonTest");
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerLocalisationAvecDocumentTest() throws OperationImpossible {
		media3.supprimerLocalisation("salleTest2", "rayonTest2");
	}
	
	@Test
	public void chercherLocalisationTest() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.ajouterLocalisation("salleTest2", "rayonTest2");
		assertEquals("La localisation localisationTest n'est pas trouvée dans la liste", new Localisation("salleTest","rayonTest"), media.chercherLocalisation("salleTest","rayonTest"));
		assertEquals("La localisation localisationTest2 n'est pas trouvée dans la liste", new Localisation("salleTest2","rayonTest2"), media.chercherLocalisation("salleTest2","rayonTest2"));
	}
	
	@Test
	public void chercherLocalisationInexistanteTest() throws OperationImpossible{
		assertEquals("La localisation inexistante à été trouvée.",null,media.chercherLocalisation("salleTest","rayonTest"));
	}
	
	@Test
	public void ajouterLocalisationTest() throws OperationImpossible{
		int initialSize = media.getLocalisationsSize();
		media.ajouterLocalisation("salleTest", "rayonTest");
		assertEquals("La localisation n'est pas ajoutée à la liste des locallisations : nombre de localisations non incrémenté", initialSize+1,media.getLocalisationsSize());
		assertEquals("La localisation n'est pas ajoutée à la liste des localisations : salle introuvable dans la liste", new Localisation("salleTest", "rayonTest"), media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void ajouterLocalisationExistante() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.ajouterLocalisation("salleTest", "rayonTest");
	}
	
	@Test
	public void modifierLocalisationTest() throws OperationImpossible{
		media.ajouterLocalisation("salleTest", "rayonTest");
		media.modifierLocalisation(media.chercherLocalisation("salleTest", "rayonTest"), "nouvelleSalle", "nouveauRayon");
		assertEquals("Localisation non changée", new Localisation("nouvelleSalle", "nouveauRayon"), media.chercherLocalisation("nouvelleSalle", "nouveauRayon"));
		assertEquals("Ancienne Localisation toujours existante", null, media.chercherLocalisation("salleTest", "rayonTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void modifierLocalisationInexistanteTest() throws OperationImpossible {
		Localisation localisationInexistante = new Localisation("sT","rT");
		media.modifierLocalisation(localisationInexistante,"salleTest", "rayonTest");
	}
	
	
	
	
	@Test
	public void supprimerCatClientTest() throws OperationImpossible{
		media.ajouterCatClient("catTest", 20, 8, 2, 3, true);
		media.supprimerCatClient("catTest");
		assertEquals("La catégorie client n'a pas été supprimée", null, media.chercherCatClient("catTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerCatClientInexistanteTest() throws OperationImpossible {
		media.supprimerCatClient("catTest");
	}
	
	@Test(expected = OperationImpossible.class)
	public void supprimerCatClientAvecClientTest() throws OperationImpossible {
		media3.supprimerCatClient("catTest2");
	}
	
	@Test
	public void chercherCatClientTest() throws OperationImpossible{
		media.ajouterCatClient("catTest", 20, 8, 2, 3, true);
		media.ajouterCatClient("catTest2", 20, 8, 2, 3, true);
		assertEquals("La catégorie client catTest n'est pas trouvée dans la liste", new CategorieClient("catTest", 20, 8, 2, 3, true), media.chercherCatClient("catTest"));
		assertEquals("La catégorie client catTest2 n'est pas trouvée dans la liste", new CategorieClient("catTest2", 20, 8, 2, 3, true), media.chercherCatClient("catTest2"));
	}
	
	@Test
	public void chercherCatClientInexistanteTest() throws OperationImpossible{
		assertEquals("La catégorie client inexistante à été trouvée.",null,media.chercherCatClient("catTest"));
	}
	
	
	
	@Test
	public void ajouterCatClientTest() throws OperationImpossible{
		int initialSize = media.getCategoriesSize();
		media.ajouterCatClient("catTest", 20, 8, 2, 3, true);
		assertEquals("La catégorie client n'est pas ajoutée à la liste des locallisations : nombre de catégories client non incrémenté", initialSize+1,media.getCategoriesSize());
		assertEquals("La catégorie client n'est pas ajoutée à la liste des localisations : catégorie client introuvable dans la liste", new CategorieClient("catTest"), media.chercherCatClient("catTest"));
	}
	
	
	@Test(expected = OperationImpossible.class)
	public void ajouterCatClientExistante() throws OperationImpossible{
		media.ajouterCatClient("catTest", 20, 8, 2, 3, true);
		media.ajouterCatClient("catTest", 20, 8, 2, 3, true);
	}
	
	
	@Test
	public void modifierCatClientTest() throws OperationImpossible{
		media.ajouterCatClient("catTest", 20, 8, 2, 3, true);
		media.modifierCatClient(media.chercherCatClient("catTest"), "nouvelleCatTest", 15, 6, 3, 4, false);
		assertEquals("Catégorie client non changée", new CategorieClient("nouvelleCatTest", 15, 6, 3, 4, false), media.chercherCatClient("nouvelleCatTest"));
		assertEquals("Ancienne Catégorie client toujours existante", null, media.chercherCatClient("catTest"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void modifierCatClientInexistanteTest() throws OperationImpossible {
		CategorieClient catClientInexistante = new CategorieClient("catT");
		media.modifierCatClient(catClientInexistante,"nouvelleCatTest", 15, 6, 3, 4, false);
	}

	@Test
	public void chercherDocumentTest() throws OperationImpossible{
		DocumentStub docu = new DocumentStub("aaa", media3.chercherLocalisation("salleTest2", "rayonTest2"), "titre1", "auteur1", "2002", media3.chercherGenre("genreTest2"));
		assertEquals("Le document n'est pas trouvé.",docu ,media3.chercherDocument("aaa"));
	}
	
	@Test
	public void chercherDocumentInexistantTest() throws OperationImpossible{
		assertEquals("Le document n'est pas trouvé.",null ,media3.chercherDocument("zzz"));
	}
	
		
	@Test
	public void ajouterDocumentTest() throws OperationImpossible{
		int initialSize = media3.getDocumentsSize();
		DocumentStub docu = new DocumentStub("bbb", media3.chercherLocalisation("salleTest2", "rayonTest2"), "titre1", "auteur1", "2002", media3.chercherGenre("genreTest2"));
		media3.ajouterDocument(docu);
		assertEquals("Le document n'est pas ajouté à la liste des documents : nombre de documents non incrémenté", initialSize+1,media3.getDocumentsSize());
		assertEquals("Le document n'est pas trouvé.",docu ,media3.chercherDocument("bbb"));
	}
	@Test(expected = OperationImpossible.class)
	public void ajouterDocumentExistantTest() throws OperationImpossible{
		DocumentStub docu = new DocumentStub("aaa", media3.chercherLocalisation("salleTest2", "rayonTest2"), "titre1", "auteur1", "2002", media3.chercherGenre("genreTest2"));
		media3.ajouterDocument(docu);
	}
	
	@Test(expected = OperationImpossible.class)
	public void ajouterDocumentGenreInexistantTest() throws OperationImpossible{
		DocumentStub docu = new DocumentStub("ddd", media3.chercherLocalisation("salleTest2", "rayonTest2"), "titre1", "auteur1", "2002", media3.chercherGenre("genreRandom"));
		media3.ajouterDocument(docu);
	}
	
	@Test(expected = OperationImpossible.class)
	public void ajouterDocumentLocalisationInexistanteTest() throws OperationImpossible{
		DocumentStub docu = new DocumentStub("eee", media3.chercherLocalisation("salleRandom", "rayonRandom"), "titre1", "auteur1", "2002", media3.chercherGenre("genreTest2"));
		media3.ajouterDocument(docu);
	}
	@Test
	public void retirerDocumentTest() throws OperationImpossible{
		media3.retirerDocument("aaa");
		assertEquals("Le document n'est pas retiré", null,media3.chercherDocument("aaa"));
	}
	@Test(expected = OperationImpossible.class)
	public void retirerDocumentEmprunteTest() throws OperationImpossible{
		DocumentStub docu = new DocumentStub("eee", media3.chercherLocalisation("salleRandom", "rayonRandom"), "titre1", "auteur1", "2002", media3.chercherGenre("genreTest2"));
		docu.setEmprunte(true);
		docu.setEmpruntable(true);
		media3.ajouterDocument(docu);
		media3.retirerDocument("eee");
	}
	@Test(expected = OperationImpossible.class)
	public void retirerDocumentInexistantTest() throws OperationImpossible{
		media3.retirerDocument("rrr");
	}
	
	
	
	@Test
	public void metEmpruntableTest() throws OperationImpossible, InvariantBroken{
		media3.metEmpruntable("aaa");
		assertTrue("Le document n'est pas empruntable.",media3.chercherDocument("aaa").estEmpruntable());
	}
	
	@Test(expected = OperationImpossible.class)
	public void metEmpruntableDocInexistantTest() throws OperationImpossible, InvariantBroken{
		media3.metEmpruntable("fff");
	}
	
	@Test
	public void metConsultableTest() throws OperationImpossible, InvariantBroken{
		media3.chercherDocument("aaa").setEmpruntable(true);
		media3.metConsultable("aaa");
		assertFalse("Le document n'est pas uniquement consultable.", media3.chercherDocument("aaa").estEmpruntable());
	}
	
	@Test(expected = OperationImpossible.class)
	public void metConsultableDocInexistantTest() throws OperationImpossible, InvariantBroken{
		media3.metConsultable("hhh");
	}
	
	@Test
	public void existeDocumentTest(){
		assertTrue("Le document n'est pas trouvé.", media3.existeDocument(media3.chercherLocalisation("salleTest2", "rayonTest2")));
	}

	@Test
	public void existeDocumentInexistantTest() throws OperationImpossible{
		media3.ajouterLocalisation("salle4", "rayon4");
		assertFalse("Un document inexistant est trouvé.", media3.existeDocument(media3.chercherLocalisation("salle4", "rayon4")));
	}
	
	@Test
	public void existeDocumentLocalisationInexistanteTest(){
		assertFalse("Un document est trouvé dans une localisation inexistante.", media3.existeDocument(media3.chercherLocalisation("salle4", "rayon4")));
	}
	
	@Test
	public void emprunterTest() throws OperationImpossible, InvariantBroken{
		media3.chercherDocument("aaa").setEmpruntable(true);
		media3.chercherDocument("aaa").setEmprunte(false);
		media3.chercherClient("Smith", "John").setnbEmpruntsEncours(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsEffectues(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsDepasses(0);
		int expectedSize = media3.getFicheEmpruntsSize() + 1;
		media3.emprunter("Smith","John", "aaa");
		assertEquals("La liste des emprunts n'a pas été incrémentée correctement.", expectedSize, media3.getFicheEmpruntsSize());
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterDocInexistantTest() throws OperationImpossible, InvariantBroken{
		media3.chercherClient("Smith", "John").setnbEmpruntsEncours(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsEffectues(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsDepasses(0);
		media3.emprunter("Smith","John", "llll");
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterDocNonEmpruntableTest() throws OperationImpossible, InvariantBroken{
		media3.chercherDocument("aaa").setEmpruntable(false);
		media3.chercherDocument("aaa").setEmprunte(false);
		media3.chercherClient("Smith", "John").setnbEmpruntsEncours(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsEffectues(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsDepasses(0);
		media3.emprunter("Smith","John", "aaa");
	}
	@Test(expected = OperationImpossible.class)
	public void emprunterDocEmprunteTest() throws OperationImpossible, InvariantBroken{
		media3.chercherDocument("aaa").setEmpruntable(true);
		media3.chercherDocument("aaa").setEmprunte(true);
		media3.chercherClient("Smith", "John").setnbEmpruntsEncours(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsEffectues(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsDepasses(0);
		media3.emprunter("Smith","John", "aaa");
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterClientInexistantTest() throws OperationImpossible, InvariantBroken{
		media3.chercherDocument("aaa").setEmpruntable(true);
		media3.chercherDocument("aaa").setEmprunte(false);
		media3.emprunter("Doe","Jane", "aaa");
	}
	
	@Test(expected = OperationImpossible.class)
	public void emprunterClientNePeutPasEmprunterTest() throws OperationImpossible, InvariantBroken{
		media3.chercherDocument("aaa").setEmpruntable(true);
		media3.chercherDocument("aaa").setEmprunte(false);
		media3.chercherClient("Smith", "John").setnbEmpruntsEncours(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsEffectues(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsDepasses(1);
		media3.emprunter("Smith","John", "aaa");
	}
	
	@Test
	public void restituerTest() throws OperationImpossible, InvariantBroken{
		media3.chercherDocument("aaa").setEmpruntable(true);
		media3.chercherDocument("aaa").setEmprunte(false);
		media3.chercherClient("Smith", "John").setnbEmpruntsEncours(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsEffectues(0);
		media3.chercherClient("Smith", "John").setnbEmpruntsDepasses(0);
		media3.emprunter("Smith","John", "aaa");
		int expectedSize = media3.getFicheEmpruntsSize() - 1;
		media3.restituer("Smith", "John", "aaa");
		assertEquals("La liste des emprunts n'est pas décrémentée.", expectedSize,media3.getFicheEmpruntsSize());
	}
	@Test(expected = OperationImpossible.class)
	public void restituerDocInexistantTest() throws OperationImpossible, InvariantBroken{
		media3.restituer("Smith","John", "hhhhh");
	}
	@Test(expected = OperationImpossible.class)
	public void restituerClientInexistantTest() throws OperationImpossible, InvariantBroken{
		media3.chercherDocument("aaa").setEmpruntable(true);
		media3.chercherDocument("aaa").setEmprunte(false);
		media3.restituer("Doe","Jane", "aaa");
	}
	@Test(expected = OperationImpossible.class)
	public void restituerClientDocNonCorrespondantsTest() throws OperationImpossible, InvariantBroken{
		media3.ajouterLocalisation("salleTest45", "rayonTest45");
		media3.ajouterGenre("genreTest45");
		DocumentStub document = new DocumentStub("oooo", media3.chercherLocalisation("salleTest45", "rayonTest45"), "titre1", "auteur1", "2002", media3.chercherGenre("genreTest45"));
		document.setEmpruntable(true);
		document.setEmprunte(false);
		media3.ajouterDocument(document);
		
		media3.ajouterCatClient("catTest45", 20, 8, 2, 3, true);
		media3.inscrire("Doe", "Jane", "57 rue du melon", "catTest45");
		
		media3.chercherClient("Doe", "Jane").setnbEmpruntsEncours(0);
		media3.chercherClient("Doe", "Jane").setnbEmpruntsEffectues(0);
		media3.chercherClient("Doe", "Jane").setnbEmpruntsDepasses(0);
		media3.emprunter("Doe","Jane", "oooo");
		
		media3.restituer("Smith", "John", "oooo");
	}
	
	@Test
	public void inscrireTest() throws OperationImpossible{
		CategorieClient catTestInscription = new CategorieClient("catTestInscription", 20, 8, 2, 3, false);
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		double cotisationTrouvee = media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		double cotisationExpected = 8;
		assertEquals("La cotisation retournée n'est pas correcte", cotisationExpected, cotisationTrouvee, 0.001);
		assertEquals("Le client n'est pas dans la liste des clients.", new Client("Haliday", "Johnny", "57 rue du melon", catTestInscription),media.chercherClient("Haliday", "Johnny"));
	}
	@Test(expected = OperationImpossible.class)
	public void inscrireClientExistant() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
	}
	@Test(expected = OperationImpossible.class)
	public void inscrireCategorieInexistante() throws OperationImpossible{
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInexistante");
	}
	@Test(expected = OperationImpossible.class)
	public void inscrireCatReduc() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, true);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
	}
	@Test
	public void inscrireReducTest() throws OperationImpossible{
		CategorieClient catTestInscription = new CategorieClient("catTestInscription", 20, 8, 2, 3, false);
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, true);
		double cotisationTrouvee = media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription", 5);
		double cotisationExpected = 8;
		assertEquals("La cotisation retournée n'est pas correcte", cotisationExpected, cotisationTrouvee, 0.001);
		assertEquals("Le client n'est pas dans la liste des clients.", new Client("Haliday", "Johnny", "57 rue du melon", catTestInscription),media.chercherClient("Haliday", "Johnny"));
	}
	
	@Test
	public void resilierTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.resilier("Haliday", "Johnny");
		assertEquals("Le client n'a pas été supprimé. ", null, media.chercherClient("Haliday", "Johnny"));
	}
	
	@Test(expected = OperationImpossible.class)
	public void resilierClientInexistantTest() throws OperationImpossible{
		media.resilier("Haliday", "Johnny");
	}
	
	@Test(expected = OperationImpossible.class)
	public void resilierEmpruntsEnCoursTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.chercherClient("Haliday", "Johnny").setnbEmpruntsEncours(5);
		media.resilier("Haliday", "Johnny");
	}
	
	@Test
	public void modifierClientTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.ajouterCatClient("catTestModification", 20, 8, 2, 3, true);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.modifierClient(media.chercherClient("Haliday", "Johnny"), "Smet", "Jean Phillipe", "40 rue allumez le feu",
                "catTestModification", 4);
		
		assertEquals("Le client n'a pas été modifié", null, media.chercherClient("Haliday", "Johnny"));
		assertEquals("Le client n'a pas été modifié correctement", new Client("Smet", "Jean Phillipe"), media.chercherClient("Smet", "Jean Phillipe"));
		assertEquals("L'adresse du client n'a pas été modifiée", "40 rue allumez le feu", media.chercherClient("Smet", "Jean Phillipe").getAdresse());
		assertEquals("La catégorie du client n'a pas été modifiée", new CategorieClient("catTestModification", 20, 8, 2, 3, true), media.chercherClient("Smet", "Jean Phillipe").getCategorie());
	}
	
	@Test(expected = OperationImpossible.class)
	public void modifierClientInexistantTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.modifierClient(new Client("Dupont","Jean"), "Smet", "Jean Phillipe", "40 rue allumez le feu",
                "catTestInscription", 4);
	}
	
	@Test(expected = OperationImpossible.class)
	public void modifierClientCatInexTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.modifierClient(media.chercherClient("Haliday", "Johnny"), "Smet", "Jean Phillipe", "40 rue allumez le feu",
                "catTestModification", 4);
		}
	
	
	@Test
	public void changerCategorieTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.ajouterCatClient("catTestModification", 20, 8, 2, 3, true);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.changerCategorie("Haliday", "Johnny", "catTestModification", 7);
		assertEquals("La catégorie du client n'a pas été modifiée", new CategorieClient("catTestModification", 20, 8, 2, 3, true), media.chercherClient("Haliday", "Johnny").getCategorie());
	}
	@Test
	public void changerCategorieSansReducTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.ajouterCatClient("catTestModification", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.changerCategorie("Haliday", "Johnny", "catTestModification", 0);
		assertEquals("La catégorie du client n'a pas été modifiée", new CategorieClient("catTestModification", 20, 8, 2, 3, false), media.chercherClient("Haliday", "Johnny").getCategorie());
	}
	
	@Test(expected = OperationImpossible.class)
	public void changerCategorieClientInexistant() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.ajouterCatClient("catTestModification", 20, 8, 2, 3, true);
		media.changerCategorie("Haliday", "Johnny", "catTestModification", 7);
	}
	@Test(expected = OperationImpossible.class)
	public void changerCategorieCategorieInexistante() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.changerCategorie("Haliday", "Johnny", "catTestModification", 7);
	}
	
	@Test
	public void changerCodeReductionTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, true);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription",7);
		media.changerCodeReduction("Haliday", "Johnny", 5);
		assertEquals("La réduction n'a pas été modifiée.",5,media.chercherClient("Haliday", "Johnny").getReduc());
	}
	@Test(expected = OperationImpossible.class)
	public void changerCodeReductionClientInexistantTest() throws OperationImpossible{
		media.changerCodeReduction("Haliday", "Johnny", 5);
	}
	
	@Test(expected = OperationImpossible.class)
	public void changerCodeReductionCategorieSansReducTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		media.changerCodeReduction("Haliday", "Johnny", 5);
	
	}
	
	@Test
	public void chercherClientTest() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		assertEquals("Le client n'a pas été retrouvé par la fonction.",new Client("Haliday","Johnny"),media.chercherClient("Haliday", "Johnny"));
	}
	
	@Test
	public void chercherClientInexistantTest(){
		assertEquals("Le client inexistant a été retrouvé par la fonction.",null,media.chercherClient("Bonjour", "Coucou"));
	}
	
	@Test
	public void existeClientExiste() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		media.inscrire("Haliday", "Johnny", "57 rue du melon", "catTestInscription");
		assertTrue("Le client de la catégorie n'a pas été détecté.", media.existeClient(media.chercherCatClient("catTestInscription")));
	}
	
	@Test
	public void existeClientnExistePas() throws OperationImpossible{
		media.ajouterCatClient("catTestInscription", 20, 8, 2, 3, false);
		assertFalse("La catégorie n'est pas considérée comme vide.", media.existeClient(media.chercherCatClient("catTestInscription")));
	}
	
	
	@Test
	public void saveToFileAndinitFromFileTest() throws OperationImpossible, InvariantBroken{
		Mediatheque mediaTest = new Mediatheque("mediaTest");
		mediaTest.setDebug(true);
		mediaTest.ajouterLocalisation("salleTest2", "rayonTest2");
		mediaTest.ajouterGenre("genreTest2");
		mediaTest.ajouterCatClient("catTest2", 20, 8, 2, 3, true);
		mediaTest.inscrire("Smith", "John", "57 rue du melon", "catTest2");
		DocumentStub doc = new DocumentStub("aaa", mediaTest.chercherLocalisation("salleTest2", "rayonTest2"), "titre1", "auteur1", "2002", mediaTest.chercherGenre("genreTest2"));
		doc.setEmpruntable(true);
		mediaTest.ajouterDocument(doc);
		mediaTest.emprunter("Smith", "John", "aaa");
		mediaTest.saveToFile();
		
		Mediatheque mediaTest2 = new Mediatheque("mediaTest");
		
		mediaTest.afficherStatistiques();
		mediaTest2.afficherStatistiques();
		
	}
	
}
