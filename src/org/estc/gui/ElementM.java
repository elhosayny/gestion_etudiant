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
import org.estc.metier.Element;
import org.estc.metier.Etudiant;
import org.estc.metier.Module;
import java.awt.Font;

public class ElementM extends JPanel {
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ElementM() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[] {33, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		JLabel label = new JLabel("Gestion des Elements");
		label.setFont(new Font("DialogInput", Font.BOLD, 18));
		panel_2.add(label);

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
		
		JButton btnModifier = new JButton("Modifier");
		
		panel.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		panel.add(btnSupprimer);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sauvgader");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weightx = 1.0;
		gbc_panel_1.weighty = 1.0;
		gbc_panel_1.anchor = GridBagConstraints.PAGE_START;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
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
		ElementTableModel model = new ElementTableModel();
		TableRowSorter<ElementTableModel> sorter 
	    = new TableRowSorter<ElementTableModel>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FrmAjouterElement dialog = new FrmAjouterElement();
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
					Element el = new Element(Integer.parseInt(model.getValueAt(row, 0).toString()),
							Integer.parseInt(model.getValueAt(row, 1).toString()), Integer.parseInt(model.getValueAt(row, 2).toString()),model.getValueAt(row, 3).toString());
					FrmModifierElement dialog = new FrmModifierElement();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					FrmModifierElement.remplirChamps(el);
					dialog.setVisible(true);
					System.out.println(el.getCodeElement()+" modified");
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
					Element el = new Element((Integer)model.getValueAt(row, 0),(Integer) model.getValueAt(row, 1),(Integer) model.getValueAt(row, 2), (String)model.getValueAt(row, 3));
					String message = "Vouslez-vous supprimer "+el.getLibelleElement()+" code"+el.getCodeElement()+"?";
				    String title = "Confirmer Suppression !";
				    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				    if (reply == JOptionPane.YES_OPTION)
				    {
				    	try {
				    		ElementDB.delete(el.getCodeElement());
				    		model.delRow(row);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				    }
					System.out.println(el.getCodeModule()+" deleted");
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
	}

}
