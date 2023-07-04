package proj;

import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AttendanceRecord extends JPanel {
    JTable RecordTable;
    JLabel JLabel;
    Container cp;
    private Connection con;
    String data[][] ={
            {"09:10","null","20h"},
            {"09:15","null","20h"},
            {"09:11","null","20h"}
            };
    String column []={"출근시간","퇴근시간","누적시간"};
       
    AttendanceRecord() {
        
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("yy.MM.dd");// 현재날짜 버튼 객체
        String s = simpl.format(date);
        
        JPanel jPanel = new JPanel();
        JLabel titleL = new JLabel("    Today");
        JLabel subtitleL = new JLabel(" <"+ s +">"); 
        JLabel centerbody = new JLabel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        
        jPanel.add(titleL);
        jPanel. add(subtitleL);
        jPanel. add(centerbody);
        add(jPanel);
        
        JPanel centerPanel = new JPanel();
        jPanel.add(centerPanel);
        
        RecordTable = new JTable( data , column );  
        RecordTable.setCellSelectionEnabled(true);
           /* String Data = null;
            int[] row = RecordTable.getSelectedRows();
            int[][][] Columns = RecordTable.getSelectedColumns();*/
        JScrollPane jScrollPane = new JScrollPane(RecordTable);
        centerPanel.add(RecordTable);
        RecordTable.setSize(30,30);



        titleL.setFont(new Font("맑은고딕", Font.BOLD, 20));
        subtitleL.setFont(new Font("맑은고딕", Font.BOLD, 20));
    }
}

