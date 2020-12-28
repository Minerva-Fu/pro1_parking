package pro1;

import java.util.Scanner;

public class Start extends FunctionUse {
	
// cd D:\InfoClass\Project\project1\pro1>java -cp bin;lib/* pro1.Start	
	
	public static void main(String[] args) {
		prt();
		Scanner scanner = new Scanner(System.in);
		String user,pswd; 
		int i;
		for( i=3; i>0; i++) {
			System.out.println("請輸入帳號:");
			user=scanner.nextLine();
			System.out.println("請輸入密碼:");
			pswd=scanner.nextLine();
			if(user.contentEquals("mary") && pswd.contains("123")) {
			System.out.println("WELCOME PARKING TABLE");
			home();
			break;
			}else if (i>1) {
				System.out.println(
						" TAT 使用者名稱或密碼輸入錯誤"+"\n"
						+"還有"+(i-1)+"次機會");
				prt();
			}else {
				System.out.println("");
			}
		}
	}
	
	private static void prt() {
		System.out.println(
			"======"+"使用者登入"+"======"
		);
	}
}
