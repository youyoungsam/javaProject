package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// ����̹��� ����, ���̵�� �н����� �����ͺ��̽� mysql �����ͺ��̽� ���� ��û 
		//-> db ������ ��ġ�� �ִ� ��ü��������
public class DBUtil {

	// 1. ����̹��� ����
	private static String driver = "com.mysql.jdbc.Driver"; // ����̹� �̸� 
	
	// 2. ������ ���̽� url ����
	private static String url = "jdbc:mysql://localhost/estimateDB"; // mysql�� �����Ҽ��ִ� ��������. 
											// ����� �����ָ� ��뿡�� �����ִ� 
	
	// 2. ����̹��� �����ϰ� �����ͺ��̽��� �����ϴ� �Լ� 
	public static Connection getConnection() {
		
		Connection con = null;
		try {
		// 2-1. ����̹��� ����. �޸𸮿� ���� ��. �ڹٿ� ��ǻ�Ϳ� ��ġ�� mysql������ ��������.
			Class.forName(driver);
		
		// 2-2. �����ͺ��̽� ����, ���� ����� db ����
			con = DriverManager.getConnection(url, "root", "123456");
		} catch (Exception e) {
		
		}
		// mysql�� ���� �Ҽ��ִ� ��ü ���� ����. 
		
		return con; // �� �ڿ��� �ݳ������ ��� �Ҽ�����.  
	}
	
	// ��� Ŭ������ �� �����ִ�. �������� ����. 
	public static void alertDisplay(int type, String title, String headerText, String contentText) {
		
		Alert alert = null;  // ���� 1. Alert �̰��� ���� ������ �α��� ����� ���̾�α�â�� ���� ���������� �۾��� ������.
		
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
