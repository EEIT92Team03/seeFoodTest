package _01_login.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_login.model.MemberBean;
import _01_login.model.MemberService;

@WebServlet("/secure/login.controller")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 準備存放錯誤訊息的 List 物件
		Map<String,String> errorMsg = new HashMap<String,String>();
        request.setAttribute("ErrorMsgKey", errorMsg); 
        HttpSession session = request.getSession(); //取得session物件
		// 1. 讀取使用者輸入資料
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		// 2. 檢查使用者輸入資料
				if(username==null || username.trim().length()==0){
					errorMsg.put("iderror", "帳號欄必須輸入");
				}
				if(password==null || password.trim().length()==0){
					errorMsg.put("pwderror", "密碼欄必須輸入");
				}
				if (!errorMsg.isEmpty()) { //如果有錯誤訊息
					RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");//連回login.jsp
					rd.forward(request, response);
					return;
				}
				
		//3.進行邏輯運算
				MemberService confirm=new MemberService();
				boolean exits = confirm.login(username, password);
				if(exits==false){
					errorMsg.put("loginerror", "帳號或密碼錯誤");
				}
	   //4.選擇要顯示的畫面
				session.setAttribute("username", username);
				session.setAttribute("exits", exits);
				
				
				
				MemberService ms=new MemberService();
				MemberBean mb=ms.checkIDPassword(username, password);
				session.setAttribute("mb", mb);
				if(exits){ //帳號密碼符合連到index.jsp
					//不加/就是同目錄(secure)
//					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//					rd.forward(request, response);
					//request.getContextPath():/LabWeb23
					
					response.sendRedirect(request.getContextPath()+"/index.jsp");
					return ; 
				}
				else{ //不符合連到login.jsp
					RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
					rd.forward(request, response);
					return ; 
				}
	}
}
