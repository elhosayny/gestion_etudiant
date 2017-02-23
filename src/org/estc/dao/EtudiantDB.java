package org.estc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import org.estc.metier.Etudiant;

public class EtudiantDB {
	
	public static ArrayList<String> getColumns() throws Exception
	{
		ArrayList<String> columns = new ArrayList<String>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM etudiant");
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i=1;i<=rsmd.getColumnCount();i++){
			columns.add(rsmd.getColumnName(i));
		}
		rs.close();
		return columns;
	}
	public static ArrayList<Etudiant> getAll()throws Exception
	{
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM etudiant");
		while(rs.next())
		{
			Etudiant e1 = new Etudiant();
			e1.setCne(rs.getInt(1));
			e1.setCin(rs.getString(2));
			e1.setNom(rs.getString(3));
			e1.setPrenom(rs.getString(4));
			e1.setDiplome(rs.getString(5));
			etudiants.add(e1);
		}
		rs.close();
		return etudiants;
	}
	public static DefaultComboBoxModel<Etudiant> getAllModel() throws Exception
	{
		DefaultComboBoxModel<Etudiant> etudiants = new DefaultComboBoxModel<Etudiant>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM etudiant");
		while(rs.next())
		{
			Etudiant e1 = new Etudiant();
			e1.setCne(rs.getInt(1));
			e1.setCin(rs.getString(2));
			e1.setNom(rs.getString(3));
			e1.setPrenom(rs.getString(4));
			e1.setDiplome(rs.getString(5));
			etudiants.addElement(e1);
		}
		rs.close();
		return etudiants;
	}
	public static Etudiant get(int cne) throws Exception
	{
		Etudiant etudiant = new Etudiant();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM etudiant WHERE cne=?");
		prepared.setInt(1, cne);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			etudiant.setCne(rs.getInt(1));
			etudiant.setCin(rs.getString(2));
			etudiant.setNom(rs.getString(3));
			etudiant.setPrenom(rs.getString(4));
			etudiant.setDiplome(rs.getString(5));
			rs.close();
			return etudiant;
		}
		else
		{
			rs.close();
			return null;
		}
		
	}
	public static void add(Etudiant etudiant)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("INSERT INTO etudiant VALUES(?,?,?,?,?)");
		prepared.setInt(1, etudiant.getCne());
		prepared.setString(2, etudiant.getCin());
		prepared.setString(3, etudiant.getNom());
		prepared.setString(4, etudiant.getPrenom());
		prepared.setString(5, etudiant.getDiplome());
		prepared.executeUpdate();
		
	}
	public static void update(Etudiant etudiant)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("UPDATE etudiant SET cin=?,nom_etudiant=?,prenom_etudiant=?,diplome_origine=? WHERE cne=?");
		prepared.setInt(5, etudiant.getCne());
		prepared.setString(1, etudiant.getCin());
		prepared.setString(2, etudiant.getNom());
		prepared.setString(3, etudiant.getPrenom());
		prepared.setString(4, etudiant.getDiplome());
		prepared.executeUpdate();
	}
	public static void delete(int cne)throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("DELETE FROM etudiant WHERE cne=?");
		prepared.setInt(1, cne);
		prepared.executeUpdate();
	}
	
	public static int etudiantCount() throws Exception
	{
		ArrayList<Etudiant> etudiant;
		etudiant = getAll();
		return etudiant.size();
	}

}
