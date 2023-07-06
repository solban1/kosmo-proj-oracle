package proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class DBHandler {
    private Connection con;
    private Statement stmt;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:JAVA";
    private static final String USERNAME = "hello";
    private static final String PASSWORD = "ware";
    private String tname;
    private ResultSet currentRs;
    public Object getData;

    public DBHandler(String tname) {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = con.createStatement();
        } catch (SQLException e) {
            System.err.println("Connection SQLException: " + e.getMessage());
        }
        this.tname = tname;
    }

    public DBHandler() {
        this("EMP");
    }

    public ResultSet executeQuery(String sql) {
        try {
            currentRs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("executeQuery() SQLException: " + e.getMessage());
            return null;
        }
        return currentRs;
    }

    public int executeUpdate(String sql) {
        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("executeUpdate() SQLException: " + e.getMessage());
            return -1;
        }
    }

    public boolean execute(String sql) {
        try {
            return stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("execute() SQLException: " + e.getMessage());
            return false;
        }
    }

    /**
     * 전체 열에 대한 select 구문을 돌림.
     * 
     * @param localTname 테이블명
     * @return {@code ResultSet}
     */
    public ResultSet executeSelect(String localTname) {
        return executeQuery("select * from " + localTname);
    }

    /**
     * 최근 select된 테이블의 전체 열에 대한 select 구문을 돌림.
     * 
     * @return ResultSet
     */
    public ResultSet executeSelect() {
        return executeSelect(tname);
    }

    /**
     * 특정 열에 대한 select 구문을 돌림.
     * 
     * @param localTname 테이블명
     * @param columns    조회할 컬럼명
     * @return ResultSet
     */
    public ResultSet executeSelectColumns(String localTname, String... columns) {
        return executeQuery("select " + String.join(", ", columns) + " from " + localTname);
    }

    public ResultSet executeSelectAttend(String ename) {
        return executeQuery(
                "select A_START, A_END from ATTEND where EMPNO=(select EMPNO from EMP where ENAME='" + ename + "') order by A_START desc");
    }

    /**
     * 최근 조회된 {@code ResultSet}에 대한 컬럼명의 {@code Vector}를 가져옴.
     * 
     * @return 컬럼명의 {@code Vector}
     */
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

    /**
     * 최근 조회된 {@code ResultSet}에 대한 레코드를 이중 {@code Vector}의 형태로 가져옴.
     * 
     * @return 레코드의 {@code Vector}
     */
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

    /**
     * 최근 조회된 {@code ResultSet}에 대한 레코드의 첫 번째 열을 {@code Vector}의 형태로 가져옴.
     * 
     * @return 첫 번째 열의 {@code Vector}
     */
    public Vector<String> getFirstRow() {
        Vector<String> row;
        try {
            if (currentRs == null || currentRs.isClosed()) {
                System.err.println("getFirstRow() 실패");
                return null;
            }
            if (!currentRs.next()) {
                return null;
            }

            final int cols = currentRs.getMetaData().getColumnCount();
            row = new Vector<>();
            for (int i = 1; i <= cols; i++) {
                row.add(currentRs.getString(i));
            }
            return row;
        } catch (SQLException e) {
            System.err.println("getFirstRow() SQLException: " + e.getMessage());
            return null;
        }
    }

    /**
     * 최근 조회된 {@code ResultSet}에 대한 레코드의 첫 번째 열, 첫 번째 컬럼의 데이터를 가져옴.
     * 
     * @return 단일 데이터
     */
    public String getFirstData() {
        try {
            if (currentRs == null || currentRs.isClosed()) {
                System.err.println("getFirstData() 실패");
                return null;
            }
            if (!currentRs.next()) {
                return null;
            }

            if (currentRs.getMetaData().getColumnCount() < 1) {
                return null;
            }
            return currentRs.getString(1);
        } catch (SQLException e) {
            System.err.println("getFirstData() SQLException: " + e.getMessage());
            return null;
        }
    }

    public String getEnameFromEmail(String email) {
        executeQuery("select ENAME from EMP where EMAIL='" + email + "'");
        return getFirstData();
    }

    public String getDnameFromEmail(String email) {
        executeQuery("select DNAME from DEPT natural join EMP where EMAIL='" + email + "'");
        return getFirstData();
    }

    public String getEmpnoFromEmail(String email) {
        executeQuery("select EMPNO from EMP where EMAIL='" + email + "'");
        return getFirstData();
    }

    /**
     * 부서명 목록을 가져옴.
     * 
     * @return 부서명의 {@code Vector}
     */
    public Vector<String> getDNames() {
        Vector<String> dNames;
        try {
            ResultSet rs = executeQuery("select DNAME from DEPT");

            dNames = new Vector<>();
            while (rs.next()) {
                dNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("getDNames() SQLException: " + e.getMessage());
            return null;
        }

        return dNames;
    }

    /**
     * 이메일과 패스워드가 일치하는지 확인함.
     * 
     * @param email 확인할 회원의 이메일
     * @param pw    확인할 회원의 패스워드
     * @return 일치하면 {@code true}, 일치하지 않거나 이메일이 없으면 {@code false}
     */
    public boolean checkPassword(String email, String pw) {
        try {
            ResultSet rs = stmt.executeQuery("select PWD from EMP where EMAIL='" + email + "'");
            if (!rs.next()) {
                return false;
            }
            String dbpw = rs.getString(1);
            return pw.equals(dbpw);
        } catch (SQLException e) {
            System.err.println("checkPassword() SQLException: " + e.getMessage());
            return false;
        }
    }

    /**
     * 특정인, 특정일의 일정을 ArrayList의 형태로 가져옴
     * 
     * @param dname
     * @param date
     * @return
     */
    public ArrayList<String> getSchedule(String dname, LocalDate date) {
        ArrayList<String> schedule;
        try {
            ResultSet rs = executeQuery(
                    "select S_BODY from SCHEDULE where trunc(S_START)=to_date('" + date + "', 'YYYY-MM-DD')");
            schedule = new ArrayList<>();
            while (rs.next()) {
                schedule.add(rs.getString(1));
            }
            return schedule;
        } catch (SQLException e) {
            System.err.println("getSchedule() SQLException: " + e.getMessage());
            return null;
        }
    }

    public void addToAttendLog(String empno, boolean isLeaving) {
        if (isLeaving) {
            int rows = executeUpdate("update ATTEND set A_END=SYSDATE where A_END is null and EMPNO=" + empno);
            if (rows != 1) {
                System.err.println("addToAttendLog() 실패: " + rows + "줄이 입력됨");
            }
        } else {
            int rows = executeUpdate("insert into ATEEND (EMPNO, A_START) values (" + empno + "), SYSDATE");
            if (rows != 1) {
                System.err.println("addToAttendLog() 실패: " + rows + "줄이 입력됨");
            }
        }
    }
}
