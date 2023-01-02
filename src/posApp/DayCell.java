package posApp;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//요일을 처리하기 위한 셀 
public class DayCell extends JButton {
	public DayCell(String title) {
		Border border = new LineBorder(Color.lightGray);
		setBorder(border);
		setBackground(Color.white);
		setOpaque(true);
		setText(title);
	}

//	protected void paintComponent(Graphics g) {
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setColor(Color.GRAY);
//		g2.fillRect(0, 0, x, y);
//
//		g2.setColor(Color.WHITE);
//		Font font = new Font("Verdana", Font.BOLD, fontSize);
//		g2.setFont(font);
//		g2.drawString(title, 30, 20);
//	}
}