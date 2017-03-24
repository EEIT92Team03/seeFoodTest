package _01_login.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _01_login.model.MemberBean;
import _01_login.model.MemberDAO;

public class MemberDAOJdbc implements MemberDAO {

	public DataSource dataSource; // 宣告DataSource類別的
									// dataSource物件為全域方便底下Connection conn使用
	// 設定DataSource

	public MemberDAOJdbc() {
		try {
			InitialContext context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_MEMBER = "select * from member where memberAddress=?";

	@Override
	public MemberBean select(String memberAddress) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		MemberBean result = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_MEMBER);
			pstmt.setString(1, memberAddress);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new MemberBean();
				
				result.setMemberId(rs.getInt("memberId"));
				result.setDepId(rs.getInt("depId"));
				result.setMemberAddress(rs.getString("memberAddress"));
				result.setMemberPassword(rs.getBytes("memberPassword"));
				result.setMemberNicknName(rs.getString("memberNicknName"));
				result.setMemberBonus(rs.getInt("memberBonus"));
			

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
			}
		}

		return result;

	}

	private static final String UPDATE = "update member set password=?,email=?,birth=? where custid=?";

	@Override
	public boolean update(byte[] password,String email,java.util.Date birth,String custid){
//		Connection conn=null;
//			PreparedStatement pstmt=null;
//			//CustomerBean result=null;
//			try {
//				conn=dataSource.getConnection();
//				pstmt=conn.prepareStatement(UPDATE);
//				pstmt.setBytes(1, password);
//				pstmt.setString(2, email);
//				if(birth!=null){
//					long time=birth.getTime();
//					pstmt.setDate(3, new java.sql.Date(time));
//				}
//				else{
//					pstmt.setString(3, null);
//				}
//				
//				pstmt.setString(4,custid);
//
//				int num=pstmt.executeUpdate();
//				
//				if(num==1){
//					return true;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			finally{
//	   
//				try {
//					if(pstmt!=null){
//					pstmt.close();	
//					}
//				} catch (Exception e) {
//				}
//				try {
//					if(conn!=null){
//					conn.close();
//					}
//				} catch (Exception e) {
//				}
//	}
		return false;
		
	}

}
