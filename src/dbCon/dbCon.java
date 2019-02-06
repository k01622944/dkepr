package dbCon;

import java.sql.*;

public class dbCon {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:cbr.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:cbr.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS CBR_Performance (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	parameter integer NOT NULL,\n"
                + "	context integer NOT NULL,\n"
                + "	parameterValue integer NOT NULL,\n"
                + "	BusinessCase integer NOT NULL,\n"
                + "	ExecutionDate String NOT NULL,\n"
                + "	ExecutionTime String NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insert(int parameter, int context, int parameterValue, int businessCase, String ExecutionDate, String ExecutionTime) {
        String sql = "INSERT INTO CBR_Performance(id,parameter,context,parameterValue,businessCase,ExecutionDate,ExecutionTime)VALUES(null,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, parameter);
            pstmt.setInt(2, context);
            pstmt.setInt(3, parameterValue);
            pstmt.setInt(4, businessCase);
            pstmt.setString(5, ExecutionDate);
            pstmt.setString(6, ExecutionTime);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
