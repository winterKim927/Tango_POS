package appMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import posApp.PosMain;
import util.DBManager;
import util.ImageManager;

public class AppMain extends JFrame{
	DBManager manager = DBManager.getInstance();
	Connection con = manager.getConnection();
	JPanel p;
	JLabel la_id, la_pass;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_login;
	FlowLayout flowLayout = new FlowLayout();
	boolean isAdmin = false;
	
	public AppMain() {
		setTitle("탱고치킨 POS");
		setLayout(flowLayout);
		p = new JPanel();
		
		la_id = new JLabel("아이디");
		la_pass = new JLabel("비밀번호");
		
		t_id = new JTextField();
		t_pass = new JPasswordField();
		bt_login = new JButton("로그인");
		
		flowLayout.setVgap(15);
		p.setLayout(flowLayout);
		p.setPreferredSize(new Dimension(300, 150));
		Dimension d = new Dimension(120, 25);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pass.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		
		p.setBackground(new Color(255,0,0,0));
		bt_login.setBackground(Color.LIGHT_GRAY);
		
		p.add(la_id);
		p.add(t_id);
		p.add(la_pass);
		p.add(t_pass);
		p.add(bt_login);
		
		add(p);
		
		getContentPane().setBackground(Color.white);
		setSize(350,200);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				manager.release(con);
				System.exit(0);
			}
		});
		
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkLoginInfo();
			}
		});
		
		t_pass.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkLoginInfo();
				}
			}
		});
	}
	
	public String getPassString() {
		char[] str = t_pass.getPassword();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			sb.append(str[i]+"");
		}
		String pass = sb.toString();
		return pass;
	}
	
	public void checkLoginInfo() {
		if(loginCheck() > 0 && t_id.getText().equals("tango")) {
			new PosMain(isAdmin = true);
			AppMain.this.setVisible(false);
		} else if (loginCheck() > 0) {
			new PosMain(isAdmin);
			AppMain.this.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(AppMain.this, "로그인 정보가 틀렸습니다");
		}
	}
	
	public int loginCheck() {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "select * from pos_user where id = ? and pass = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, getPassString());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt);
		}
		return result;
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}
