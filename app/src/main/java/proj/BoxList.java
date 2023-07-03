package proj;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class BoxList extends JScrollPane {
    JPanel boxPanel;
    public BoxList() {
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        boxPanel = new JPanel(new GridLayout(0, 1));
        setViewportView(boxPanel);
    }

    @Override
    public Component add(Component comp) {
        boxPanel.add(comp);
        return comp;
    }

    @Override
    public void remove(Component comp) {
        boxPanel.remove(comp);
    }
}
