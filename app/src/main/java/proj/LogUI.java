//Kjoonyoung 변경 0628 6:32
//Kjoonyoung 변경 0628 6:32

// 수정 -> pull -> commit -> push     (ctrl + shift + p)
package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LogUI extends JFrame {
    Container cp;
    JPanel p1, p2;
    JTextArea idT, pwdT;
    JLabel northL;
    JButton b1, b2;
    LogUI(){
        
    }
    void init(){
        b1 = new JButton("로그인");
        b2 = new JButton("회원가입");

        northL = new JLabel("로그인 화면");
        northL.setFont(new Font("맑은고딕", Font.BOLD, 18)); //글씨체 변경 맑은고딕,굵게, 18
        northL.setForeground(Color.WHITE);
        p1 = new JPanel();
        p1.setBackground(Color.DARK_GRAY); //JPanel 색상
        p1.add(northL);

        idT = new JTextArea(2, 30);
        pwdT = new JTextArea();
        p2 = new JPanel();
        p2.add(idT);
        p2.add(pwdT);

        cp = getContentPane();
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2);
    }
    void setUI(){
        setTitle("LogIn");
        setSize(350, 550);
        setVisible(true);
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        LogUI lu = new LogUI();
        lu.init();
        lu.setUI();
    }

}