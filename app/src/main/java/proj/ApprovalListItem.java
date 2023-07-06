package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.google.common.collect.ImmutableList;

public class ApprovalListItem extends JPanel {
    private JPanel leftPanel;
    private JPanel contentPanel;
    private JPanel rightPanel;
    private JButton approveButton;
    private JButton rejectButton;
    private ActionListener approveListener;
    private ActionListener rejectListener;
    private ApprovalList parentList;
    
    private String startDate = "";
    private String endDate = "";
    private String ename = "";
    private String empno = "";

    void setParentList(ApprovalList parentList) {
        this.parentList = parentList;
    }

    String getStartDate() {
        return startDate;
    }

    String getEndDate() {
        return endDate;
    }

    String getEmpno() {
        return empno;
    }

    public ApprovalListItem(String startDate, String endDate, String empno, String ename) {
        super(new BorderLayout());
        this.startDate = startDate;
        this.endDate = endDate;
        this.empno = empno;
        this.ename = ename;

        initListeners();
        initUI();
    }

    private void initListeners() {
        approveListener = e -> {
            int response = JOptionPane.showConfirmDialog(parentList, "승인하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                parentList.approve(this);
            }
        };
        rejectListener = e -> {
            int response = JOptionPane.showConfirmDialog(parentList, "반려하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                parentList.reject(this);
            }
        };
    }

    private void initUI() {
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel(new ImageIcon("res/beach.png")));
        add(leftPanel, BorderLayout.WEST);

        contentPanel = new JPanel(new GridLayout(2, 1));
        contentPanel.add(new JLabel("휴가신청: %s ~ %s".formatted(startDate, endDate)));
        contentPanel.add(new JLabel("기안자: " + ename));
        add(contentPanel);

        rightPanel = new JPanel(new GridLayout(1, 2));
        approveButton = new JButton(new ImageIcon("res/check.png"));
        approveButton.addActionListener(approveListener);
        rejectButton = new JButton(new ImageIcon("res/close.png"));
        rejectButton.addActionListener(rejectListener);
        ImmutableList.of(approveButton, rejectButton).forEach(btn -> {
            btn.setPreferredSize(new Dimension(50, 50));
            btn.setBackground(Color.WHITE);
            btn.setBorder(new BevelBorder(BevelBorder.RAISED));
            rightPanel.add(btn);
        });

        add(rightPanel, BorderLayout.EAST);
    }
}
