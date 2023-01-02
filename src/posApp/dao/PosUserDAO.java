package posApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import posApp.domain.OrderDetail;
import posApp.domain.PosUser;
import util.DBManager;

public class PosUserDAO {
	DBManager manager = DBManager.getInstance();
	Connection con = manager.getConnection();
	
	public int insert(PosUser dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into pos_user(pos_user_idx, id, pass, name)"
				+ " values(seq_pos_user_idx.nextval, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			pstmt.setString(3, dto.getName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		ArrayList<PosUser> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from pos_user order by pos_user_idx";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PosUser dto = new PosUser();
				dto.setPos_user_idx(rs.getInt("pos_user_idx"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setRegdate(rs.getDate("regdate"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return list;
	}
	
	public int update(PosUser dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update pos_user set name = ?, id = ?, pass = ? where pos_user_idx = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPass());
			pstmt.setInt(4, dto.getPos_user_idx());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt);
		}
		
		return result;
	}
	
	public int delete(int idx) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from pos_user where pos_user_idx = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt);
		}
		
		return result;
	} 
}
