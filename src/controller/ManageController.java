package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageController implements Initializable{

	// ======================= 관리자 메인 창 ====================== //
	
	@FXML private Button btnReviewManage;
	@FXML private Button btnMemManage;
	@FXML private Button btnProductM;
	@FXML private Button btnExit;
	
	public void initialize(URL location, ResourceBundle resources) {
		
		// 1. 회원관리 이벤트
		btnMemManage.setOnAction(e -> {handleBtnSuperAction();});
		
		// 2. 제품 의견 관리
		btnReviewManage.setOnAction( e -> handlerBtnReviewManage(e));
		
		// 3. 상품 관리
		btnProductM.setOnAction( e -> handlerBtnProductM(e));
		
		// 4. 종료 이벤트
		btnExit.setOnAction( e -> handlerBtnExitAction(e));
		
		
	}

	// 1. 회원 관리 이벤트
	public void handleBtnSuperAction() {
		
		try {
			
			Stage superStage = new Stage();
			
			Parent root;
			
			root = FXMLLoader.load(getClass().getResource("../view/superWindow.fxml"));
			
			Scene scene = new Scene(root);
			superStage.setTitle("Administrator");
			superStage.setScene(scene);
			superStage.show();
			
		} catch(IOException e) {}
		
	}
	
	// 2. 제품 의견 관리
	public void handlerBtnReviewManage(ActionEvent e) {
		
		try {
			
			Stage chartStage=new Stage();
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/manageReview.fxml"));
			
			Scene scene = new Scene(root);
			chartStage.setTitle("Review Page");
			chartStage.setScene(scene);
			chartStage.show();
			
		} catch (IOException e1) {	
			
		}
		
	}
	
	// 3. 상품 관리 이벤트
	public void handlerBtnProductM(ActionEvent e) {
		
		try {
			
			Stage newProdStage = new Stage();
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/product_manage.fxml"));
			
			Scene scene = new Scene(root);
			newProdStage.setTitle("New Production Page");
			newProdStage.setScene(scene);
			newProdStage.show();
			
		} catch (IOException e1) {	
			
		}
	}
	
	// 4. 종료 이벤트
	public void handlerBtnExitAction(ActionEvent e) {
		
		Platform.exit();
		
	}
	
	// ====================== 함수 기능 ===================== //

	// 경고창 처리 함수
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
