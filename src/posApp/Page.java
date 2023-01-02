package posApp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	PosMain main;
	public Page(PosMain main) {
		this.main = main;
		setBackground(Color.white);
		setVisible(false);
		setSize(PosMain.CONTENTSIZE);
		setLocation(0, 120);
	}
}
