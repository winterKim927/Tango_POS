package posApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import posApp.domain.OrderDetail;

public class RevenueDetailModel extends AbstractTableModel{
	ArrayList<OrderDetail> list = new ArrayList();
	String[] colName = {"메뉴이름","수량","테이블번호"};
	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return colName.length;
	}

	public Object getValueAt(int row, int col) {
		OrderDetail dto = list.get(row);
		Object data = null;
		switch(col) {
			case 0 : data = dto.getMenuName(); break;
			case 1 : data = dto.getCount(); break;
			case 2 : data = dto.getTableNo(); break;
		}
		return data;
	}
	
	public String getColumnName(int col) {
		return colName[col];
	}
	
	public void setList(List list) {
		this.list = (ArrayList)list;
	}
	
	

}
