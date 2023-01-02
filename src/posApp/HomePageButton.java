package posApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import util.ImageManager;

public class HomePageButton extends JButton {
	PosMain main;
	Image image;
	int index;
	public HomePageButton(PosMain main, Image image, int index) {
		this.main = main;
		this.image = image;
		this.index = index;
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index == HomePage.EXIT_BUTTON) {
					if(JOptionPane.showConfirmDialog(main, "종료하시겠습니까?") == JOptionPane.OK_OPTION) {
						main.powerOff();
					}
				} else if(index == main.HALLPAGE){
					main.showHide(index);
				} else if(index > 0 && main.isAdmin) {
					main.showHide(index);
					if (index == main.REVENUEPAGE) {
						RevenuePage page = (RevenuePage) main.pages[main.REVENUEPAGE];
						page.printDate();
					}
				} else {
					JOptionPane.showMessageDialog(main, "관리자로 로그인 하세요");
				}
			}	
		});
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(image, 0, 0, 250, 200, null);
	}
}
