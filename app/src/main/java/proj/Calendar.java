package proj;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Calendar extends JPanel {
    String weekList[] = {"일","월","화","수","목","금","토"};
    JButton lastweekButton, nextweekButton;
    JPanel upPanel;
    JTable calendarTable;
    Calendar(){
        setLayout(new BorderLayout());

        lastweekButton = new JButton("<-");
        nextweekButton = new JButton("->");
        for(int i=0; i<weekList.length;i++){
            
        }
        upPanel.setLayout(new BorderLayout());
        upPanel.add(lastweekButton, BorderLayout.WEST);
        upPanel.add(nextweekButton, BorderLayout.EAST);

        add(BorderLayout.NORTH);
    
    }
}
