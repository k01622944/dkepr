package generator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
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


        String file = "cbr_output.txt";

        Properties p = new Properties();
        int contexts = p.getamountc();
        int paramCount = p.getamountparam();

        ContextClass contexClass = new ContextClass(paramCount, "aimCtx");

        try{
            PrintWriter outputStream = new PrintWriter(file);
            outputStream.println(contexClass.classNameToString());
            outputStream.println(contexClass.paramsToString());
            outputStream.println(contexClass.contextsToString(contexts));
            outputStream.println(contexClass.detParamValuesToString());
            outputStream.close();
            System.out.println("done");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
