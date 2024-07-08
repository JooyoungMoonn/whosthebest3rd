package dc.human.whosthebest.vo;

public class TeamInfoVO {
	private int tID;
	private String tName;
	private String tLogo;
	private String tRegion;
	private String tPhone;
	private int tRankScore;
	private int tMinAge;
	private int tMaxAge;
	private int tMaxMember;
	private int tMember;
	private String tInfo;
	private String createdID;
	private String createdDate;
	private String updatedID;
	private String updatedDate;

	public TeamInfoVO() {
	}

	public int gettID() {
		return tID;
	}

	public void settID(int tID) {
		this.tID = tID;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String gettLogo() {
		return tLogo;
	}

	public void settLogo(String tLogo) {
		this.tLogo = tLogo;
	}

	public String gettRegion() {
		return tRegion;
	}

	public void settRegion(String tRegion) {
		this.tRegion = tRegion;
	}

	public String gettPhone() {
		return tPhone;
	}

	public void settPhone(String tPhone) {
		this.tPhone = tPhone;
	}

	public int gettRankScore() {
		return tRankScore;
	}

	public void settRankScore(int tRankScore) {
		this.tRankScore = tRankScore;
	}

	public int gettMinAge() {
		return tMinAge;
	}

	public void settMinAge(int tMinAge) {
		this.tMinAge = tMinAge;
	}

	public int gettMaxAge() {
		return tMaxAge;
	}

	public void settMaxAge(int tMaxAge) {
		this.tMaxAge = tMaxAge;
	}

	public int gettMaxMember() {
		return tMaxMember;
	}

	public void settMaxMember(int tMaxMember) {
		this.tMaxMember = tMaxMember;
	}

	public int gettMember() {
		return tMember;
	}

	public void settMember(int tMember) {
		this.tMember = tMember;
	}

	public String gettInfo() {
		return tInfo;
	}

	public void settInfo(String tInfo) {
		this.tInfo = tInfo;
	}

	public String getCreatedID() {
		return createdID;
	}

	public void setCreatedID(String createdID) {
		this.createdID = createdID;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedID() {
		return updatedID;
	}

	public void setUpdatedID(String updatedID) {
		this.updatedID = updatedID;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
}
