package proj;

public class ApprovalList extends BoxList<ApprovalListItem> {    
    public ApprovalList() {
        super();
        for (int i = 0; i < 2; i++) {
            ApprovalListItem item = new ApprovalListItem();
            add(item);
        }
        

    }

    
}
