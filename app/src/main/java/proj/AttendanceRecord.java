package proj;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class AttendanceRecord extends JPanel {
    JTable RecordTable;
    JLabel JLabel;
    Container cp;

    AttendanceRecord() {

        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("yy.MM.dd");// 현재날짜 버튼 객체
        String s = simpl.format(date);

        JPanel jPanel = new JPanel();
        JLabel titleL = new JLabel("                           Today");
        JLabel subtitleL = new JLabel("                        <" + s + ">");
        
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jPanel.add(titleL);
        jPanel.add(subtitleL);
        
        setLayout(new BorderLayout());
        
        add(jPanel,BorderLayout.NORTH);
        // 테이블 추가
    
        DBHandler db = new DBHandler();
        db.executeSelectAttend(Prop.ename);
        Vector<String> columnData = db.getColumnData();
        Vector<String> columnName = new Vector<String>();
        columnName.add("출근시간");
        columnName.add("퇴근시간");
        columnName.add("업무시간");
        Vector<Vector<String>> data = db.getData();
        for (Vector<String> row : data) {
            if(row.get(1) == null) {
                continue;
            }
            DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
            long minutes = ChronoUnit.MINUTES.between(LocalDateTime.parse(row.get(0), f), LocalDateTime.parse(row.get(1), f));
            row.add(minutes / 60 + "시간 " + minutes % 60 + "분");
            row.add(row.get(1));
        }
        //System.out.println(columnData);
        RecordTable = new JTable(data,columnName);
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(RecordTable);
        add(sp);
        
        titleL.setFont(new Font("맑은고딕", Font.BOLD, 20));
        subtitleL.setFont(new Font("맑은고딕", Font.BOLD, 20));

    }

}
