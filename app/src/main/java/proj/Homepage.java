package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Homepage extends JPanel {

    JLabel jL1, jL2;
    JOptionPane jp;
    Container con;

    JButton CalendarButton = new JButton("일정");
    JButton downb4 = new JButton();
    int exitnum = 0;

    JButton logButton = new JButton("<<버튼을 눌러주세요");
    DBHandler db = new DBHandler();
    
    Homepage() {
        
        Date date = new Date();
        // System.out.println(date);
        SimpleDateFormat simpl = new SimpleDateFormat("yy.MM.dd");// 현재날짜 버튼 객체
        String s = simpl.format(date);

        
        JPanel pp3 = new JPanel();
        setLayout(new BorderLayout());
        pp3.setLayout(new GridLayout(2, 3));
        
        JButton todyaButton = new JButton("날씨로딩중"); // 현재 날짜
        WeatherClient.setWeatherAsync(  todyaButton,"<HTML>오늘의 날씨 <br>  <HTML>");
        JButton boardButton = new JButton("게시판");
        JButton logoutButton = new JButton(new ImageIcon("res/logout1.png"));
       
    
        pp3.add(CalendarButton);
        pp3.add(todyaButton);
        pp3.add(boardButton);
        pp3.add(logoutButton);

        JPanel pp2 = new JPanel();
        setLayout(new BorderLayout());
        pp2.setLayout(new GridLayout(1, 0));

        String htmlString = "<HTML>(" + Prop.dname + ") <br> " + Prop.ename + "님 환영합니다<HTML>";
        JButton upb = new JButton(htmlString); //사원 정보 
        // upb.setText( "<HTML>(Prop.dname) <br> Prop.ename + 님 환영합니다<HTML> "); // HTML
        // 로 가능

        upb.setHorizontalAlignment(SwingConstants.LEFT);

        pp2.add(upb);

        add(pp2);
        pp2.add(pp3);

        JPanel pp1 = new JPanel(); // 상단위

        pp1.setLayout(new BorderLayout());

        JButton upb1 = new JButton();

        add(upb1);

        JPanel btnpanel = new JPanel();
        setLayout(new BorderLayout());

        btnpanel.setLayout(new GridLayout(1, 3));
        JButton workButton = new JButton("출근");

        db.executeQuery("select EMPNO,A_START,A_END from ATTEND where EMPNO=" + Prop.empno
                + " and trunc(A_START)=trunc(SYSDATE) and A_END is null ");// 사원번호가 자신의 번호고 출근일시가 오늘이고 퇴근 일시가 null 인
                                                                           // 데이터를 찾는다
        if (db.getFirstData() != null) {
            workButton.setText("퇴근");
        }

        btnpanel.add(workButton);
        btnpanel.add(logButton);

        workButton.addActionListener(new workbtnActionListener());
        boardButton.addActionListener(new HandlerBoardbutton(this));
        CalendarButton.addActionListener(new HandlerCalButton(this));

        ImageIcon icon = new ImageIcon("res/clerk3.jpg");  //이미지 
        JPanel sP = new JPanel();
        JLabel imgLabel = new JLabel(icon);
        //JLabel centertitLabel = new JLabel(WeatherClient.setWeatherAsync);
        sP.add(imgLabel);

        pp1.add(sP, BorderLayout.CENTER); //가운데 이미지 패널 
        sP.setBackground(new Color(142, 202, 210));

 
        pp2.add(pp3, BorderLayout.NORTH);
        pp2.add(pp1, BorderLayout.SOUTH);

        pp1.add(btnpanel, BorderLayout.NORTH); // 수정

        add(pp1, BorderLayout.CENTER); 
        add(pp2, BorderLayout.NORTH);

        upb.setBorderPainted(false); // 버튼 윤곽선 지우기

        workButton.setBorderPainted(false);
        logButton.setBorderPainted(false);

        CalendarButton.setBorderPainted(false);
        todyaButton.setBorderPainted(false);
        logoutButton.setBorderPainted(false);
        boardButton.setBorderPainted(false);

        workButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        logButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        upb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        CalendarButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        todyaButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        logoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        boardButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));

        logoutButton.addActionListener(new HhandlerButton(this)); // 로그아웃 버튼

        workButton.setBackground(new Color(Prop.COLOR_MAIN));
        logButton.setBackground(new Color(Prop.COLOR_MAIN));

        upb.setBackground(new Color(002, 170, 178));
        logoutButton.setBackground(new Color(180, 239, 236));
        todyaButton.setBackground(new Color(188, 206, 178));
        CalendarButton.setBackground(new Color(207, 255, 229));
        boardButton.setBackground(new Color(170, 240, 209));

    }

    class workbtnActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) { // 출근 퇴근 버튼 액션리스너
            JButton wbtn = (JButton) e.getSource();
            wbtn.setBorderPainted(false);

            Date date1 = new Date();
            SimpleDateFormat simpl = new SimpleDateFormat("yy.MM.dd hh:mm aaa");
            String s1 = simpl.format(date1);

            if (wbtn.getText().equals("출근")) {
                System.out.println("출근 버튼 클릭");
                JOptionPane.showMessageDialog(null, "출근완료", null, JOptionPane.PLAIN_MESSAGE);
                db.executeUpdate("insert into ATTEND(EMPNO, A_START) values(" + Prop.empno + ", SYSDATE)");
                wbtn.setText("퇴근");

            } else {
                wbtn.setText("출근");
                System.out.println("퇴근버튼 클릭");
                JOptionPane.showMessageDialog(null, "퇴근 완료", null, JOptionPane.PLAIN_MESSAGE);

                // UPDATE ATTEND set A_END = (SYSDATE) where empno=1002 and
                // trunc(A_START)=trunc(SYSDATE) and (A_END is null);
                db.executeUpdate("UPDATE ATTEND set A_END = (SYSDATE) where EMPNO =" + Prop.empno
                        + " and trunc(A_START)= trunc(SYSDATE) and (A_END is null)");
            }

            logButton.setText(s1);

        }
    }

    class HhandlerButton implements ActionListener {
        Homepage hp;

        HhandlerButton(Homepage hp) {
            this.hp = hp;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            /*
             * UIManager.put("OptionPane.background", new Color(Prop.COLOR_MAIN)); //
             * UIOPTION 색상
             * UIManager.put("Panel.background", new Color(Prop.COLOR_MAIN));
             * UIManager.put("Button.background", new Color(194, 177, 227));
             */

            System.out.println("로그아웃 버튼 클릭");
            int i = JOptionPane.showConfirmDialog(null, "로그아웃하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (i == JOptionPane.OK_OPTION) {
                LogUI lu = new LogUI();
                lu.init();
                lu.setUI();
                SwingUtilities.getWindowAncestor(hp).setVisible(false);
            }
        }
    }

    class HandlerCalButton implements ActionListener {
        Homepage hp;

        HandlerCalButton(Homepage hp) {
            this.hp = hp;

        }

        @Override // 일정버튼 누르면 일정표로 가게만드는
        public void actionPerformed(ActionEvent e) {
            ((MainUI) SwingUtilities.getAncestorOfClass(MainUI.class, hp)).change(new Calendar());

        }

    }

    class HandlerBoardbutton implements ActionListener {
        Homepage hp;

        HandlerBoardbutton(Homepage hp) {
            this.hp = hp;
        }
        @Override // 게시판 으로가게 만드는 액션리스너
        public void actionPerformed(ActionEvent e) {
            ((MainUI) SwingUtilities.getAncestorOfClass(MainUI.class, hp)).change(new Board());
        }
    }

    public static void main(String[] args) {
        Homepage h = new Homepage();

    }

}
