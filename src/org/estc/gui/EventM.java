package org.estc.gui;

public class EventM {

	public static void addEt(){
		FrmAjouterEtudiant frm = new FrmAjouterEtudiant();
		frm.setVisible(true);
	}
	
	public static void editEt(){
		FrmModifierEtudiant frm = new FrmModifierEtudiant();
		frm.setVisible(true);
	}
	
	public static void delEt(){
		FrmSupprimerEtudiant frm = new FrmSupprimerEtudiant();
		frm.setVisible(true);
	}
	
}
