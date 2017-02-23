package org.estc.gui;

import javax.swing.UIManager.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JTree;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.tree.DefaultTreeModel;

import org.estc.dao.ElementDB;
import org.estc.dao.EtudiantDB;
import org.estc.dao.FiliereDB;
import org.estc.dao.ModuleDB;
import org.estc.metier.Filiere;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;

public class FrmTree extends JFrame {
	private static JPanel panel_1 = new JPanel();
	private static CardLayout cardLayout = new CardLayout();
	private JLabel lblNewLabel_2;
	private JLabel lblValnbret_2;
	private static char content = 'e';
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					        if ("Metal".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					} catch (Exception e) {
					}
					FrmTree frame = new FrmTree();
					frame.setExtendedState(frame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public FrmTree() {
		setMinimumSize(new Dimension(690, 490));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/app.png").getPath()));
		setTitle("HOME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 514);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEtudiant = new JMenu("Filieres");
		menuBar.add(mnEtudiant);
		
		JMenuItem mntmAffichierTousLes = new JMenuItem("Affichier tous les filieres");
		mntmAffichierTousLes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('f');
				content = 'f';
			}
		});
		mnEtudiant.add(mntmAffichierTousLes);
		
		JMenuItem mntmAjouterUnNouvau = new JMenuItem("Ajouter un nouveau filiere");
		mntmAjouterUnNouvau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmAjouterFiliere dialog = new FrmAjouterFiliere();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnEtudiant.add(mntmAjouterUnNouvau);
		
