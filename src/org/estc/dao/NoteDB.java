package org.estc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


import org.estc.metier.Note;

public class NoteDB {
	
	public static ArrayList<String> getColumns() throws Exception
	{
		ArrayList<String> columns = new ArrayList<String>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM note");
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i=1;i<=rsmd.getColumnCount();i++){
			columns.add(rsmd.getColumnName(i));
		}
		rs.close();
		return columns;
	}
	public static ArrayList<Note> getAll()throws Exception
	{
		ArrayList<Note> notes = new ArrayList<Note>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM note");
		while(rs.next())
		{
			Note n = new Note();
			n.setCodeNote(rs.getInt(1));
			n.setCne(rs.getInt(2));
			n.setCodeElement(rs.getInt(3));
			n.setSession(rs.getString(4));
			n.setNote(rs.getDouble(5));
			notes.add(n);
		}
		return notes;
	}
	public static ArrayList<String[]> getModules(String name, String session)throws Exception
	{
		ArrayList<String[]> notes = new ArrayList<>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs;
		int max = 0;
		if(name.equals("Tous")){
			rs= state.executeQuery("CALL `psmt`();"); 
			max = 3;
		}else{
			PreparedStatement ps;
			if(session.equals("Tous")){
				ps = cnx.prepareStatement("CALL `psm`(?);");
				ps.setString(1, name);
				
			}else{
				ps = cnx.prepareStatement("CALL `psmr`(?, ?);");
				ps.setString(1, name);
				ps.setString(2, session);
				
			}
			rs = ps.executeQuery();
		}
		while(rs.next())
		{
			String[] st = new String[4];
			st[0] = rs.getString(1);
			st[1] = rs.getString(2);
			st[2] = rs.getString(3);
			if(max != 3)
			st[3] = rs.getString(4);
			
			notes.add(st);
		}
		if(!notes.isEmpty()){
			return notes;
		}
		return null;
	}
	
	public static ArrayList<String[]> getElements(String name, String session)throws Exception
	{
		ArrayList<String[]> notes = new ArrayList<>();
		Connection cnx = DBUtils.getConnecttion();
		ResultSet rs;
		PreparedStatement ps;
		if(name.equals("Tous")){
			ps = cnx.prepareStatement("CALL `pselt`(?);"); 
			if(session.equals("Tous"))
				ps.setString(1, "%");
			else
				ps.setString(1, session);
		}else{
			ps = cnx.prepareStatement("CALL `psel`(?,?);");
			ps.setString(1, name);
			if(session.equals("Tous"))
				ps.setString(2, "%");
			else
				ps.setString(2, session);
		}
		rs = ps.executeQuery();
		while(rs.next())
		{
			String[] st = new String[6];
			st[0] = rs.getString(1);
			st[1] = rs.getString(2);
			st[2] = rs.getString(3);
			st[3] = rs.getString(4);
			st[4] = rs.getString(5);
			st[5] = rs.getString(6);
			
			notes.add(st);
		}
		/*if(!notes.isEmpty()){
			return notes;
		}*/
		return notes;
	}
	public static Note get(int code) throws Exception
	{
		Note note = new Note();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM note WHERE code_note=?");
		prepared.setInt(1, code);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			note.setCodeNote(rs.getInt(1));
			note.setCne(rs.getInt(2));
			note.setCodeElement(rs.getInt(3));
			note.setNote(rs.getDouble(4));
			note.setSession(rs.getString(5));
			rs.close();
			return note;
		}
		else
		{
			rs.close();
			return null;
		}
		
	}
	public static Note get(String code) throws Exception
	{
		Note note = new Note();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM note WHERE code_note=?");
		prepared.setString(1, code);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			note.setCodeNote(rs.getInt(1));
			note.setCne(rs.getInt(2));
			note.setCodeElement(rs.getInt(3));
			note.setNote(rs.getDouble(4));
			note.setSession(rs.getString(5));
			rs.close();
			return note;
		}
		else
		{
			rs.close();
			return null;
		}
		
	}
	public static void add(Note note)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("INSERT INTO note(cne,code_element,note,session_n) VALUES(?,?,?,?)");
		prepared.setInt(1, note.getCne());
		prepared.setInt(2, note.getCodeElement());
		prepared.setDouble(3, note.getNote());
		prepared.setString(4, note.getSession());
		prepared.executeUpdate();
		
	}
	public static void update(Note note)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("UPDATE note SET cne=?,code_element=?,note=?,session_n=? WHERE code_note=?");
		prepared.setInt(5, note.getCodeNote());
		prepared.setInt(1, note.getCne());
		prepared.setInt(2, note.getCodeElement());
		prepared.setDouble(3, note.getNote());
		prepared.setString(4, note.getSession());
		prepared.executeUpdate();
	}
	public static void delete(int code)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("DELETE FROM note WHERE code_note=?");
		prepared.setInt(1, code);
		prepared.executeUpdate();
	}
}
