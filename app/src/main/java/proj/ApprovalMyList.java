package proj;

import java.util.Vector;

public class ApprovalMyList extends BoxList<ApprovalListItem> {
    DBHandler db;
    public ApprovalMyList() {
        super();
        db = new DBHandler();
        db.executeQuery("select EMPNO, ENAME, to_char(O_START, 'YYYY-MM-DD'), to_char(O_END, 'YYYY-MM-DD') from OFF natural join EMP where ACCEPTED='N' and EMPNO=any(select EMPNO from EMP where DEPTNO=(select DEPTNO from DEPT where DNAME='" + Prop.dname + "'))");
        for (Vector<String> row : db.getData()) {
            ApprovalListItem item = new ApprovalListItem(row.get(2), row.get(3), row.get(0), row.get(1));
            add(item);
        }
    }
}
