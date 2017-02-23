package org.estc.metier;

public class Etudiant {
	
	private int cne;
	private String cin;
	private String nom;
	private String prenom;
	
	public Etudiant(int cne, String cin, String nom, String prenom, String diplome) {
		this.cne = cne;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.diplome = diplome;
	}
	public Etudiant() {
		
	}
	public int getCne() {
		return cne;
	}
	public void setCne(int cne) {
		this.cne = cne;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public String toString()
	{
		return nom+" "+prenom;
	}
	private String diplome;

}
