package _01_login.model;

public class MemberBean {

	private int memberId;
	private int depId;
	private String memberAddress;
	private byte[] memberPassword;
	private String memberNicknName;
	private int memberBonus;

	@Override
	public String toString() {
		return "MemberBean [memberId=" + memberId + ", depId=" + depId + ", memberAddress=" + memberAddress
				+ ", memberPassword=" + memberPassword + ", memberNicknName=" + memberNicknName + ", memberBonus="
				+ memberBonus + "]";
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public byte[] getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(byte[] memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberNicknName() {
		return memberNicknName;
	}

	public void setMemberNicknName(String memberNicknName) {
		this.memberNicknName = memberNicknName;
	}

	public int getMemberBonus() {
		return memberBonus;
	}

	public void setMemberBonus(int memberBonus) {
		this.memberBonus = memberBonus;
	}

}
