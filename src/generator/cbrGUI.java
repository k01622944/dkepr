package generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import GUI.generatorGUI;

public class cbrGUI extends JFrame{
    private JButton generateButton;

    public cbrGUI(){
        setTitle("CBR & Inheritance Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400,400);
        setResizable(false);
        initComponents();
        add(this.generateButton);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
       /* try{
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
        String file = "cbr_output.txt";

        generatorGUI gui = new generatorGUI();

        Properties p = new Properties();
        int contexts = p.getamountc();
        int paramCount = p.getamountparam();
        int paramValues = p.getamountparamvalues();

        ContextClass contexClass = new ContextClass( "aimCtx");

        try{
            PrintWriter outputStream = new PrintWriter(file);
            outputStream.println(contexClass.generateCbrData(paramCount,contexts,paramValues));
            outputStream.close();
            System.out.println("done");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    private void initComponents() {
        this.generateButton = new JButton("Daten generieren");
        this.generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }
}
