package posApp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class HallPage extends Page{
	CardLayout cardLayout = new CardLayout();
	JPanel hallTablePage;
	HallPageTablePanel[] tableList = new HallPageTablePanel[6];
	OrderPage[] orderPageList = new OrderPage[tableList.length];
	
	public HallPage(PosMain main) {
		super(main);
		
		setLayout(cardLayout);
		
		hallTablePage = new JPanel();
		hallTablePage.setLayout(null);
		hallTablePage.setBackground(Color.white);
		add(hallTablePage, "tables");
		
		createTables();
		createPages();
	}
	
	public void createTables() {
		for (int i = 0; i < tableList.length; i++) {
			int index = i;
			HallPageTablePanel table = new HallPageTablePanel(this, i);
			if(i<3) {
				table.setLocation(100+300*i, 80);
			} else {
				table.setLocation(100+300*(i-3), 350);
			}
			
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					cardLayout.show(HallPage.this, "order"+index);
				}
			});
			tableList[i] = table;
			hallTablePage.add(table);
		}
	}
	
	public void createPages() {
		for (int i = 0; i < orderPageList.length; i++) {
			orderPageList[i] = new OrderPage(this, i);
			this.add(orderPageList[i], "order"+i);
		}
	}
}
