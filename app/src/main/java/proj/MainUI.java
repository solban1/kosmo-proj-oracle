package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainUI extends JFrame {
    private JPanel topPanel, bottomPanel, dummyPanel, dummyPanel2;
    private JLabel titleLbl, titleTime, dummyLbl;
    private HashMap<String, JButton> bottomBtns;
    private ActionListener attendListener, menuListener, homeListener, infoListener, logoutListener;
    private Component currentPanel;
    private String formattedTime;

    public MainUI() {
        super("HelloWare");
        initListeners();
                
        topPanel = new JPanel();
        topPanel.setBackground(new Color(Prop.COLOR_MAIN));
        bottomPanel = new JPanel(new GridLayout(1, 5));
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("aaa hh:mm:ss");
        String s1 = simpl.format(date);
        titleLbl = new JLabel("Main", SwingConstants.CENTER);
        titleLbl.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
        titleLbl.setForeground(Color.WHITE);
        titleTime = new JLabel(s1, SwingConstants.CENTER);
        titleTime.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
        titleTime.setForeground(Color.WHITE);
        dummyLbl = new JLabel();
        topPanel.setLayout(new GridLayout(1,3));
        topPanel.add(dummyLbl);
        topPanel.add(titleLbl);
        topPanel.add(titleTime);

        bottomBtns = new HashMap<>(7);
        bottomBtns.put("근태", new JButton("근태"));
        bottomBtns.get("근태").addActionListener(attendListener);
        bottomBtns.put("메뉴", new JButton("메뉴"));
        bottomBtns.get("메뉴").addActionListener(menuListener);
        bottomBtns.put("홈", new JButton("홈"));
        bottomBtns.get("홈").addActionListener(homeListener);
        bottomBtns.put("정보", new JButton("정보"));
        bottomBtns.get("정보").addActionListener(infoListener);

        Font btnFont = new Font("Malgun Gothic", Font.PLAIN, 16);
        bottomBtns.forEach((k, v) -> {
            v.setFont(btnFont);
            v.setOpaque(true);
            v.setBackground(new Color(Prop.COLOR_MAIN));
            v.setForeground(Color.WHITE);
            v.setPreferredSize(new Dimension(1, 40));
        });

        bottomPanel.add(bottomBtns.get("근태"));
        bottomPanel.add(bottomBtns.get("메뉴"));
        bottomPanel.add(bottomBtns.get("홈"));
        bottomPanel.add(bottomBtns.get("정보"));

        dummyPanel = new JPanel();
        dummyPanel.add(new JLabel("dummyPanel"));
        add(dummyPanel);
        dummyPanel2 = new JPanel();
        dummyPanel2.add(new JLabel("dummyPanel2"));

        setUI();
        timer();
    }
    public void timer(){
        //titleTime = new JLabel();
        Timer timer = new Timer();

        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                Date date1 = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("aaa hh:mm:ss");
                formattedTime = sdf.format(date1);
                titleTime.setText(formattedTime);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 50);
    }

    @Override
    public Component add(Component comp) {
        addImpl(comp, null, -1);
        currentPanel = comp;
        return comp;
        
    }

    private void initListeners() {
        attendListener = e -> {
            change(new AttendanceRecord());
        };
        menuListener = e -> {
            change(new MenuPanel());
        };
        homeListener = e -> {
            change(new Homepage());
        };
        infoListener = e -> {
            change(new InfoPanel());
        };
        logoutListener = e -> {
            LogUI lu = new LogUI();
            lu.init();
            lu.setUI();
            setVisible(false);
        };

    }

    public void change(Component comp) {
        remove(currentPanel);
        add(comp);
        validate();
    }

    private void setUI() {
        setSize(Prop.WIN_WIDTH, Prop.WIN_HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new MainUI();
    }
}
