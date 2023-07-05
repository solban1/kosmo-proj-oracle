package proj;

import javax.swing.JOptionPane;

public class ApprovalList extends BoxList<ApprovalListItem> {
    DBHandler db;

    public ApprovalList() {
        super();
        for (int i = 0; i < 5; i++) {
            ApprovalListItem item = new ApprovalListItem("startDate", "endDate", "empno", "name");
            add(item);
        }
        

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
    }

    
}
