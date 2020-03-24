package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.memberShipVO;

public class memManageController implements Initializable {
	
	// ================== ������ â => ȸ������ ==================== // 
	
	private Stage superStage;
	
	@FXML private Button btnExit;
	
	@FXML private TableView<memberShipVO> superTableView;
	ObservableList<memberShipVO> superList = FXCollections.observableArrayList();

	ArrayList<memberShipVO> dbArrayListSuper;
	
	private memberShipVO selectedSuper;
	private int selectedSuperIndex;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Connection con = DBUtil.getConnection();
		
		//���̺�� �⺻����
		settingTableViewMember();
		
		//���̺�� �ι� Ŭ���ϸ� ȸ������â ����
		superTableView.setOnMouseClicked(e ->{handleSuperTableAction(e);});
		
		// ��� ��ư �̺�Ʈ ó��
		btnExit.setOnAction( e -> handlerBtnExitAction(e));
	}
	
	//���̺�� �⺻����
	public void settingTableViewMember() {
		
		TableColumn tcID = superTableView.getColumns().get(0);
		tcID.setCellValueFactory(new PropertyValueFactory<>("memberID"));
		tcID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcPass = superTableView.getColumns().get(1);
		tcPass.setCellValueFactory(new PropertyValueFactory<>("memberPassWord"));
		tcPass.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcName = superTableView.getColumns().get(2);
		tcName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
		tcName.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcEmail = superTableView.getColumns().get(3);
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("memberEmail"));
		tcEmail.setStyle("-fx-alignment : CENTER;");
		
		superTableView.setItems(superList);
		
		try {
			dbArrayListSuper = memberDAO.getMemberTotalData();
		} catch (ClassNotFoundException e) {

		}
		for(memberShipVO member : dbArrayListSuper) {
			superList.add(member);
		}
	}
	
	//���̺�� �ι� Ŭ���ϸ� ȸ������â ����
	public void handleSuperTableAction(MouseEvent e) {
		
		selectedSuper = superTableView.getSelectionModel().getSelectedItem();
		selectedSuperIndex = superTableView.getSelectionModel().getSelectedIndex();
		
		if(e.getClickCount() == 2) {
			
			try {
				Stage superInfoStage = new Stage();
				superInfoStage.initModality(Modality.WINDOW_MODAL);
				superInfoStage.initOwner(superStage);
				
				superInfoStage.setTitle(selectedSuper.getMemberName() + "���� ȸ������â");
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/superInfo.fxml"));
				Parent root = loader.load();
				
				TextField superInfoEmail = (TextField)root.lookup("#superInfoEmail");
				TextField superInfoName = (TextField)root.lookup("#superInfoName");
				PasswordField superInfoPass = (PasswordField)root.lookup("#superInfoPass");
				TextField superInfoID = (TextField)root.lookup("#superInfoID");
				Button superInfoBtnModi = (Button)root.lookup("#superInfoBtnModi");
				Button superInfoBtnDelete = (Button)root.lookup("#superInfoBtnDelete");
				Button superInfoBtnCancel = (Button)root.lookup("#superInfoBtnCancel");				
				
				
				superInfoEmail.setText(selectedSuper.getMemberEmail());
				superInfoName.setDisable(true);
				superInfoName.setText(selectedSuper.getMemberName());
				superInfoID.setDisable(true);
				superInfoID.setText(selectedSuper.getMemberID());	
				superInfoPass.setText(selectedSuper.getMemberPassWord());
				
				// �����ϱ� 
				superInfoBtnModi.setOnAction(e2 ->{
					superList.remove(selectedSuperIndex);
					
					memberShipVO member = new memberShipVO(superInfoID.getText(),
							superInfoPass.getText(),
							superInfoName.getText(),
							superInfoEmail.getText());
							
					superList.add(member);
					superInfoStage.close();
					int count = memberDAO.updateMemberData(member, selectedSuper);
					
					if(count !=0) {
						//superList.remove(selectedSuperIndex);
						//superList.add(selectedSuperIndex, selectedSuper);
						dbArrayListSuper.set(selectedSuperIndex, member);
						alertDisplay(5,"����","���� ���� �Ϸ�","���������� �Ϸ��Ͽ����ϴ�.");
						superInfoStage.close();
					}else {
						return ;
					}
				});
				
				superInfoBtnDelete.setOnAction(e3 ->{
					int count;
					try {
						count = memberDAO.deleteMemberData(selectedSuper.getMemberID());
						if(count != 0) {
							superList.remove(selectedSuperIndex);
							dbArrayListSuper.remove(selectedSuperIndex);
							memManageController.alertDisplay(5,"����","���� �Ϸ�","������ �Ϸ��Ͽ����ϴ�.");
							superInfoStage.close();
						}else {
							return ;
						}
					} catch (ClassNotFoundException e1) {

					}
				});
				
				superInfoBtnCancel.setOnAction(e4 -> {superInfoStage.close();});
				
				Scene scene = new Scene(root);
				superInfoStage.setScene(scene);
				superInfoStage.show();
			}catch(Exception e1) {}
			
		}
		
	}

	// ��� ��ư �̺�Ʈ ó��
	public void handlerBtnExitAction(ActionEvent e) {
		
		((Stage) btnExit.getScene().getWindow()).close();
		
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
