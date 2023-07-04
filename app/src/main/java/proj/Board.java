//새 글 최신날짜 최상단 위치
package proj;

import java.awt.BorderLayout;
import java.awt.Font;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Board extends JPanel{
    JButton newBoard; //upPanel 글쓰기
    JLabel boardTitle; //upPanel title과 현재 날짜 출력하기위한 Label
    JPanel up, boardIndex; //boardIndex t[] 의 글번호 제목 작성자 등록일 등 표시하기 위한 Panel
    JPanel[] l, t; //main 위치 글번호 + 제목 작성자 등록일
    JScrollPane js; // tableModel.addRow(new Object[]{"SCROLLBAR_AS_NEEDED"});
    Board(){
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("yy.MM.dd hh:mm aaa");
        String s1 = simpl.format(date);
        newBoard = new JButton("글쓰기");
        boardTitle = new JLabel("게시판"+s1);

        up = new JPanel();
        up.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        up.add(newBoard);
        up.add(boardTitle);

        //validate();
        mainPanel();

        setLayout(new BorderLayout());
        add(up, BorderLayout.NORTH);
        add(boardIndex, BorderLayout.BEFORE_FIRST_LINE);
    }
    void mainPanel(){

    }
}
