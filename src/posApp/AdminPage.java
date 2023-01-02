package posApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

import posApp.dao.PosUserDAO;
import posApp.domain.PosUser;
import posApp.model.PosUserTableModel;
import util.DBManager;

public class AdminPage extends Page implements ActionListener{
	DBManager dbManager = DBManager.getInstance();
	
	JPanel p_west;
	JLabel la_name, la_id, la_pass;
	JTextField t_name;
	JTextField t_id;
	JTextField t_pass;  
	JButton bt_regist; 
	
	JPanel p_center;
	PosUserTableModel model; 
	JTable table;
	JScrollPane scroll;
	JButton bt_del;
	
	public PosUserDAO userDAO = new PosUserDAO();
	
	
	public AdminPage(PosMain main) {
		super(main);
		setLayout(new BorderLayout());
		
		//서쪽 영역
		p_west = new JPanel();
		la_name = new JLabel("이름");
		la_id = new JLabel("아이디");
		la_pass = new JLabel("비밀번호");
		
		t_name = new JTextField();
		t_id = new JTextField();
		t_pass = new JTextField();
		bt_regist = new JButton("등록");
		
		FlowLayout westLayout = new FlowLayout();
		westLayout.setHgap(10);
		westLayout.setVgap(20);
		Border registBorder = BorderFactory.createTitledBorder("사용자 등록");
		p_west.setLayout(westLayout);
		p_west.setBorder(registBorder);
		p_west.setBackground(Color.white);
		p_west.setPreferredSize(new Dimension(250, 500));
		
		Dimension l = new Dimension(50, 25);
		Dimension t = new Dimension(150, 25);
		la_name.setPreferredSize(l);
		la_id.setPreferredSize(l);
		la_pass.setPreferredSize(l);
		t_name.setPreferredSize(t);
		t_id.setPreferredSize(t);
		t_pass.setPreferredSize(t);
		
		p_west.add(la_name);
		p_west.add(t_name);
		p_west.add(la_id);
		p_west.add(t_id);
		p_west.add(la_pass);
		p_west.add(t_pass);
		p_west.add(bt_regist);
		
		//센터영역
		p_center = new JPanel();
		
		table = new JTable(model = new PosUserTableModel(this));
		scroll = new JScrollPane(table);
		
		bt_del = new JButton("삭제");
		
		Border listBorder = BorderFactory.createTitledBorder("사용자 목록");
		p_center.setBorder(listBorder);
		p_center.setBackground(Color.white);
		p_center.setPreferredSize(new Dimension(730,450));
		
		table.setPreferredSize(new Dimension(700, 525));
		scroll.setPreferredSize(new Dimension(700, 550));
		
		p_center.add(scroll);
		p_center.add(bt_del);
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
		refreshTable();
		
		bt_regist.addActionListener(this);
		bt_del.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_regist) {
			if(JOptionPane.showConfirmDialog(this, "등록할까요?") == JOptionPane.OK_OPTION)
			regist();
		} else if(obj == bt_del) {
			del();
		}
	}
	
	public void regist() {
		PosUser dto = new PosUser();
		dto.setName(t_name.getText());
		dto.setId(t_id.getText());
		dto.setPass(t_pass.getText());
		int result = userDAO.insert(dto);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "등록완료");
		}
		t_name.setText("");
		t_id.setText("");
		t_pass.setText("");
		refreshTable();
	}
	
	public void del() {
		int row = table.getSelectedRow();
		if(row == 0) {
			JOptionPane.showMessageDialog(this, "Tango 계정은 삭제할 수 없습니다\n[관리자 삭제 불가]");
		} else if(JOptionPane.showConfirmDialog(this, "삭제할까요?") == JOptionPane.OK_OPTION){
			int idx = model.list.get(row).getPos_user_idx();
			int result = userDAO.delete(idx);
			if(result > 0) {
				model.list.remove(row);
				table.updateUI();
				JOptionPane.showMessageDialog(this, "삭제 완료");
			}
		}
	}
	
	public void refreshTable() {
		model.list = (ArrayList)userDAO.selectAll();
		table.updateUI();
	}
}
