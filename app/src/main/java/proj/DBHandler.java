package proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBHandler {
    private Connection con;
    private Statement stmt;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:JAVA";
    private static final String USERNAME = "hello";
    private static final String PASSWORD = "ware";
    private String tname;
    private ResultSet currentRs;

    public DBHandler(String tname) {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = con.createStatement();
            this.tname = tname;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public DBHandler() {
        this("DEPT");
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        currentRs = stmt.executeQuery(sql);
        return currentRs;
    }

    public int executeUpdate(String sql) throws SQLException {
        return stmt.executeUpdate(sql);
    }

    public boolean execute(String sql) throws SQLException {
        return stmt.execute(sql);
    }

    public void executeSelect() {
        try {
            executeQuery("select * from " + tname);
        } catch (SQLException e) {
            System.err.println("executeSelect() SQLException: " + e);
        }
    }
    public Vector<String> getColumnData() {
        Vector<String> columnData;
        try {
            if (currentRs == null || currentRs.isClosed()) {
                System.err.println("getColumnData() 실패");
                return null;
            }

            ResultSetMetaData rsm = currentRs.getMetaData();
            final int cols = rsm.getColumnCount();

            columnData = new Vector<>();
            for (int i = 1; i <= cols; i++) {
                columnData.add(rsm.getColumnLabel(i));
            }
        } catch (SQLException e) {
            System.err.println("getColumnData() SQLException: " + e.getMessage());
            return null;
        }

        return columnData;
    }

    public Vector<Vector<String>> getData() {
        Vector<Vector<String>> data;
        try {
            if (currentRs == null || currentRs.isClosed()) {
                System.err.println("getData() 실패");
                return null;
            }

            final int cols = currentRs.getMetaData().getColumnCount();

            data = new Vector<>();
            while (currentRs.next()) {
                Vector<String> row = new Vector<>();
                for (int i = 1; i <= cols; i++) {
                    row.add(currentRs.getString(i));
                }
                data.add(row);
            }
            
        } catch (SQLException e) {
            System.err.println("getColumnData() SQLException: " + e.getMessage());
            return null;
        }

        return data;
    }

    public boolean checkPassword(String id, String pw) {
        try {
            ResultSet rs = stmt.executeQuery("select PWD from EMP where EMPNO=" + id);
            if (!rs.next()) {
                return false;
            }
            return pw == rs.getString(1);
        } catch (SQLException e) {
            System.err.println("checkPassword() SQLException: " + e.getMessage());
            return false;
        }
    }
}
