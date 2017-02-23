package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
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
import org.estc.metier.Note;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class FrmAjouterNote extends JDialog {
	private JTextField txtNote;
	private ButtonGroup groupe;
	private JRadioButton rdbtnOrdinaire;
	private JRadioButton rdbtnRatrappage;
	private JComboBox comboEtudiant;
	private JComboBox comboElement;

	public FrmAjouterNote() {
		setBounds(100, 100, 478, 300);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Ajouter une note", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 442, 243);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JLabel lblCne = new JLabel("Etudiant");
				lblCne.setBounds(32, 29, 62, 14);
				panel.add(lblCne);
			}
			{
				try {
					comboEtudiant = new JComboBox(EtudiantDB.getAllModel());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}
				comboEtudiant.setBounds(165, 26, 239, 20);
				panel.add(comboEtudiant);
			}
			{
				try {
					comboElement = new JComboBox(ElementDB.getAllModel());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}
				comboElement.setBounds(165, 72, 239, 20);
				panel.add(comboElement);
			}
			{
				JLabel lblElement = new JLabel("Element");
				lblElement.setBounds(32, 75, 46, 14);
				panel.add(lblElement);
			}
			{
				JLabel lblSession = new JLabel("Session");
				lblSession.setBounds(32, 121, 46, 14);
				panel.add(lblSession);
			}
			{
				txtNote = new JTextField();
				txtNote.setBounds(165, 164, 114, 20);
				txtNote.setColumns(10);
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
				JLabel lblNote = new JLabel("Note");
				lblNote.setBounds(32, 167, 46, 14);
				panel.add(lblNote);
			}
			{
				JButton btnAjouter = new JButton("Ajouter");
				btnAjouter.addActionListener(new ActionListener() {
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
							if(rdbtnOrdinaire.isSelected())session="Ordinaire";
							else session="Rattrapage";
							if(Double.parseDouble(txtNote.getText()) <= 20){
								Note n = new Note(e.getCne(),elem.getCodeElement(),session,Double.parseDouble(txtNote.getText()));
								try {
									NoteDB.add(n);
									JOptionPane.showMessageDialog(null, "Bien ajouter","Info",JOptionPane.INFORMATION_MESSAGE);
									FrmTree.refresh();
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				});
				btnAjouter.setBounds(176, 209, 89, 23);
				panel.add(btnAjouter);
			}
			
			rdbtnOrdinaire = new JRadioButton("Ordinaire");
			rdbtnOrdinaire.setSelected(true);
			rdbtnOrdinaire.setBounds(165, 117, 109, 23);
			panel.add(rdbtnOrdinaire);
			
			rdbtnRatrappage = new JRadioButton("Rattrapage");
			rdbtnRatrappage.setBounds(276, 117, 109, 23);
			panel.add(rdbtnRatrappage);
			groupe = new ButtonGroup();
			groupe.add(rdbtnOrdinaire);
			groupe.add(rdbtnRatrappage);
		}
	}
}
