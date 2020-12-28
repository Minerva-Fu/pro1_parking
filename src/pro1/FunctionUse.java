package pro1;

import java.util.List;
import java.util.Scanner;

public class FunctionUse {
	
	public static void home() {
			Scanner scanner = new Scanner(System.in);
			System.out.println(
					"==="+"請選擇要執行的動作?"+"==="+"\r\n"
					+"===="+"1"+" 查詢\""+"\n"
					+"===="+"2"+" 新增"+"\n"
					+"===="+"3"+" 刪除"+"\n"
					+"===="+"4"+" 修改"+"\n"
					+"===="+"其他數字鍵離開}"+ "\r\n"
					+"=========================");
			
			int hc = Integer.parseInt(scanner.nextLine());
			if(hc == 1) {
				select();
			}else if (hc == 2) {
				insert();
			}else if (hc == 3) {
				delete();
			}else if (hc == 4) {
				update();
			}else {
				System.out.println("謝謝使用");
				
			}						
		}
			
			//查詢功能
			public static void select() {
				Scanner scanner = new Scanner(System.in);
				System.out.println(
					"==="+"選擇查詢方法?"+"==="+"\n"
					+"===="+"1"+" 地區"+"\n"
					+"===="+"2"+" 停車場名稱"+"\n"
					+"===="+"3"+" Home"+"\r\n"
					+"=========================");
				
				//sc=select choose
				int sc =Integer.parseInt(scanner.nextLine());
				if( sc == 1) {	
					ParkingDAO parkingDAO = new ParkingJDBCDAO();
					List<Parking> parkings = parkingDAO.listParking();
					System.out.println("輸入要查詢的地區編號:");
					System.out.println(
							"====桃園市個行政區域代碼===="+"\r\n"
							+"===="+"1	桃園區\r\n" 
							+"===="+"2	中壢區\r\n" 
							+"===="+"3	八德區\r\n" 
							+"===="+"4	平鎮區\r\n"  
							+"===="+"5	大溪區\r\n"  
							+"===="+"6	楊梅區\r\n" 
							+"===="+"7	龜山區\r\n" 
							+"===="+"8	蘆竹區\r\n" 
							+"===="+"9	大園區\r\n" 
							+"===="+"11	新屋區\r\n" 
							+"===="+"12	龍潭區\r\n"
							+"=========================");
					int areaId = Integer.parseInt(scanner.nextLine());
					for (Parking park : parkings) {
						if( park.getAreaId() == areaId) {
							park.setAreaId(areaId);
							parkingDAO.selectByareaid(park);
							break;
						}
					}
					home();
				}else if (sc == 2) {
					ParkingDAO parkingDAO = new ParkingJDBCDAO();
					List<Parking> parkings = parkingDAO.listParking();
					System.out.println("請輸入停車場名稱:");
					String parkName = scanner.nextLine();
					
					for (Parking park : parkings) {
						if(park.getParkName().contains(parkName)){
							System.out.println(park.toString());					
						}
					}
					home();
				}else if(sc == 3) {
						home();
				}else {
						System.out.println("��J���~");
						select();
				}
			}
		
			//新增功能
			public static void insert() {
				Scanner scanner = new Scanner(System.in);
				System.out.println(
					"===="+"選擇下一步"+"===="+"\n"
					+"===="+"1"+" 新增資料"+"\n"
					+"===="+"2"+" 返回Home"+"\r\n"
					+"=========================");
				
				//ic=insert choose
				int ic =Integer.parseInt(scanner.nextLine());
				if( ic == 1) {
					ParkingDAO parkingDAO = new ParkingJDBCDAO();
					List<Parking> parkings = parkingDAO.listParking();
					for (Parking park : parkings) {
						System.out.println("地區編號:");
						int nareaId = Integer.parseInt(scanner.nextLine());
						park.setAreaId(nareaId);
						System.out.println("\n"+"地區:");
						String nareaName = scanner.nextLine();
						park.setAreaName(nareaName);
						System.out.println("\n"+"停車場名字:");
						String nparkName = scanner.nextLine();
						park.setParkName(nparkName);
						System.out.println("\n"+"總停車位:");
						int ntotalSpace = Integer.parseInt(scanner.nextLine());
						park.setTotalSpace(ntotalSpace);
						System.out.println("\n"+"剩餘車位:");
						int nsurplusSpace = Integer.parseInt(scanner.nextLine());
						park.setSurplusSpace(nsurplusSpace);
						System.out.println("\n"+"計費方式:");
						String npayGuide  = scanner.nextLine();
						park.setPayGuide(npayGuide);
						System.out.println("\n"+"地址:");
						String naddress = scanner.nextLine();
						park.setAddress(naddress);
						System.out.println("\n"+"停車場編號:");
						String nparkId = scanner.nextLine();
						park.setParkId(nparkId);
						System.out.println("經度:");
						float nwgsX = Float.parseFloat(scanner.nextLine());
						park.setWgsX(nwgsX);
						System.out.println("緯度:");
						float nwgsY = Float.parseFloat(scanner.nextLine());
						park.setWgsY(nwgsY);
			
						parkingDAO.insertParking(park);	
						System.out.println("done");
						break;
					}	
					home();
				}else if(ic == 2) {
					home();
				}else {
					System.out.println("輸入錯誤");
					insert();
				}
			}	
		
			
			
			//刪除功能
			public static void delete() {
				Scanner scanner = new Scanner(System.in);
				System.out.println(
					"===="+"選擇下一步"+"===="+"\n"
					+"===="+"1"+" 刪除資料"+"\n"
					+"===="+"2"+" 返回Home"+"\r\n");
				//dc=delete choose
				int dc =Integer.parseInt(scanner.nextLine());
				if( dc == 1) {
					ParkingDAO parkingDAO = new ParkingJDBCDAO();
					List<Parking> parkings = parkingDAO.listParking();
					System.out.println("輸入要刪除的停車場名稱: ");
					String parkName = scanner.nextLine();
					for (Parking park : parkings) {		
						if (park.getParkName().equals(parkName)) {	
							park.setParkName(parkName);
							parkingDAO.deletParking(park);	
							System.out.println("成功刪除該筆資料:");
							
							}
					}
					home();
				}else if(dc == 2) {
					home();
							
				}else {
					System.out.println("輸入錯誤");
					delete();
				}	
			}
	
			
			//修改功能
			public static void update() {
				Scanner scanner = new Scanner(System.in);
				System.out.println(
					"===="+"選擇下一步"+"===="+"\n"
					+"===="+"1"+" 更新資料"+"\n"
					+"===="+"2"+" 返回Home"+"\r\n");
				//uc=update choose
				int uc =Integer.parseInt(scanner.nextLine());
				if( uc == 1) {
					ParkingDAO parkingDAO = new ParkingJDBCDAO();
					List<Parking> parkings = parkingDAO.listParking();
					System.out.println("請輸入您要更改的停車場");
					String parkName = scanner.nextLine();
					for (Parking park : parkings) {		
						if (park.getParkName().equals(parkName)) {	
							System.out.println("更改停車位為:");
							int ntotalSpace = Integer.parseInt(scanner.nextLine());
							park.setTotalSpace(ntotalSpace);
							parkingDAO.updateParking(park);	
							System.out.println("done");				
							}
						}
					home();
			    }else if(uc == 2) {
			    	home();
			    }else {
			    	System.out.println("輸入錯誤~");
			    	update();
			    }
			}
			
			public static void output() {
				ParkingDAO parkingDAO = new ParkingJDBCDAO();
				parkingDAO.outputCSV();
				
			}
}
