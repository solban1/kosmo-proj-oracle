package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class Homepage extends JPanel {

    JLabel jL1, jL2;
    JOptionPane jp;
    Container con;
    JButton bN1 = new JButton("출근");
    JButton bN2 = new JButton("퇴근");
    JButton bN3 = new JButton("정보");
    JButton bS1 = new JButton("등록하기");
    JButton bS2 = new JButton("수정하기");
    JButton bS3 = new JButton("지우기");
    JButton downb4 = new JButton();
    int exitnum = 0;
 
    JButton logButton = new JButton("출근 버튼을 눌러주세요");


    Homepage() {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpl = new SimpleDateFormat("yy.MM.dd");//현재날짜 버튼 객체
        String s = simpl.format(date);
        

        JPanel pp3 = new JPanel();
        setLayout(new BorderLayout());
        pp3.setLayout(new GridLayout(2, 3));

        JButton downb1 = new JButton("일정");
        JButton downb2 = new JButton(s); //현재 날짜
        JButton downb3 = new JButton("업무");
        JButton downb4 = new JButton("로그아웃");

        pp3.add(downb1);
        pp3.add(downb2);
        pp3.add(downb3);
        pp3.add(downb4);

        JPanel pp2 = new JPanel();
        setLayout(new BorderLayout());
        pp2.setLayout(new GridLayout(1, 3));
        JButton upb = new JButton("사원 이름,부서번호");

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
     
        btnpanel.add(workButton);
        btnpanel.add(logButton);
     

        workButton.addActionListener(new workbtnActionListener());



        JPanel sP = new JPanel();
        sP.setLayout(new GridLayout(1, 3)); // 수정 지우기 삭제 버튼
        sP.add(bS1);
        sP.add(bS2);
        sP.add(bS3);

        pp2.add(pp3, BorderLayout.NORTH);
        pp2.add(pp1, BorderLayout.SOUTH);



        pp1.add(btnpanel, BorderLayout.NORTH); // 수정
      

        add(pp1, BorderLayout.CENTER);
        add(pp2, BorderLayout.NORTH);

        upb.setBorderPainted(false); // 버튼 윤곽선 지우기

        workButton.setBorderPainted(false);
        logButton.setBorderPainted(false);
      
        downb1.setBorderPainted(false);
        downb2.setBorderPainted(false);
        downb3.setBorderPainted(false);
        downb4.setBorderPainted(false);

        workButton.setFont(new Font("맑은고딕", Font.BOLD, 20));
        logButton.setFont(new Font("맑은고딕", Font.BOLD, 13));
      

        downb1.setFont(new Font("궁서체", Font.BOLD, 13));
        downb2.setFont(new Font("맑은고딕", Font.BOLD, 13));
        downb3.setFont(new Font("맑은고딕", Font.BOLD, 13));
        downb4.setFont(new Font("맑은고딕", Font.BOLD, 13));



        
 

        downb4.addActionListener(new HhandlerButton(this)); // 로그아웃 버튼
   



        workButton.setBackground(new Color(Prop.COLOR_MAIN));
        logButton.setBackground(new Color(Prop.COLOR_MAIN));
      
  
        upb.setBackground(new Color(002, 170, 178));
        downb1.setBackground(new Color(180, 239, 236));
        downb2.setBackground(new Color(188, 206, 178));
        downb3.setBackground(new Color(207, 255, 229));
        downb4.setBackground(new Color(170, 240, 209));

    }

    class workbtnActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) { // 출근 퇴근 버튼 액션리스너
            JButton wbtn = (JButton) e.getSource();
            wbtn.setBorderPainted(false);

            
            Date date1 = new Date();
            
            SimpleDateFormat simpl = new SimpleDateFormat("yy.MM.dd hh:mm aaa");
            String s1 = simpl.format(date1);
            System.out.println(date1);

            if (wbtn.getText().equals("출근"))
                wbtn.setText("퇴근");

            else
                wbtn.setText("출근");


            if (wbtn.getText() == "출근") {

                System.out.println("출근버튼 클릭");
                /*
                 * JOptionPane.showConfirmDialog(null, "출근하시겠습니까?", "출근",
                 * JOptionPane.YES_NO_OPTION,
                 * JOptionPane.INFORMATION_MESSAGE, icon1);
                 */
                JOptionPane.showMessageDialog(null, "퇴근 완료", null, JOptionPane.PLAIN_MESSAGE);

            } else {
                System.out.println("퇴근버튼 클릭");
                /*
                 * JOptionPane.showConfirmDialog(null, "퇴근하시겠습니까?", "퇴근",
                 * JOptionPane.YES_NO_OPTION,
                 * JOptionPane.INFORMATION_MESSAGE, null);
                 */
                JOptionPane.showMessageDialog(null, "출근완료", null, JOptionPane.PLAIN_MESSAGE);

            }

            logButton.setText(s1);

        }
    }

    public static void main(String[] args) {
        Homepage h = new Homepage();

    }

}


class HhandlerButton implements ActionListener {
    Homepage hp;

    HhandlerButton(Homepage hp) {
        this.hp = hp;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UIManager.put("OptionPane.background", new Color(Prop.COLOR_MAIN)); // UIOPTION 색상
        UIManager.put("Panel.background", new Color(Prop.COLOR_MAIN));
        UIManager.put("Button.background", new Color(194, 177, 227));

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
