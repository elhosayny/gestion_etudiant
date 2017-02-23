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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAjouterModule extends JDialog {
	private JTextField txtLibelle;
	private JTextField txtResponsable;
	private JTextField txtHoraire;
	private JComboBox comboFiliere;

	public FrmAjouterModule() {
		setTitle("Ajouter un module");
		setModal(true);
		setBounds(100, 100, 479, 375);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Ajouter un module", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(23, 11, 430, 314);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Libelle");
				lblNewLabel.setBounds(38, 37, 108, 14);
				panel.add(lblNewLabel);
			}
			{
				txtLibelle = new JTextField();
				txtLibelle.setBounds(156, 34, 213, 20);
				panel.add(txtLibelle);
				txtLibelle.setColumns(10);
			}
			{
				JLabel lblNomRsponsable = new JLabel("Nom r\u00E9sponsable");
				lblNomRsponsable.setBounds(38, 91, 114, 14);
				panel.add(lblNomRsponsable);
			}
			{
				txtResponsable = new JTextField();
				txtResponsable.setBounds(156, 88, 213, 20);
				panel.add(txtResponsable);
				txtResponsable.setColumns(10);
			}
			{
				JLabel lblVolumeHoraire = new JLabel("Volume horaire");
				lblVolumeHoraire.setBounds(38, 145, 108, 14);
				panel.add(lblVolumeHoraire);
			}
			{
				txtHoraire = new JTextField();
				txtHoraire.setBounds(156, 142, 74, 20);
				panel.add(txtHoraire);
				txtHoraire.setColumns(10);
			}
			{
				JLabel lblFiliere = new JLabel("Fili\u00E8re");
				lblFiliere.setBounds(38, 199, 46, 14);
				panel.add(lblFiliere);
			}
			
			
			try {
				comboFiliere = new JComboBox(FiliereDB.getAllModel());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
			}
			comboFiliere.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			comboFiliere.setBounds(156, 196, 213, 20);
			panel.add(comboFiliere);
			{
				JButton btnNewButton = new JButton("Ajouter");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(txtLibelle.getText().isEmpty()||txtResponsable.getText().isEmpty()||txtHoraire.getText().isEmpty()||comboFiliere.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veillez remplir tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						else if(!Tools.isNumber(txtHoraire.getText()))
						{
							JOptionPane.showMessageDialog(null, "Le volume horaire saisie n'est pas valide","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Filiere filiere = (Filiere)comboFiliere.getSelectedItem();
							Module m = new Module(txtLibelle.getText(),txtResponsable.getText(),Integer.parseInt(txtHoraire.getText()),filiere.getCodeFiliere());
							try {
								ModuleDB.add(m);
								JOptionPane.showMessageDialog(null, "Bine ajouter","Erreur",JOptionPane.INFORMATION_MESSAGE);
								FrmTree.refresh();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				btnNewButton.setBounds(168, 265, 89, 23);
				panel.add(btnNewButton);
				setLocationRelativeTo(null);
			}
		}
	}
}
