package _03_listCoupon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductService;

@WebServlet("/pages/ProductInsert.do")
public class CouponInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CouponInsert() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//準備存放錯誤訊息的List物件
		List<String> errorMessage=new ArrayList<String>();
		request.setAttribute("ErrorMsgKey", errorMessage);
		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		int	ID=0;
		double Price=0.0;
		int	Expire =0;
		
		//1. 檢查id欄位
		String id=request.getParameter("id");
		if (id != null && id.trim().length() > 0) {
			try {
			ID = Integer.parseInt(id.trim()); //因為ProductBean內的id型態為int所以要轉型態
			} catch (NumberFormatException e) {
				errorMessage.add("欄位必須為數值");
			}
		}		
		else if(id==null || id.trim().length()==0){
			errorMessage.add("id欄必須輸入");
		}	
		//2. 檢查name欄位
		String name=request.getParameter("name");
		if(name==null || name.trim().length()==0){
			errorMessage.add("name欄必須輸入");
		}
		//3.檢查price欄位
		String price=request.getParameter("price");
		if (price != null && price.trim().length() > 0) {
			try {
			Price = Double.parseDouble(price.trim()); //因為ProductBean內的price型態為double所以要轉型態
			} catch (NumberFormatException e) {
				errorMessage.add("price欄位必須為數值");
			}
		}
		else if(price==null || price.trim().length()==0){
			errorMessage.add("price欄必須輸入");
		}	

		//4.檢查make欄位
		String make=request.getParameter("make");
		java.sql.Date date = null;

		if (make != null && make.trim().length() > 0) {
			try {
				date = java.sql.Date.valueOf(make); //因為ProductBean內的make型態為date所以要轉型態
			} catch (IllegalArgumentException e) {
				errorMessage.add("make欄格式錯誤");
			}
		}
		
		//5.檢查expire欄位
		String expire=request.getParameter("expire");
		if (expire != null && expire.trim().length() > 0) {
			try {
				Expire = Integer.parseInt(expire.trim()); //因為ProductBean內的id型態為int所以要轉型態
			} catch (NumberFormatException e) {
				errorMessage.add("expire欄位必須為數值");
			}
		}
		else if(expire==null || expire.trim().length()==0){
			errorMessage.add("expire欄必須輸入");
		}		

		if (!errorMessage.isEmpty()) { //如果有錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("update.jsp");//連回update.jsp			
			rd.forward(request, response);
			return;
		}
	
		CouponBean bean=new CouponBean(ID,name,Price,date,Expire);
		CouponService service=new CouponService();
		service.insert(bean); //透過ProductService類別的物件service寫入資料庫
		response.sendRedirect("maintain.jsp"); //成功就連回maintain.jsp
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
