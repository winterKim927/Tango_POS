package posApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import posApp.domain.OrderDetail;
import util.DBManager;

public class OrderDetailDAO {
	DBManager manager = DBManager.getInstance();
	Connection con = manager.getConnection();
	
	public int insert(OrderDetail dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into order_detail(order_detail_idx, order_summary_idx, menuname, quantity, tableno)"
				+ " values(seq_order_detail_idx.nextval, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getOrder_summary_idx());
			pstmt.setString(2, dto.getMenuName());
			pstmt.setInt(3, dto.getCount());
			pstmt.setInt(4, dto.getTableNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt);
		}
		
		return result;
	}
	
	public List selectUsingSumIdx(int sumIdx) {
		ArrayList<OrderDetail> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from order_detail where order_summary_idx = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sumIdx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderDetail dto = new OrderDetail();
				dto.setOrder_detail_idx(rs.getInt("order_detail_idx"));
				dto.setOrder_summary_idx(rs.getInt("order_summary_idx"));
				dto.setMenuName(rs.getString("menuname"));
				dto.setCount(rs.getInt("quantity"));
				dto.setTableNo(rs.getInt("tableno")+1);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return list;
	}
}
