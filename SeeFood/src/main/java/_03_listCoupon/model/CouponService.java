package _03_listCoupon.model;
import java.util.ArrayList;
import java.util.List;

import _03_listCoupon.model.dao.CouponDAOJdbc;

public class CouponService {
	private CouponDAO productDao = new CouponDAOJdbc(); //多型，父類別物件可以用子類別ProductDAOJdbc類別內的方法
	
	public List<CouponBean> select(CouponBean bean) {  //select()函數，參數為ProductBean型態的物件
		List<CouponBean> result = null; //宣告List型態且泛型為ProductBean的物件result
		if(bean!=null && bean.getId()!=0) {  //參數不為空值，且取得的ID不等於0
			CouponBean temp = productDao.select(bean.getId()); 
			if(temp!=null) {  
				result = new ArrayList<CouponBean>(); 
				result.add(temp);
			}
		} else {
			result = productDao.select(); 
		}
		return result;
	}
	public CouponBean insert(CouponBean bean) { //insert()函數，參數為ProductBean型態的物件
		CouponBean result = null;  //宣告ProductBean型態的物件result
		if(bean!=null) { //如果參數不為空值
			result = productDao.insert(bean);  //就將參數寫入資料庫，且result值
		}
		return result;
	}
	public CouponBean update(CouponBean bean) {
		CouponBean result = null;
		if(bean!=null) {
			result = productDao.update(bean.getName(), bean.getPrice(),
					bean.getMake(), bean.getExpire(), bean.getId());
		}
		return result;
	}
	public boolean delete(CouponBean bean) {
		boolean result = false;
		if(bean!=null) {
			result = productDao.delete(bean.getId());
		}
		return result;
	}
}
