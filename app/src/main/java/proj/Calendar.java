package proj;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Calendar extends JPanel {
    String weekList[] = {"일","월","화","수","목","금","토"};
    JButton lastweekButton, nextweekButton;
    JTable calendarTable;
    Calendar(){
        setLayout(new BorderLayout());

        lastweekButton = new JButton("<-");
        nextweekButton = new JButton("->");
        for(int i=0; i<weekList.length;i++){
            
        }
        calendarTable(weekList,null);
        add(lastweekButton);
        add(nextweekButton);

    }
    private void calendarTable(String[] weekList2, Object object) {
    }
}
