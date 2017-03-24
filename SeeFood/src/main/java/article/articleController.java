package article;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/js/article.controller")
public class articleController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
                this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("UTF-8");
		//接收資料		
				String word=request.getParameter("content");
				System.out.println("word="+word);  //測試是否成功接收
		//驗證資料
				
				if(word==null||word.trim().length()==0){
					Map<String, String> errors=new HashMap<String, String>();
					request.setAttribute("errors", errors);
					errors.put("noword", "你尚未輸入文字");
					
					request.getRequestDispatcher("/ckeditor/samples/article.jsp"
							).forward(request, response);
				
			}
	
	}
}
