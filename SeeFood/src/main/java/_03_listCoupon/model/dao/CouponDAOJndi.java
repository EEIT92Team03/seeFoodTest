package _03_listCoupon.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _03_listCoupon.model.CouponBean;

public class CouponDAOJndi {
	public DataSource dataSource; // 宣告DataSource類別的
									// dataSource物件為全域方便底下Connection conn使用
	int Id = 0;

	// 設定DataSource
	public CouponDAOJndi() {
		try {
			InitialContext context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id; // 此處的setId是給update.jsp送值設定用，並將實例變數的Id改為參數的id，最後再給getSelect_1()的where條件用
	}

	private static final String SELECT_BY_ID = "select * from coupon where cpid=?";

	public CouponBean getSelect_1() { // 回傳給update.jsp用
		// get方法不能放參數，否則jsp的EL無法讀取
		CouponBean result = null;

		ResultSet rset = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, Id);
			rset = stmt.executeQuery();
			if (rset.next()) {
				result = new CouponBean();
				result.setCpResId(rset.getInt("cpResId"));
				result.setCpType(rset.getInt("cpType"));
				result.setCpData(rset.getString("cpData"));
				result.setCpPhoto(rset.getBytes("cpPhoto"));
				result.setCpHowBonus(rset.getInt("cpHowBonus"));
				result.setCpStarTime(rset.getDate("cpStarTime"));
				result.setCpOverTime(rset.getDate("cpOverTime"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public CouponBean select(int cpid) {
		CouponBean result = null;

		ResultSet rset = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, cpid);
			rset = stmt.executeQuery();
			if (rset.next()) {
				result = new CouponBean();
				result.setCpId(rset.getInt("cpId"));
				result.setCpResId(rset.getInt("cpResId"));
				result.setCpType(rset.getInt("cpType"));
				result.setCpData(rset.getString("cpData"));
				result.setCpPhoto(rset.getBytes("cpPhoto"));
				result.setCpHowBonus(rset.getInt("cpHowBonus"));
				result.setCpStarTime(rset.getDate("cpStarTime"));
				result.setCpOverTime(rset.getDate("cpOverTime"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public List<CouponBean> getAllCoupon() { // 將值回傳給maintain.jsp用
		return select();
	}

	private static final String SELECT_ALL = "select * from coupon";

	// @Override
	public List<CouponBean> select() {
		List<CouponBean> result = null;

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {

			result = new ArrayList<CouponBean>();
			while (rset.next()) {
				CouponBean row = new CouponBean();
				row.setCpId(rset.getInt("cpId"));
				row.setCpResId(rset.getInt("cpResId"));
				row.setCpType(rset.getInt("cpType"));
				row.setCpData(rset.getString("cpData"));
				row.setCpPhoto(rset.getBytes("cpPhoto"));
				row.setCpHowBonus(rset.getInt("cpHowBonus"));
				row.setCpStarTime(rset.getDate("cpStarTime"));
				row.setCpOverTime(rset.getDate("cpOverTime"));

				result.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String INSERT = "insert into coupon (cpResId, cpType, cpData, cpHowBonus, cpStarTime,cpOverTime) "
			+ "values (?, ?, ?, ?, ?,?)";

	public CouponBean insert(CouponBean bean) {
		CouponBean result = null;
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {

			if (bean != null) {
				stmt.setInt(1, bean.getCpResId());
				stmt.setInt(2, bean.getCpType());
				stmt.setString(3, bean.getCpData());
				// stmt.setByte(4, new byte[](bean.getCpPhoto()));
				stmt.setInt(4, bean.getCpHowBonus());

				java.util.Date cpStarTime = bean.getCpStarTime();
				if (cpStarTime != null) {
					stmt.setDate(5, new java.sql.Date(cpStarTime.getTime()));

				} else {
					stmt.setDate(5, null);
				}

				java.util.Date cpOverTime = bean.getCpStarTime();
				if (cpOverTime != null) {
					stmt.setDate(6, new java.sql.Date(cpOverTime.getTime()));

				} else {
					stmt.setDate(6, null);
				}

				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(bean.getCpResId());
				}

				// 時間方法好朋友
				// java.util.Date make = bean.getMake();
				// if(make!=null) {
				// stmt.setDate(4, new java.sql.Date(make.getTime()));
				// } else {
				// stmt.setDate(4, null);
				// }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "update coupon set cpType=?, cpData=?, cpHowBonus=?, cpStarTime=?, cpOverTime=? where cpResId=?";
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.dao.PRO2DAO#update(java.lang.String, double, java.util.Date,
	 * int, int)
	 */

	public CouponBean update(int cpResId,int cpType, String cpData, int cpHowBonus, java.util.Date cpStarTime,
			java.util.Date cpOverTime,int cpId ) {
		CouponBean result = null;
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE);) {

			// stmt.setString(1, name);
			// stmt.setDouble(2, price);
			// if(make!=null) {
			// stmt.setDate(3, new java.sql.Date(make.getTime()));
			// } else {
			// stmt.setDate(3, null);
			// }
			// stmt.setInt(4, expire);
			// stmt.setInt(5, id);

			// 照片施工中
			// stmt.setByte(4, new byte[](bean.getCpPhoto()));
			stmt.setInt(1, cpType);
			stmt.setString(2, cpData);
			stmt.setInt(3, cpHowBonus);
			if (cpStarTime != null) {
				stmt.setDate(4, new java.sql.Date(cpStarTime.getTime()));
			} else {
				stmt.setDate(4, null);
			}
			if (cpOverTime != null) {
				stmt.setDate(5, new java.sql.Date(cpOverTime.getTime()));
			} else {
				stmt.setDate(5, null);
			}
			stmt.setInt(6, cpResId);

			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(cpResId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "delete from coupon where cpResId=?";

	public boolean delete(int cpid) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {

			stmt.setInt(1, cpid);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
