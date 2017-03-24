package _03_listCoupon.model;

import java.util.List;

public interface CouponDAO {

	CouponBean select(int cpid);

	List<CouponBean> select();

	CouponBean insert(CouponBean bean);

	
	CouponBean update(int cpType,String cpData, int cpHowBonus,java.util.Date cpStarTime,java.util.Date cpOverTime,int cpResId );
	boolean delete(int cpid);

	CouponBean getSelect_1();

}