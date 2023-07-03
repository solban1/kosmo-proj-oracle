package proj;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Calendar extends JFrame {
    String weekList[] = {"일","월","화","수","목","금","토"};
    JButton lastweekButton, nextweekButton;
    JTable calendarTable;
    JPanel calPanel;
    Container cp;
    Calendar(){
        setLayout(new BorderLayout());

        lastweekButton = new JButton("<-");
        nextweekButton = new JButton("->");
        for(int i=0; i<weekList.length;i++){
            
        }
        calPanel.add(lastweekButton);
        calPanel.add(nextweekButton);
        
        ((JFrame) cp).getContentPane();
        cp.add(calPanel);
        setUI();
    }
    void setUI(){
        setTitle("LogIn");
        setSize(Prop.WIN_WIDTH, Prop.WIN_HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        new Calendar();
    }
}
