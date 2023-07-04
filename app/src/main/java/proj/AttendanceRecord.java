package proj;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;



public class AttendanceRecord extends JPanel {
    
    JLabel JLabel;
    Container con;
    
    AttendanceRecord() {
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("yy.MM.dd");// 현재날짜 버튼 객체
        String s = simpl.format(date);

       /* JPanel JPanel = new JPanel();
        JLabel upJLabel = new JLabel("                            Today"); //title 
        
       
        setLayout(new BorderLayout());
        upJLabel.setLayout(new GridLayout(1,1));
        add(upJLabel,BorderLayout.NORTH);
        
        JLabel centerupJLabel = new JLabel("                        <"+ s +">");
        add(centerupJLabel,BorderLayout.NORTH);
        
        JPanel uppPanel = new JPanel();*/
        
        
        JPanel jPanel = new JPanel();
        JLabel titleL = new JLabel("                            Today");
        JLabel subtitleL = new JLabel("                        <"+ s +">"); 
      
        setLayout(new BorderLayout());
        jPanel.setLayout(new GridLayout(2, 0));


        jPanel.add(titleL,BorderLayout.NORTH);
        jPanel.add(subtitleL,BorderLayout.SOUTH);//
       



        titleL.setFont(new Font("맑은고딕", Font.BOLD, 20));
        subtitleL.setFont(new Font("맑은고딕", Font.BOLD, 20));
    }
}

