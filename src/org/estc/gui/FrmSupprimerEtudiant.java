package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.estc.dao.EtudiantDB;
import org.estc.dao.Tools;
import org.estc.metier.Etudiant;

public class FrmSupprimerEtudiant extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JPanel contentPane;
	private static JTextField txtCne;

	public FrmSupprimerEtudiant() {
		setModal(true);
		setTitle("Supprimer Etudiant");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Supprimer un etudiant", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(11, 11, 338, 102);
		contentPane.add(panel);
		
		JLabel label = new JLabel("CNE");
		label.setBounds(30, 31, 58, 14);
		panel.add(label);
		
		txtCne = new JTextField();
		txtCne.setColumns(10);
		txtCne.setBounds(98, 28, 223, 20);
		panel.add(txtCne);
		
		JButton btnSupprimer = new JButton("Supprimer");
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCne.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Veillez saisir le CNE", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else if (!Tools.isNumber(txtCne.getText())||txtCne.getText().length()<10)
				{
					JOptionPane.showMessageDialog(null, "Veillez saisir un CNE valide\nexemple 1600000000", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Etudiant e;
					try {
						e = EtudiantDB.get(Integer.parseInt(txtCne.getText()));
						if(e != null){
							
							String message = "Vouslez-vous supprimer "+e.getNom()+" cne "+e.getCne()+"?";
						    String title = "Confirmer Suppression !";
						    
						    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						    if (reply == JOptionPane.YES_OPTION)
						    {
						    	try {
									EtudiantDB.delete(e.getCne());
									JOptionPane.showMessageDialog(null,"Bien Supprimer","Info",JOptionPane.INFORMATION_MESSAGE);
								} catch (Exception e1) {
									
									e1.printStackTrace();
								}
						    }
						}else{
							JOptionPane.showMessageDialog(null, "CNE inexistant", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						//nothing
					}
				}
				
			}
		});
		btnSupprimer.setBounds(121, 59, 97, 23);
		panel.add(btnSupprimer);
		setLocationRelativeTo(null);
	}

}
