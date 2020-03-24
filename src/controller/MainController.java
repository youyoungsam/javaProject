package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.memberShipVO;
import model.productionVO;

public class MainController implements Initializable{

	// ======================== ����� ���� â ======================== //

	private memberShipVO memVO;
	
	@FXML private Button btnEstimate;
	@FXML private Button btnChase;
	@FXML private Button btnEdit;
	@FXML private Button btnStuffChart;
	@FXML private Button btnReview;
	@FXML private Button btnProfEdit;
	@FXML private Button btnDelete;
	@FXML private Button btnSearch;
	@FXML private Button btnExit;
	@FXML private Button btnList;
	
	@FXML private TextField txtSearch;
	
	@FXML private TableView<productionVO> tableView;
	
	private ProductionDAO prodDAO;
	
	private ObservableList<productionVO> selectProd;
	ObservableList<productionVO> data2;
	
	ObservableList<memberShipVO> data = FXCollections.observableArrayList();
	
	@FXML private ImageView imageView;
	@FXML private ImageView mainImageView;
	
	private String selectFileName = ""; // �̹��� ���ϸ�
	private String localUrl = ""; // �̹��� ���� ���
	private Image localImage;

	private File selectedFile = null;

	// �̹��� ó��
	// �̹��� ������ ������ �Ű������� ���� ��ü ����
	private File dirSave = new File("C:/images");
	// �̹��� �ҷ��� ������ ������ ���� ��ü ����
	private File file = null;
	
	ObservableList<memberShipVO> superList = FXCollections.observableArrayList();
	private int selectedSuperIndex;
	private memberShipVO selectedSuper;
	ArrayList<memberShipVO> dbArrayListSuper;
	
	private String s;
	private String s1;
	private String s2;
	private String s3;
	private String s4;
	private String s5;
	private String s6;
	private String s7;
	private String s8;
	
	private memberDAO memDAO;
	
	public void initialize(URL location, ResourceBundle resources) {
		
		// 0. ���̺� �÷�
		tableColumeEvent();
		
		// 1.���� ¥�� â �̺�Ʈ // ���̺� �並 �������� �̺�Ʈ 
		btnEstimate.setOnAction( e -> handlerBtnEstimateAction(e));
		
		// 2. ��ǰ ��Ʈ �̺�Ʈ
		btnStuffChart.setOnAction( e -> handlerBtnStuffChartAction(e));
		
		// 3. ��ǰ �ǰ� �̺�Ʈ
		btnReview.setOnAction( e -> handlerBtnReviewAction(e));
		
		// 4. �̹��� ���� �̺�Ʈ 
		btnProfEdit.setOnAction( e -> handlerBtnProfEdit(e));
		
		// 5. �˻� �̺�Ʈ
		btnSearch.setOnAction( e -> handlerBtnSearch(e));
		
		// 6. ��ü ����Ʈ �θ���
		btnList.setOnAction( e -> handlerBtnList(e));
		
		// 7. ���̺�� �������� �̺�Ʈ
		tableView.setOnMouseClicked( e -> handlerSkinTableViewPressAction(e));
		
		// 8. ���� �̺�Ʈ 
		btnExit.setOnAction( e -> handlerBtnExitAction(e));

		
	}


	// 0. ���̺� �÷�
	public void tableColumeEvent() {
		
		data2 = FXCollections.observableArrayList();
		
		TableColumn colPname = new TableColumn("��ǰ��");
		colPname.setPrefWidth(150); // 77
		colPname.setStyle("-fx-alignment: CENTER;");
		colPname.setCellValueFactory(new PropertyValueFactory("productName"));
		
		TableColumn colItem = new TableColumn("ǰ��");
		colItem.setPrefWidth(60); // 108
		colItem.setStyle("-fx-alignment: CENTER;");
		colItem.setCellValueFactory(new PropertyValueFactory("item"));
		
		TableColumn colInfo = new TableColumn("����");
		colInfo.setPrefWidth(269); // 195
		colInfo.setStyle("-fx-alignment: CENTER;");
		colInfo.setCellValueFactory(new PropertyValueFactory("info"));
		
		TableColumn colFilename = new TableColumn("�̹���");
		colFilename.setPrefWidth(100); // 77
		colFilename.setStyle("-fx-alignment: CENTER;");
		colFilename.setCellValueFactory(new PropertyValueFactory("filename"));
		
		tableView.setItems(data2);
		tableView.getColumns().addAll(colPname, colItem, colInfo, colFilename);
		
		
	}
	
