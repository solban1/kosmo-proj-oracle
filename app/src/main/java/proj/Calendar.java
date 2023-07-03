package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Calendar extends JPanel {
    JButton lastweekButton, nextweekButton;
    JPanel upPanel, mainPanel;
    JPanel[] weekPanel;
    JLabel[] l;
    JPanel[] t;
    ArrayList<ArrayList<String>> day;
    Calendar(){
        lastweekButton = new JButton("<---"); //나중에 이미지로 대체
        nextweekButton = new JButton("--->"); //나중에 이미지로 대체
        upPanel = new JPanel();
        weekPanel = new JPanel[7];
        mainPanel = new JPanel();
        l = new JLabel[7];
        t = new JPanel[7];

        LocalDate sunday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        DBHandler db = new DBHandler();
        day = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ArrayList<String> list = db.getSchedule("인사팀", sunday.plusDays(i));
            day.add(list);
        }

        for(int i=0; i<=6; i++){
            l[i] = new JLabel();
            // t[i] = day.get(i);
            l[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
            // t[i].setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            l[i].setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
            // t[i].setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
        }
        l[0].setText("  일  ");
        l[1].setText("  월  ");
        l[2].setText("  화  ");
        l[3].setText("  수  ");
        l[4].setText("  목  ");
        l[5].setText("  금  ");
        l[6].setText("  토  ");

        upPanel.setLayout(new BorderLayout());
        upPanel.add(lastweekButton, BorderLayout.WEST);
        upPanel.add(nextweekButton, BorderLayout.EAST);
        for(int i=0; i<=6; i++){
            weekPanel[i] = new JPanel(new BorderLayout());
            weekPanel[i].add(l[i], BorderLayout.WEST);
            t[i] = new JPanel(new GridLayout(0, 1));
            final int innerI = i;
            System.out.println(day.get(i));
            day.get(i).forEach(e -> {
                JLabel lbl = new JLabel(e);
                t[innerI].add(lbl);
            });
            t[i].setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            t[i].setBorder(new LineBorder(new Color(Prop.COLOR_MAIN)));
            weekPanel[i].add(t[i], BorderLayout.CENTER);
            mainPanel.add(weekPanel[i]);
        }

        mainPanel.setLayout(new GridLayout(7,1));
        mainPanel.setBackground(new Color(Prop.COLOR_MAIN));
        mainPanel.setForeground(Color.WHITE);

        setLayout(new BorderLayout());
        add(upPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
}
