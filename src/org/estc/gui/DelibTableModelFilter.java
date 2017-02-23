package org.estc.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.estc.dao.NoteDB;
import org.estc.metier.Etudiant;
import org.estc.metier.Note;

public class DelibTableModelFilter extends AbstractTableModel{
		
		ArrayList<String> noteColumns = new ArrayList<>();
		ArrayList<String[]> notes = new ArrayList<>();
		String SESSION, NAME;
		public DelibTableModelFilter(char type, String name, String session)
		{
			SESSION = session;
			NAME = name;
			try {
				
				switch(type){
					case 'M':
						noteColumns.add("Nom Etudiant");
						noteColumns.add("Prenom Etudiant");
						if(!name.equals("Tous"))
							noteColumns.add("Module");
						noteColumns.add("Etat");
						CaculeNoteModule(name, session);
						break;
					case 'E':
						noteColumns.add("Code Note");
						noteColumns.add("CNE");
						noteColumns.add("Nom Etudiant");
						noteColumns.add("Element");
						noteColumns.add("Session");
						noteColumns.add("Etat");
						CaculeNoteElement(name, session);
						break;
					default:
						break;
				}
				/*
				notes.forEach(action);*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		private void CaculeNoteModule(String name, String session){
			try {
				notes = new ArrayList<>();
				notes = NoteDB.getModules(name, session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void CaculeNoteSession(String name, String session) {
			try {
				notes = new ArrayList<>();
				notes = NoteDB.getModules(name, session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void CaculeNoteElement(String name, String session) {
			try {
				notes = new ArrayList<>();
				notes = NoteDB.getElements(name, session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public int getColumnCount() {
			
			return noteColumns.size();
		}

		@Override
		public int getRowCount() {
			if(notes == null){
				return 0;
			}
			return notes.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			if(columnIndex == noteColumns.size()-1){
				double nt = Double.parseDouble(notes.get(rowIndex)[columnIndex]);
				if(nt >= 10){
					return "V";
				}else{
					
					if(noteColumns.size() == 6 && notes.get(rowIndex)[4].equals("Ordinaire"))
						if(nt != -1)
							return "OR";
						else
							return "ABS";
					else
						return "NV";
				}
			}
			switch(columnIndex)
			{
			case 0:
				return notes.get(rowIndex)[0];
			case 1:
				return notes.get(rowIndex)[1];
			case 2:
				return notes.get(rowIndex)[2];
			case 3:
				return notes.get(rowIndex)[3];
			case 4:
				return notes.get(rowIndex)[4];
			case 5:
				return notes.get(rowIndex)[5];
			default:
				return null;
			}
			
		}
		public String getColumnName(int  column)
		{
			return noteColumns.get(column);
		}
		
		public void delRow(int row) {
			notes.remove(row);       
	        this.fireTableDataChanged();
	         
		}

	}