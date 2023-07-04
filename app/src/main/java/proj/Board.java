//새 글 최신날짜 최상단 위치
package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class Board extends JPanel{
    JButton newBoard; //upPanel 글쓰기
    JLabel dummy, boardTitle, numLabel, titleLabel, wriLabel, dateLabel; //upPanel title과 현재 날짜 출력하기위한 Label
    JPanel up, boardAll, boardIndex; //boardIndex t[] 의 글번호 제목 작성자 등록일 등 표시하기 위한 Panel
    JPanel[] boardPanels; //main 위치 글번호 + 제목 작성자 등록일
    JScrollPane js; // tableModel.addRow(new Object[]{"SCROLLBAR_AS_NEEDED"});
    String formattedTime;

    JLabel boardBody;
    Vector<Vector<String>> bBody;
    Board(){
        newBoard = new JButton("글쓰기",new ImageIcon("res/compose.png"));
        newBoard.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        newBoard.setBackground(Color.WHITE);
        newBoard.setBorder(new BevelBorder(BevelBorder.RAISED));
        newBoard.setBorderPainted(false);
        newBoard.setFocusPainted(false);
        boardTitle = new JLabel("게시판", SwingConstants.CENTER);
        boardTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        dummy = new JLabel("  ");

        up = new JPanel(new GridLayout(1,5));
        up.setBackground(Color.WHITE);
        up.setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
        up.add(dummy);
        up.add(boardTitle);
        up.add(newBoard);
        
        GridBagLayout gbLayout = new GridBagLayout();
        boardIndex = new JPanel();
        boardIndex.setLayout(gbLayout);
        numLabel = new JLabel("번호", SwingConstants.CENTER);
        numLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        titleLabel = new JLabel("내용", SwingConstants.CENTER);
        titleLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        wriLabel = new JLabel("작성자", SwingConstants.CENTER);
        wriLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
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
        boardIndex.add(titleLabel, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        boardIndex.add(wriLabel, constraints);
        constraints.gridx = 3;
        constraints.gridy = 0;
        boardIndex.add(dateLabel, constraints);
        boardIndex.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        boardIndex.setBackground(Color.WHITE);
        
        boardAll = new JPanel(new BorderLayout());
        boardAll.add(boardIndex, BorderLayout.NORTH);

        validate();
        mainPanel();

        setLayout(new BorderLayout());
        add(up, BorderLayout.NORTH);
        add(boardAll);
    }
    void mainPanel(){
        bBody = new Vector<>();
        /*Vector<String> row1 = new Vector<>();
        row1.add("1");
        row1.add("2");
        row1.add("3");
        row1.add("4");
        
        Vector<String> row2 = new Vector<>();
        row2.add("5");
        row2.add("6");
        row2.add("7");
        row2.add("8");

        bBody.add(row1);
        bBody.add(row2);*/

        DBHandler dh = new DBHandler();
        dh.executeSelect("BOARD order by B_NO desc");
        bBody = dh.getData();

        for(int i=1; i<=bBody.size(); i++){
            JLabel numLbl = new JLabel(bBody.get(i-1).get(0), SwingConstants.CENTER);
            JLabel bodyLbl = new JLabel(bBody.get(i-1).get(2), SwingConstants.CENTER);
            JLabel wrtLbl = new JLabel(bBody.get(i-1).get(1), SwingConstants.CENTER);
            JLabel dateLbl = new JLabel(bBody.get(i-1).get(3), SwingConstants.CENTER);
            numLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            bodyLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            wrtLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            dateLbl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL; 
            constraints.weightx = 1;

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
