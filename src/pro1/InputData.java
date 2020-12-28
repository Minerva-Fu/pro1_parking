package pro1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InputData {

	public static void main(String[] args) {
	
		try (
	  			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1","mary","123");
	  			FileInputStream fis = new FileInputStream("D:\\InfoClass\\Project\\路外停車資訊.json");
	  			BufferedReader brpark = new BufferedReader(new InputStreamReader(fis));
	  			
	  			) {
	  		Statement stmt = connection.createStatement();
			PreparedStatement pstmt= connection.prepareStatement
					("insert into parking(areaId,areaName,parkName,totalSpace,surplusSpace,payGuide,address,parkId,wgsX,wgsY) values(?,?,?,?,?,?,?,?,?,?)");
	  		
	  		String c ="";
	  		StringBuilder b = new StringBuilder("");
	  		while((c = brpark.readLine()) != null) {
	  			b.append(c);
	  		}
	  		
	  		
	  		JSONObject parking = new JSONObject(b.toString());
	  		JSONArray parkingLots = parking.getJSONArray("parkingLots");
	  		for (int i =0 ; i< parkingLots.length(); i++) {
	   			
	  			JSONObject info = parkingLots.getJSONObject(i);
	  			
	  			pstmt.setInt(1,Integer.parseInt(info.getString("areaId")));
	  			pstmt.setString(2, info.getString("areaName"));
	  			pstmt.setString(3, info.getString("parkName"));
	  			pstmt.setInt(4, info.getInt("totalSpace"));
	  			pstmt.setInt(5, Integer.parseInt(info.getString("surplusSpace")));
	  			pstmt.setString(6, info.getString("payGuide"));
	  			pstmt.setString(7, info.getString("address"));
	  			pstmt.setString(8, info.getString("parkId"));
	  			pstmt.setFloat(9, info.getFloat("wgsX"));
	  			pstmt.setFloat(10, info.getFloat("wgsY"));
	  			
	  			pstmt.executeUpdate();
	  			
	  			pstmt.clearParameters();
	  			System.out.println("finish");
	   		}
	  		
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(JSONException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		
		}
	}

}


