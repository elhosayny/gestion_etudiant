package org.estc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import org.estc.metier.Module;



public class ModuleDB {
	
	
	public static ArrayList<String> getColumns() throws Exception
	{
		ArrayList<String> columns = new ArrayList<String>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM module");
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
			columns.add(rsmd.getColumnName(i));
		}
		rs.close();
		return columns;
		
	}
	public static ArrayList<Module> getAll() throws Exception
	{
		ArrayList<Module> modules = new ArrayList<Module>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM module");
		while(rs.next())
		{
			Module m = new Module();
			m.setCodeModule(rs.getInt(1));
			m.setLibelleModule(rs.getString(2));
			m.setNomResponable(rs.getString(3));
			m.setVolumeHoraire(rs.getInt(4));
			m.setCodeFiliere(rs.getInt(5));
			modules.add(m);
		}
		rs.close();
		return modules;
	}
	public static DefaultComboBoxModel<Module> getAllModel() throws Exception
	{
		DefaultComboBoxModel<Module> modules = new DefaultComboBoxModel<>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT code_module,libelle_module FROM module");
		while(rs.next())
		{
			Module m = new Module();
			m.setCodeModule(rs.getInt(1));
			m.setLibelleModule(rs.getString(2));
			modules.addElement(m);
		}
		rs.close();
		return modules;
	}
	public static DefaultComboBoxModel<Module> getAllFilter() throws Exception
	{
		DefaultComboBoxModel<Module> modules = new DefaultComboBoxModel<>();
		Connection cnx = DBUtils.getConnecttion();
		Statement state = cnx.createStatement();
		ResultSet rs = state.executeQuery("SELECT code_module,libelle_module FROM module");
		modules.addElement(new Module(-1, "Tous", "nom Resp", -1));
		while(rs.next())
		{
			Module m = new Module();
			m.setCodeModule(rs.getInt(1));
			m.setLibelleModule(rs.getString(2));
			modules.addElement(m);
		}
		rs.close();
		return modules;
	}
	public static int moduleCount() throws Exception
	{
		ArrayList<Module> module = getAll();
		return module.size();
	}
	public static Module get(int code) throws Exception
	{
		Module module = new Module();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM module WHERE code_module=?");
		prepared.setInt(1, code);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			module.setCodeModule(rs.getInt(1));
			module.setLibelleModule(rs.getString(2));
			module.setNomResponable(rs.getString(3));
			module.setVolumeHoraire(rs.getInt(4));
			module.setCodeFiliere(rs.getInt(5));
			return module;
		}
		else
		{
			return null;
		}
		
	}
	public static Module get(String code) throws Exception
	{
		Module module = new Module();
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("SELECT * FROM module WHERE libelle_module=?");
		prepared.setString(1, code);
		ResultSet rs = prepared.executeQuery();
		if(rs.next())
		{
			module.setCodeModule(rs.getInt(1));
			module.setLibelleModule(rs.getString(2));
			module.setNomResponable(rs.getString(3));
			module.setVolumeHoraire(rs.getInt(4));
			module.setCodeFiliere(rs.getInt(5));
			return module;
		}
		else
		{
			return null;
		}
		
	}
	public static void add(Module module) throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("INSERT INTO module VALUES(?,?,?,?,?)");
		prepared.setInt(1, module.getCodeModule());
		prepared.setString(2, module.getLibelleModule());
		prepared.setString(3, module.getNomResponable());
		prepared.setInt(4, module.getVolumeHoraire());
		prepared.setInt(5, module.getCodeFiliere());
		prepared.executeUpdate();
	}
	public static void update(Module module) throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("UPDATE module SET libelle_module=?,nom_responsable=?,volume_horaire=?,code_filiere=? WHERE code_module=?");
		prepared.setString(1, module.getLibelleModule());
		prepared.setString(2, module.getNomResponable());
		prepared.setInt(3, module.getVolumeHoraire());
		prepared.setInt(4, module.getCodeFiliere());
		prepared.setInt(5, module.getCodeModule());
		prepared.executeUpdate();
	}
	public static void delete(int code) throws Exception
	{
		Connection cnx = DBUtils.getConnecttion();
		PreparedStatement prepared = cnx.prepareStatement("DELETE FROM module WHERE code_module=?");
		prepared.setInt(1, code);
		prepared.executeUpdate();
	}

}
