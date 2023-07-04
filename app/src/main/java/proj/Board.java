//새 글 최신날짜 최상단 위치
package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Board extends JPanel{
    JButton newBoard; //upPanel 글쓰기
    JLabel boardTitle, numLabel, titleLabel, wriLabel, dateLabel; //upPanel title과 현재 날짜 출력하기위한 Label
    JPanel up, boardAll, boardIndex; //boardIndex t[] 의 글번호 제목 작성자 등록일 등 표시하기 위한 Panel
    JPanel[] l, t; //main 위치 글번호 + 제목 작성자 등록일
    JScrollPane js; // tableModel.addRow(new Object[]{"SCROLLBAR_AS_NEEDED"});
    String formattedTime;
    Board(){
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("hh:mm aaa");
        String s1 = simpl.format(date);

        newBoard = new JButton("글쓰기");
        boardTitle = new JLabel("게시판         "+s1);
        newBoard.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        boardTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        newBoard.setBackground(Color.WHITE);
        newBoard.setBorder(new BevelBorder(BevelBorder.RAISED));

        up = new JPanel(new BorderLayout());
        up.setBackground(Color.WHITE);
        up.setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
        up.add(newBoard, BorderLayout.WEST);
        up.add(boardTitle, BorderLayout.EAST);
        
        GridBagLayout gbLayout = new GridBagLayout();
        boardIndex = new JPanel();
        boardIndex.setLayout(gbLayout);
        numLabel = new JLabel("글 번호");
        titleLabel = new JLabel("제목");
        wriLabel = new JLabel("작성자");
        dateLabel = new JLabel("등록일");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; 
        constraints.weightx = 0.25;
        gbLayout.columnWidths = new int[]{5, 50, 5, 15};

        constraints.gridx = 0;
        constraints.gridy = 0;
        boardIndex.add(numLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        boardIndex.add(titleLabel, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        boardIndex.add(wriLabel, constraints);
        constraints.gridx = 4;
        constraints.gridy = 0;
        boardIndex.add(dateLabel, constraints);
        boardIndex.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        boardIndex.setBackground(Color.WHITE);
        boardIndex.setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
        
        boardAll = new JPanel(new BorderLayout());
        boardAll.add(boardIndex, BorderLayout.NORTH);

        //validate();
        //mainPanel();

        setLayout(new BorderLayout());
        add(up, BorderLayout.NORTH);
        add(boardAll);
        timer();
    }
    void mainPanel(){

    }
    void timer(){
        Timer timer = new Timer();

        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                Date date1 = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aaa");
                formattedTime = sdf.format(date1);
                boardTitle.setText("게시판         "+formattedTime);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }
}
