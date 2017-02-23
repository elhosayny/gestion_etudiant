package org.estc.metier;

public class Module {
	private int codeModule;
	private String libelleModule;
	private String nomResponable;
	private int volumeHoraire;
	
	public Module(){}
	
	public Module(String libelle,String responsable,int volume,int codeFiliere)
	{
		this.libelleModule = libelle;
		this.nomResponable = responsable;
		this.volumeHoraire=volume;
		this.codeFiliere=codeFiliere;
	}
	
	public Module(int code,String libelle,String responsable,int volume,int codeFiliere)
	{
		this.codeModule=code;
		this.libelleModule = libelle;
		this.nomResponable = responsable;
		this.volumeHoraire=volume;
		this.codeFiliere=codeFiliere;
	}
	
	public Module(int codeModule, String libelleModule, String nomResponable, int volumeHoraire){
		this.codeModule = codeModule;
		this.libelleModule = libelleModule;
		this.nomResponable = nomResponable;
		this.volumeHoraire = volumeHoraire;
	}
	
	public int getVolumeHoraire() {
		return volumeHoraire;
	}
	public void setVolumeHoraire(int volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}
	private int codeFiliere;
	
	public int getCodeFiliere() {
		return codeFiliere;
	}
	public void setCodeFiliere(int codeFiliere) {
		this.codeFiliere = codeFiliere;
	}
	public int getCodeModule() {
		return codeModule;
	}
	public void setCodeModule(int codeModule) {
		this.codeModule = codeModule;
	}
	public String getLibelleModule() {
		return libelleModule;
	}
	public void setLibelleModule(String libelleModule) {
		this.libelleModule = libelleModule;
	}
	public String getNomResponable() {
		return nomResponable;
	}
	public void setNomResponable(String nomResponable) {
		this.nomResponable = nomResponable;
	}  
	public String toString()
	{
		return libelleModule;
	}
}
