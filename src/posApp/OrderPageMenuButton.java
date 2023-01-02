package posApp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;

import util.ImageManager;

public class OrderPageMenuButton extends JButton{
	String path;
	ImageManager manager;
	Image image;
	
	public OrderPageMenuButton(String btName) {
		path = "res/images/"+btName+".png";
		manager = new ImageManager();
		image = manager.getImage(path, 100, 100);
		setPreferredSize(new Dimension(100,100));
		
	}
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.clearRect(0, 0, 100, 100);
		g2.drawImage(image, 0, 0, 100, 100, null);
	}
}
