package proj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

public class MenuPanel extends JPanel {
    private JButton attendLogButton;
    private JButton calButton;
    private JButton approvalButton;
    private JButton boardButton;
    private JButton pwdChangeButton;

    private ActionListener attendLogListener;
    private ActionListener calListener;
    private ActionListener approvalListener;
    private ActionListener boardListener;
    private ActionListener pwdChangeListener;

    public MenuPanel() {
        super(new GridLayout(5, 1));
        initListeners();
        initUI();

    }

    private void initListeners() {
        attendLogListener = e -> {
            System.out.println(getParent());
            getParent().validate();
            getParent().remove(this);
        };
        calListener = e -> {
            ((MainUI)SwingUtilities.getAncestorOfClass(MainUI.class, this)).change(new Calendar());
        };
        approvalListener = e -> {
            ((MainUI)SwingUtilities.getAncestorOfClass(MainUI.class, this)).change(new ApprovalSubmenu());
        };
        boardListener = e -> {
            ((MainUI)SwingUtilities.getAncestorOfClass(MainUI.class, this)).change(new Board());
        };
        attendLogListener = e -> {
            ((MainUI)SwingUtilities.getAncestorOfClass(MainUI.class, this)).change(new AttendanceRecord());
        };
        pwdChangeListener = e -> {
            DBHandler dh;
            JPanel panel;
            JLabel pwd, newPwd, newNewPwd;
            JPasswordField pwdField, newPwdField, newNewPwdField;
            dh = new DBHandler();
            /*String newPwd;
            String pwd = JOptionPane.showInputDialog(null, "현재 패스워드 입력", null);
            if (pwd != null && pwd.equals(Prop.pwd)){
                newPwd = JOptionPane.showInputDialog(null, "변경할 패스워드를 입력해주세요", null);
                if(newPwd!=null&&newPwd.length() != 0){
                    dh.executeUpdate("update EMP set PWD='"+newPwd+"' where EMPNO="+Prop.empno);
                    Prop.pwd = newPwd; 
                }
            }else if(pwd != null){
                JOptionPane.showMessageDialog(null, "비밀번호를 확인해 주세요", "Message", JOptionPane.ERROR_MESSAGE);
            }*/

            GridBagLayout gBagLayout = new GridBagLayout();
            panel = new JPanel();
            panel.setLayout(gBagLayout);
            pwd = new JLabel("현재 비밀번호: ", SwingConstants.CENTER);
            pwdField = new JPasswordField(23);
            newPwd = new JLabel("변경할 비밀번호: ", SwingConstants.CENTER);
            newPwdField = new JPasswordField(23);
            newNewPwd = new JLabel("비밀번호 확인: ", SwingConstants.CENTER);
            newNewPwdField = new JPasswordField(23);
            pwdField.setEchoChar('●');
            newPwdField.setEchoChar('●');
            newNewPwdField.setEchoChar('●');
            pwdField.setPreferredSize(new Dimension(5, 30));
            newPwdField.setPreferredSize(new Dimension(5, 30));
            newNewPwdField.setPreferredSize(new Dimension(5, 30));


            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL; 
            constraints.weightx = 1;
            constraints.weighty = 2;
            gBagLayout.columnWidths = new int[]{40, 80};

            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(pwd, constraints);
            constraints.gridx = 1;
            constraints.gridy = 0;
            panel.add(pwdField, constraints);
            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(newPwd, constraints);
            constraints.gridx = 1;
            constraints.gridy = 1;
            panel.add(newPwdField, constraints);
            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(newNewPwd, constraints);
            constraints.gridx = 1;
            constraints.gridy = 2;
            panel.add(newNewPwdField, constraints);

            String[] options = {"OK", "Cancel"};
            int option = JOptionPane.showOptionDialog(null, panel, "The title", 
                         JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                         null, options, options[0]);

            if(option==JOptionPane.YES_OPTION){
                char[] password = pwdField.getPassword();
                char[] newPassword = newPwdField.getPassword();
                char[] newNewPassword = newNewPwdField.getPassword();
                String passwordS = new String(password);
                String newPasswordS = new String(newPassword);
                String newNewPasswordS = new String(newNewPassword);
                if(pwdField != null && newPwdField!=null &&passwordS.equals(Prop.pwd)){
                    System.out.println(newPasswordS);
                    if(newPasswordS.equals(newNewPasswordS)){
                        dh.executeUpdate("update EMP set PWD='"+newPasswordS+"' where EMPNO="+Prop.empno);
                        Prop.pwd = newPasswordS;
                        JOptionPane.showMessageDialog(null, "비밀번호가 변경 되었습니다.", "Message", JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "새 비밀번호가 일치하지 않습니다.", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "비밀번호를 확인해 주세요", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }

        };
    }

    private void initUI() {
        attendLogButton = new JButton("근태기록", new ImageIcon("res/bag.png"));
        
        attendLogButton.addActionListener(attendLogListener);

        calButton = new JButton("일정관리", new ImageIcon("res/calendar.png"));
        calButton.addActionListener(calListener);

        approvalButton = new JButton("결재관리", new ImageIcon("res/clipboard.png"));
        approvalButton.addActionListener(approvalListener);

        boardButton = new JButton("게시판", new ImageIcon("res/list.png"));
        boardButton.addActionListener(boardListener);

        pwdChangeButton = new JButton("비밀번호", new ImageIcon("res/password1.png"));
        pwdChangeButton.addActionListener(pwdChangeListener);

        List.of(attendLogButton, calButton, approvalButton, boardButton, pwdChangeButton).forEach(btn -> {
            btn.setBackground(new Color(Prop.COLOR_MAIN).brighter());
            btn.setOpaque(true);
            btn.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
            add(btn);
        });
    }
}