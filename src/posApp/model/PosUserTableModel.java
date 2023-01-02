package posApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import posApp.AdminPage;
import posApp.domain.OrderSummary;
import posApp.domain.PosUser;

public class PosUserTableModel extends AbstractTableModel{
	public ArrayList<PosUser> list = new ArrayList();
	String[] colName = {"등록번호", "이름", "아이디", "비밀번호", "등록일"};
	AdminPage page;
	
	public PosUserTableModel(AdminPage page) {
		this.page = page;
	}
	
	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return colName.length;
	}

	public Object getValueAt(int row, int col) {
		PosUser dto = list.get(row);
		Object data = null;
		switch(col) {
			case 0 : data = dto.getPos_user_idx(); break;
			case 1 : data = dto.getName(); break;
			case 2 : data = dto.getId(); break;
			case 3 : data = dto.getPass(); break;
			case 4 : data = dto.getRegdate(); break;
		}
		return data;
	}
	
	public String getColumnName(int col) {
		return colName[col];
	}
	
	public boolean isCellEditable(int row, int col) {
		boolean flag = true;
		if(row == 0 && col != 3) {
			flag = false;
		} else if(row > 0 && col == 0 || row > 0 && col == 4) {
			flag = false;
		}
		return flag;
	}
	
	public void setValueAt(Object value, int row, int col) {
		PosUser dto = list.get(row);
		switch (col) {
			case 1 :	dto.setName((String)value); break;
			case 2 : dto.setId((String)value); break;
			case 3 : dto.setPass((String)value);
		}
		int result = page.userDAO.update(dto);
		if (result > 0) {
			System.out.println("업데이트 성공");
		}
	}
}
