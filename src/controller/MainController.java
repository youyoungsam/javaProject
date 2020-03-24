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

	// ======================== 사용자 메인 창 ======================== //

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
	
	private String selectFileName = ""; // 이미지 파일명
	private String localUrl = ""; // 이미지 파일 경로
	private Image localImage;

	private File selectedFile = null;

	// 이미지 처리
	// 이미지 저장할 폴더를 매개변수로 파일 객체 생성
	private File dirSave = new File("C:/images");
	// 이미지 불러올 파일을 저장할 파일 객체 선언
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
		
		// 0. 테이블 컬럼
		tableColumeEvent();
		
		// 1.견적 짜기 창 이벤트 // 테이블 뷰를 눌렀을때 이벤트 
		btnEstimate.setOnAction( e -> handlerBtnEstimateAction(e));
		
		// 2. 제품 차트 이벤트
		btnStuffChart.setOnAction( e -> handlerBtnStuffChartAction(e));
		
		// 3. 제품 의견 이벤트
		btnReview.setOnAction( e -> handlerBtnReviewAction(e));
		
		// 4. 이미지 수정 이벤트 
		btnProfEdit.setOnAction( e -> handlerBtnProfEdit(e));
		
		// 5. 검색 이벤트
		btnSearch.setOnAction( e -> handlerBtnSearch(e));
		
		// 6. 전체 리스트 부르기
		btnList.setOnAction( e -> handlerBtnList(e));
		
		// 7. 테이블뷰 눌렀을때 이벤트
		tableView.setOnMouseClicked( e -> handlerSkinTableViewPressAction(e));
		
		// 8. 종료 이벤트 
		btnExit.setOnAction( e -> handlerBtnExitAction(e));

		
	}


	// 0. 테이블 컬럼
	public void tableColumeEvent() {
		
		data2 = FXCollections.observableArrayList();
		
		TableColumn colPname = new TableColumn("제품명");
		colPname.setPrefWidth(150); // 77
		colPname.setStyle("-fx-alignment: CENTER;");
		colPname.setCellValueFactory(new PropertyValueFactory("productName"));
		
		TableColumn colItem = new TableColumn("품목");
		colItem.setPrefWidth(60); // 108
		colItem.setStyle("-fx-alignment: CENTER;");
		colItem.setCellValueFactory(new PropertyValueFactory("item"));
		
		TableColumn colInfo = new TableColumn("정보");
		colInfo.setPrefWidth(269); // 195
		colInfo.setStyle("-fx-alignment: CENTER;");
		colInfo.setCellValueFactory(new PropertyValueFactory("info"));
		
		TableColumn colFilename = new TableColumn("이미지");
		colFilename.setPrefWidth(100); // 77
		colFilename.setStyle("-fx-alignment: CENTER;");
		colFilename.setCellValueFactory(new PropertyValueFactory("filename"));
		
		tableView.setItems(data2);
		tableView.getColumns().addAll(colPname, colItem, colInfo, colFilename);
		
		
	}
	
	// 1. 견적짜기 창 이벤트
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

	// 2. 제품 차트 이벤트
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

	// 3. 제품 의견 이벤트
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
	
	// 4. 이미지 수정 이벤트
	public void handlerBtnProfEdit(ActionEvent e) {

		// 1. 파일 추져를 부른다 ( 스테이지, 씬 , 루트 구성되어있음 )
	       FileChooser fileChooser = new FileChooser();
	       
	       // 2. 파일 추져에 확장자 기능을 부여할수있다. 
	       fileChooser.getExtensionFilters().addAll( 
	             
	    		 new ExtensionFilter("이미지 파일", "*.png"),
	             new ExtensionFilter("이미지 파일", "*.jpg"),                     
	             new ExtensionFilter("이미지 파일", "*.gif"),	                
	             new ExtensionFilter("모든 파일", "*.*")
	             
	             );
	       
		// 2. 주인 스테이지를 할당해야된다. ( 파일 추져 스테이지 할당 )
		try {
	
			selectedFile = fileChooser.showOpenDialog(btnProfEdit.getScene().getWindow());
			if (selectedFile != null) {
				// 이미지 파일 경로
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
	
	// 5. 검색 이벤트
	public void handlerBtnSearch(ActionEvent e) {
		
		try {
			
			ArrayList<productionVO> list = new ArrayList<productionVO>();
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.getProdCheck(txtSearch.getText());
			
			if(list == null) {
				throw new Exception("검색 오류");
			}
			
			data2.removeAll(data2);
			
			for ( productionVO svo : list ) {
				
				data2.add(svo);
			}
		
		} catch (Exception e1) {
			alertDisplay(1,"검색 결과","이름 검색 오류", e1.toString());
		}
		
		txtSearch.clear();
		
	}
	
	// 6. 전체 리스트 불러오기
	public void handlerBtnList(ActionEvent e) {
		
		try {
			
			ArrayList<productionVO> list = new ArrayList<productionVO>();
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.getProdCheck(txtSearch.getText());
			
			if(list == null) {
				throw new Exception("전체리스트 오류");
			}
			
			data2.removeAll(data2);
			
			for ( productionVO svo : list ) {
				
				data2.add(svo);
			}
		
		} catch (Exception e1) {
			alertDisplay(1,"전체리스트 오류","리스트 불러오기 오류", e1.toString());
		}
		
	}
	
	// 8. 테이블뷰 눌렀을때 이벤트
	public void handlerSkinTableViewPressAction(MouseEvent e) {
			
		// 한번 눌렀을때 
		if (e.getClickCount() != 2) {

			String imageFile1;
			
			imageFile1 = ProductionDAO
					.getSelectSkin1(tableView.getSelectionModel().getSelectedItem().getProductName());
			
			localImage = new Image("/comImages/" + imageFile1, false);
			imageView.setImage(localImage);

	      // 두번 눌렀을때 상세 정보 
			
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
	       	  memberController.alertDisplay(3, "실패", "화면 전환 실패", "다시 확인해주세요.");

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

	// 8. 종료 이벤트 
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

	// 맨 처음의 테이블 뷰에 데이터 베이스 값을 읽어서 테이블 뷰에 가져온다.
	public void EtmtotalList() {
		
		ArrayList<productionVO> list = null;
		productionVO productionVO = null;
		
		ProductionDAO productionDAO = new ProductionDAO();
		list = productionDAO.selectProductionList(s1);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "경고", "DB 가져오기 오류", "점검 필요");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			productionVO = list.get(i);
			data2.add(productionVO); // 모든 디비에 있는 내용이 테이블뷰에 연결되어 값이 다 들어감.
		}
		
	}
	
	// 이미지 저장
	public String imageSave(File file1) {
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// 이미지 파일명 생성
			fileName = "student" + System.currentTimeMillis() + "_" + file1.getName();
			bis = new BufferedInputStream(new FileInputStream(file1));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + fileName));

			// 선택한 이미지 파일 InputStream의 마지막에 이르렀을 경우는 -1
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
