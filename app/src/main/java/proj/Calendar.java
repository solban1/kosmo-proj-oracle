package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Calendar extends JPanel {
    JButton lastweekButton, nextweekButton;
    JPanel upPanel, mainPanel;
    JPanel[] weekPanel;
    JLabel[] l;
    JLabel up, lbl;
    JPanel[] t;
    ArrayList<ArrayList<String>> day;
    int nextClick;
    Calendar(){
        lastweekButton = new JButton(new ImageIcon("res/arrow_back.png"));
        lastweekButton.setBackground(new Color(Prop.COLOR_MAIN));
        lastweekButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        //lastweekButton.setBorderPainted(false);
        lastweekButton.setFocusPainted(false);
        nextweekButton = new JButton(new ImageIcon("res/arrow_forward.png"));
        nextweekButton.setBackground(new Color(Prop.COLOR_MAIN));
        nextweekButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        //nextweekButton.setBorderPainted(false);
        nextweekButton.setFocusPainted(false);
        upPanel = new JPanel();
        up = new JLabel(" ", SwingConstants.CENTER);
        weekPanel = new JPanel[7];
        mainPanel = new JPanel();
        l = new JLabel[7];
        t = new JPanel[7];

        for(int i=0; i<=6; i++){
            l[i] = new JLabel();
            l[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
            l[i].setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
        }

        l[0].setText("  일  ");
        l[1].setText("  월  ");
        l[2].setText("  화  ");
        l[3].setText("  수  ");
        l[4].setText("  목  ");
        l[5].setText("  금  ");
        l[6].setText("  토  ");

        validate();
        mainPanel();

        setLayout(new BorderLayout());
        add(upPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        lastweekButton.addActionListener(new LastweekButtonListener(this));
        nextweekButton.addActionListener(new NextweekButtonListener(this));
    }
    void mainPanel(){
        LocalDate sunday = LocalDate.now().plusWeeks(nextClick).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        DBHandler db = new DBHandler();
        remove(mainPanel);
        mainPanel = new JPanel();
        
        day = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ArrayList<String> list = db.getSchedule("인사팀", sunday.plusDays(i));
            day.add(list);
        }

        for(int i=0; i<=6; i++){
            weekPanel[i] = new JPanel(new BorderLayout());
            weekPanel[i].add(l[i], BorderLayout.WEST);
            t[i] = new JPanel(new GridLayout(0, 1));
            final int innerI = i;
            day.get(i).forEach(e -> {
                lbl = new JLabel(e);
                t[innerI].add(lbl);
            });
            t[i].setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            t[i].setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
            weekPanel[i].add(t[i], BorderLayout.CENTER);
            mainPanel.add(weekPanel[i]);
        }
        up.setText(sunday+" ~ "+sunday.plusDays(6));
        upPanel.setLayout(new BorderLayout());
        upPanel.add(lastweekButton, BorderLayout.WEST);
        upPanel.add(up);
        upPanel.add(nextweekButton, BorderLayout.EAST);

        mainPanel.setLayout(new GridLayout(7,1));
        mainPanel.setBackground(new Color(Prop.COLOR_MAIN));
        mainPanel.setForeground(Color.WHITE);
        add(mainPanel);
        validate();
    }
    public static Calendar getInstance() {
        return null;
    }
    public Object getTime() {
        return null;
    }
}
class LastweekButtonListener implements ActionListener{
    Calendar cal;
    LastweekButtonListener(Calendar cal){
        this.cal = cal;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        cal.nextClick -= 1;
        cal.mainPanel();
    }
}
class NextweekButtonListener implements ActionListener{
    Calendar cal;
    NextweekButtonListener(Calendar cal){
        this.cal = cal;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        cal.nextClick += 1;
        cal.mainPanel();
    }
}
