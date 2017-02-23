package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.estc.dao.ElementDB;
import org.estc.dao.ModuleDB;
import org.estc.dao.Tools;
import org.estc.metier.Element;
import org.estc.metier.Module;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAjouterElement extends JDialog {
	private JTextField txtCoefficient;
	private JTextField txtLibelle;
	JComboBox comboModule;

	public FrmAjouterElement() {
		setBounds(100, 100, 450, 265);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ajouter un element", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 214);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Module");
		lblNewLabel.setBounds(38, 32, 46, 14);
		panel.add(lblNewLabel);
		
		try {
			comboModule = new JComboBox(ModuleDB.getAllModel());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		comboModule.setBounds(122, 29, 252, 20);
		panel.add(comboModule);
		
		JLabel lblCoefficient = new JLabel("Coefficient");
		lblCoefficient.setBounds(38, 74, 83, 14);
		panel.add(lblCoefficient);
		
		txtCoefficient = new JTextField();
		txtCoefficient.setBounds(122, 71, 86, 20);
		panel.add(txtCoefficient);
		txtCoefficient.setColumns(10);
		
		JLabel lblLibelle = new JLabel("Libelle");
		lblLibelle.setBounds(38, 119, 46, 14);
		panel.add(lblLibelle);
		
		txtLibelle = new JTextField();
		txtLibelle.setBounds(122, 116, 252, 20);
		panel.add(txtLibelle);
		txtLibelle.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboModule.getSelectedItem().equals("")||txtCoefficient.getText().isEmpty()||txtLibelle.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Veillez remplir tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				else if(!Tools.isNumber(txtCoefficient.getText()))
				{
					JOptionPane.showMessageDialog(null, "Le champs Coefficient n'est pas valide","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Module m = (Module) comboModule.getSelectedItem();
					Element e =new Element(m.getCodeModule(),Integer.parseInt(txtCoefficient.getText()),txtLibelle.getText());
					try {
						ElementDB.add(e);
						JOptionPane.showMessageDialog(null, "Bien Ajouter","Info",JOptionPane.INFORMATION_MESSAGE);
						FrmTree.refresh();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAjouter.setBounds(162, 168, 89, 23);
		panel.add(btnAjouter);
	}
}
