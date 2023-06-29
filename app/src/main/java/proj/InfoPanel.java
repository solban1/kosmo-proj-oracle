package proj;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class InfoPanel extends JPanel{
    JComboBox<String> dNameBox;
    JPanel infoJ;
    JTable infoTable;
    JScrollPane infoScroll;

    Vector<String> dNames; //콤보박스를 눌러 부서명을 고를수있게
    Vector<Vector<String>> rowData; //해당 데이터의 내용을 출력 (ex: 이름 전화번호, 이메일)
    InfoPanel(){
        DBHandler dh = new DBHandler();
        dh.executeSelect();
        dNames = dh.getColumnData();
        rowData = dh.getData();

        dNameBox = new JComboBox<String>(dNames);
        dNameBox.setBackground(new Color(Prop.COLOR_MAIN));
        //dNameBox.setForeground(Color.GRAY);
        infoTable = new JTable(rowData,dNames);
        infoScroll = new JScrollPane(infoTable);

        setBorder(new EmptyBorder(15, 5, 15, 5));
        add(dNameBox);
        add(infoScroll);
    }
}
//executeSelect, getColumnData, getData 