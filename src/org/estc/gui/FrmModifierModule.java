package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.estc.dao.FiliereDB;
import org.estc.dao.ModuleDB;
import org.estc.dao.Tools;
import org.estc.metier.Filiere;
import org.estc.metier.Module;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmModifierModule extends JDialog {
	private static JTextField txtLibelle;
	private static  JTextField txtResponsable;
	private static JTextField txtHoraire;
	private static JTextField txtCode;
	private static JComboBox comboFiliere;

	public void disableCode(){
		txtCode.setEnabled(true);
	}
	public static void remplirChamps(Module m)
	{
		txtCode.setText(Integer.toString(m.getCodeModule()));
		txtLibelle.setText(m.getLibelleModule());
		txtResponsable.setText(m.getNomResponable());
		txtHoraire.setText(Integer.toString(m.getVolumeHoraire()));
		for(int i=0;i<comboFiliere.getItemCount();i++)
		{
			Filiere f = (Filiere)comboFiliere.getItemAt(i);
			if(f.getCodeFiliere()==m.getCodeFiliere()) comboFiliere.setSelectedIndex(i);
		}
		txtCode.setEnabled(false);
	}
	public FrmModifierModule() {
		setBounds(100, 100, 461, 402);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modifier un module", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 430, 341);
			getContentPane().add(panel);
			{
				JLabel label = new JLabel("Libelle");
				label.setBounds(43, 79, 108, 14);
				panel.add(label);
			}
			{
				txtLibelle = new JTextField();
				txtLibelle.setColumns(10);
				txtLibelle.setBounds(161, 76, 213, 20);
				panel.add(txtLibelle);
			}
			{
				JLabel label = new JLabel("Nom r\u00E9sponsable");
				label.setBounds(43, 133, 114, 14);
				panel.add(label);
			}
			{
				txtResponsable = new JTextField();
				txtResponsable.setColumns(10);
				txtResponsable.setBounds(161, 130, 213, 20);
				panel.add(txtResponsable);
			}
			{
				JLabel label = new JLabel("Volume horaire");
				label.setBounds(43, 187, 108, 14);
				panel.add(label);
			}
			{
				txtHoraire = new JTextField();
				txtHoraire.setColumns(10);
				txtHoraire.setBounds(161, 184, 74, 20);
				panel.add(txtHoraire);
			}
			{
				JLabel label = new JLabel("Fili\u00E8re");
				label.setBounds(43, 241, 46, 14);
				panel.add(label);
			}
			{
				try {
					comboFiliere = new JComboBox(FiliereDB.getAllModel());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}
				comboFiliere.setBounds(161, 238, 213, 20);
				panel.add(comboFiliere);
			}
			{
				JButton btnModifier = new JButton("Modifier");
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtLibelle.getText().isEmpty()||txtResponsable.getText().isEmpty()||txtHoraire.getText().isEmpty()||comboFiliere.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veillez remplir tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						else if(!Tools.isNumber(txtCode.getText()))
						{
							JOptionPane.showMessageDialog(null, "Le code saisie n'est pas valide","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						else if(!Tools.isNumber(txtHoraire.getText()))
						{
							JOptionPane.showMessageDialog(null, "Le volume horaire saisie n'est pas valide","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Filiere filiere = (Filiere)comboFiliere.getSelectedItem();
							Module m = new Module(Integer.parseInt(txtCode.getText()), txtLibelle.getText(), txtResponsable.getText(),Integer.parseInt(txtHoraire.getText()),filiere.getCodeFiliere());
							try {
								ModuleDB.update(m);
								System.out.println(m);
								JOptionPane.showMessageDialog(null, "Bien Modifier","Info",JOptionPane.INFORMATION_MESSAGE);
								FrmTree.refresh();
								dispose();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				btnModifier.setBounds(173, 307, 89, 23);
				panel.add(btnModifier);
			}
			
			JLabel lblCode = new JLabel("code");
			lblCode.setBounds(43, 34, 46, 14);
			panel.add(lblCode);
			
			txtCode = new JTextField();
			txtCode.setBounds(161, 31, 74, 20);
			panel.add(txtCode);
			txtCode.setColumns(10);
		}
	}
}
