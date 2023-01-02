package posApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import posApp.domain.OrderDetail;
import posApp.domain.OrderSummary;
import util.DBManager;

public class OrderSummaryDAO {
	DBManager manager = DBManager.getInstance();
	Connection con = manager.getConnection();
	
	public int insert(OrderSummary orderSummary) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into order_summary(order_summary_idx, payment_number, totalprice)"
				+ " values(seq_order_summary_idx.nextval, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderSummary.getPayment_number());
			pstmt.setInt(2, orderSummary.getTotalprice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt);
		}
		return result;
	}
	
	public int selectIdx(int payment_number) {
		int idx = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select order_summary_idx from order_summary where payment_number = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, payment_number);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				idx = rs.getInt("order_summary_idx");
			} else {
				System.out.println("selectIDX 쿼리 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return idx;
	}
	
	public int selectDailyRevenue(String fullDate) {
		int dailyRevenue = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sum(totalprice) as dailyRevenue from order_summary"
				+ " WHERE PAYDATE BETWEEN TO_DATE(?) AND TO_DATE(?)+1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fullDate);
			pstmt.setString(2, fullDate);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dailyRevenue = rs.getInt("dailyRevenue");
			} else {
				System.out.println("쿼리실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return dailyRevenue;
	}
	
	public List selectAll(String fullDate) {
		ArrayList<OrderSummary> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from order_summary WHERE PAYDATE BETWEEN TO_DATE(?) AND TO_DATE(?)+1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fullDate);
			pstmt.setString(2, fullDate);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderSummary dto = new OrderSummary();
				dto.setOrder_summary_idx(rs.getInt("order_summary_idx"));
				dto.setPayment_number(rs.getInt("Payment_number"));
				dto.setTotalprice(rs.getInt("totalprice"));
				dto.setPaydate(rs.getDate("paydate"));
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
