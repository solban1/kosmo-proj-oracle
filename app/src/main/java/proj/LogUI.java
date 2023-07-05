//Kjoonyoung 변경 0629 12:57
//Login창 구현 0629 14:13
//MainTitle 크기 변경 0629 14:48ㄴ
//joinButton //삭제 23-06-30 굳이 회원가입 버튼이 필요x -> 회사에서 이메일 + 사원번호로 id pwd 부여 로그인 후 비밀번호 변경
//pwdField Enter 입력시 로그인 시도
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class LogUI extends JFrame {
    private Container cp;
    private JPanel upPanel, downPanel, centerPanel;
    JTextField idField;
    JPasswordField pwdField;
    private JLabel northL1, laImg1;
    private ImageIcon image;
    JButton loginButton;
    JCheckBox lookpwdBox;
    DBHandler dh;
    LogUI(){

    }
    void init(){
        upPanel = new JPanel();
        northL1 = new JLabel("Welcome");
        northL1.setFont(new Font("맑은고딕", Font.BOLD, 24)); //글씨체 변경 맑은고딕,굵게, 18
        northL1.setForeground(Color.WHITE);
        upPanel.setBackground(new Color(Prop.COLOR_MAIN)); //JPanel 색상
        upPanel.add(northL1);

        idField = new JTextField(23);
        pwdField = new JPasswordField(23);
        pwdField.setEchoChar('●');
        lookpwdBox = new JCheckBox("체크박스 클릭시 비밀번호 보임");
        lookpwdBox.setBackground(Color.WHITE);
        image = new ImageIcon("res/logo1.png"); //아이콘 삽입
        laImg1 = new JLabel(image);
        laImg1.setBorder(new EmptyBorder(0, 0, 30, 0));   
        
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(60,0,60,0)); //패널 위 아래 공백
        centerPanel.add(laImg1);
        centerPanel.add(idField);
        centerPanel.add(pwdField);
        centerPanel.add(lookpwdBox);

        loginButton = new JButton("LogIn");
        loginButton.setOpaque(true);
        loginButton.setBackground(new Color(Prop.COLOR_MAIN));
        loginButton.setBorder(new EtchedBorder(30));
        loginButton.setFont(new Font("null",Font.PLAIN,15));
        loginButton.setForeground(Color.WHITE);

        downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1,2));
        downPanel.setBorder(new EmptyBorder(5, 30, 5, 30));
        downPanel.setBackground(Color.WHITE);
        downPanel.add(loginButton);

        cp = getContentPane();
        cp.add(upPanel, BorderLayout.NORTH);
        cp.add(downPanel, BorderLayout.SOUTH);
        cp.add(centerPanel);   

        lookpwdBox.addActionListener(new LookpwdListener(this));
        loginButton.addActionListener(new LoginListener(this));
        pwdField.addActionListener(new LoginListener(this));
    }
    void setUI(){
        setTitle("LogIn");
        setSize(Prop.WIN_WIDTH, Prop.WIN_HEIGHT);
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

class LookpwdListener implements ActionListener{ //check되었을때 비밀번호 * 표시
    LogUI lu;
    public LookpwdListener(LogUI lu) {
        this.lu = lu;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(lu.lookpwdBox.isSelected()){
            lu.pwdField.setEchoChar((char)0);
            lu.pwdField.setText(new String(lu.pwdField.getPassword()));
        }else{
            lu.pwdField.setEchoChar('●');
        }
    }
}

class LoginListener implements ActionListener {
    LogUI lu;
    LoginListener(LogUI lu) {
        this.lu = lu;
    }
    public void actionPerformed(ActionEvent e) {
        String inputId = lu.idField.getText();
        String inputPwd = new String(lu.pwdField.getPassword());
        if(lu.dh==null){
            lu.dh = new DBHandler();
            lu.dh.checkPassword(inputId, inputPwd);
        }
        if(lu.dh.checkPassword(inputId, inputPwd)){
            Prop.email = inputId;
            Prop.ename = lu.dh.getEnameFromEmail(inputId);
            
            new MainUI();
            lu.setVisible(false);
        }else{
           JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 확인해주세요", "try again", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(inputId);
        System.out.println(inputPwd);
    }
}