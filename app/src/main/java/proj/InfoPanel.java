package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class InfoPanel extends JPanel{
    JComboBox<String> dNameBox;
    JPanel infoJ;
    JTable infoTable;
    JScrollPane infoScroll;

    Vector<String> dNames; //콤보박스를 눌러 부서명을 고를수있게
    Vector<Vector<String>> rowData; //해당 데이터의 내용을 출력 (ex: 이름 전화번호, 이메일)
    InfoPanel(){
        setLayout(new BorderLayout());

        DBHandler dh = new DBHandler();
        dh.executeSelectColumns("EMP","ENAME","PH","EMAIL");
        dNames = dh.getColumnData();
        rowData = dh.getData();

        dNameBox = new JComboBox<String>(dh.getDNames());
        dNameBox.setBackground(new Color(Prop.COLOR_MAIN));
        dNameBox.setForeground(Color.WHITE);
        infoTable = new JTable(rowData,dNames);
        infoTable.setSize(30,30);
        infoScroll = new JScrollPane(
            infoTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(dNameBox,BorderLayout.NORTH);
        add(infoScroll);
        //System.out.println(rowData);
    }
}
//executeSelect, getColumnData, getData 