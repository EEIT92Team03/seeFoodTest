package _01_login.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import _01_login.model.dao.MemberDAOJdbc;

public class MemberService {
	private MemberDAO memberDao = new MemberDAOJdbc();
	static private List<MemberBean> memberList = new ArrayList<MemberBean>();
	
	
	public boolean login(String username, String password) {
		MemberBean bean = memberDao.select(username);//傳遞username到customerDao類別select()函數判斷
		if(bean!=null) {
			if(password!=null && password.length()!=0) { //檢查密碼的值與長度是否符合規定
				byte[] pass = password.getBytes();	//將使用者輸入的密碼轉成byte
				byte[] temp = bean.getMemberPassword();	//從資料庫抓取密碼

				if(Arrays.equals(pass, temp)) { //比較兩者是否相等
					System.out.println("login最裡面");
					return true; //相等傳回true
				}
			}
		}
		return false; //否則傳回false
	}
	public MemberBean checkIDPassword(String username, String password) {
		MemberBean bean = memberDao.select(username);
		// 檢查userId與password是否正確
		System.out.println("checkIDPassword最外層");
		
		MemberBean mb = new MemberBean();//創造一個名為mb的bean準備塞入準會員登入資料

			if(username!=null) {
				if(password!=null && password.length()!=0) { //檢查密碼的值與長度是否符合規定
					System.out.println("進入帳密判別");
					byte[] pass = password.getBytes();	//將使用者輸入的密碼轉成byte
					byte[] temp = bean.getMemberPassword();	//從資料庫抓取密碼
					String mb1 = bean.getMemberNicknName();//抓出準會員的暱稱
					if(Arrays.equals(pass, temp)) { //比較兩者是否相等
						
						System.out.print(mb1);
						mb.setMemberNicknName(mb1);//把抓出來的暱稱 塞入剛剛的mb
						return mb; //相等傳回true
						
					}
				}
			}
		
		return null;
	}

}
