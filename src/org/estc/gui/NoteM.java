package org.estc.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import org.estc.dao.ElementDB;
import org.estc.dao.EtudiantDB;
import org.estc.dao.ModuleDB;
import org.estc.dao.NoteDB;
import org.estc.dao.Tools;
import org.estc.metier.Element;
import org.estc.metier.Etudiant;
import org.estc.metier.Module;
import org.estc.metier.Note;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class NoteM extends JPanel {
	private JTextField textField;
	private JTable table;
	private JComboBox comboBoxModule_1;
	JComboBox comboBoxModule = new JComboBox();
	JComboBox comboBoxElement = new JComboBox();
	JComboBox comboBoxSess = new JComboBox();
	JButton btnModifier = new JButton("Modifier");
	JButton btnSupprimer = new JButton("Supprimer");
	NoteTableModel.NoteTableModelFilter model;
	TableRowSorter<NoteTableModel.NoteTableModelFilter> sorter;
	
	public void setComboBoxElement(JComboBox comboBoxElement){
		this.comboBoxElement = comboBoxElement;
	}
	
	/**
	 * Create the panel.
	 */
	public NoteM() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[] {33, 33, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);

		JPanel panelTitre = new JPanel();
		GridBagConstraints gbc_panelTitre = new GridBagConstraints();
		gbc_panelTitre.fill = GridBagConstraints.BOTH;
		gbc_panelTitre.gridx = 0;
		gbc_panelTitre.gridy = 0;
		add(panelTitre, gbc_panelTitre);
		
		JLabel lblGestionDeNote = new JLabel("Gestion des Notes");
		lblGestionDeNote.setFont(new Font("DialogInput", Font.BOLD, 18));
		panelTitre.add(lblGestionDeNote);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		
		JButton btnAjouter = new JButton("Ajouter");
		
		panel.add(btnAjouter);
		
		JLabel lblCne = new JLabel("Recherche :");
		panel.add(lblCne);
		
		textField = new JTextField();
		
		panel.add(textField);
		textField.setColumns(10);
		
		
		panel.add(btnModifier);
		btnModifier.setEnabled(false);
		
		panel.add(btnSupprimer);
		btnSupprimer.setEnabled(false);
		
		JButton btnNewButton = new JButton("Imprimer");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Tools.printTable("Note Etudiant", "GLAASRI", table);
			}
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.weightx = 1.0;
		gbc_panel_1.weighty = 1.0;
		gbc_panel_1.anchor = GridBagConstraints.PAGE_START;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {30};
		gbl_panel_1.rowHeights = new int[] {30};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0};
		panel_1.setLayout(gbl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		model = new NoteTableModel.NoteTableModelFilter('M', "Tous", "S");
		sorter 
	    = new TableRowSorter<NoteTableModel.NoteTableModelFilter>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FrmAjouterNote dialog = new FrmAjouterNote();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				int row = table.getSelectedRow();
				if(row != -1){
					Element el = null;
					try {
						el = ElementDB.get((String)model.getValueAt(row, 3));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					double nt = 0;
					System.out.println(model.getValueAt(row, 5).getClass()+" fdv "+model.getValueAt(row, 5).getClass().equals(java.lang.Double.class));
					if(model.getValueAt(row, 5).getClass().equals(java.lang.Double.class) && org.estc.dao.Tools.isDouble((Double)model.getValueAt(row, 5)))
						nt = (Double)model.getValueAt(row, 5);
					else
						nt = -1;
					Note n = new Note(Integer.parseInt((String) model.getValueAt(row, 0)),
							Integer.parseInt((String) model.getValueAt(row, 1)),(Integer)el.getCodeElement() ,(String) model.getValueAt(row, 4),nt);
					
					FrmModifierNote dialog = new FrmModifierNote();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					FrmModifierNote.remplirChamps(n);
					dialog.setVisible(true);
					System.out.println(n.getCodeNote()+" modified");
				}else{
					JOptionPane.showMessageDialog(null, "Selectionner une ligne pour la modifier","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				int row = table.getSelectedRow();
				if(row != -1){
					Element el = null;
					try {
						el = ElementDB.get((String)model.getValueAt(row, 3));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					double nt = 0;
					System.out.println(model.getValueAt(row, 5).getClass()+" fdv "+model.getValueAt(row, 5).getClass().equals(java.lang.Double.class));
					if(model.getValueAt(row, 5).getClass().equals(java.lang.Double.class) && org.estc.dao.Tools.isDouble((Double)model.getValueAt(row, 5)))
						nt = (Double)model.getValueAt(row, 5);
					else
						nt = -1;
	
					Note n = new Note(Integer.parseInt((String) model.getValueAt(row, 0)),
							Integer.parseInt((String) model.getValueAt(row, 1)),(Integer)el.getCodeElement() ,
							(String) model.getValueAt(row, 4), nt);
					String message = "Vouslez-vous supprimer la de "+n.getCne()+" ?";
				    String title = "Confirmer Suppression !";
				    
				    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				    if (reply == JOptionPane.YES_OPTION)
				    {
				    	try {
							NoteDB.delete(n.getCodeNote());
							model.delRow(row);
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
				    }
					System.out.println(n.getCodeNote()+" deleted");
				}else{
					JOptionPane.showMessageDialog(null, "Selectionner une ligne pour la supprimer","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void filter() {
				  String text = textField.getText();
			        if (text.length() == 0) {
			          sorter.setRowFilter(null);
			        } else {
			          sorter.setRowFilter(RowFilter.regexFilter(text));
			        }
			  }
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				filter();
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				filter();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				filter();
			}
			});
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		
		JLabel label = new JLabel("Filter :");
		panel_2.add(label);

		try {
			comboBoxModule_1 = new JComboBox(ModuleDB.getAllFilter());
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		comboBoxModule_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				comboBoxSess.setSelectedIndex(0);
				
				moduleComboboxH();
				
			}
		});
		panel_2.add(comboBoxModule_1);
		
		try {
			
			comboBoxElement = new JComboBox(ElementDB.getAllFilter(-1));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		comboBoxElement.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {

				elementComboboxH();
				
			}
		});
		panel_2.add(comboBoxElement);
		
		
		comboBoxSess.setModel(new DefaultComboBoxModel(new String[] { "Tous","Ordinaire", "Rattrapage"}));
		comboBoxSess.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				String st = (String)comboBoxSess.getSelectedItem();
				Element el = (Element)comboBoxElement.getSelectedItem();
				if(st.equals("Tous"))
					moduleComboboxH();
				else
					elementComboboxH();
			}
		});
		panel_2.add(comboBoxSess);
		
	}

	protected void elementComboboxH() {
		Element el = (Element)comboBoxElement.getSelectedItem();

		try {
			String st = (String)comboBoxSess.getSelectedItem();
			if(el.getLibelleElement().equals("Tous") && st.equals("Tous")){
				moduleComboboxH();
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
			}else{
				model = new NoteTableModel.NoteTableModelFilter('E', el.getLibelleElement(), st);
				sorter = new TableRowSorter<NoteTableModel.NoteTableModelFilter>(model);
				table.setModel(model);
				table.setRowSorter(sorter);
				btnModifier.setEnabled(true);
				btnSupprimer.setEnabled(true);
			}
			
		} catch (Exception e) {}
		
	}

	protected void moduleComboboxH() {
		Module m = (Module)comboBoxModule_1.getSelectedItem();
		String st = (String)comboBoxSess.getSelectedItem();
		
		try {
			comboBoxElement.removeAllItems();
			comboBoxElement.setModel(ElementDB.getAllFilter(m.getCodeModule()));
			model = new NoteTableModel.NoteTableModelFilter('M', m.getLibelleModule(), "Tous");
			sorter = new TableRowSorter<NoteTableModel.NoteTableModelFilter>(model);
			table.setModel(model);
			table.setRowSorter(sorter);
			btnModifier.setEnabled(false);
			btnSupprimer.setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
