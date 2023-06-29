package proj;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class InfoPanel extends JPanel{
    JComboBox<Object> dNameBox;
    JPanel infoJ;
    JTable infoTable;
    JScrollPane infoScroll;

    JButton j1;

    Vector<Object> dNames; //콤보박스를 눌러 부서명을 고를수있게
    Vector<Vector<Object>> rowData; //해당 데이터의 내용을 출력 (ex: 이름 전화번호, 이메일)
    InfoPanel(){
        dNameBox = new JComboBox<>(dNames);
        dNameBox.setBackground(new Color(Prop.COLOR_MAIN));
        dNameBox.setForeground(Color.WHITE);
        infoTable = new JTable(rowData,dNames);
        infoScroll = new JScrollPane(infoTable);

        j1 = new JButton("실험용 버튼");

        infoJ = new JPanel();
        infoJ.setBorder(new EmptyBorder(15, 5, 15, 5));
        infoJ.add(infoScroll);
        infoJ.add(j1);
    }
    public static void main(String[] args){
        new InfoPanel();
    }
}
