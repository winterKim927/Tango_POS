package posApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import posApp.domain.OrderSummary;

public class RevenueSummaryModel extends AbstractTableModel{
	ArrayList<OrderSummary> list = new ArrayList();
	String[] colName = {"전표번호","결제번호","결제액","날짜"};
	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return colName.length;
	}

	public Object getValueAt(int row, int col) {
		OrderSummary dto = list.get(row);
		Object data = null;
		switch(col) {
			case 0 : data = dto.getOrder_summary_idx(); break;
			case 1 : data = dto.getPayment_number(); break;
			case 2 : data = dto.getTotalprice(); break;
			case 3 : data = dto.getPaydate(); break;
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
