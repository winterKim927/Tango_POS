package posApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;

import util.ImageManager;

public class HomeButton extends JButton{
	Image image;
	public HomeButton(Image image) {
		this.image = image;
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.clearRect(0, 0, 80, 80);
		g2.drawImage(image, 0, 0, 80, 80, null);
	}
}
