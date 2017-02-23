package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.estc.dao.EtudiantDB;
import org.estc.dao.Tools;
import org.estc.metier.Etudiant;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmModifierEtudiant extends JDialog {

	private static JPanel contentPane;
	private static JTextField txtCne;
	private static JTextField txtCin;
	private static JTextField txtNom;
	private static JTextField txtPrenom;
	private static JTextField txtDiplome;
	
	public static void remplirChamp(Etudiant e)
	{
		txtCne.setText(Integer.toString(e.getCne()));
		txtCin.setText(e.getCin());
		txtNom.setText(e.getNom());
		txtPrenom.setText(e.getPrenom());
		txtDiplome.setText(e.getDiplome());
	}
	public FrmModifierEtudiant() {
		setModal(true);
		setTitle("Modifier un etudiant");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modifier un etudiant", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(11, 11, 338, 243);
		contentPane.add(panel);
		
		JLabel label = new JLabel("CNE");
		label.setBounds(30, 31, 58, 14);
		panel.add(label);
		
		txtCne = new JTextField();
		txtCne.setColumns(10);
		txtCne.setBounds(98, 28, 223, 20);
		panel.add(txtCne);
		
		JLabel label_1 = new JLabel("CIN");
		label_1.setBounds(30, 62, 58, 14);
		panel.add(label_1);
		
		txtCin = new JTextField();
		txtCin.setColumns(10);
		txtCin.setBounds(98, 59, 86, 20);
		panel.add(txtCin);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(98, 90, 223, 20);
		panel.add(txtNom);
		
		JLabel label_2 = new JLabel("Nom");
		label_2.setBounds(30, 93, 58, 14);
		panel.add(label_2);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(98, 123, 223, 20);
		panel.add(txtPrenom);
		
		JLabel label_3 = new JLabel("Prenom");
		label_3.setBounds(30, 126, 58, 14);
		panel.add(label_3);
		
		txtDiplome = new JTextField();
		txtDiplome.setColumns(10);
		txtDiplome.setBounds(98, 154, 223, 20);
		panel.add(txtDiplome);
		
		JLabel label_4 = new JLabel("Diplome");
		label_4.setBounds(30, 157, 46, 14);
		panel.add(label_4);
		
		JButton btnModifier = new JButton("Modifier");
		
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCne.getText().isEmpty()||txtCin.getText().isEmpty()||txtNom.getText().isEmpty()||txtPrenom.getText().isEmpty()||txtDiplome.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Veillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else if (!Tools.isNumber(txtCne.getText())||txtCne.getText().length()<10)
				{
					JOptionPane.showMessageDialog(null, "Veillez saisir un CNE valide\nexemple 1600000000", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Etudiant e  = new Etudiant(Integer.parseInt(txtCne.getText()),txtCin.getText(),txtNom.getText(),txtPrenom.getText(),txtDiplome.getText());
					try {
						EtudiantDB.update(e);
						JOptionPane.showMessageDialog(null,"Bien Modifier","Info",JOptionPane.INFORMATION_MESSAGE);
						FrmTree.refresh();
						dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
						
					}
				}
			}
		});
		btnModifier.setBounds(132, 201, 97, 23);
		panel.add(btnModifier);
		setLocationRelativeTo(null);
	}
}
