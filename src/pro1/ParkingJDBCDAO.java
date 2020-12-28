package pro1;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.omg.CORBA.PUBLIC_MEMBER;

import oracle.jdbc.driver.DatabaseError;

public class ParkingJDBCDAO implements ParkingDAO {

	private javax.sql.DataSource dataSource;
	public javax.sql.DataSource getDataSource(){
		//當dataSource為空值才要執行
		if(dataSource == null) {
			BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("oracle.jdbc.OracleDriver");
	        ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
	        ds.setUsername("mary");
	        ds.setPassword("123");
	        ds.setMaxTotal(50); 
	        ds.setMaxIdle(50);
			dataSource = ds;
		}
		return dataSource;		
	}
	
	@Override
	//取得所有的parking table的資料，轉換成Parking物件，回傳List因為會有多筆
	public List<Parking> listParking(){
		List<Parking> list =new ArrayList<Parking>();
        
		try (  	Connection connection = getDataSource().getConnection();
				) {
			Statement stmt = connection.createStatement();			
			ResultSet rs = stmt.executeQuery("select * from parking");				
			while(rs.next()){
				//將每一筆資料轉為parking物件							
				Parking park = new Parking();
				park.setAreaId(Integer.parseInt(rs.getString("areaId")));
				park.setAreaName(rs.getString("areaName"));
				park.setParkName(rs.getString("parkName"));
				park.setTotalSpace(rs.getInt("totalSpace"));
				park.setSurplusSpace(Integer.parseInt(rs.getString("surplusSpace")));
				park.setPayGuide(rs.getString("payGuide"));
				park.setAddress(rs.getString("address"));
				park.setParkId(rs.getString("parkId"));
				park.setWgsX(rs.getFloat("wgsX"));
				park.setWgsY(rs.getFloat("wgsY"));
				
	  			list.add(park);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;			
	}	

	
	
	//更新資料		
	@Override
	public void updateParking (Parking park) {
		try (  Connection connection = getDataSource().getConnection();
				) {

			PreparedStatement pstmt = connection.prepareStatement("update parking set totalSpace=? where parkName=?");
			pstmt.setInt(1,park.getTotalSpace());
			pstmt.setString(2, park.getParkName());
			pstmt.executeUpdate();
			pstmt.clearParameters();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//刪除資料	
	@Override
	public void deletParking(Parking park) {
		try (  Connection connection = getDataSource().getConnection();	
				) {
			
				PreparedStatement pstmt = connection.prepareStatement("delete from parking where parkName=?");
				
				pstmt.setString(1, park.getParkName());	
				pstmt.executeUpdate();
				pstmt.clearParameters();
								
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		
	}
	
	
		//新增資料
		@Override
		public void insertParking(Parking park) {
			try (  Connection connection = getDataSource().getConnection();	
					) {
					PreparedStatement pstmt= connection.prepareStatement
						("insert into parking(areaId,areaName,parkName,totalSpace,surplusSpace,payGuide,address,parkId,wgsX,wgsY) values(?,?,?,?,?,?,?,?,?,?)");
					
					pstmt.setInt(1,park.getAreaId());
					pstmt.setString(2, park.getAreaName());
					pstmt.setString(3, park.getParkName());			
					pstmt.setInt(4, park.getTotalSpace());
					pstmt.setInt(5, park.getSurplusSpace());
					pstmt.setString(6,park.getPayGuide());
					pstmt.setString(7, park.getAddress());
					pstmt.setString(8, park.getParkId());
					pstmt.setFloat(9, park.getWgsX());
					pstmt.setFloat(10, park.getWgsY());
					pstmt.executeUpdate();
					pstmt.clearParameters();
				 				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
					
			}	 	
	
		//查詢-依地區編號		
		@Override
		public void selectByareaid(Parking park) {
			try (  Connection connection = getDataSource().getConnection();
					) {
				
				PreparedStatement pstmt = connection.prepareStatement(
						"select areaname,parkname,totalspace,payguide from parking where areaid like ?");
				
				pstmt.setInt(1,park.getAreaId());
				ResultSet rs = pstmt.executeQuery();

				while(rs.next()) {
					System.out.println(
							"\n"+"地區: " + rs.getString("areaName")+"\n"
							+"停車場名稱: " + rs.getString("parkName")+"\n"
							+"總停車位: "+rs.getInt("totalSpace")+"\n"
							+"計費:"+rs.getString("payGuide")+"\n"
							);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		//查詢-依停車場名字		
		@Override
		public void selectByparkname(Parking park) {
			try (  Connection connection = getDataSource().getConnection();	
					) {
				
						PreparedStatement pstmt = connection.prepareStatement(
								"select areaid,areaname,totalspace,payguide from parking where parkname =?");
						pstmt.setString(1, "%"+park.getParkName()+"%");
						ResultSet rs = pstmt.executeQuery();
						
						while(rs.next()) {
							System.out.println(
									"\n"+"地區編號:"+rs.getInt("areaId")+"\n"
									+"地區:" + rs.getString("areaName")+"\n"
									+"總停車位:"+rs.getInt("totalSpace")+"\n"
									+"計費:"+rs.getString("payGuide")+"\n"		
									);
						}
	 				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	
	//匯出檔案CSV
	@Override
	public void outputCSV() {		
		try (
				Connection connection = getDataSource().getConnection();	
	  			FileOutputStream fos = new FileOutputStream("D:\\Users\\USER\\Desktop\\���~������T.csv");
				BufferedWriter brpark = new BufferedWriter(new OutputStreamWriter(fos,"MS950"));				
				PreparedStatement pstmt= connection.prepareStatement("select * from parking");	
	  			ResultSet rs = pstmt.executeQuery();
				) {
					
	  		Parking tpark = new Parking();
	  		brpark.write("AREAID"+ ","+"AREANAME"+ ","+"PARKNAME"+ ","+"TOTALSPACE"+ ","+"SURPLUSSPACE"+ ","+"PAYGUIDE"+ ","+"ADDRESS"+ ","+"PARKID"+ ","+"WgsX"+ ","+"WgsY"+"\r\n");
	  		
	  		while(rs.next()) {
	  				
	  			int areaId = Integer.parseInt(rs.getString("areaId"));
				String areaName = rs.getString("areaName");
				String parkName =rs.getString("parkName");
				int totalSpace =rs.getInt("totalSpace");
	  			int surplusSpace =Integer.parseInt(rs.getString("surplusSpace"));
	  			String payGuide = rs.getString("payGuide");
	  			String address = rs.getString("address");
	  			String parkId = rs.getString("parkId");
	  			float wgsX = rs.getFloat("wgsX");
	  			float wgsY = rs.getFloat("wgsY");
	  				
	  			String s = Integer.parseInt(rs.getString("areaId")) + ","+rs.getString("areaName")+ ","+rs.getString("parkName")
	  					+ ","+Integer.toString(rs.getInt("totalSpace"))+ ","+rs.getString("surplusSpace")+ ","+rs.getString("payGuide")
	  					+ ","+rs.getString("address")+ ","+rs.getString("parkId")+ ","+rs.getFloat("wgsX")+ ","+rs.getFloat("wgsY")+"\r\n";
	  				  			  			
	  			brpark.write(s);  
	  			System.out.println(s);
	  		}
	  			  		
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
	
	}

	  		

		
		
		
	}
}

