<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.sql.*"%>
	<%@ page import="javax.naming.*"%>
	<%@ page import="javax.sql.DataSource"%>
	<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//在java程式中取得Connection敘述(公式要背)
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
			conn = ds.getConnection();
			//<以上為公式>

			String selectDept = "Select * from Dept";
			pstmt = conn.prepareStatement(selectDept);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				out.println(rs.getString("deptid"));
				out.println(rs.getString("deptname"));
				out.println("<br>");
			}
			//以上為查詢 會員分類

// 			String selectCoupon = "Select * from Coupon";
// 			pstmt = conn.prepareStatement(selectCoupon);
// 			rs = pstmt.executeQuery();
// 			while (rs.next()) {
// 				out.println("流水編號:"+rs.getString("cpId"));
// 				out.println("來源餐廳:"+rs.getString("cpResId"));
// 				out.println(rs.getString("cpType")+"料理");
// 				out.println("優惠卷內容:"+rs.getString("cpData"));
// 				out.println("圖片:"+rs.getString("cpPhoto"));
// 				out.println("所需紅利:"+rs.getString("cpHowBonus"));
// 				out.println("開始時間:"+rs.getString("cpStarTime"));
// 				out.println("結束使用時間:"+rs.getString("cpOverTime"));
// 				out.println("<br>");
// 			}
			//以上為查詢優惠券種類
			
			String selectCoupon2 = "select typeData,resName,cpData,cpHowBonus from coupon,couponTypes,restaurant where coupon.cpType = couponTypes.couponTypdId and coupon.cpResId =restaurant.resId";
			pstmt = conn.prepareStatement(selectCoupon2);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//out.println("流水編號:"+rs.getString("cpId"));
				out.println("來源餐廳:"+rs.getString("resName"));
				out.println(rs.getString("typeData")+"優惠");
				out.println("  優惠卷內容:"+rs.getString("cpData"));
				//out.println("圖片:"+rs.getString("cpPhoto"));
				out.println("所需紅利:"+rs.getString("cpHowBonus"));
				//out.println("開始時間:"+rs.getString("cpStarTime"));
				//out.println("結束使用時間:"+rs.getString("cpOverTime"));
				out.println("<br>");
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
	%>

</body>
</html>