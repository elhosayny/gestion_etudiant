package org.estc.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import org.estc.dao.ElementDB;
import org.estc.dao.ModuleDB;
import org.estc.dao.NoteDB;
import org.estc.metier.Element;
import org.estc.metier.Module;
import org.estc.metier.Note;

import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;

public class DelibM extends JPanel {
	private JTable table;
	private JComboBox comboBoxModule_1;
	JComboBox comboBoxModule = new JComboBox();
	JComboBox comboBoxElement = new JComboBox();
	JComboBox comboBoxSess = new JComboBox();
	DelibTableModelFilter model;
	TableRowSorter<DelibTableModelFilter> sorter;
	
	public void setComboBoxElement(JComboBox comboBoxElement){
		this.comboBoxElement = comboBoxElement;
	}

	/**
	 * Create the panel.
	 */
	public DelibM() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[] {33, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JTextField textField = new JTextField();
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JLabel label_1 = new JLabel("Delibiration");
		label_1.setFont(new Font("DialogInput", Font.BOLD, 18));
		panel.add(label_1);
		
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
		model = new DelibTableModelFilter('M', "Tous", "S");
		sorter = new TableRowSorter<DelibTableModelFilter>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		
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
		gbc_panel_2.gridy = 1;
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
			}else{
				model = new DelibTableModelFilter('E', el.getLibelleElement(), st);
				sorter 
			    = new TableRowSorter<DelibTableModelFilter>(model);
				table.setModel(model);
				table.setRowSorter(sorter);
			}
		} catch (Exception e) {}
		
	}

	protected void moduleComboboxH() {
		Module m = (Module)comboBoxModule_1.getSelectedItem();
		String st = (String)comboBoxSess.getSelectedItem();
		
		try {
			comboBoxElement.removeAllItems();
			comboBoxElement.setModel(ElementDB.getAllFilter(m.getCodeModule()));
			model = new DelibTableModelFilter('M', m.getLibelleModule(), "Tous");
			sorter 
		    = new TableRowSorter<DelibTableModelFilter>(model);
			table.setModel(model);
			table.setRowSorter(sorter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
