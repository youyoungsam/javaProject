package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	// ======================= 로그인 창 ======================== // 
	
	@FXML private TextField txtId;
	@FXML private PasswordField txtPassword;
	@FXML private Button btnLogin;
	@FXML private Button btnExit;
	@FXML private Button btnSignUp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		// 1. 회원, 관리자 로그인 버튼 이벤트 처리 => 메인창, 관리자 창으로 이동
		btnLogin.setOnAction( e -> {	handlerBtnLoginAction(e); });

		// 2. 회원가입창 이벤트 처리
		btnSignUp.setOnAction( e -> handlerBtnSignUpAction(e));
		
		// 3. 취소 버튼 이벤트 처리
		btnExit.setOnAction( e -> {	handlerBtnExitAction(e); });
		
		// test
		// txtId.setText("mu3386");
		// txtPassword.setText("1234"); // 테스트할때 이런식으로 아이디, 비밀번호를 입력해둘수 있음.
	
	}

	// 1. 회원, 관리자 로그인 버튼 이벤트 처리 => 메인창, 관리자 창으로 이동
	public void handlerBtnLoginAction(ActionEvent e) {
		
		// 1-1. 아이디와 비밀번호가 입력이 안되었을때 경고창을 준다.
		if( txtId.getText().equals("") || txtPassword.getText().equals("")) {
			alertDisplay(1,"로그인 실패","아이디 패스워드 미입력!","다시 입력해주세요.");
			
		// 1-2. 아이디와 비밀번호가 올바르게 입력되었을때.	
		}else if(txtId.getText().equals("mu3386") && txtPassword.getText().equals("1234")) {
			
			// 로그인이 완료 되었으면 다음 메인 창으로 이동한다. 
			Parent mainView = null;
			Stage mainStage = null;
			
			try {
				
				mainView = FXMLLoader.load(getClass().getResource("/view/main_page_.fxml"));

				Scene scene = new Scene(mainView);
				
				mainStage = new Stage();

				scene.getStylesheets().add(getClass().getResource("font.css").toString());
				
				mainStage.setScene(scene);
				mainStage.setTitle("Main Page");
				
				// 현재 스테이지를 닫고 새로운 창을 연다.
				((Stage) btnLogin.getScene().getWindow()).close();
				
				mainStage.show();
				
			} catch (IOException e1) {
				alertDisplay(1,"메인창 실패","메인창을 불러올수 없습니다.\n","다시 입력해주세요.\n"+"경로 : "+e.toString());
			}
		}else if (txtId.getText().equals("master") && txtPassword.getText().equals("1234")) {
			
			Parent manageView = null;
			Stage manageStage = null;
			
			try {

				manageView = FXMLLoader.load(getClass().getResource("/view/manage.fxml"));

				Scene scene = new Scene(manageView);
				
				manageStage = new Stage();
				
				scene.getStylesheets().add(getClass().getResource("font.css").toString());
				
				manageStage.setScene(scene);
				manageStage.setTitle("Manage Page");
				
				// 현재 스테이지를 닫고 새로운 창을 연다.
				((Stage) btnLogin.getScene().getWindow()).close();
				
				manageStage.show();
				
			} catch (IOException e1) {
				alertDisplay(1,"메인창 실패","메인창을 불러올수 없습니다.\n","다시 입력해주세요.\n"+"경로 : "+e.toString());
			}
			
		// 1-3. 아이디와 비밀번호 불일치 경고창을 준다.
		}else {
			alertDisplay(1,"로그인 실패","아이디 패스워드 불일치!","다시 입력해주세요.");
		}
	}
	
	// 2. 회원가입창 이벤트 처리
	public void handlerBtnSignUpAction(ActionEvent e) {
		
		try {
			
			Stage memberStage=new Stage();
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/signup.fxml"));
			
			Scene scene = new Scene(root);
			memberStage.setTitle("MemberShip");
			memberStage.setScene(scene);
			memberStage.show();
			
		} catch (IOException e1) {	
			
		}
		
	}
	
	// 3. 취소 버튼 이벤트 처리
	public void handlerBtnExitAction(ActionEvent e) {
		
		((Stage) btnExit.getScene().getWindow()).close();
		
	}
	
	// 경고창을 처리하는 함수
	public void alertDisplay(int type, String title, String headerText, String contentText) {
		
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
		alert.setContentText(headerText+"\n"+contentText);
		alert.setResizable(false);
		alert.show();
		
	}
}
