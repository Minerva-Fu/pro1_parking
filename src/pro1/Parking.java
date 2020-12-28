package pro1;

public class Parking {

	private int areaId ;
	private String areaName ;
	private String parkName; 
	private int totalSpace ;
	private int surplusSpace ;
	private String payGuide ;
	private String address ;
	private String parkId;
	private float wgsX ;
	private float wgsY ;
	
	@Override
	public String toString() {
			return "地區編號=" + areaId + "\n"
					+"地區=" + areaName + "\n"
					+ "停車場名字=" + parkName + "\n"
					+ "總停車位="+ totalSpace + "\n"
					+ "剩餘車位=" + surplusSpace + "\n"
					+ "計費方式=" + payGuide + "\n"
					+ "地址=" + address + "\n"
					+ "停車場編號=" + parkId + "\n"
					+ "經度=" + wgsX + "\n"
					+ "緯度=" + wgsY + "\n";
	}
	
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public int getTotalSpace() {
		return totalSpace;
	}
	public void setTotalSpace(int totalSpace) {
		this.totalSpace = totalSpace;
	}
	public int getSurplusSpace() {
		return surplusSpace;
	}
	public void setSurplusSpace(int surplusSpace) {
		this.surplusSpace = surplusSpace;
	}
	public String getPayGuide() {
		return payGuide;
	}
	public void setPayGuide(String payGuide) {
		this.payGuide = payGuide;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public float getWgsX() {
		return wgsX;
	}
	public void setWgsX(float wgsX) {
		this.wgsX = wgsX;
	}
	public float getWgsY() {
		return wgsY;
	}
	public void setWgsY(float wgsY) {
		this.wgsY = wgsY;
	}

	
	
	
}
