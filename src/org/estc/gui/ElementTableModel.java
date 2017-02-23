package org.estc.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.estc.dao.ElementDB;
import org.estc.metier.Element;

public final class ElementTableModel extends AbstractTableModel{
	
	private ArrayList<Element> elements;
	private ArrayList<String> elementColumns;
	public ElementTableModel()
	{
		try
		{
			this.elements = ElementDB.getAll();
			this.elementColumns = ElementDB.getColumns();
		}catch(Exception e){}
	}

	@Override
	public int getColumnCount() {
		
		return elementColumns.size();
	}

	@Override
	public int getRowCount() {
		
		return elements.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex)
		{	
		case 0:
			return elements.get(rowIndex).getCodeElement();
		case 1:
			return elements.get(rowIndex).getCodeModule();
		case 2:
			return elements.get(rowIndex).getCoefficient();
		case 3:
			return elements.get(rowIndex).getLibelleElement();
		default:
			return null;
		}
	}
	
	public String getColumnName(int column)
	{
		return elementColumns.get(column);	
	}
	
	public void delRow(int row) {
        
		elements.remove(row);       
        this.fireTableDataChanged();
         
	}
	

}
