package posApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import posApp.domain.OrderDetail;

//HallPage 내에 HallTablePage에 표시될 테이블 디자인
public class HallPageTablePanel extends JPanel{
	HallPage page;
	int tableNo;
	String totalPrice;
	ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>(); 
	
	public HallPageTablePanel(HallPage page, int tableNo) {
		this.page = page;
		this.tableNo = tableNo;
		LineBorder border = new LineBorder(Color.black);
		setBorder(border);
		setSize(200,200);
		setPreferredSize(new Dimension(200,200));
	}
	
	public void setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
		totalPrice = page.orderPageList[tableNo].p_table.t_total.getText();
		update(getGraphics());
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.clearRect(0, 0, 200, 200);
		
		//테이블 번호 라인 색칠
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 200, 200);
		
		//테이블 번호 기입
		g2.setColor(Color.black);
		g2.setFont(new Font("NANUM", Font.BOLD, 20));
		g2.drawString(tableNo+1+"", 10, 20);
		
		//주문이 있을 경우에만 테이블 색칠
		if (orderDetailList.size() > 0) {
			//테이블 내용 색칠
			g2.setColor(new Color(181,230,29));
			g2.fillRect(0, 30, 200, 200);
			//테이블 내용 기입
			g2.setColor(Color.black);
			g2.setFont(new Font("NANUM", Font.PLAIN, 15));
			for (int i = 0; i < orderDetailList.size(); i++) {
				if (i<6) {
					g2.drawString(orderDetailList.get(i).getMenuName(), 10, 60 + 20 * i);
					g2.drawString(orderDetailList.get(i).getCount() + "", 180, 60 + 20 * i);
				} else {
					g2.setFont(new Font("NANUM", Font.BOLD, 20));
					g2.drawString("...", 10, 190);
				}
			}
			g2.setFont(new Font("NANUM", Font.PLAIN, 15));
			g2.drawString(totalPrice, 90, 190);
		}
	}
}
