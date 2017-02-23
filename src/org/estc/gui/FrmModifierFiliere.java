package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.estc.dao.FiliereDB;
import org.estc.metier.Filiere;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmModifierFiliere extends JDialog {
	private static JTextField txtLibelle;
	private static JTextField txtDepartement;
	private static JTextField txtCode;

	public void disableCode(){
		txtCode.setEnabled(true);
	}
	public static void remplirChamp(Filiere f)
	{
		txtCode.setText(Integer.toString(f.getCodeFiliere()));
		txtLibelle.setText(f.getLibelleFiliere());
		txtDepartement.setText(f.getDepartement());
		txtCode.setEnabled(false);
	}
	public FrmModifierFiliere() {
		setBounds(100, 100, 411, 287);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modifier un filiere", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 23, 368, 210);
			getContentPane().add(panel);
			{
				JLabel label = new JLabel("Libell\u00E9");
				label.setBounds(10, 73, 63, 14);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("D\u00E9partement");
				label.setBounds(10, 112, 63, 14);
				panel.add(label);
			}
			{
				txtLibelle = new JTextField();
				txtLibelle.setColumns(10);
				txtLibelle.setBounds(92, 70, 244, 20);
				panel.add(txtLibelle);
			}
			{
				txtDepartement = new JTextField();
				txtDepartement.setColumns(10);
				txtDepartement.setBounds(92, 109, 244, 20);
				panel.add(txtDepartement);
			}
			{
				JButton btnModifier = new JButton("Modifier");
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(txtLibelle.getText().isEmpty()||txtDepartement.getText().isEmpty())
						{
							JOptionPane.showMessageDialog(null,"Veillez remplir tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Filiere f = new Filiere(Integer.parseInt(txtCode.getText()),txtLibelle.getText(),txtDepartement.getText());
							
							try {
								FiliereDB.update(f);
								JOptionPane.showMessageDialog(null,"Bien Modifier","Info",JOptionPane.INFORMATION_MESSAGE);
								FrmTree.refresh();
								dispose();
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
							}
							
						}
					}
				});
				btnModifier.setBounds(139, 164, 89, 23);
				panel.add(btnModifier);
			}
			{
				txtCode = new JTextField();
				txtCode.setBounds(92, 28, 86, 20);
				panel.add(txtCode);
				txtCode.setColumns(10);
			}
			{
				JLabel lblCode = new JLabel("Code");
				lblCode.setBounds(10, 31, 46, 14);
				panel.add(lblCode);
			}
		}
	}

}
