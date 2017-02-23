package org.estc.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.estc.dao.EtudiantDB;
import org.estc.metier.Etudiant;

public class EtudiantTableModel extends AbstractTableModel{
	
	private ArrayList<Etudiant> etudiants;
	private ArrayList<String> etudiantColumns;
	private List<Integer> lEditable;
	public EtudiantTableModel(){
		try
		{
			etudiants = new ArrayList<Etudiant>(EtudiantDB.getAll()); 
			etudiantColumns = EtudiantDB.getColumns();
			lEditable = new ArrayList<>();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean isCellEditable(int row, int col) {
		
		if(this.lEditable.contains(row))
            return true;
		else 
			return false;
      
    }

	@Override
	public int getColumnCount() {
		
		return etudiantColumns.size();
	}

	@Override
	public int getRowCount() {
		
		return etudiants.size();
	}
	
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex)
		{
		case 0:
			return etudiants.get(rowIndex).getCne();
		case 1:
			return etudiants.get(rowIndex).getCin();
		case 2:
			return etudiants.get(rowIndex).getNom();
		case 3:
			return etudiants.get(rowIndex).getPrenom();
		case 4:
			return etudiants.get(rowIndex).getDiplome();
		default:
				return null;
			
		}
	}
	
	public void setValueAt(Object value, int row, int col) {
        switch(col)
		{
		case 0:
			etudiants.get(row).setCne((Integer)value);
			break;
		case 1:
			etudiants.get(row).setCin((String) value);
			break;
		case 2:
			etudiants.get(row).setNom((String) value);
			break;
		case 3:
			etudiants.get(row).setPrenom((String) value);
			break;
		case 4:
			etudiants.get(row).setDiplome((String) value);
			break;
		default:
			break;
			
		}
    	this.fireTableDataChanged();
     
	}
	
	public void addRow(Etudiant row) {
        
		etudiants.add(row);
        this.fireTableDataChanged();
        
    }
	
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	public void delRow(int row) {
        
		etudiants.remove(row);       
        this.fireTableDataChanged();
         
	}
	public String getColumnName(int column)
	{
		return etudiantColumns.get(column);
	}
	
	public void setEditable(Integer i){
		this.lEditable.add(i);
	}

}
