package tests;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import mediatheque.document.Document;

public class DocumentStub extends Document {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocumentStub(String code, Localisation localisation, String titre, String auteur, String annee, Genre genre)
			throws OperationImpossible {
		super(code, localisation, titre, auteur, annee, genre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int dureeEmprunt() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double tarifEmprunt() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
