package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.estc.dao.EtudiantDB;
import org.estc.dao.Tools;
import org.estc.metier.Etudiant;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAjouterEtudiant extends JDialog {

	private JPanel contentPane;
	private JTextField txtCne;
	private JLabel lblCin;
	private JTextField txtCin;
	private JTextField txtNom;
	private JLabel lblNom;
	private JTextField txtPrenom;
	private JLabel lblPrenom;
	private JTextField txtDiplome;
	private JLabel lblDiplome;
	private JButton btnNewButton;

	public FrmAjouterEtudiant() {
		setModal(true);
		setTitle("Ajouter un nouveau etudiant");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 398, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajouter un etudiant", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 362, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCne = new JLabel("CNE");
		lblCne.setBounds(30, 31, 58, 14);
		panel.add(lblCne);
		
		txtCne = new JTextField();
		txtCne.setBounds(98, 28, 223, 20);
		panel.add(txtCne);
		txtCne.setColumns(10);
		
		lblCin = new JLabel("CIN");
		lblCin.setBounds(30, 62, 58, 14);
		panel.add(lblCin);
		
		txtCin = new JTextField();
		txtCin.setBounds(98, 59, 86, 20);
		panel.add(txtCin);
		txtCin.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setBounds(98, 90, 223, 20);
		panel.add(txtNom);
		txtNom.setColumns(10);
		
		lblNom = new JLabel("Nom");
		lblNom.setBounds(30, 93, 58, 14);
		panel.add(lblNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(98, 123, 223, 20);
		panel.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(30, 126, 58, 14);
		panel.add(lblPrenom);
		
		txtDiplome = new JTextField();
		txtDiplome.setBounds(98, 154, 223, 20);
		panel.add(txtDiplome);
		txtDiplome.setColumns(10);
		
		lblDiplome = new JLabel("Diplome");
		lblDiplome.setBounds(30, 157, 46, 14);
		panel.add(lblDiplome);
		
		btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
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
					Etudiant e= new Etudiant(Integer.parseInt(txtCne.getText()),txtCin.getText(),txtNom.getText(),txtPrenom.getText(),txtDiplome.getText());
					
					try {
						EtudiantDB.add(e);
						JOptionPane.showMessageDialog(null,"Bien Ajouter","Info",JOptionPane.INFORMATION_MESSAGE);
						FrmTree.refresh();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(142, 201, 89, 23);
		panel.add(btnNewButton);
		setLocationRelativeTo(null);
	}
}
