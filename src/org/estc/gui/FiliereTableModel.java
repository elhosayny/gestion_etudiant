package org.estc.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.estc.dao.FiliereDB;
import org.estc.metier.Filiere;

public class FiliereTableModel extends AbstractTableModel{
	
	
	
	ArrayList<String> filiereColumns = new ArrayList<>();
	ArrayList<Filiere> filieres = new ArrayList<>();
	public FiliereTableModel()
	{
		try
		{
			filiereColumns = FiliereDB.getColumns();
			filieres = FiliereDB.getAll();
					
		}catch(Exception e){}
	}
	@Override
	public int getColumnCount() {
		
		return filiereColumns.size();
	}

	@Override
	public int getRowCount() {
		
		return filieres.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex)
		{
			case 0:
				return filieres.get(rowIndex).getCodeFiliere();
			case 1:
				return filieres.get(rowIndex).getLibelleFiliere();
			case 2:
				return filieres.get(rowIndex).getDepartement();
			default:
				return null;
		}
	}
	public String getColumnName(int column)
	{
		return filiereColumns.get(column);
	}
	
	public void delRow(int row) {
        
		filieres.remove(row);       
        this.fireTableDataChanged();
         
	}

}
