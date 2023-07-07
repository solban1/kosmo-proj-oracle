package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class InfoPanel extends JPanel{
    JComboBox<String> dNameBox;
    JPanel infoJ;
    JTable infoTable;
    JScrollPane infoScroll;
    DBHandler dh;

    Vector<String> dNames; //콤보박스를 눌러 부서명을 고를수있게
    Vector<Vector<String>> rowData; //해당 데이터의 내용을 출력 (ex: 이름 전화번호, 이메일)
    InfoPanel(){
        setLayout(new BorderLayout());

        dh = new DBHandler();
        dh.executeSelectColumns("EMP","ENAME","PH","EMAIL");
        dNames = dh.getColumnData();
        rowData = dh.getData();
        dNameBox = new JComboBox<>();
        dNameBox.addItem("전 부서");
        dh.getDNames().forEach(str -> {
            dNameBox.addItem(str);
        });
        dNameBox.setBackground(new Color(Prop.COLOR_MAIN));
        dNameBox.setForeground(Color.WHITE);
        infoTable = new JTable(rowData,dNames);
        infoTable.setEnabled(false); //수정불가
        infoScroll = new JScrollPane(infoTable);
        infoScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		infoScroll.getVerticalScrollBar().setUnitIncrement(16);
        infoTable.setRowHeight(25);
        add(dNameBox,BorderLayout.NORTH);
        add(infoScroll);
        
        dNameBox.addActionListener(new ComboxListener(this));
    }
}
class ComboxListener implements ActionListener{
    InfoPanel ip;
    ComboxListener(InfoPanel ip){
        this.ip = ip;
    }
    public void actionPerformed(ActionEvent ae){
        JComboBox<String> dbox = (JComboBox<String>)ae.getSource();
        Object item = dbox.getSelectedItem();
        //System.out.println(item); //기술 0 인사 1
        ip.dh.executeSelectColumns("EMP natural join DEPT"+" where"+" DNAME='"+item+"'", "ENAME","PH","EMAIL");
        ip.infoTable.setModel(new DefaultTableModel(ip.dh.getData(), ip.dNames));
        if(item=="전 부서"){
            ip.dh.executeSelectColumns("EMP","ENAME","PH","EMAIL");
            ip.infoTable.setModel(new DefaultTableModel(ip.dh.getData(), ip.dNames));
        }
    }
}