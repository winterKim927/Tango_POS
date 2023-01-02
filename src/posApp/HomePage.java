package posApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import util.ImageManager;

import javax.swing.JButton;

public class HomePage extends Page{
	HomePageButton[] buttons  = new HomePageButton[4];
	ImageManager im = new ImageManager();
	
	public static final int HALL_BUTTON = 1;
	public static final int REVENUE_BUTTON = 2;
	public static final int STOCK_BUTTON = 3;
	public static final int EXIT_BUTTON = 4;
	
	String imgName[] = {"res/images/hall.png", "res/images/revenue.png", "res/images/admin.png", "res/images/exit.png"};
	
	public HomePage(PosMain main) {
		super(main);
		setLayout(null);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new HomePageButton(main, im.getImage(imgName[i]), i+1);
			buttons[i].setPreferredSize(new Dimension(300,300));
			buttons[i].setOpaque(true);
			add(buttons[i]);
		}
		buttons[0].setBounds(180,100,250,200);
		buttons[1].setBounds(550,100,250,200);
		buttons[2].setBounds(180,350,250,200);
		buttons[3].setBounds(550,350,250,200);
	}
}
