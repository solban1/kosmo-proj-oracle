//Kjoonyoung 변경 0629 12:57

// 수정 -> pull -> commit -> push _ctrl + shift + p
package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogUI extends JFrame {
    Container cp;
    JPanel upPanel, downPanel, centerPanel;
    JTextField idText;
    JPasswordField pwdField;
    JLabel northL1, laImg1;
    ImageIcon image;
    JButton logInButton, logOutButton;
    JCheckBox lookpwdBox;
    LogUI(){
        
    }
    void init(){
        upPanel = new JPanel();
        northL1 = new JLabel("로그인 화면");
        northL1.setFont(new Font("맑은고딕", Font.BOLD, 18)); //글씨체 변경 맑은고딕,굵게, 18
        northL1.setForeground(Color.WHITE);
        upPanel.setBackground(Color.DARK_GRAY); //JPanel 색상
        upPanel.add(northL1);
        
        downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1,2));
        logInButton = new JButton("로그인");
        logOutButton = new JButton("회원가입");
        //image = new ImageIcon(); //아이콘 삽입
        //laImg1 = new JLabel(image);
        //downPanel.add(laImg1);
        downPanel.add(logInButton);
        downPanel.add(logOutButton);

        idText = new JTextField(21);
        pwdField = new JPasswordField(21);
        lookpwdBox = new JCheckBox("look pwd");
        centerPanel = new JPanel();
        centerPanel.add(idText);
        centerPanel.add(pwdField);
        centerPanel.add(lookpwdBox);

        cp = getContentPane();
        cp.add(upPanel, BorderLayout.NORTH);
        cp.add(downPanel, BorderLayout.SOUTH);
        cp.add(centerPanel);

        lookpwdBox.addActionListener(new lookpwdHandler(this));
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

class lookpwdHandler implements ActionListener{ //check되었을때 비밀번호 * 표시
    LogUI lu;
    public lookpwdHandler(LogUI lu) {
        this.lu = lu;
    }
    @Override
    public void actionPerformed(ActionEvent a){
        if(lu.lookpwdBox.isSelected()){
            lu.pwdField.setEchoChar((char)0);
            lu.pwdField.setText(new String(lu.pwdField.getPassword()));
        }else{
            lu.pwdField.setEchoChar('*');
        }
    }
}