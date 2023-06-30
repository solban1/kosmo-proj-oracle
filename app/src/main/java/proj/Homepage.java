package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JButton[] btn = new JButton[5];
    JButton bN1 = new JButton("출근하기");
    JButton bN2 = new JButton("퇴근하기");
    JButton bN3 = new JButton("정보");
    JButton bS1 = new JButton("등록하기");
    JButton bS2 = new JButton("수정하기");
    JButton bS3 = new JButton("지우기");
    JButton downb4 = new JButton("로그아웃");
    int exitnum = 0;

    Homepage() {

        JPanel pp3 = new JPanel();
        setLayout(new BorderLayout());
        pp3.setLayout(new GridLayout(2, 3));

        JButton downb1 = new JButton("일정");
        JButton downb2 = new JButton("현재 날짜");
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

        JPanel Np = new JPanel(); // 버튼 출퇴근 버튼

        setLayout(new BorderLayout());

        Np.setLayout(new GridLayout(1, 2));
        Np.add(bN1);
        Np.add(bN2);

        JPanel sP = new JPanel();
        sP.setLayout(new GridLayout(1, 3)); // 수정 지우기 삭제 버튼
        sP.add(bS1);
        sP.add(bS2);
        sP.add(bS3);

        pp2.add(pp3, BorderLayout.NORTH);
        pp2.add(pp1, BorderLayout.SOUTH);

        pp1.add(Np, BorderLayout.NORTH);
        pp1.add(sP, BorderLayout.SOUTH);

        add(pp1, BorderLayout.CENTER);
        add(pp2, BorderLayout.NORTH);

   
        upb.setBorderPainted(false);     //버튼 윤곽선 지우기
        downb1.setBorderPainted(false);
        downb2.setBorderPainted(false);
        downb3.setBorderPainted(false);
        downb4.setBorderPainted(false);

        bN1.setFont(new Font("맑은고딕", Font.BOLD, 24)); // 글꼴
        bN2.setFont(new Font("맑은고딕", Font.BOLD, 24));
       

        bN1.addActionListener(new Hhandler(this)); // 출근
        bN2.addActionListener(new Hhandler(this)); // 퇴근
        bS1.addActionListener(new Hhandler(this)); // 수정
        bS2.addActionListener(new Hhandler(this)); // 수정하기
        bS3.addActionListener(new Hhandler(this)); // 지우기

        downb4.addActionListener(new HhandlerButton(this)); // 로그아웃 버튼
      

        bN1.setBackground(new Color(Prop.COLOR_MAIN));
        bN2.setBackground(new Color(Prop.COLOR_MAIN));
        bS1.setBackground(new Color(Prop.COLOR_MAIN));
        bS2.setBackground(new Color(Prop.COLOR_MAIN));
        bS3.setBackground(new Color(Prop.COLOR_MAIN));
        upb.setBackground(new Color(Prop.COLOR_MAIN));
        downb1.setBackground(new Color(Prop.COLOR_MAIN));
        downb2.setBackground(new Color(Prop.COLOR_MAIN));
        downb3.setBackground(new Color(Prop.COLOR_MAIN));
        downb4.setBackground(new Color(Prop.COLOR_MAIN));

    }

    public static void main(String[] args) {
        Homepage h = new Homepage();

    }
}

class Hhandler implements ActionListener {
    Homepage h1;

    Hhandler(Homepage h1) {
        this.h1 = h1;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageIcon icon1 = new ImageIcon("res/out1.png"); // 출근 이미지
        ImageIcon icon2 = new ImageIcon("res/go1.png"); // 퇴근이미지

        UIManager.put("OptionPane.background", new Color(177, 224, 227)); // UIOPTION 색상
        // UIManager.put("Panel.background", new Color(53, 20, 219));
        UIManager.put("Button.background", new Color(194, 177, 227));

        JButton b = (JButton) e.getSource();
        JOptionPane jp = new JOptionPane();

        if (b.equals(h1.bN1)) {

            System.out.println("출근버튼 클릭");
            /*JOptionPane.showConfirmDialog(null, "출근하시겠습니까?", "출근", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, icon1);*/
            JOptionPane.showMessageDialog(null,"출근이 완료되었습니다",null,JOptionPane.PLAIN_MESSAGE);

        } else if (b.equals(h1.bN2)) {
            System.out.println("퇴근버튼 클릭");
            JOptionPane.showConfirmDialog(null, "퇴근하시겠습니까?", "퇴근", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, icon2);
        } else if (b.equals(h1.bS1)) {
            System.out.println("등록버튼 클릭");
            JOptionPane.showConfirmDialog(null, "등록하시겠습니까?", "등록중", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null);   
        } else if (b.equals(h1.bS2)) {
            System.out.println("수정버튼 클릭");
            JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정중", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null);
        } else if (b.equals(h1.bS3)) {
            System.out.println("삭제버튼 클릭");
            JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null);
        }
    }
}

class HhandlerButton implements ActionListener {
    Homepage hp;

    HhandlerButton(Homepage hp) {
        this.hp = hp;

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
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
  
