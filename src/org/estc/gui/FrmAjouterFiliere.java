package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.estc.dao.FiliereDB;
import org.estc.metier.Filiere;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAjouterFiliere extends JDialog {
	private JTextField txtLibelle;
	private JTextField txtDepartement;

	public FrmAjouterFiliere() {
		setBounds(100, 100, 401, 256);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ajouter un filiere", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 368, 197);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Libell\u00E9");
		lblNewLabel.setBounds(21, 48, 63, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("D\u00E9partement");
		lblNewLabel_1.setBounds(21, 87, 63, 14);
		panel.add(lblNewLabel_1);
		
		txtLibelle = new JTextField();
		txtLibelle.setBounds(103, 45, 244, 20);
		panel.add(txtLibelle);
		txtLibelle.setColumns(10);
		
		txtDepartement = new JTextField();
		txtDepartement.setBounds(103, 84, 244, 20);
		panel.add(txtDepartement);
		txtDepartement.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtLibelle.getText().isEmpty()||txtDepartement.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Veillez remplir tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Filiere f = new Filiere(txtLibelle.getText(),txtDepartement.getText());
					
					try {
						FiliereDB.add(f);
						JOptionPane.showMessageDialog(null,"Bien Ajouter","Info",JOptionPane.INFORMATION_MESSAGE);
						FrmTree.refresh();
						txtLibelle.setText("");
						txtDepartement.setText("");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnAjouter.setBounds(139, 143, 89, 23);
		panel.add(btnAjouter);
	}
}
