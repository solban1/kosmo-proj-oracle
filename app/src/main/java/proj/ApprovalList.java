package proj;

public class ApprovalList extends BoxList<ApprovalListItem> {    
    public ApprovalList() {
        super();
        for (int i = 0; i < 50; i++) {
            ApprovalListItem item = new ApprovalListItem(String.valueOf(i));
            add(item);
        }
        

    }

    
}