		JMenuItem mntmModifierUnFiliere = new JMenuItem("Modifier un filiere d\u00E9ja existe");
		mntmModifierUnFiliere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmModifierFiliere frame = new FrmModifierFiliere();
				frame.disableCode();
				frame.setVisible(true);
			}
		});
		mnEtudiant.add(mntmModifierUnFiliere);
		
		JMenuItem mntmSupprimerUnFiliere = new JMenuItem("Supprimer un filiere");
		mntmSupprimerUnFiliere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmSupprimerFiliere dialog = new FrmSupprimerFiliere();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnEtudiant.add(mntmSupprimerUnFiliere);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		JMenu mnEtudiant_1 = new JMenu("Etudiants");
		menuBar.add(mnEtudiant_1);
		
		JMenuItem mntmAfficherTousLes = new JMenuItem("Afficher tous les etudiants");
		mntmAfficherTousLes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('e');
				content = 'e';
			}
		});
		mnEtudiant_1.add(mntmAfficherTousLes);
		
		JMenuItem mntmAjouterUnNouveau = new JMenuItem("Ajouter un nouveau etudiant");
		mntmAjouterUnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmAjouterEtudiant frm = new FrmAjouterEtudiant();
				frm.setVisible(true);
			}
		});
		mnEtudiant_1.add(mntmAjouterUnNouveau);
		
		JMenuItem mntmModifierUnEtudiant = new JMenuItem("Modifier un etudiant existant");
		mntmModifierUnEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmModifierEtudiant frm = new FrmModifierEtudiant();
				frm.setVisible(true);
			}
		});
		mnEtudiant_1.add(mntmModifierUnEtudiant);
		
		JMenuItem mntmSupprimerUnEtudiant = new JMenuItem("Supprimer un etudiant");
		mntmSupprimerUnEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmSupprimerEtudiant frm = new FrmSupprimerEtudiant();
				frm.setVisible(true);
			}
		});
		mnEtudiant_1.add(mntmSupprimerUnEtudiant);
		
		JMenu mnModules = new JMenu("Modules");
		menuBar.add(mnModules);
		
		JMenuItem mntmAfficherLaListe = new JMenuItem("Afficher la liste des modules");
		mntmAfficherLaListe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('m');
				content = 'm';
			}
		});
		mnModules.add(mntmAfficherLaListe);
		
		JMenuItem mntmAjouterUnModule = new JMenuItem("Ajouter un module");
		mntmAjouterUnModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmAjouterModule dialog = new FrmAjouterModule();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnModules.add(mntmAjouterUnModule);
		
		JMenuItem mntmModifierUnModule = new JMenuItem("Modifier un module existant");
		mntmModifierUnModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmModifierModule frame = new FrmModifierModule();
				frame.disableCode();
				frame.setVisible(true);
			}
		});
		mnModules.add(mntmModifierUnModule);
		
		JMenuItem mntmSupprimerUnModule = new JMenuItem("Supprimer un module");
		mntmSupprimerUnModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmSupprimerModule frm = new FrmSupprimerModule();
				frm.setVisible(true);
			}
		});
		mnModules.add(mntmSupprimerUnModule);
		
		JMenu mnElements = new JMenu("Elements");
		menuBar.add(mnElements);
		
		JMenuItem mntmAfficherTousLes_1 = new JMenuItem("Afficher tous les elements");
		mntmAfficherTousLes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('E');
				content = 'E';
			}
		});
		mnElements.add(mntmAfficherTousLes_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ajouter un element module");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmAjouterElement dialog = new FrmAjouterElement();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnElements.add(mntmNewMenuItem);
		
		JMenuItem mntmModifierUnElement = new JMenuItem("Modifier un element existant");
		mntmModifierUnElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmModifierElement dialog = new FrmModifierElement();
				dialog.disableCode();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnElements.add(mntmModifierUnElement);
		
		JMenuItem mntmSupprimerUnElement = new JMenuItem("Supprimer un element");
		mntmSupprimerUnElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmSupprimerElement frm = new FrmSupprimerElement();
				frm.setVisible(true);
			}
		});
		mnElements.add(mntmSupprimerUnElement);
		
		JMenu mnNotes = new JMenu("Notes");
		menuBar.add(mnNotes);
		
		JMenuItem mntmAffichierTousLes_1 = new JMenuItem("Afficher tous les note");
		mntmAffichierTousLes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('n');
				content = 'n';
			}
		});
		mnNotes.add(mntmAffichierTousLes_1);
		
		JMenuItem mntmAjouterUneNote = new JMenuItem("Ajouter une note");
		mntmAjouterUneNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmAjouterNote dialog = new FrmAjouterNote();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnNotes.add(mntmAjouterUneNote);
		
		JMenuItem mntmModifierUneNote = new JMenuItem("Modifier une note");
		mntmModifierUneNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmModifierNote dialog = new FrmModifierNote();
				dialog.disableCode();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnNotes.add(mntmModifierUneNote);
		
		JMenu mnDelib = new JMenu("Deliberation");
		menuBar.add(mnDelib);
		
		JMenuItem mntmAfficherDelib = new JMenuItem("Afficher la deliberation");
		mntmAfficherDelib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('d');
				content = 'd';
			}
		});
		mnDelib.add(mntmAfficherDelib);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0};
		gridBagLayout.rowHeights = new int[] {37, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.window);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel = new JLabel("Nombre d'Etudiant");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ValNbrEt");
		lblNewLabel_1.setBackground(SystemColor.controlHighlight);
		try {
			lblNewLabel_2 = new JLabel(Integer.toString(EtudiantDB.etudiantCount()));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_1);
		
		
		JLabel lblValnbret = new JLabel("Nombre de Filiere");
		lblValnbret.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblValnbret = new GridBagConstraints();
		gbc_lblValnbret.insets = new Insets(0, 0, 5, 0);
		gbc_lblValnbret.gridx = 0;
		gbc_lblValnbret.gridy = 2;
		panel_2.add(lblValnbret, gbc_lblValnbret);
		
		JLabel lblValnbret_1 = new JLabel("ValNbrEt");
		try {
			lblValnbret_2 = new JLabel(Integer.toString(FiliereDB.filiereCount()));
			lblValnbret_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		lblValnbret_2.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_lblValnbret_1 = new GridBagConstraints();
		gbc_lblValnbret_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblValnbret_1.gridx = 0;
		gbc_lblValnbret_1.gridy = 3;
		panel_2.add(lblValnbret_2, gbc_lblValnbret_1);
		
		JLabel label = new JLabel("Nombre de Module");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 4;
		panel_2.add(label, gbc_label);
		
		JLabel label_1 = new JLabel("3");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		try {
			label_1 = new JLabel(Integer.toString(ModuleDB.moduleCount()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		label_1.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 5;
		panel_2.add(label_1, gbc_label_1);
		
		JLabel label_2 = new JLabel("Nombre d'Element");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 6;
		panel_2.add(label_2, gbc_label_2);
		
		JLabel label_3 = new JLabel("3");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		try {
			label_3 = new JLabel(Integer.toString(ElementDB.elementCount()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		label_3.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 7;
		panel_2.add(label_3, gbc_label_3);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{96, 95, 95, 95, 95, 0};
		gbl_panel.rowHeights = new int[] {50};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Gestion Etudiant");
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('e');
				content = 'e';
			}
		});
		
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/images/etudiant.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Gestion Module");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('m');
				content = 'm';
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/images/module.png")));
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setToolTipText("Gestion Element");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('E');
				content = 'E';
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("/images/elementM.png")));
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("Gestion Note");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 0;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('n');
				content = 'n';
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(getClass().getResource("/images/note.png")));
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("Delibiration");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_4.gridx = 4;
		gbc_btnNewButton_4.gridy = 0;
		panel.add(btnNewButton_4, gbc_btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTree.setMainPanel('d');
				content = 'd';
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(getClass().getResource("/images/delib.png")));
		
		
		panel_1.setLayout(cardLayout);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weightx = 5.0;
		gbc_panel_1.weighty = 10.0;
		gbc_panel_1.anchor = GridBagConstraints.PAGE_START;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		getContentPane().add(panel_1, gbc_panel_1);
		
		FrmTree.setMainPanel('e');
		
	}
	
	public static void refresh(){
		setMainPanel(content);
	}
	public static void setMainPanel(char c){
		JPanel panel;
		
		switch(c){
			case 'e':
				panel = new EtudiantM();
				break;
			case 'm':
				panel = new ModuleM();
				break;
			case 'E':
				panel = new ElementM();
				break;
			case 'f':
				panel = new FiliereM();
				break;
			case 'n':
				panel = new NoteM();
				break;
			case 'd':
				panel = new DelibM();
				break;	
			default:
				panel = new JPanel();
				break;
		}
		
		panel_1.add(panel, "main");

		cardLayout.show(panel_1, "main");
	}
}
