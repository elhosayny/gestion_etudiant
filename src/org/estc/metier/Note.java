package org.estc.metier;

public class Note {
	private int codeNote;
	private int cne;
	private int codeElement;
	private String session;
	private double note;
	
	
	public Note(int cne, int codeElement, String session, double note) {
		this.cne=cne;
		this.codeElement=codeElement;
		this.session=session;
		this.note=note;
	}
	public Note(int code,int cne, int codeElement, String session, double note) {
		this.codeNote=code;
		this.cne=cne;
		this.codeElement=codeElement;
		this.session=session;
		this.note=note;
	}
	public Note()
	{}
	public int getCodeNote() {
		return codeNote;
	}
	public void setCodeNote(int codeNote) {
		this.codeNote = codeNote;
	}
	
	
	public int getCne() {
		return cne;
	}
	public void setCne(int cne) {
		this.cne = cne;
	}
	public int getCodeElement() {
		return codeElement;
	}
	public void setCodeElement(int codeElement) {
		this.codeElement = codeElement;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	
}
