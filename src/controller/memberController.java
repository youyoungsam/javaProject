package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.memberShipVO;

public class memberController implements Initializable{
	
	// ====================== ȸ������ â ===================== // 
	public Stage memberStage;
	
	@FXML private TextField txtId;
	@FXML private PasswordField txtPassword;
	@FXML private TextField txtName;
	@FXML private TextField txtEmail;
	
	// ObservableList<String> memberComboList = FXCollections.observableArrayList();
	
	@FXML private Button btnCheck;	
	@FXML private Button btnFinish;
	@FXML private Button btnCancel;
	
	ArrayList<memberShipVO> dbMember;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Connection con = DBUtil.getConnection();
		
		// 1. �ߺ�Ȯ��
		btnCheck.setOnAction(e ->{handleBtnCheckAction();});
		// 2. ȸ������ �Ϸ� ��ư
		btnFinish.setOnAction(e -> {handleBtnFinishAction();});
		// 3. ��� ��ư
		btnCancel.setOnAction(e -> {handelBtnCancelAction();});
	
	}

	// 1. �ߺ�Ȯ��
	public void handleBtnCheckAction() {
		
		memberShipVO checkMemberID = null;
		memberDAO member = new memberDAO();
		
		checkMemberID = new memberShipVO(txtId.getText().trim()); 
		member.checkMemberID(checkMemberID);
		
	}
	
	// 2. finish��ư
	public void handleBtnFinishAction() {
		
		try {
			
			memberShipVO member = new memberShipVO(
					
					txtId.getText(),
					txtPassword.getText(),
					txtName.getText(),
					txtEmail.getText());
			
			int count;
		
			count = memberDAO.insertMemberData(member);
			
			if(count != 0) {
				alertDisplay(5,"���� ����","���������� �����ϼ̽��ϴ�.", "���ϵ帳�ϴ�.\nȸ�����Կ� �����߽��ϴ�.");		
			}
			Stage stage1 = (Stage)btnFinish.getScene().getWindow();
			stage1.close();	
			
		} catch (ClassNotFoundException e) {
		
		}		
		
		//if(memberID.getText().equals("") || memberPassword.getText().equals(""))

	}	
	
	// ��� ��ư
	public void handelBtnCancelAction() {
		Stage stage = (Stage)btnCancel.getScene().getWindow();
		stage.close();		
	}

	// ���â�� ó���ϴ� �Լ�
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
		alert.setContentText(headerText+"\n"+contentText);
		alert.setResizable(false);
		alert.show();
		
	}
}
