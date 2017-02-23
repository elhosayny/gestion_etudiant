package org.estc.dao;

import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Tools {
	
	public static boolean isNumber(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			if(!Character.isDigit(str.charAt(i)))return false; 
		}
		return true;
	}
	public static void printTable(String headerMessage,String footerMessage,JTable list)
	{
		MessageFormat header = new MessageFormat(headerMessage); 
         MessageFormat footer =  new MessageFormat(footerMessage);
         JTable.PrintMode printMode = JTable.PrintMode.FIT_WIDTH;
		try {
		    boolean complete = list.print(printMode,header,footer);
		    if (complete) {
		       JOptionPane.showMessageDialog(null, "Bien imprimé");
		    } else {
		    	JOptionPane.showMessageDialog(null, "Erreur");
		    }
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(null, pe.getMessage());
		}
	}
	public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	public static boolean isDouble(double str) {
        return true;
    }
}
