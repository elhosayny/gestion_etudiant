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
import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmModifierElement extends JDialog {
	private static JTextField txtCoefficient;
	private static JTextField txtLibelle;
	private static  JTextField txtCode;
	private static JComboBox comboModule;

	public void disableCode(){
		txtCode.setEnabled(true);
	}
	public static void remplirChamps(Element e)
	{
		txtCode.setText(Integer.toString(e.getCodeElement()));
		txtCoefficient.setText(Integer.toString(e.getCoefficient()));
		txtLibelle.setText(e.getLibelleElement());
		for(int i=0;i<comboModule.getItemCount();i++)
		{
			Module m = (Module)comboModule.getItemAt(i);
			if(m.getCodeModule()==e.getCodeModule()) comboModule.setSelectedIndex(i);
		}
		txtCode.setEnabled(false);
	}
	public FrmModifierElement() {
		setBounds(100, 100, 449, 315);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modifier un element", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 414, 254);
			getContentPane().add(panel);
			{
				JLabel label = new JLabel("Module");
				label.setBounds(38, 72, 46, 14);
				panel.add(label);
			}
			{
				
				try {
					comboModule = new JComboBox(ModuleDB.getAllModel());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}
				comboModule.setBounds(122, 69, 252, 20);
				panel.add(comboModule);
			}
			{
				JLabel label = new JLabel("Coefficient");
				label.setBounds(38, 114, 83, 14);
				panel.add(label);
			}
			{
				txtCoefficient = new JTextField();
				txtCoefficient.setColumns(10);
				txtCoefficient.setBounds(122, 111, 86, 20);
				panel.add(txtCoefficient);
			}
			{
				JLabel label = new JLabel("Libelle");
				label.setBounds(38, 159, 46, 14);
				panel.add(label);
			}
			{
				txtLibelle = new JTextField();
				txtLibelle.setColumns(10);
				txtLibelle.setBounds(122, 156, 252, 20);
				panel.add(txtLibelle);
			}
			{
				JButton btnModifier = new JButton("Modifier");
				btnModifier.addActionListener(new ActionListener() {
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
							Element e =new Element(Integer.parseInt(txtCode.getText()),m.getCodeModule(),Integer.parseInt(txtCoefficient.getText()),txtLibelle.getText());
							try {
								ElementDB.update(e);
								JOptionPane.showMessageDialog(null, "Bien Modifier","Info",JOptionPane.INFORMATION_MESSAGE);
								FrmTree.refresh();
								dispose();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				btnModifier.setBounds(162, 208, 89, 23);
				panel.add(btnModifier);
			}
			{
				txtCode = new JTextField();
				txtCode.setBounds(122, 26, 86, 20);
				panel.add(txtCode);
				txtCode.setColumns(10);
			}
			{
				JLabel lblCode = new JLabel("Code");
				lblCode.setBounds(38, 29, 46, 14);
				panel.add(lblCode);
			}
		}
	}

}
