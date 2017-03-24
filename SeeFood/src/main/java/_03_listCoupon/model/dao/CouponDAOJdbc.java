package _03_listCoupon.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import _03_listCoupon.model.CouponBean;

public class CouponDAOJdbc {

	public static void main(String[] args) {
		CouponDAOJdbc couponDAOJdbc = new CouponDAOJdbc();

		// ---查詢單筆 採用產品序號

		 CouponBean select = couponDAOJdbc.select(15);
		 System.out.println(select);

		// ---查詢全部
		// List<CouponBean> selectAll = couponDAOJdbc.select();
		// System.out.println(selectAll);

		// ---新增單筆
//		 CouponBean cb = new CouponBean();
//		 cb.setCpResId(9);
//		 cb.setCpType(300);;
//		 cb.setCpData("HQHQ測試測試");
//		 cb.setCpPhoto(null);
//		 cb.setCpHowBonus(99);
//		 cb.setCpStarTime(LocalDateTime.now());
//		 cb.setCpOverTime(new java.util.Date());
//		 couponDAOJdbc.insert(cb);
//		 System.out.println(cb);
//		
		
		// ---修改
		// CouponBean update =couponDAOJdbc.update(10, "餅乾買十送一",20, new
		// java.util.Date(), new java.util.Date(), 3);
		// update.setCpResId(3);
		 
		// ---刪除
		 
	}

	private String URL = "jdbc:sqlserver://localhost:1433;databaseName=EEIT92";
	private String USERNAME = "sa";
	private String PASSWORD = "sa123456";

	private static final String SELECT_BY_ID = "select * from coupon where cpid=?";

	public CouponBean getSelect_1() {
		CouponBean cn = null;
		return cn;
	}

	public CouponBean select(int cpid) {

		CouponBean cb = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstm = conn.prepareStatement(SELECT_BY_ID);
			pstm.setInt(1, cpid);// 把問號填補
			rs = pstm.executeQuery(); // 從資料庫撈出來的資料放進去rs

			if (rs.next()) {

				cb = new CouponBean();

				// 從rs取出資料 並 放入Java Bean 物件

				cb.setCpId(rs.getInt("cpId"));
				cb.setCpResId(rs.getInt("cpResId"));
				cb.setCpType(rs.getInt("cpType"));
				cb.setCpData(rs.getString("cpData"));
				cb.setCpPhoto(rs.getBytes("cpPhoto"));
				cb.setCpHowBonus(rs.getInt("cpHowBonus"));
				
				//1.8新time  localDateTime   使用   
				//ResultSet尚未支援新的localDateTime 所以採用轉換再轉
				
				Date test = rs.getTimestamp("cpStarTime"); //從資料庫撈出來為date
				System.out.println(test);
				Instant test2=test.toInstant();//轉成instant>>
				LocalDateTime test3 = LocalDateTime.ofInstant(test2, ZoneOffset.systemDefault());//從instant 轉成localDateTime
				cb.setCpStarTime(test3);
				cb.setCpOverTime(rs.getDate("cpOverTime"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return cb;

	}

	public List<CouponBean> getAllCoupon() { // 將值回傳給maintain.jsp用
		return null;
	}

	private static final String SELECT_ALL = "select * from coupon";

	// @Override
	public List<CouponBean> select() {
		List<CouponBean> lcb = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstm = conn.prepareStatement(SELECT_ALL);
			rs = pstm.executeQuery();
			lcb = new ArrayList<CouponBean>();
			while (rs.next()) {
				CouponBean row = new CouponBean();
				row.setCpResId(rs.getInt("cpResId"));
				row.setCpType(rs.getInt("cpType"));
				row.setCpData(rs.getString("cpData"));
				row.setCpPhoto(rs.getBytes("cpPhoto"));
				row.setCpHowBonus(rs.getInt("cpHowBonus"));
				
				Date test = rs.getTimestamp("cpStarTime");
				Instant test2=test.toInstant();
				LocalDateTime test3 = LocalDateTime.ofInstant(test2, ZoneOffset.systemDefault());
				row.setCpStarTime(test3);
				row.setCpOverTime(rs.getDate("cpOverTime"));
				lcb.add(row);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

		return lcb;
	}

	private static final String INSERT = "insert into coupon (cpResId, cpType, cpData, cpPhoto, cpHowBonus, cpStarTime,cpOverTime) "
			+ "values (?, ?, ?, ? ,?, ?, ?)";

	public CouponBean insert(CouponBean bean) {

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CouponBean cb = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstm = conn.prepareStatement(INSERT);
			cb = new CouponBean();
			if (cb != null) {
				pstm.setInt(1, bean.getCpResId());
				pstm.setInt(2, bean.getCpType());
				pstm.setString(3, bean.getCpData());
				pstm.setBytes(4, bean.getCpPhoto());
				pstm.setInt(5, bean.getCpHowBonus());

				LocalDateTime cpStarTime = bean.getCpStarTime();
				if (cpStarTime != null) {
					pstm.setObject(6, LocalDateTime.now());

				} else {
					pstm.setObject(6, null);
				}

				java.util.Date cpOverTime = bean.getCpOverTime();
				if (cpOverTime != null) {
					pstm.setDate(7, new java.sql.Date(cpOverTime.getTime()));

				} else {
					pstm.setDate(7, null);
				}

				int i = pstm.executeUpdate();
				if (i == 1) {
					cb = this.select(bean.getCpResId());
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return cb;
	}

	private static final String UPDATE = "update coupon set cpResId=?,cpType=?, cpData=?, cpPhoto=?,"
			+ " cpHowBonus=?, cpStarTime=?, cpOverTime=? where cpId=?";

	public CouponBean update(int cpResId,int cpType, String cpData,byte[] cpPhoto, int cpHowBonus, java.util.Date cpStarTime,
			java.util.Date cpOverTime, int cpId) {

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstm = conn.prepareStatement(UPDATE);
			rs = pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return null;
	}

	private static final String DELETE = "delete from coupon where cpId=?";

	public boolean delete(int cpid) {

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstm = conn.prepareStatement(DELETE);
			rs = pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return false;
	}
}
