package org.estc.metier;

public class Element {
	private int codeElement;
	private int codeModule;
	private int coefficient;
	private String libelleElement;
	
	public Element(){}
	
	public Element(int codeElement, int codeModule, int coefficient, String libelleElement){
		this.codeElement = codeElement;
		this.codeModule = codeModule;
		this.coefficient = coefficient;
		this.libelleElement = libelleElement;
	}
	
	public Element(int module,int coefficient,String libelle)
	{
		this.codeModule=module;
		this.coefficient=coefficient;
		this.libelleElement=libelle;
	}
	
	public String getLibelleElement() {
		return libelleElement;
	}
	public void setLibelleElement(String libelleElement) {
		this.libelleElement = libelleElement;
	}
	public int getCodeElement() {
		return codeElement;
	}
	public void setCodeElement(int codeElement) {
		this.codeElement = codeElement;
	}
	public int getCodeModule() {
		return codeModule;
	}
	public void setCodeModule(int codeModule) {
		this.codeModule = codeModule;
	}
	public int getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	} 
	public String toString()
	{
		return this.libelleElement;
	}
}
