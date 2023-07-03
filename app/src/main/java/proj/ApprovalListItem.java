package proj;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApprovalListItem extends JPanel {
    private JPanel leftPanel;
    private JPanel contentPanel;
    private String text = "";

    public ApprovalListItem(String text) {
        super(new BorderLayout());
        this.text = text;
        
        initUI();

    }

    private void initUI() {
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel(new ImageIcon("res/beach.png")));
        add(leftPanel, BorderLayout.WEST);
        
        contentPanel = new JPanel(new GridLayout(2, 1));
        contentPanel.add(new JLabel(text));
        add(contentPanel);
    }
}
