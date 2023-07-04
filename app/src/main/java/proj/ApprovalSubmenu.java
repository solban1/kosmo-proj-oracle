package proj;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ApprovalSubmenu extends JPanel {
    private JButton newButton;
    private JButton listButton;
    private ActionListener newListener;
    private ActionListener listListener;

    public ApprovalSubmenu() {
        super(new GridLayout(2, 1));
        initListeners();
        initUI();
    }

    private void initListeners() {
        newListener = e -> {
            // ((MainUI)SwingUtilities.getAncestorOfClass(MainUI.class, this)).change(new
            // ApprovalNew());
            ((MainUI) SwingUtilities.getAncestorOfClass(MainUI.class, this)).change(new ApprovalNew());

        };

        listListener = e -> {
            ((MainUI) SwingUtilities.getAncestorOfClass(MainUI.class, this)).change(new ApprovalList());
        };
    }

    private void initUI() {
        newButton = new JButton("기안", new ImageIcon("res/board_new.png"));
        newButton.addActionListener(newListener);
        listButton = new JButton("결재대기", new ImageIcon("res/board_pending.png"));
        listButton.addActionListener(listListener);
        List.of(newButton, listButton).forEach(btn -> {
            btn.setBackground(new Color(Prop.COLOR_MAIN).brighter());
            btn.setOpaque(true);
            btn.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
            add(btn);
        });
    }
}
