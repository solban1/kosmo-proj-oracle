package proj;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MenuPanel extends JPanel {
    private JButton attendLogButton;
    private JButton calButton;
    private JButton approvalButton;
    private JButton boardButton;
    private JButton logoutButton;

    private ActionListener attendLogListener;
    private ActionListener calListener;
    private ActionListener approvalListener;
    private ActionListener boardListener;
    private ActionListener logoutListener;

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
            ((MainUI)SwingUtilities.getAncestorOfClass(JFrame.class, this)).change(new Calendar());
        };
        approvalListener = e -> {

            getParent().validate();
            getParent().remove(this);
        };
        boardListener = e -> {
            getParent().validate();
            getParent().remove(this);
        };
        logoutListener = e -> {
            int response = JOptionPane.showConfirmDialog(getParent(), "로그아웃하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                LogUI lu = new LogUI();
                lu.init();
                lu.setUI();
                SwingUtilities.getWindowAncestor(this).setVisible(false);
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

        logoutButton = new JButton("로그아웃", new ImageIcon("res/logout.png"));
        logoutButton.addActionListener(logoutListener);

        List.of(attendLogButton, calButton, approvalButton, boardButton, logoutButton).forEach(btn -> {
            btn.setBackground(new Color(Prop.COLOR_MAIN).brighter());
            btn.setOpaque(true);
            btn.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
            add(btn);
        });
    }
}
