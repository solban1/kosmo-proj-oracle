package proj;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;

public class AttendanceRecord extends JFrame {
    
    AttendanceRecord() {
        JFrame jFrame = new JFrame();
        
        JPanel uppPanel = new JPanel();
        setLayout(new BorderLayout());
        

        uppPanel.setLayout(new GridLayout(1,0));
        JButton titlebtn = new JButton("Today");
        add(titlebtn);
        uppPanel.add(titlebtn);
        uppPanel.add(titlebtn, BorderLayout.NORTH);

    }
}

