package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// 드라이버를 적재, 아이디와 패스워드 데이터베이스 mysql 데이터베이스 연결 요청 
		//-> db 연결점 위치를 주는 객체참조변수
public class DBUtil {

	// 1. 드라이버명 저장
	private static String driver = "com.mysql.jdbc.Driver"; // 드라이버 이름 
	
	// 2. 데이터 베이스 url 저장
	private static String url = "jdbc:mysql://localhost/estimateDB"; // mysql로 접속할수있는 프로토콜. 
											// 상대방것 적어주면 상대에게 들어갈수있다 
	
	// 2. 드라이버를 적재하고 데이터베이스를 연결하는 함수 
	public static Connection getConnection() {
		
		Connection con = null;
		try {
		// 2-1. 드라이버를 적재. 메모리에 적재 됨. 자바와 컴퓨터에 설치된 mysql서버를 연결해줌.
			Class.forName(driver);
		
		// 2-2. 데이터베이스 연결, 원격 제어로 db 접근
			con = DriverManager.getConnection(url, "root", "123456");
		} catch (Exception e) {
		
		}
		// mysql로 접근 할수있는 객체 참조 변수. 
		
		return con; // 꼭 자원을 반납해줘야 사용 할수있음.  
	}
	
	// 모든 클래스가 다 쓸수있다. 정적으로 만듦. 
	public static void alertDisplay(int type, String title, String headerText, String contentText) {
		
		Alert alert = null;  // 로직 1. Alert 이것을 쓰지 않으면 로그인 사용자 다이얼로그창을 따로 만들정도로 작업이 많아짐.
		
		switch(type) {
			case 1: alert = new Alert(AlertType.WARNING); break;
			case 2: alert = new Alert(AlertType.CONFIRMATION); break;
			case 3: alert = new Alert(AlertType.ERROR); break;
			case 4: alert = new Alert(AlertType.NONE); break;
			case 5: alert = new Alert(AlertType.INFORMATION); break;
		}
		
		alert.setTitle(title);					
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.setResizable(false);
		alert.showAndWait();
		
	}
}