	// 1. ����¥�� â �̺�Ʈ
	public void handlerBtnEstimateAction(ActionEvent e) {
		
		try {
			
			Stage chartStage=new Stage();
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/etm_page.fxml"));
			
			Scene scene = new Scene(root);
			chartStage.setTitle("Etm Page");
			chartStage.setScene(scene);
			chartStage.show();
			
		} catch (IOException e1) {	
			
		}
	}

	// 2. ��ǰ ��Ʈ �̺�Ʈ
	public void handlerBtnStuffChartAction(ActionEvent e) {
		
		try {
			
			Stage chartStage = new Stage();
			
			Parent chartRoot = FXMLLoader.load(getClass().getResource("/view/chart_page.fxml"));
			
			Scene scene = new Scene(chartRoot);
			chartStage.setTitle("Chart Page");
			chartStage.setScene(scene);
			chartStage.show();
			
		} catch (IOException e1) {	
			
		}
		
	}

	// 3. ��ǰ �ǰ� �̺�Ʈ
	public void handlerBtnReviewAction(ActionEvent e) {
		
		try {
			
			Stage chartStage=new Stage();
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/review.fxml"));
			
			Scene scene = new Scene(root);
			chartStage.setTitle("Review Page");
			chartStage.setScene(scene);
			chartStage.show();
			
		} catch (IOException e1) {	
			
		}
		
	}
	
	// 4. �̹��� ���� �̺�Ʈ
	public void handlerBtnProfEdit(ActionEvent e) {

		// 1. ���� ������ �θ��� ( ��������, �� , ��Ʈ �����Ǿ����� )
	       FileChooser fileChooser = new FileChooser();
	       
	       // 2. ���� ������ Ȯ���� ����� �ο��Ҽ��ִ�. 
	       fileChooser.getExtensionFilters().addAll( 
	             
	    		 new ExtensionFilter("�̹��� ����", "*.png"),
	             new ExtensionFilter("�̹��� ����", "*.jpg"),                     
	             new ExtensionFilter("�̹��� ����", "*.gif"),	                
	             new ExtensionFilter("��� ����", "*.*")
	             
	             );
	       
		// 2. ���� ���������� �Ҵ��ؾߵȴ�. ( ���� ���� �������� �Ҵ� )
		try {
	
			selectedFile = fileChooser.showOpenDialog(btnProfEdit.getScene().getWindow());
			if (selectedFile != null) {
				// �̹��� ���� ���
				localUrl = selectedFile.toURI().toURL().toString();
			}
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		// Image edit
		localImage = new Image(localUrl, false);
		mainImageView.setImage(localImage);
	
		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
		}
	
	}
	
	// 5. �˻� �̺�Ʈ
	public void handlerBtnSearch(ActionEvent e) {
		
		try {
			
			ArrayList<productionVO> list = new ArrayList<productionVO>();
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.getProdCheck(txtSearch.getText());
			
			if(list == null) {
				throw new Exception("�˻� ����");
			}
			
			data2.removeAll(data2);
			
			for ( productionVO svo : list ) {
				
				data2.add(svo);
			}
		
		} catch (Exception e1) {
			alertDisplay(1,"�˻� ���","�̸� �˻� ����", e1.toString());
		}
		
		txtSearch.clear();
		
	}
	
