package proj;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

public class BoxList<T extends Component> extends JScrollPane {
    private JPanel boxPanel;
    private Vector<T> listItem;
    
    public BoxList() {
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        boxPanel = new JPanel(null);
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        //boxPanel = new JPanel(new GridLayout(0, 1));
        setViewportView(boxPanel);
        listItem = new Vector<>();
    }

    @Override
    public Component add(Component comp) {
        listItem.add((T)comp);
        ((JComponent)comp).setAlignmentY(Component.TOP_ALIGNMENT);
        ((JComponent)comp).setBorder(new BevelBorder(BevelBorder.RAISED));
        comp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
        boxPanel.add(comp);
        return comp;
    }

    @Override
    public void remove(Component comp) {
        boxPanel.remove(comp);
    }
}
