package posApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import posApp.domain.OrderDetail;

public class OrderListTableModel extends AbstractTableModel{
	String[] colName = {"메뉴명","단가","수량", "총액"};
	ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>(); 
	public int getRowCount() {
		return orderDetailList.size();
	}

	public int getColumnCount() {
		return 4;
	}

	public Object getValueAt(int row, int col) {
		OrderDetail dto = orderDetailList.get(row);
		Object data = null;
		switch(col) {
			case 0 : data = dto.getMenuName(); break;
			case 1 : data = dto.getPrice(); break;
			case 2 : data = dto.getCount(); break;
			case 3 : data = dto.getPrice()*dto.getCount()+"원"; break;
		}
		return data;
	}
	
	public String getColumnName(int col) {
		return colName[col];
	}
	
	public void setOrderList(List list) {
		orderDetailList = (ArrayList)list;
	}
}
