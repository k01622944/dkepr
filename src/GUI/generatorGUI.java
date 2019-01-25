package GUI;

import generator.ContextClass;
import generator.DbUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class generatorGUI {
    JFrame generator=new JFrame("Generator");
    JPanel cbrPanel = new JPanel();
    JPanel cbrEntryPanel = new JPanel();
    JPanel inheritancePanel = new JPanel();
    JTable resultsCbr = new JTable();
    JScrollPane resultsCbrPane;
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
        resultsCbr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resultsCbr = fillTable("select * from CBR_Performance");
        resizeColumnWidth(resultsCbr);
        resultsCbrPane = new JScrollPane(resultsCbr);

        this.generateButtonCbr.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int params = Integer.parseInt(paramTextField.getText());
                 int contexts = Integer.parseInt(contextTextField.getText());
                 int paramValues = Integer.parseInt(paramValuesField.getText());
                 int runs = Integer.parseInt(runsTextField.getText());
                 ContextClass cbr = new ContextClass("aimCtx");
                 for(int i = 1; i<runs;i++) {
                     cbr.generateCbrData(params, contexts, paramValues, runs);
                 }
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

}