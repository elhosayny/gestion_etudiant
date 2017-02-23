package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.estc.dao.ElementDB;
import org.estc.dao.EtudiantDB;
import org.estc.dao.NoteDB;
import org.estc.metier.Element;
import org.estc.metier.Etudiant;
import org.estc.metier.Filiere;
import org.estc.metier.Note;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class FrmModifierNote extends JDialog {
	private static JTextField txtNote;
	private static JTextField txtCode;
	private static JComboBox comboEtudiant;
	private static JComboBox comboElement;
	private static JRadioButton radioOrdinaire;
	private static JRadioButton radioRattrapage;

	public void disableCode(){
		txtCode.setEnabled(true);
	}
	public static void remplirChamps(Note e)
	{
		txtNote.setText(Double.toString(e.getNote()));
		txtCode.setText(Integer.toString(e.getCodeNote()));
		for(int i=0;i<comboEtudiant.getItemCount();i++)
		{
			Etudiant e1 = (Etudiant)comboEtudiant.getItemAt(i);
			if(e1.getCne()==e.getCne()) comboEtudiant.setSelectedIndex(i);
		}
		for(int i=0;i<comboElement.getItemCount();i++)
		{
			 Element elt = (Element)comboElement.getItemAt(i);
			if(elt.getCodeElement()==e.getCodeElement()) comboElement.setSelectedIndex(i);
		}
		if(e.getSession().equals("Ordinaire")) radioOrdinaire.setSelected(true);
		else radioRattrapage.setSelected(true);
		txtCode.setEnabled(false);
	}
	public FrmModifierNote() {
		setBounds(100, 100, 478, 352);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modifier une note", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 442, 291);
			getContentPane().add(panel);
			{
				JLabel label = new JLabel("Etudiant");
				label.setBounds(32, 77, 62, 14);
				panel.add(label);
			}
			{
				try {
					comboEtudiant = new JComboBox(EtudiantDB.getAllModel());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}
				comboEtudiant.setBounds(165, 74, 239, 20);
				panel.add(comboEtudiant);
			}
			{
				try {
					comboElement = new JComboBox(ElementDB.getAllModel());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}
				comboElement.setBounds(165, 120, 239, 20);
				panel.add(comboElement);
			}
			{
				JLabel label = new JLabel("Element");
				label.setBounds(32, 123, 46, 14);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Session");
				label.setBounds(32, 169, 46, 14);
				panel.add(label);
			}
			{
				txtNote = new JTextField();
				txtNote.setColumns(10);
				txtNote.setBounds(165, 212, 114, 20);
				txtNote.addKeyListener(new java.awt.event.KeyAdapter() {
		            public void keyTyped(KeyEvent evt) {
		                char c = evt.getKeyChar();
		                //le caractère est numérique
		                if ((c >= '0' && c <= '9') || c == '-') {
		                    // OK
		                } else {
		                    //suppression du caractère
		                    evt.consume();
		                }
		            }
		        });
				panel.add(txtNote);
			}
			{
				JLabel label = new JLabel("Note");
				label.setBounds(32, 215, 46, 14);
				panel.add(label);
			}
			{
				JButton btnModifier = new JButton("Modifier");
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(comboEtudiant.getSelectedItem().equals("")||comboElement.getSelectedItem().equals("")||txtNote.getText().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "Veillez remplir tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Etudiant e = (Etudiant) comboEtudiant.getSelectedItem();
							Element elem = (Element) comboElement.getSelectedItem();
							String session="";
							if(radioOrdinaire.isSelected())session=radioOrdinaire.getText();
							else session=radioRattrapage.getText();
							if(Double.parseDouble(txtNote.getText()) <= 20){
								Note n = new Note(Integer.parseInt(txtCode.getText()),e.getCne(),elem.getCodeElement(),session,Double.parseDouble(txtNote.getText()));
								try {
									NoteDB.update(n);
									JOptionPane.showMessageDialog(null, "Bien modifier","Info",JOptionPane.INFORMATION_MESSAGE);
									FrmTree.refresh();
									dispose();
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				});
				btnModifier.setBounds(176, 257, 89, 23);
				panel.add(btnModifier);
			}
			{
				radioOrdinaire = new JRadioButton("Ordinaire");
				radioOrdinaire.setSelected(true);
				radioOrdinaire.setBounds(165, 165, 109, 23);
				panel.add(radioOrdinaire);
			}
			{
				radioRattrapage = new JRadioButton("Rattrapage");
				radioRattrapage.setBounds(276, 165, 109, 23);
				panel.add(radioRattrapage);
			}
			{
				txtCode = new JTextField();
				txtCode.setBounds(165, 32, 86, 20);
				panel.add(txtCode);
				txtCode.setColumns(10);
			}
			{
				JLabel lblCode = new JLabel("Code");
				lblCode.setBounds(32, 35, 46, 14);
				panel.add(lblCode);
			}
		}
	}

}