	// 6. ��ü ����Ʈ �ҷ�����
	public void handlerBtnList(ActionEvent e) {
		
		try {
			
			ArrayList<productionVO> list = new ArrayList<productionVO>();
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.getProdCheck(txtSearch.getText());
			
			if(list == null) {
				throw new Exception("��ü����Ʈ ����");
			}
			
			data2.removeAll(data2);
			
			for ( productionVO svo : list ) {
				
				data2.add(svo);
			}
		
		} catch (Exception e1) {
			alertDisplay(1,"��ü����Ʈ ����","����Ʈ �ҷ����� ����", e1.toString());
		}
		
	}
	
	// 8. ���̺�� �������� �̺�Ʈ
	public void handlerSkinTableViewPressAction(MouseEvent e) {
			
		// �ѹ� �������� 
		if (e.getClickCount() != 2) {

			String imageFile1;
			
			imageFile1 = ProductionDAO
					.getSelectSkin1(tableView.getSelectionModel().getSelectedItem().getProductName());
			
			localImage = new Image("/comImages/" + imageFile1, false);
			imageView.setImage(localImage);

	      // �ι� �������� �� ���� 
			
	      } else {

	         Parent mainView = null;
	         Stage mainStage = null;

	         try {

	            mainView = FXMLLoader.load(getClass().getResource("/view/more_Info.fxml"));
	            Scene scene = new Scene(mainView);
	            mainStage = new Stage();

	            mainStage.setTitle("More Infomation Page");
	            mainStage.setScene(scene); //

	            mainStage.setResizable(false);

	            mainStage.show();
	         } catch (Exception e1) {
	       	  memberController.alertDisplay(3, "����", "ȭ�� ��ȯ ����", "�ٽ� Ȯ�����ּ���.");

	         }
	         
	         
	         ImageView infoImageView1 = (ImageView) mainView.lookup("#infoImageView1");
	         ImageView infoImageView2 = (ImageView) mainView.lookup("#infoImageView2");
	         ImageView infoImageView3 = (ImageView) mainView.lookup("#infoImageView3");
	         Button btnExit = (Button) mainView.lookup("#btnExit");
			
	    
	         String imageFile1 = ProductionDAO
						.getSelectSkin1(tableView.getSelectionModel().getSelectedItem().getProductName());
	         String imageFile2 = ProductionDAO
	               .getSelectSkin2(tableView.getSelectionModel().getSelectedItem().getProductName());
	         String imageFile3 = ProductionDAO
		               .getSelectSkin3(tableView.getSelectionModel().getSelectedItem().getProductName());
	        
	         
	         localImage = new Image("/comImages/" + imageFile1, false);
	         infoImageView1.setImage(localImage);
	         localImage = new Image("/comImages/" + imageFile2, false);
	         infoImageView2.setImage(localImage);
	         localImage = new Image("/comImages/" + imageFile3, false);
	         infoImageView3.setImage(localImage);
			
	         btnExit.setOnAction(e1 -> {
	            ((Stage) btnExit.getScene().getWindow()).close();
	         });
			
	      }

	   }

	// 8. ���� �̺�Ʈ 
	public void handlerBtnExitAction(ActionEvent e) {
		
		Platform.exit();
		
	}

	
	
	// ====================== �Լ� ��� ===================== //
	
	// ���â ó�� �Լ�
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

	// �� ó���� ���̺� �信 ������ ���̽� ���� �о ���̺� �信 �����´�.
	public void EtmtotalList() {
		
		ArrayList<productionVO> list = null;
		productionVO productionVO = null;
		
		ProductionDAO productionDAO = new ProductionDAO();
		list = productionDAO.selectProductionList(s1);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			productionVO = list.get(i);
			data2.add(productionVO); // ��� ��� �ִ� ������ ���̺�信 ����Ǿ� ���� �� ��.
		}
		
	}
	
	// �̹��� ����
	public String imageSave(File file1) {
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// �̹��� ���ϸ� ����
			fileName = "student" + System.currentTimeMillis() + "_" + file1.getName();
			bis = new BufferedInputStream(new FileInputStream(file1));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + fileName));

			// ������ �̹��� ���� InputStream�� �������� �̸����� ���� -1
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return fileName;
	}
}
