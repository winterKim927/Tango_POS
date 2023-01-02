package posApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import posApp.HallPage;
import posApp.Page;
import posApp.PosMain;
import posApp.dao.OrderDetailDAO;
import posApp.dao.OrderSummaryDAO;
import posApp.domain.OrderDetail;
import posApp.domain.OrderSummary;

//테이블 당 주문을 처리하고 상세내역을 표시할 패널
public class OrderPage extends JPanel{
	//디자인
	OrderListTablePanel p_table;
	OrderPageMenuListPanel p_menu;
	OrderPageModifyPanel p_bt;
	HallPage hallPage;
	//자료
	int tableNo;
	ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>(); 
	
	public OrderPage(HallPage hallPage, int tableNo) {
		this.hallPage = hallPage;
		this.tableNo = tableNo;
		setLayout(null);
		
		p_table = new OrderListTablePanel(tableNo);
		p_menu = new OrderPageMenuListPanel(this, tableNo);
		p_bt = new OrderPageModifyPanel();
		
		p_menu.setBounds(500, 0, 500, 550);
		p_bt.setBounds(500, 550, 500, 180);
		
		setLayout(null);
		add(p_table);
		add(p_menu);
		add(p_bt);
		
		setSize(PosMain.CONTENTSIZE);
		setBackground(Color.gray);
		addListeners();
	}
	
	public void addListeners() {
		p_bt.bt_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hallPage.cardLayout.show(hallPage, "tables");
			}
		});
		
		p_bt.bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = p_table.table.getSelectedRow();
				p_menu.orderDetailList.remove(index);
				p_menu.updateList();
			}
		});
		
		p_bt.bt_cancelAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(hallPage, "주문을 전부 취소할까요?")==JOptionPane.OK_OPTION) {
					p_menu.orderDetailList.removeAll(p_menu.orderDetailList);
					p_menu.updateList();
				}
			}
		});
		
		p_bt.bt_commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(OrderPage.this, "결제할까요?")==JOptionPane.OK_OPTION) {
					insert();
				}
			}
		});
	}
	
	public void insert() {
		OrderSummaryDAO sumDAO = new OrderSummaryDAO();
		OrderDetailDAO detailDAO = new OrderDetailDAO();
		int totalprice = 0;
		int payment_number = (int)System.currentTimeMillis();
		for (int i = 0; i < orderDetailList.size(); i++) {
			OrderDetail dto = orderDetailList.get(i);
			totalprice += dto.getCount()*dto.getPrice();
		}
		OrderSummary orderSummary = new OrderSummary();
		orderSummary.setTotalprice(totalprice);
		orderSummary.setPayment_number(payment_number);
		int result = sumDAO.insert(orderSummary);
		int count = 0;
		if (result > 0) {
			int sumIDX = sumDAO.selectIdx(payment_number);
			if (sumIDX != 0) {
				for (int i = 0; i < orderDetailList.size(); i++) {
					orderDetailList.get(i).setOrder_summary_idx(sumIDX);
					count += detailDAO.insert(orderDetailList.get(i));
				} 
			} else {
				System.out.println("sumIDX 쿼리 실패");
			}
		} else {
			System.out.println("sumDAO 쿼리 실패");
		}
		if(count == orderDetailList.size()) {
			JOptionPane.showMessageDialog(this, "결제 완료");
			p_menu.orderDetailList.removeAll(p_menu.orderDetailList);
			p_menu.updateList();
		}
	}
}
