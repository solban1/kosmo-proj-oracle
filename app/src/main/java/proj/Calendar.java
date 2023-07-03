package proj;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calendar extends JPanel {
    JButton lastweekButton, nextweekButton;
    JPanel upPanel,weekPanel, mainPanel;
    JLabel[] l;

    Calendar(){
        lastweekButton = new JButton("<-");
        nextweekButton = new JButton("->");
        upPanel = new JPanel();
        mainPanel = new JPanel();
        weekPanel = new JPanel();
        l = new JLabel[6];
        weekPanel.setLayout(new BoxLayout(weekPanel, BoxLayout.Y_AXIS));
        weekPanel.setBackground(new Color(Prop.COLOR_MAIN));
        weekPanel.setForeground(Color.WHITE);

        for(int i=0; i<6;i++){
            l[i] = new JLabel();
            weekPanel.add(l[i]);
        }
        System.out.println(l);
        upPanel.setLayout(new BorderLayout());
        upPanel.add(lastweekButton, BorderLayout.WEST);
        upPanel.add(nextweekButton, BorderLayout.EAST);

        add(upPanel, BorderLayout.NORTH);
        add(weekPanel, BorderLayout.WEST);
        add(mainPanel);
    }
}
