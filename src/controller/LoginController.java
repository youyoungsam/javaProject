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

	// ======================= �α��� â ======================== // 
	
	@FXML private TextField txtId;
	@FXML private PasswordField txtPassword;
	@FXML private Button btnLogin;
	@FXML private Button btnExit;
	@FXML private Button btnSignUp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		// 1. ȸ��, ������ �α��� ��ư �̺�Ʈ ó�� => ����â, ������ â���� �̵�
		btnLogin.setOnAction( e -> {	handlerBtnLoginAction(e); });

		// 2. ȸ������â �̺�Ʈ ó��
		btnSignUp.setOnAction( e -> handlerBtnSignUpAction(e));
		
		// 3. ��� ��ư �̺�Ʈ ó��
		btnExit.setOnAction( e -> {	handlerBtnExitAction(e); });
		
		// test
		// txtId.setText("mu3386");
		// txtPassword.setText("1234"); // �׽�Ʈ�Ҷ� �̷������� ���̵�, ��й�ȣ�� �Է��صѼ� ����.
	
	}

	// 1. ȸ��, ������ �α��� ��ư �̺�Ʈ ó�� => ����â, ������ â���� �̵�
	public void handlerBtnLoginAction(ActionEvent e) {
		
		// 1-1. ���̵�� ��й�ȣ�� �Է��� �ȵǾ����� ���â�� �ش�.
		if( txtId.getText().equals("") || txtPassword.getText().equals("")) {
			alertDisplay(1,"�α��� ����","���̵� �н����� ���Է�!","�ٽ� �Է����ּ���.");
			
		// 1-2. ���̵�� ��й�ȣ�� �ùٸ��� �ԷµǾ�����.	
		}else if(txtId.getText().equals("mu3386") && txtPassword.getText().equals("1234")) {
			
			// �α����� �Ϸ� �Ǿ����� ���� ���� â���� �̵��Ѵ�. 
			Parent mainView = null;
			Stage mainStage = null;
			
			try {
				
				mainView = FXMLLoader.load(getClass().getResource("/view/main_page_.fxml"));

				Scene scene = new Scene(mainView);
				
				mainStage = new Stage();

				scene.getStylesheets().add(getClass().getResource("font.css").toString());
				
				mainStage.setScene(scene);
				mainStage.setTitle("Main Page");
				
				// ���� ���������� �ݰ� ���ο� â�� ����.
				((Stage) btnLogin.getScene().getWindow()).close();
				
				mainStage.show();
				
			} catch (IOException e1) {
				alertDisplay(1,"����â ����","����â�� �ҷ��ü� �����ϴ�.\n","�ٽ� �Է����ּ���.\n"+"��� : "+e.toString());
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
				
				// ���� ���������� �ݰ� ���ο� â�� ����.
				((Stage) btnLogin.getScene().getWindow()).close();
				
				manageStage.show();
				
			} catch (IOException e1) {
				alertDisplay(1,"����â ����","����â�� �ҷ��ü� �����ϴ�.\n","�ٽ� �Է����ּ���.\n"+"��� : "+e.toString());
			}
			
		// 1-3. ���̵�� ��й�ȣ ����ġ ���â�� �ش�.
		}else {
			alertDisplay(1,"�α��� ����","���̵� �н����� ����ġ!","�ٽ� �Է����ּ���.");
		}
	}
	
	// 2. ȸ������â �̺�Ʈ ó��
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
	
	// 3. ��� ��ư �̺�Ʈ ó��
	public void handlerBtnExitAction(ActionEvent e) {
		
		((Stage) btnExit.getScene().getWindow()).close();
		
	}
	
	// ���â�� ó���ϴ� �Լ�
	public void alertDisplay(int type, String title, String headerText, String contentText) {
		
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
