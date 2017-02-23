package org.estc.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.estc.dao.ModuleDB;
import org.estc.metier.Module;

public class ModuleTableModel extends AbstractTableModel{
	
	ArrayList<String> moduleColumns = new ArrayList<>();
	ArrayList<Module> modules = new ArrayList<>();
	public ModuleTableModel()
	{
		try {
			moduleColumns = ModuleDB.getColumns();
			modules = ModuleDB.getAll();
		} catch (Exception e) {
		}
	}

	@Override
	public int getColumnCount() {
		
		return moduleColumns.size();
	}

	@Override
	public int getRowCount() {
		
		return modules.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex)
		{
		case 0:
			return modules.get(rowIndex).getCodeModule();
		case 1:
			return modules.get(rowIndex).getLibelleModule();
		case 2:
			return modules.get(rowIndex).getNomResponable();
		case 3:
			return modules.get(rowIndex).getVolumeHoraire();
		case 4:
			return modules.get(rowIndex).getCodeFiliere();
		default:
			return null;
		}
	}
	public String getColumnName(int column)
	{
		return moduleColumns.get(column);
	}
	
	public void delRow(int row) {
        
		modules.remove(row);       
        this.fireTableDataChanged();
         
	}

}
