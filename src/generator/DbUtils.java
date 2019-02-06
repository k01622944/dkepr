package generator;
import java.sql.*;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class DbUtils {
    public static TableModel resultSetToTableModel(ResultSet rs) {
        try {
            Connection connection= DriverManager.getConnection(
                    "jdbc:sqlite:cbr.db");
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();

            //columnNames.add("Id");
            columnNames.add("Parameter");
            columnNames.add("Kontexte");
            columnNames.add("Parameterwerte");
            columnNames.add("Business Cases");
            columnNames.add("Date");
            columnNames.add("Ausführungszeit");

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();

                for (int i = 2; i <= numberOfColumns; i++) {
                    newRow.addElement(rs.getObject(i));
                }

                rows.addElement(newRow);
            }
            rs.close();
            return new DefaultTableModel(rows, columnNames);

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    public static TableModel resultSetToTableModelInheritance(ResultSet rs) {
        try {
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/projektdke","gruppe1","Dke&Inheritance");
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();

            //columnNames.add("Id");
            columnNames.add("Rules");
            columnNames.add("Facts");
            columnNames.add("Annotations");
            columnNames.add("Runs");
            columnNames.add("Total time");
            columnNames.add("Average time");
            columnNames.add("Date");

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();

                for (int i = 2; i <= numberOfColumns; i++) {
                    newRow.addElement(rs.getObject(i));
                }

                rows.addElement(newRow);
            }
            rs.close();
            return new DefaultTableModel(rows, columnNames);

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}