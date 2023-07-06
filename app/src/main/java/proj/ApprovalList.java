package proj;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ApprovalList extends BoxList<ApprovalListItem> {
    DBHandler db;

    public ApprovalList() {
        super();
        db = new DBHandler();
        db.executeQuery("select EMPNO, ENAME, to_char(O_START, 'YYYY-MM-DD'), to_char(O_END, 'YYYY-MM-DD') from OFF natural join EMP where ACCEPTED='N' and EMPNO=any(select EMPNO from EMP where DEPTNO=(select DEPTNO from DEPT where DNAME='" + Prop.dname + "'))");
        for (Vector<String> row : db.getData()) {
            ApprovalListItem item = new ApprovalListItem(row.get(2), row.get(3), row.get(0), row.get(1));
            add(item);
        }
    }

    public Component add(ApprovalListItem comp) {
        comp.setParentList(this);
        return add((Component)comp);
    }

    void approve(ApprovalListItem item) {
        if (db == null) {
            db = new DBHandler();
        }

        int rows = db.executeUpdate("update OFF set ACCEPTED='Y' where EMPNO=" + item.getEmpno() + " and O_START=to_date('%s', 'YYYY-MM-DD')".formatted(item.getStartDate()));
        if (rows != 1) {
            JOptionPane.showMessageDialog(this, "승인에 실패하였습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        rows = db.executeUpdate("update EMP set DAYOFF=DAYOFF-(to_date('%s', 'YYYY-MM-DD')-to_date('%s', 'YYYY-MM-DD')+1) where EMPNO=%s".formatted(item.getEndDate(), item.getStartDate(), item.getEmpno()));
        if (rows != 1) {
            JOptionPane.showMessageDialog(this, "승인에 실패하였습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        remove(item);
        revalidatePanel();
        repaint();
    }

    void reject(ApprovalListItem item) {
        if (db == null) {
            db = new DBHandler();
        }

        int rows = db.executeUpdate("delete from OFF where EMPNO=" + item.getEmpno() + " and O_START=to_date('%s', 'YYYY-MM-DD')".formatted(item.getStartDate()));
        if (rows != 1) {
            JOptionPane.showMessageDialog(this, "반려에 실패하였습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        remove(item);
        revalidatePanel();
        repaint();
    }

    
}
