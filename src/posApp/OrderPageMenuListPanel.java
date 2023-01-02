package posApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;

import posApp.domain.OrderDetail;
import posApp.domain.OrderSummary;

//등록된 메뉴들을 표시하고, 클릭하면 주문을 처리하는 패널
public class OrderPageMenuListPanel extends Panel{
	JButton[] menuBtList = new JButton[10]; 
	String[] menuName = {"후라이드치킨","양념치킨","골뱅이소면","마른안주세트","과일안주","참이슬","처음처럼","생맥주500cc","클라우드","카스"};
	int[] menuPrice = {16000, 18000, 15000, 12000, 12000, 4000, 4000, 4000, 5000, 5000};
	ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>(); 
	HallPage hallPage;
	OrderPage orderPage;
	int tableNo;
	public OrderPageMenuListPanel(OrderPage orderPage, int tableNo) {
		this.orderPage = orderPage;
		this.tableNo = tableNo;
		hallPage = (HallPage)orderPage.hallPage;
		setLayout(new GridLayout(4, 3, 3, 3));
		for (int i = 0; i < menuBtList.length; i++) {
			final int index = i;
			menuBtList[i] = new JButton(menuName[i]);
			menuBtList[i].setFont(new Font("NANUM", Font.BOLD, 20));
			add(menuBtList[i]);
			menuBtList[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == menuBtList[index]) {
						createList(index);
					}
				}
			});
		}
		setBackground(Color.white);
	}
	
	//주문메뉴 클릭시 추가된 메뉴를 리스트로 만드는 메서드
	public void createList(int index) {
		//리스트에 이미 같은 메뉴가 있을 경우 수량만 추가
		for (int i = 0; i < orderDetailList.size(); i++) {
			OrderDetail dto = orderDetailList.get(i);
			if(dto.getMenuName().equals(menuBtList[index].getText())) {
				dto.setCount(dto.getCount()+1);
				//dto.setTotalPrice(dto.getPrice()*dto.getCount());
				updateList();
				return;
			}
		}
		//리스트에 메뉴가 없을 경우 새로 추가
		OrderSummary orderSummary = new OrderSummary();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setTableNo(tableNo);
		orderDetail.setMenuName(menuName[index]);
		orderDetail.setPrice(menuPrice[index]);
		orderDetail.setCount(1);
		//orderDetail.setTotalPrice();
		orderDetailList.add(orderDetail);
		updateList();
	}
	
	//주문화면에 표시되는 총액 갱신
	public void updateTotalPrice() {
		int totalPrice = 0;
		for (int i = 0; i < orderDetailList.size(); i++) {
			int price = orderDetailList.get(i).getPrice();
			int count = orderDetailList.get(i).getCount();
			totalPrice += price*count;
		}
		DecimalFormat df = new DecimalFormat("###,###,###");
		orderPage.p_table.t_total.setText("총액 : "+df.format(totalPrice)+"원");
	}
	
	//테이블당 주문 리스트 갱신
	public void updateList() {
		updateTotalPrice();
		orderPage.orderDetailList = orderDetailList;
		orderPage.p_table.model.setOrderList(orderDetailList);; //테이블 모델의 리스트 교체
		orderPage.p_table.updateUI(); //주문화면 표 갱신
		orderPage.hallPage.tableList[tableNo].setOrderDetailList(orderDetailList);
	}
}
