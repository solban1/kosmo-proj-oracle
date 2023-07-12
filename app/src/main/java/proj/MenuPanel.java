package proj;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
            /*int response = JOptionPane.showConfirmDialog(getParent(), "로그아웃하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                LogUI lu = new LogUI();
                lu.init();
                lu.setUI();
                SwingUtilities.getWindowAncestor(this).setVisible(false);
            }*/
            dh = new DBHandler();
            String newPwd;
            String pwd = JOptionPane.showInputDialog(null, "현재 패스워드 입력", null);
            if (pwd != null && pwd.equals(Prop.pwd)){
                newPwd = JOptionPane.showInputDialog(null, "변경할 패스워드를 입력해주세요", null);
                if(newPwd!=null&&newPwd.length() != 0){
                    dh.executeUpdate("update EMP set PWD='"+newPwd+"' where EMPNO="+Prop.empno);
                    Prop.pwd = newPwd; 
                }
            }else if(pwd != null){
                JOptionPane.showMessageDialog(null, "비밀번호를 확인해 주세요", "Message", JOptionPane.ERROR_MESSAGE);
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
