package pro1;

import java.util.List;

public interface ParkingDAO {

	List<Parking> listParking();

	//更新資料	
	void updateParking(Parking park);

	//刪除資料	
	void deletParking(Parking park);

	//新增資料
	void insertParking(Parking park);

	//查詢-依地區編號		
	void selectByareaid(Parking park);

	//查詢-依停車場名字	
	void selectByparkname(Parking park);
	
	//匯出檔案CSV
	void outputCSV();

}