package _01_login.model;

public interface MemberDAO {

	boolean update(byte[] password, String email, java.util.Date birth, String custid);

	MemberBean select(String custid);

}