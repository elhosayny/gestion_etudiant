package org.estc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import org.estc.metier.Filiere;



public class FiliereDB {
	
	public static ArrayList<String> getColumns() throws Exception
	{
		ArrayList<String> columns = new ArrayList<String>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM filiere");
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i=1;i<=rsmd.getColumnCount();i++){
			columns.add(rsmd.getColumnName(i));
		}
		rs.close();
		return columns;
	}
	public static ArrayList<Filiere> getAll()throws Exception
	{
		ArrayList<Filiere> filieres = new ArrayList<>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM filiere");
		while(rs.next())
		{
			Filiere f = new Filiere();
			f.setCodeFiliere(rs.getInt(1));
			f.setLibelleFiliere(rs.getString(2));
			f.setDepartement(rs.getString(3));
			filieres.add(f);
		}
		rs.close();
		return filieres;
	}
	public static Filiere get(int code) throws Exception
	{
		Filiere filiere	 = new Filiere();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM filiere WHERE code_filiere=?");
		prepared.setInt(1, code);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			filiere.setCodeFiliere(rs.getInt(1));
			filiere.setLibelleFiliere(rs.getString(2));
			filiere.setDepartement(rs.getString(3));
			rs.close();
			return filiere;
		}
		else
		{
			rs.close();
			return null;
		}
		
	}
	
	public static Filiere get(String lebelle) throws Exception
	{
		Filiere filiere	 = new Filiere();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM filiere WHERE libelle_filiere=?");
		prepared.setString(1, lebelle);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			filiere.setCodeFiliere(rs.getInt(1));
			filiere.setLibelleFiliere(rs.getString(2));
			filiere.setDepartement(rs.getString(3));
			rs.close();
			return filiere;
		}
		else
		{
			rs.close();
			return null;
		}
		
	}
	
	public static DefaultComboBoxModel<Filiere> getAllModel()throws Exception
	{
		DefaultComboBoxModel<Filiere> filiereNames = new DefaultComboBoxModel<Filiere>();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT code_filiere,libelle_filiere FROM filiere");
		ResultSet rs = prepared.executeQuery();
		while(rs.next())
		{
			Filiere f = new Filiere();
			f.setCodeFiliere(rs.getInt(1));
			f.setLibelleFiliere(rs.getString(2));
			filiereNames.addElement(f);
		}
		rs.close();
		return filiereNames;
		
	}
	public static void add(Filiere filiere)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("INSERT INTO filiere VALUES(?,?,?)");
		prepared.setInt(1, filiere.getCodeFiliere());
		prepared.setString(2, filiere.getLibelleFiliere());
		prepared.setString(3, filiere.getDepartement());
		prepared.executeUpdate();
		
	}
	public static void update(Filiere filiere)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("UPDATE filiere SET libelle_filiere=?,departement=?  WHERE code_filiere=?");
		prepared.setInt(3, filiere.getCodeFiliere());
		prepared.setString(1, filiere.getLibelleFiliere());
		prepared.setString(2, filiere.getDepartement());
		prepared.executeUpdate();
	}
	public static void delete(int code)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("DELETE FROM filiere WHERE code_filiere=?");
		prepared.setInt(1, code);
		prepared.executeUpdate();
	}	
	
	public static  int filiereCount() throws Exception
	{
		ArrayList<Filiere> filiere = getAll();
		return filiere.size();
	}

}
