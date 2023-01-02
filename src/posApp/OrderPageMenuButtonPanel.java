package posApp;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OrderPageMenuButtonPanel extends JPanel{
	String[] btName = {"hamburger","hall","revenue","stock","exit"};
	OrderPageMenuButton[] btList = new OrderPageMenuButton[5];
	PosMain main;
	
	public OrderPageMenuButtonPanel(PosMain main) {
		this.main = main;
		createButtons();
		setBounds(880, 200, 100, 500);
		setBackground(Color.white);
	}
	
	public void createButtons() {
		for (int i = 0; i < btList.length; i++) {
			btList[i] = new OrderPageMenuButton(btName[i]);
			add(btList[i]);
		}
	}
}
