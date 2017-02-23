package org.estc.metier;

public class Filiere {
	private int codeFiliere;
	private String libelleFiliere;
	private String departement;
	
	public Filiere(){}
	
	public Filiere(String libelle, String departement)
	{
		this.libelleFiliere=libelle;
		this.departement=departement;
	}
	
	public Filiere(int codeFiliere, String libelleFiliere, String departement){
		this.codeFiliere = codeFiliere;
		this.libelleFiliere = libelleFiliere;
		this.departement = departement;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public int getCodeFiliere() {
		return codeFiliere;
	}
	public void setCodeFiliere(int codeFiliere) {
		this.codeFiliere = codeFiliere;
	}
	public String getLibelleFiliere() {
		return libelleFiliere;
	}
	public void setLibelleFiliere(String libelleFiliere) {
		this.libelleFiliere = libelleFiliere;
	}
	public String toString()
	{
		return libelleFiliere;
	}
}
