package org.estc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import org.estc.metier.Element;

public class ElementDB {
	
	public static ArrayList<String> getColumns() throws Exception
	{
		ArrayList<String> columns = new ArrayList<String>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM element");
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i=1;i<=rsmd.getColumnCount();i++){
			columns.add(rsmd.getColumnName(i));
		}
		rs.close();
		return columns;
	}
	public static ArrayList<Element> getAll()throws Exception
	{
		ArrayList<Element> elements = new ArrayList<Element>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM element");
		while(rs.next())
		{
			Element e1 = new Element();
			e1.setCodeElement(rs.getInt(1));
			e1.setCodeModule(rs.getInt(2));
			e1.setCoefficient(rs.getInt(3));
			e1.setLibelleElement(rs.getString(4));
			elements.add(e1);
		}
		rs.close();
		return elements;
	}
	public static int elementCount() throws Exception
	{
		ArrayList<Element> element = getAll();
		return element.size();
	}
	public static DefaultComboBoxModel<Element> getAllModel()throws Exception
	{
		DefaultComboBoxModel<Element> elements = new DefaultComboBoxModel<>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM element");
		while(rs.next())
		{
			Element e1 = new Element();
			e1.setCodeElement(rs.getInt(1));
			e1.setCodeModule(rs.getInt(2));
			e1.setCoefficient(rs.getInt(3));
			e1.setLibelleElement(rs.getString(4));
			elements.addElement(e1);
		}
		rs.close();
		return elements;
	}
	public static DefaultComboBoxModel<Element> getAllFilter(int codeModule)throws Exception
	{
		DefaultComboBoxModel<Element> elements = new DefaultComboBoxModel<>();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared;

		if(codeModule != -1){
			prepared = cnx.prepareStatement("SELECT * FROM element WHERE code_module=?");
			prepared.setInt(1, codeModule);
			elements.addElement(new Element(-1, -1, -1, "Tous"));
		}else{
			prepared = cnx.prepareStatement("SELECT * FROM element");
			elements.addElement(new Element(-1, -1, -1, "Tous"));
		}
		ResultSet rs = prepared.executeQuery();
		while(rs.next())
		{
			Element e1 = new Element();
			e1.setCodeElement(rs.getInt(1));
			e1.setCodeModule(rs.getInt(2));
			e1.setCoefficient(rs.getInt(3));
			e1.setLibelleElement(rs.getString(4));
			elements.addElement(e1);
		}
		rs.close();
		return elements;
	}
	public static Element get(int code) throws Exception
	{
		Element element = new Element();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM element WHERE code_element=?");
		prepared.setInt(1, code);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			element.setCodeElement(rs.getInt(1));
			element.setCodeModule(rs.getInt(2));
			element.setCoefficient(rs.getInt(3));
			element.setLibelleElement(rs.getString(4));
			rs.close();
			return element;
		}
		else
		{
			rs.close();
			return null;
		}
		
	}
	public static Element get(String code) throws Exception
	{
		Element element = new Element();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM element WHERE libelle_element=?");
		prepared.setString(1, code);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			element.setCodeElement(rs.getInt(1));
			element.setCodeModule(rs.getInt(2));
			element.setCoefficient(rs.getInt(3));
			element.setLibelleElement(rs.getString(4));
			rs.close();
			return element;
		}
		else
		{
			rs.close();
			return null;
		}
		
	}
	public static void add(Element element)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("INSERT INTO element VALUES(?,?,?,?)");
		prepared.setInt(1, element.getCodeElement());
		prepared.setInt(2, element.getCodeModule());
		prepared.setInt(3, element.getCoefficient());
		prepared.setString(4, element.getLibelleElement());
		prepared.executeUpdate();
		
	}
	public static void update(Element element)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("UPDATE element SET code_module=?,coefficient=?,libelle_element=? WHERE code_element=?");
		prepared.setInt(4, element.getCodeElement());
		prepared.setInt(1, element.getCodeModule());
		prepared.setInt(2, element.getCoefficient());
		prepared.setString(3, element.getLibelleElement());
		prepared.executeUpdate();
	}
	public static void delete(int code)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("DELETE FROM element WHERE code_element=?");
		prepared.setInt(1, code);
		prepared.executeUpdate();
	}	
}
