package posApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

//날짜에 사용하기 위한 셀
public class DateCell extends JButton{
	Color color = Color.white;
	Color titleColor = Color.darkGray;
	RevenuePage page;
	String title;
	String content;
	public DateCell(String title, String content) {
		this.page = page;
		this.title = title;
		this.content = content;
		setBorder(new LineBorder(Color.lightGray));
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, 150, 120);
		g2.setColor(color);
		g2.fillRect(0, 0, 150, 120);
		g2.setColor(titleColor);
		Font font = new Font("NANUM", Font.BOLD, 15);
		g2.setFont(font);
		g2.drawString(title, 10, 20);
		g2.setColor(Color.white);
		g2.drawString(content, 40, 60);
	}
}