package posApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import util.ImageManager;

public class InfoPanel extends JPanel{
	PosMain main;
	HomeButton bt_home;
	String currentTime;
	Thread timeThread;
	
	public InfoPanel(PosMain main) {
		this.main = main;
		setLayout(null);
		createHomeButton();
		
		timeThread = new Thread() {
			public void run() {
				while(true) {
					getCurrentDate();
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		timeThread.start();
		setSize(1000, 120);
	}
	
	public void createHomeButton() {
		ImageManager manager = new ImageManager();
		Image image = manager.getImage("res/images/logo.png", 80, 80);
		bt_home = new HomeButton(image);
		bt_home.setBounds(50, 20, 80, 80);
		add(bt_home);

		bt_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showHide(main.HOMEPAGE);
			}
		});
	}
	
	public void getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		currentTime = formatter.format(new Date());
	}
	

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(156, 197, 223));
		g2.fillRect(0, 0, 1000, 120);
		
		g2.setColor(Color.white);
		g2.setFont(new Font("MALGUN", Font.ITALIC, 40));
		g2.drawString("탱고치킨 별내점", 150, 70);
		
		g2.setColor(Color.gray);
		g2.fillRect(560, 20, 400, 80);
		
		g2.setColor(Color.white);
		g2.setFont(new Font("MALGUN", Font.PLAIN, 25));
		g2.drawString(currentTime, 600, 65);
		
		
	}
}
