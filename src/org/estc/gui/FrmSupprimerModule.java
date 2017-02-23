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
import org.estc.dao.ModuleDB;
import org.estc.dao.Tools;
import org.estc.metier.Etudiant;
import org.estc.metier.Module;

public class FrmSupprimerModule extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JPanel contentPane;
	private static JTextField txtCode;

	public FrmSupprimerModule() {
		setModal(true);
		setTitle("Supprimer Module");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Supprimer un Module", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(11, 11, 338, 102);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Lebelle ");
		label.setBounds(30, 31, 58, 14);
		panel.add(label);
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(98, 28, 223, 20);
		panel.add(txtCode);
		
		JButton btnSupprimer = new JButton("Supprimer");
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCode.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Veillez saisir le Module", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else
				{
					Module m;
					try {
						m = ModuleDB.get(txtCode.getText());
						if(m != null){
							
							String message = "Vouslez-vous supprimer "+m.getLibelleModule()+" code "+m.getCodeModule()+"?";
						    String title = "Confirmer Suppression !";
						    // display the JOptionPane showConfirmDialog
						    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						    if (reply == JOptionPane.YES_OPTION)
						    {
						    	try {
									ModuleDB.delete(m.getCodeModule());
									JOptionPane.showMessageDialog(null,"Bien Supprimer","Info",JOptionPane.INFORMATION_MESSAGE);
								} catch (Exception e1) {
									
									e1.printStackTrace();
								}
						    }
						}else{
							JOptionPane.showMessageDialog(null, "Module inexistant", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						
					}
				}
				
			}
		});
		btnSupprimer.setBounds(121, 59, 97, 23);
		panel.add(btnSupprimer);
		setLocationRelativeTo(null);
	}

}
