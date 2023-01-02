package posApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import posApp.PosMain;
import posApp.model.OrderListTableModel;

public class OrderListTablePanel extends JPanel{
	OrderListTableModel model;
	JLabel la_tableNo;
	JTable table;
	JScrollPane scroll;
	JTextField t_total;
	FlowLayout flowLayout = new FlowLayout();

	int tableNo;
	
	public OrderListTablePanel(int tableNo) {
		this.tableNo = tableNo;
		setLayout(flowLayout);
		flowLayout.setVgap(30);
		la_tableNo = new JLabel("테이블 번호 : " + (tableNo+1));
		table = new JTable(model = new OrderListTableModel());
		scroll = new JScrollPane(table);
		t_total = new JTextField("총액", 30);
		
		la_tableNo.setFont(new Font("GULIM", Font.CENTER_BASELINE, 20));
		table.setPreferredSize(new Dimension(500,600));
		table.setRowHeight(30);
		t_total.setPreferredSize(new Dimension(500,40));
		t_total.setEditable(false);
		t_total.setBackground(Color.white);
		
		add(la_tableNo);
		add(scroll);
		add(t_total);
		
		setSize(500,680);
	}
}
