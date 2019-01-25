package generator;

import GUI.generatorGUI;

import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        /*
        try{
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/projektdke","gruppe1","Dke&Inheritance");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from CBR_Performance");
            System.out.println("ID \t | \t Parameter \t | \t Contexts");
            while(rs.next())
                System.out.println(rs.getInt(1)+"\t | \t "+rs.getString(2)+"\t\t\t | \t "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
*/

        generatorGUI generatorGUI = new generatorGUI();

//ausgabe
        String file = "cbr_output.txt";

        ContextClass contexClass = new ContextClass("aimCtx");

    }
}
