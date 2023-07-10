package proj;

import java.util.Vector;

public class ApprovalMyList extends BoxList<ApprovalListItem> {
    DBHandler db;
    public ApprovalMyList() {
        super();
        db = new DBHandler();
        db.executeQuery("select ENAME from EMP where EMPNO=(select DHEAD from DEPT where DNAME= '" + Prop.dname + "')");
        String mgr = db.getFirstData();
        db.executeQuery("select EMPNO, ENAME, to_char(O_START, 'YYYY-MM-DD'), to_char(O_END, 'YYYY-MM-DD'), ACCEPTED from OFF natural join EMP where EMPNO=" + Prop.empno + " order by O_START desc");
        for (Vector<String> row : db.getData()) {
            ApprovalListItem item = new ApprovalListItem(row.get(2), row.get(3), row.get(0), row.get(1), row.get(4), mgr);
            add(item);
        }
    }
}