package GUI;

import generator.ContextClass;
import generator.DbUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import generator_inheritance.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class generatorGUI {
    JFrame generator=new JFrame("Generator");
    JPanel cbrPanel = new JPanel();
    JPanel cbrEntryPanel = new JPanel();
    JPanel inheritancePanel = new JPanel();
    JPanel inheritanceEntryPanel = new JPanel();
    JTable resultsCbr = new JTable();
    JScrollPane resultsCbrPane;
    JTable resultsI = new JTable();
    JScrollPane resultsIPane;
    private JButton generateButtonCbr ;
    private JButton generateButtonInheritance ;
    private JTextField paramTextField;
    private JLabel paramLabel;
    private JLabel contextLabel;
    private JTextField contextTextField;
    private JLabel paramValuesLabel;
    private JTextField paramValuesField;
    private JLabel runsLabel;
    private JTextField runsTextField;
    private JTextField factsTextField;
    private JLabel regelLabel;
    private JLabel factsLabel;
    private JTextField regelTextField;
    private JLabel annotationLabelField;
    private JTextField annotationTextField;
    private JLabel runsLabelI;
    private JTextField runsTextFieldI;


    private JTabbedPane tabs = new JTabbedPane();

    public generatorGUI(){
        generator.setBounds(800,800,800,800);
        cbrPanel.setBounds(800,800,800,800);
        //cbrEntryPanel.setBounds(0,0,400,200);
        inheritancePanel.setBounds(800,800,800,800);

        generator.setTitle("CBR & Inheritance Generator");
        generator.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //generator.setLayout(new FlowLayout());
        generator.setSize(1000,1000);
        generator.setResizable(true);
        initComponents();
        cbrPanel.setLayout(new FlowLayout());
        cbrEntryPanel.setLayout(new GridLayout(4,4));
        cbrEntryPanel.add(paramLabel);
        cbrEntryPanel.add(paramTextField);
        cbrEntryPanel.add(contextLabel);
        cbrEntryPanel.add(contextTextField);
        cbrEntryPanel.add(paramValuesLabel);
        cbrEntryPanel.add(paramValuesField);
        cbrEntryPanel.add(runsLabel);
        cbrEntryPanel.add(runsTextField);
        inheritancePanel.setLayout(new FlowLayout());
        inheritanceEntryPanel.setLayout(new GridLayout(4,4));
        inheritanceEntryPanel.add(factsLabel);
        inheritanceEntryPanel.add(factsTextField);
        inheritanceEntryPanel.add(regelLabel);
        inheritanceEntryPanel.add(regelTextField);
        inheritanceEntryPanel.add(annotationLabelField);
        inheritanceEntryPanel.add(annotationTextField);
        inheritanceEntryPanel.add(runsLabelI);
        inheritanceEntryPanel.add(runsTextFieldI);
        inheritancePanel.add(inheritanceEntryPanel);
        inheritancePanel.add(resultsIPane);
        cbrPanel.add(cbrEntryPanel);
        cbrPanel.add(this.generateButtonCbr);
        cbrPanel.add(resultsCbrPane);
        inheritancePanel.add(this.generateButtonInheritance);
        generator.add(tabs);
        tabs.addTab("CBR Generator", cbrPanel);
        tabs.addTab("Inheritance Generator", inheritancePanel);

        generator.setLocationRelativeTo(null);
        generator.pack();
        generator.revalidate();
        generator.setVisible(true);
    }

    private void initComponents() {
        this.generateButtonCbr = new JButton("Daten generieren");
        this.generateButtonInheritance = new JButton("Daten für Inheritance generieren");
        this.paramTextField = new JTextField(8);
        this.contextTextField=new JTextField(8);
        this.paramValuesField= new JTextField(8);
        this.paramValuesLabel=new JLabel("Anzahl der ParameterValues:");
        this.contextLabel = new JLabel( "Anzahl der Kontexte:" ) ;
        this.paramLabel = new JLabel( "Anzahl der Parameter: " ) ;
        this.runsLabel = new JLabel("Anzahl der Runs:");
        this.runsTextField= new JTextField(8);
        this.generateButtonInheritance = new JButton("Daten für Inheritance generieren");
        this.factsTextField = new JTextField(8);
        this.regelTextField=new JTextField(8);
        this.annotationTextField= new JTextField(8);
        this.regelLabel=new JLabel("Anzahl der Rules:");
        this.runsLabelI = new JLabel( "Anzahl der Runs:" ) ;
        this.factsLabel = new JLabel( "Anzahl der Facts: " ) ;
        this.annotationLabelField = new JLabel("Anzahl der Annotations:");
        this.runsTextFieldI= new JTextField(8);
        resultsI.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resultsI = fillTableInheritance("select * from Inheritance_Performance");
        resizeColumnWidth(resultsI);
        resultsIPane = new JScrollPane(resultsI);
        resultsCbr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resultsCbr = fillTable("select * from CBR_Performance");
        resizeColumnWidth(resultsCbr);
        resultsCbrPane = new JScrollPane(resultsCbr);

        this.generateButtonCbr.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 int paramCount = Integer.parseInt(paramTextField.getText());
                 int contextCount = Integer.parseInt(contextTextField.getText());
                 int paramValuesCount = Integer.parseInt(paramValuesField.getText());
                 int businessCasesCount = ((Integer.parseInt(paramValuesField.getText()) )*(Integer.parseInt(paramValuesField.getText())));
                 int runs = Integer.parseInt(runsTextField.getText());
                 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                 String date = format.format(new Date());
                 for (int i = 0; i < runs; i++) {
                     long start = System.currentTimeMillis();
                     ContextClass cbr = new ContextClass("aimCtx");
                     cbr.generateCbrData(paramCount, contextCount, paramCount, runs);
                     long end = System.currentTimeMillis();
                     NumberFormat fm = new DecimalFormat("#0.0000");
                     String pTime = fm.format((end - start) / 1000d);
                     addData("select * from CBR_Performance","INSERT INTO CBR_Performance VALUES(NULL," + paramCount + "," + contextCount + "," + paramValuesCount +
                             "," + businessCasesCount + ",'" + date + "','" + pTime + "')");

                 }
                 refreshTable();


             }
         });

        this.generateButtonInheritance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int ruleCount = Integer.parseInt(regelTextField.getText());
                int factCount = Integer.parseInt(factsTextField.getText());
                int annotationsCount = Integer.parseInt(annotationTextField.getText());
                int runs = Integer.parseInt(runsTextFieldI.getText());

                InheritanceApp app = new InheritanceApp();
                String time = app.testInheritance(ruleCount, factCount, annotationsCount, runs);
                String[] splitted = time.split(";");
                String totalTime = splitted[0];
                String avgTime = splitted[1];


                addData("select * from Inheritance_Performance","INSERT INTO Inheritance_Performance VALUES(NULL," + ruleCount + "," + factCount + "," + annotationsCount +
                        "," + runs + ",'" + totalTime + "','" + avgTime + "', SYSDATE())");

                        refreshTableInheritance();


            }
        });

    }

    public JTable fillTable(String Query) {
        JTable table = null;
        try {
        Connection connection= DriverManager.getConnection(
                "jdbc:mysql://db4free.net:3306/projektdke","gruppe1","Dke&Inheritance");
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery(Query);

        table = new JTable(DbUtils.resultSetToTableModel(rs));

        rs.close();
        stmt.close();
        connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return table;

    }

    public JTable fillTableInheritance(String Query) {
        JTable table = null;
        try {
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/projektdke","gruppe1","Dke&Inheritance");
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(Query);

            table = new JTable(DbUtils.resultSetToTableModelInheritance(rs));

            rs.close();
            stmt.close();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return table;

    }

    public void addData(String Query, String update) {
        JTable table = null;
        try {
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/projektdke","gruppe1","Dke&Inheritance");
            Statement stmt=connection.createStatement();
            ResultSet rs2=stmt.executeQuery(Query);
            stmt.executeUpdate(update);

            //table = new JTable(DbUtils.resultSetToTableModel(rs2));
            //resultsCbr.repaint(); // Repaint all the component (all Cells).

            rs2.close();
            stmt.close();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 700)
                width=700;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    public void refreshTable() {
        Connection con = null;

        try{
            String query="select * from CBR_Performance";
                con= DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/projektdke","gruppe1","Dke&Inheritance");
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        resultsCbr.setModel(DbUtils.resultSetToTableModel(rs));
        pstmt.close();
        rs.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void refreshTableInheritance() {
        Connection con = null;

        try{
            String query="select * from Inheritance_Performance";
            con= DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/projektdke","gruppe1","Dke&Inheritance");
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            resultsI.setModel(DbUtils.resultSetToTableModelInheritance(rs));
            pstmt.close();
            rs.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}