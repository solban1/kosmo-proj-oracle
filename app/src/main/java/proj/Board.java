//새 글 최신날짜 최상단 위치
package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class Board extends JPanel{
    private JButton newBoard, deleteBoard; //upPanel 글쓰기
    private JLabel boardTitle, numLabel, bodyLabel, wrtLabel, dateLabel; //upPanel title과 현재 날짜 출력하기위한 Label
    private JPanel up, boardAll, boardIndex; //boardIndex t[] 의 글번호 제목 작성자 등록일 등 표시하기 위한 Panel
    private JScrollPane js; // tableModel.addRow(new Object[]{"SCROLLBAR_AS_NEEDED"});

    JLabel numLbl, bodyLbl, wrtLbl, dateLbl;

    Vector<Vector<String>> bBodyList;
    Board(){
        newBoard = new JButton("글쓰기",new ImageIcon("res/compose.png"));
        newBoard.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        newBoard.setBackground(Color.WHITE);
        newBoard.setBorder(new BevelBorder(BevelBorder.RAISED));
        newBoard.setBorderPainted(false);
        newBoard.setFocusPainted(false);
        deleteBoard = new JButton("삭제",new ImageIcon("res/delete.png"));
        deleteBoard.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        deleteBoard.setBackground(Color.WHITE);
        deleteBoard.setBorder(new BevelBorder(BevelBorder.RAISED));
        deleteBoard.setBorderPainted(false);
        deleteBoard.setFocusPainted(false);
        boardTitle = new JLabel("게시판", SwingConstants.CENTER);
        boardTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        up = new JPanel(new GridLayout(1,5));
        up.setBackground(Color.WHITE);
        up.setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
        up.add(deleteBoard);
        up.add(boardTitle);
        up.add(newBoard);
        newBoard.addActionListener(new NewBoardListener(this));
        deleteBoard.addActionListener(new DeleteBoardListener(this));
        
        GridBagLayout gbLayout = new GridBagLayout();
        boardIndex = new JPanel();
        boardIndex.setLayout(gbLayout);
        numLabel = new JLabel("번호", SwingConstants.CENTER);
        numLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        bodyLabel = new JLabel("내용", SwingConstants.CENTER);
        bodyLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        wrtLabel = new JLabel("작성자", SwingConstants.CENTER);
        wrtLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        dateLabel = new JLabel("날짜", SwingConstants.CENTER);
        dateLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; 
        constraints.weightx = 1;
        gbLayout.columnWidths = new int[]{40, 140, 50, 100};

        constraints.gridx = 0;
        constraints.gridy = 0;
        boardIndex.add(numLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        boardIndex.add(bodyLabel, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        boardIndex.add(wrtLabel, constraints);
        constraints.gridx = 3;
        constraints.gridy = 0;
        boardIndex.add(dateLabel, constraints);
        boardIndex.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        boardIndex.setBackground(Color.WHITE);
        
        boardAll = new JPanel(new BorderLayout());
        boardAll.add(boardIndex, BorderLayout.NORTH);
        js = new JScrollPane(boardAll) ;
        js.getVerticalScrollBar().setUnitIncrement(15);

        validate();
        mainPanel();

        setLayout(new BorderLayout());
        add(up, BorderLayout.NORTH);
        add(js);

    }
    void mainPanel(){
        bBodyList = new Vector<>();
        DBHandler dh = new DBHandler();
        dh.executeSelectColumns("BOARD natural join EMP order by B_NO desc", "B_NO", "B_BODY", "ENAME", "WDATE");
        bBodyList = dh.getData();

        for(int i=1; i<=bBodyList.size(); i++){
            numLbl = new JLabel(bBodyList.get(i-1).get(0), SwingConstants.CENTER);
            bodyLbl = new JLabel(bBodyList.get(i-1).get(1), SwingConstants.CENTER);
            wrtLbl = new JLabel(bBodyList.get(i-1).get(2), SwingConstants.CENTER);
            dateLbl = new JLabel(bBodyList.get(i-1).get(3), SwingConstants.CENTER);
            numLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            bodyLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            wrtLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            dateLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL; 
            constraints.weightx = 1;
            constraints.ipady = 5;

            constraints.gridx = 0;
            constraints.gridy = i;
            boardIndex.add(numLbl, constraints);
            constraints.gridx = 1;
            constraints.gridy = i;
            boardIndex.add(bodyLbl, constraints);
            constraints.gridx = 2;
            constraints.gridy = i;
            boardIndex.add(wrtLbl, constraints);
            constraints.gridx = 3;
            constraints.gridy = i;
            boardIndex.add(dateLbl, constraints);
            boardIndex.setFont(new Font("맑은 고딕", Font.BOLD, 15));
            boardIndex.setBackground(Color.WHITE);

        }
    }
}
class NewBoardListener implements ActionListener{
    Board bod;
    DBHandler dh;
    NewBoardListener(Board bod){
        this.bod = bod;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        bod.mainPanel();
        dh = new DBHandler();
        UIManager.put("OptionPane.messageFont", new Font("맑은 고딕", Font.PLAIN, 15));
        String result = JOptionPane.showInputDialog(null, "글쓰기", null);
        if(result != null){
            dh.executeUpdate("insert into BOARD(EMPNO, B_BODY, WDATE) VALUES("+Prop.empno+", '"+result+"', SYSDATE)"); //VALUES('내용내용 hihi', SYSDATE);
            ((MainUI)SwingUtilities.getAncestorOfClass(MainUI.class, bod)).change(new Board());
        }
    }
}
class DeleteBoardListener implements ActionListener{
    Board bod;
    DBHandler dh;
    DeleteBoardListener(Board bod){
        this.bod = bod;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        dh = new DBHandler();
        UIManager.put("OptionPane.messageFont", new Font("맑은 고딕", Font.PLAIN, 12));
        String result = JOptionPane.showInputDialog(null, "글 번호 입력!", null);
        dh.executeSelectColumns("BOARD natural join EMP"+" where"+" B_NO='"+result+"'", "EMPNO");
        String id = dh.getFirstData();
        if(result != null && id.equals(Prop.empno)){
            dh.executeUpdate("Delete from BOARD where B_NO='"+result+"'");
            ((MainUI)SwingUtilities.getAncestorOfClass(MainUI.class, bod)).change(new Board());
        }else{
            JOptionPane.showMessageDialog(null, "자신의 글만 삭제 가능합니다", "권한없음", JOptionPane.ERROR_MESSAGE);
        }
       
    }
}