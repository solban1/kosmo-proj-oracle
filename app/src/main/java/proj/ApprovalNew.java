package proj;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

import com.github.lgooddatepicker.components.DatePicker;

public class ApprovalNew extends JPanel {
	private DatePicker startDateField;
	private DatePicker endDateField;
	private JTextField writerField;
	private JTextField approvalField;
	private ActionListener boxListener;
	private ActionListener submitListener;
	private ActionListener cancelListener;
	private JPanel panel;

	private DBHandler db;

	private void initListeners() {
		boxListener = e -> {
			if (((JComboBox<String>)e.getSource()).getSelectedItem().equals("휴가")) {
				panel.setVisible(true);
			} else {
				panel.setVisible(false);
			}
		};

		submitListener = e -> {
			LocalDate startDate = startDateField.getDate();
			LocalDate endDate = endDateField.getDate();
			if (startDate == null) {
				JOptionPane.showMessageDialog(this, "시작일을 입력해 주세요.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (endDate == null) {
				JOptionPane.showMessageDialog(this, "종료일을 입력해 주세요.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (startDate.isBefore(LocalDate.now().plusDays(1))) {
				JOptionPane.showMessageDialog(this, "시작일은 내일 날짜부터 지정할 수 있습니다.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int days = startDate.until(endDate).getDays() + 1;
			if (days <= 0) {
				JOptionPane.showMessageDialog(this, "종료일은 시작일보다 앞설 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			db.executeQuery("select DAYOFF from EMP where EMPNO=" + Prop.empno);
			int remainingDays = Integer.parseInt(db.getFirstData());
			if (days > remainingDays) {
				JOptionPane.showMessageDialog(this, """
					남은 휴가일수가 부족합니다.
					남은 휴가일수: %d일""".formatted(remainingDays), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int response = JOptionPane.showConfirmDialog(this, """
				휴가를 신청하시겠습니까?
				시작일: %s
				종료일: %s
				휴가기간: %d일
				남은 휴가일수: %d일""".formatted(startDate, endDate, days, remainingDays - days), "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (response == JOptionPane.YES_OPTION) {
				int rows = db.executeUpdate("insert into OFF (EMPNO, O_START, O_END) values (%s, to_date('%s', 'YYYY-MM-DD'), to_date('%s', 'YYYY-MM-DD'))".formatted(Prop.empno, startDate.toString(), endDate.toString()));
				if (rows != 1) {
					JOptionPane.showMessageDialog(this, "신청이 정상적으로 완료되지 않았습니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(this, "신청이 완료되었습니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
			}
		};

		cancelListener = e -> {
			((MainUI) SwingUtilities.getAncestorOfClass(MainUI.class, this)).change(new ApprovalSubmenu());
		};
	}
	
	/**
	 * Create the panel.
	 */
	public ApprovalNew() {
		initListeners();
		
		Font lblFont = new Font("맑은 고딕", Font.PLAIN, 12);
		setLayout(new BorderLayout(0, 0));
		
		JComboBox<String> box = new JComboBox<>();
		box.setFont(lblFont);
		box.setModel(new DefaultComboBoxModel<String>(new String[] {"선택하세요", "휴가"}));
		box.setSelectedIndex(0);
		box.addActionListener(boxListener);
		add(box, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setVisible(false);
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{40, 62, 113, 88, 0, 0};
		gbl_panel.rowHeights = new int[]{100, 21, 21, 21, 21, 23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel writerLbl = new JLabel("기안자");
		writerLbl.setFont(lblFont);
		GridBagConstraints gbc_writerLbl = new GridBagConstraints();
		gbc_writerLbl.anchor = GridBagConstraints.EAST;
		gbc_writerLbl.insets = new Insets(0, 0, 5, 5);
		gbc_writerLbl.gridx = 1;
		gbc_writerLbl.gridy = 1;
		panel.add(writerLbl, gbc_writerLbl);
		
		writerField = new JTextField();
		writerField.setEditable(false);
		GridBagConstraints gbc_writerField = new GridBagConstraints();
		gbc_writerField.anchor = GridBagConstraints.NORTH;
		gbc_writerField.fill = GridBagConstraints.HORIZONTAL;
		gbc_writerField.insets = new Insets(0, 0, 5, 0);
		gbc_writerField.gridwidth = 3;
		gbc_writerField.gridx = 2;
		gbc_writerField.gridy = 1;
		panel.add(writerField, gbc_writerField);
		writerField.setColumns(10);
		
		JLabel approvalLbl = new JLabel("결재자");
		approvalLbl.setFont(lblFont);
		GridBagConstraints gbc_approvalLbl = new GridBagConstraints();
		gbc_approvalLbl.anchor = GridBagConstraints.EAST;
		gbc_approvalLbl.insets = new Insets(0, 0, 5, 5);
		gbc_approvalLbl.gridx = 1;
		gbc_approvalLbl.gridy = 2;
		panel.add(approvalLbl, gbc_approvalLbl);
		
		approvalField = new JTextField();
		approvalField.setEditable(false);
		GridBagConstraints gbc_approvalField = new GridBagConstraints();
		gbc_approvalField.anchor = GridBagConstraints.NORTH;
		gbc_approvalField.fill = GridBagConstraints.HORIZONTAL;
		gbc_approvalField.insets = new Insets(0, 0, 5, 0);
		gbc_approvalField.gridwidth = 3;
		gbc_approvalField.gridx = 2;
		gbc_approvalField.gridy = 2;
		panel.add(approvalField, gbc_approvalField);
		approvalField.setColumns(10);
		
		JLabel startDateLbl = new JLabel("휴가시작일");
		startDateLbl.setFont(lblFont);
		GridBagConstraints gbc_startDateLbl = new GridBagConstraints();
		gbc_startDateLbl.anchor = GridBagConstraints.EAST;
		gbc_startDateLbl.insets = new Insets(0, 0, 5, 5);
		gbc_startDateLbl.gridx = 1;
		gbc_startDateLbl.gridy = 3;
		panel.add(startDateLbl, gbc_startDateLbl);
		
		startDateField = new DatePicker();
		GridBagConstraints gbc_startDateField = new GridBagConstraints();
		gbc_startDateField.anchor = GridBagConstraints.NORTH;
		gbc_startDateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_startDateField.insets = new Insets(0, 0, 5, 5);
		gbc_startDateField.gridwidth = 3;
		gbc_startDateField.gridx = 2;
		gbc_startDateField.gridy = 3;
		panel.add(startDateField, gbc_startDateField);
		
		JLabel endDateLbl = new JLabel("휴가종료일");
		endDateLbl.setFont(lblFont);
		GridBagConstraints gbc_endDateLbl = new GridBagConstraints();
		gbc_endDateLbl.anchor = GridBagConstraints.EAST;
		gbc_endDateLbl.insets = new Insets(0, 0, 5, 5);
		gbc_endDateLbl.gridx = 1;
		gbc_endDateLbl.gridy = 4;
		panel.add(endDateLbl, gbc_endDateLbl);

		endDateField = new DatePicker();
		GridBagConstraints gbc_endDateField = new GridBagConstraints();
		gbc_endDateField.anchor = GridBagConstraints.NORTH;
		gbc_endDateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_endDateField.insets = new Insets(0, 0, 5, 5);
		gbc_endDateField.gridwidth = 3;
		gbc_endDateField.gridx = 2;
		gbc_endDateField.gridy = 4;
		panel.add(endDateField, gbc_endDateField);
		
		JButton submitButton = new JButton("결재요청");
		submitButton.setFont(lblFont);
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.anchor = GridBagConstraints.NORTH;
		gbc_submitButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_submitButton.insets = new Insets(0, 0, 0, 5);
		gbc_submitButton.gridx = 2;
		gbc_submitButton.gridy = 5;
		submitButton.addActionListener(submitListener);
		panel.add(submitButton, gbc_submitButton);
		
		JButton cancelButton = new JButton("취소");
		cancelButton.setFont(lblFont);
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridwidth = 2;
		gbc_cancelButton.anchor = GridBagConstraints.NORTH;
		gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = 5;
		cancelButton.addActionListener(cancelListener);
		panel.add(cancelButton, gbc_cancelButton);

		writerField.setText(Prop.ename);
		
		db = new DBHandler();
		db.executeQuery("select EMPNO, ENAME from EMP where EMPNO=(select DHEAD from DEPT where DEPTNO=(select DEPTNO from EMP where EMPNO=" + Prop.empno + "))");
		Vector<String> mgr = db.getFirstRow();
		if (mgr == null) {
			approvalField.setText(Prop.ename);
		} else {
			approvalField.setText(mgr.get(1));
		}
	}

}
