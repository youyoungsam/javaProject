package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.memberShipVO;
import model.productionVO;
import model.reviewVO;

public class ProductionController implements Initializable {

	// =============== 관리자 창 -> 상품 관리창 =============== //
	
	@FXML private TextField txtSearchField;
	
	@FXML private Button btnSearch;
	@FXML private Button btnNewProduction;
	@FXML private Button btnEdit;
	@FXML private Button btnDelete;
	@FXML private Button btnExit;
	@FXML private Button btnList;
	
	@FXML private TableView<productionVO> tableView;
	ObservableList<productionVO> data =  FXCollections.observableArrayList();
	
	private ObservableList<productionVO> selectProduction;
	
	private productionVO selectedProd;
	private int selectedIndex;
	
	private ProductionDAO productionDAO;
	
	private File dirSave = new File("C:/images");
	private String selectFileName = "";
	private File selectedFile = null;
	private String localUrl = ""; // 이미지 파일 경로
	private Image localImage;
	
	private boolean editDelete = false;
	
	ArrayList<productionVO> dbArrayListSuper;
	
	private String s;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		selectProduction = FXCollections.observableArrayList();
		
		// 0. 테이블 컬럼 이벤트
		tableColumeEvent();
		
		// 1. 리스트 출력 이벤트
		btnList.setOnAction( e -> handlerBtnList(e));
		
		// 2. 새 제품 등록 이벤트
		btnNewProduction.setOnAction( e -> handlerBtnNewProduction(e));
		
		// 3. 수정 이벤트
		btnEdit.setOnAction( e -> handlerBtnEditAction(e));
		
		// 4. 삭제 이벤트
		btnDelete.setOnAction( e -> handlerBtnDeleteAction(e));
		
		// 5. 검색 이벤트
		btnSearch.setOnAction( e -> handlerBtnSearch(e));
		
		// 6. 종료 이벤트
		btnExit.setOnAction( e -> handlerBtnExitAction(e));
		

	}

	// 0. 테이블 컬럼 이벤트
	public void tableColumeEvent() {
		
		TableColumn colPname = new TableColumn("제품명");
		colPname.setPrefWidth(107); // 77
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
		colFilename.setPrefWidth(80); // 77
		colFilename.setStyle("-fx-alignment: CENTER;");
		colFilename.setCellValueFactory(new PropertyValueFactory("filename"));
		
		TableColumn colNo = new TableColumn("번호");
		colNo.setPrefWidth(1); // 77
		colNo.setStyle("-fx-alignment: CENTER;");
		colNo.setCellValueFactory(new PropertyValueFactory("no"));
		
		tableView.setItems(data);
		tableView.getColumns().addAll(colPname, colItem, colInfo, colFilename, colNo);
	}
	
	// 1. 리스트 출력 이벤트
	public void handlerBtnList(ActionEvent e) {
	
		try {
			
			ArrayList<productionVO> list = new ArrayList<productionVO>();
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.getProdCheck(txtSearchField.getText());
			
			if(list == null) {
				throw new Exception("전체리스트 오류");
			}
			
			data.removeAll(data);
			
			for ( productionVO svo : list ) {
				
				data.add(svo);
			}
		
		} catch (Exception e1) {
			alertDisplay(1,"전체리스트 오류","리스트 불러오기 오류", e1.toString());
		}
	
	}
	
	// 2. 새 제품 등록 이벤트
	public void handlerBtnNewProduction(ActionEvent e) {
		
		Parent BtnNewProdroot;
		
		try {
			
			BtnNewProdroot = FXMLLoader.load(getClass().getResource("/view/manage_new_product.fxml"));
        
        Stage btnNewProdStage = new Stage();
        btnNewProdStage.initModality(Modality.WINDOW_MODAL);
        btnNewProdStage.initOwner(btnNewProduction.getScene().getWindow());
        btnNewProdStage.setTitle("New Production Page");
        btnNewProdStage.setResizable(false);
        
        ImageView imageViewProduct =(ImageView) BtnNewProdroot.lookup("#imageViewProduct");
        TextField textFieldProductName= (TextField) BtnNewProdroot.lookup("#textFieldProductName");
        TextField textFieldItemName= (TextField) BtnNewProdroot.lookup("#textFieldItemName");
        TextArea textFieldInformation= (TextArea) BtnNewProdroot.lookup("#textAreaInformation");
        Button btnImageLoad = (Button) BtnNewProdroot.lookup("#btnImageLoad");
        Button buttonSave = (Button) BtnNewProdroot.lookup("#buttonSave");
        Button buttonExit = (Button) BtnNewProdroot.lookup("#buttonExit");
       
        // 새 제품 등록창에서 이미지등록버튼
        btnImageLoad.setOnAction(e2 -> {
				// 1. 파일 추져를 부른다 ( 스테이지, 씬 , 루트 구성되어있음 )
				FileChooser fileChooser = new FileChooser();

				// 2. 파일 추져에 확장자 기능을 부여할수있다.
				fileChooser.getExtensionFilters().addAll(

						new ExtensionFilter("이미지 파일", "*.png"), 
						new ExtensionFilter("이미지 파일", "*.jpg"),
						new ExtensionFilter("텍스트 파일", "*.txt"),  
						new ExtensionFilter("모든 파일", "*.*")

				);
				try {
					// 2. 주인 스테이지를 할당해야된다. ( 파일 추져 스테이지 할당 )
					selectedFile = fileChooser.showOpenDialog(btnNewProdStage);
					// 파일 찾아서 열기 누르면 이쪽으로 들어옴.
					// => 인풋스트림 아웃풋스트림 리더 라이터 할수있음.

					// 파일 정보 찍기 // 경로를 가져옴

					if (selectedFile != null) {
						localUrl = selectedFile.toURI().toURL().toString();
					}
					
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				localImage = new Image(localUrl, false);
				imageViewProduct.setImage(localImage);

				if (selectedFile != null) {
					selectFileName = selectedFile.getName();
				}

			});
			
	        //새재품등록창에서 저장버튼
	        buttonSave.setOnAction(e3->{
	           
	        	try { // 오류가 발생될때를 대비
	    			
	    			File dirMake = new File(dirSave.getAbsolutePath());
	                
	    			// 이미지 저장 폴더 생성
	    			if (!dirMake.exists()) {
	    				dirMake.mkdir();
	    			}

	    			// 이미지 파일 저장
	    			String fileName = imageSave(selectedFile);
	    			
    				productionVO svo = new productionVO(textFieldProductName.getText(), textFieldItemName.getText()
    						, textFieldInformation.getText(), fileName); // 어차피 여기에 저장하면 
	    				

    				productionDAO = new ProductionDAO(); // 객체를 부른다.
	    					
	    					int count = productionDAO.insertProductionData(svo); 
	    								// 데이터베이스 테이블에 레코드 값을 넣어버린 순간
	    					
	    					if(count != 0) {
	    						data.removeAll(data);
	    						EtmtotalList();	
	    					}else {
	    						throw new Exception("데이터베이스 등록 실패");
	    					}
	    				
	    			
	    				alertDisplay(1,"등록 성공","테이블 등록 완료","테이블에 정보를 등록합니다.");
	    		
	    		} catch (Exception e2) {
	    			alertDisplay(1,"등록 실패","등록 오류", e2.toString());
	    			return;
	    		}
	        	
	        });
	        
	        //새재품등록창에서 나가기버튼
	        buttonExit.setOnAction(e4->{
	        	btnNewProdStage.close();
	        });
	        
	        Scene scene=new Scene(BtnNewProdroot);
	        btnNewProdStage.setScene(scene);
	        btnNewProdStage.show();
	        
		} catch (IOException e1) {
			
		}
	        
	}

	// 3. 수정 이벤트
	public void handlerBtnEditAction(ActionEvent e) {
		
		Parent BtnNewProdroot;
		
		selectedProd = tableView.getSelectionModel().getSelectedItem();
		selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		
		try {
			
			BtnNewProdroot = FXMLLoader.load(getClass().getResource("/view/product_edit.fxml"));
	        
	        Stage btnNewProdStage = new Stage();
	        btnNewProdStage.initModality(Modality.WINDOW_MODAL);
	        btnNewProdStage.initOwner(btnNewProduction.getScene().getWindow());
	        btnNewProdStage.setTitle("Production Edit Page");
	        btnNewProdStage.setResizable(false);
	        
	        ImageView imageViewProduct =(ImageView) BtnNewProdroot.lookup("#imageViewProduct");
	        TextField textFieldProductName= (TextField) BtnNewProdroot.lookup("#textFieldProductName");
	        TextField textFieldItemName= (TextField) BtnNewProdroot.lookup("#textFieldItemName");
	        TextArea textFieldInformation= (TextArea) BtnNewProdroot.lookup("#textAreaInformation");
	        Button btnImageChange = (Button) BtnNewProdroot.lookup("#btnImageChange");
	        Button buttonEdit = (Button) BtnNewProdroot.lookup("#buttonEdit");
	        Button buttonExit = (Button) BtnNewProdroot.lookup("#buttonExit");
	        
	        String imageFile1;
			
			imageFile1 = ProductionDAO
					.getSelectSkin1(tableView.getSelectionModel().getSelectedItem().getProductName());
			
			localImage = new Image("/comImages/" + imageFile1, false);
			imageViewProduct.setImage(localImage);
	        
	        imageViewProduct.setImage(localImage);
	        textFieldProductName.setText(selectedProd.getProductName());
	        textFieldItemName.setText(selectedProd.getItem());
	        textFieldInformation.setText(selectedProd.getInfo());
	        
	        btnImageChange.setOnAction(e2 -> {
				// 1. 파일 추져를 부른다 ( 스테이지, 씬 , 루트 구성되어있음 )
				FileChooser fileChooser = new FileChooser();

				// 2. 파일 추져에 확장자 기능을 부여할수있다.
				fileChooser.getExtensionFilters().addAll(

					new ExtensionFilter("이미지 파일", "*.png"),new ExtensionFilter("텍스트 파일", "*.txt"),
					new ExtensionFilter("한글 파일", "*.hwp"), new ExtensionFilter("모든 파일", "*.*")

				);
				
				try {
					// 2. 주인 스테이지를 할당해야된다. ( 파일 추져 스테이지 할당 )
					selectedFile = fileChooser.showOpenDialog(btnNewProdStage);
					// 파일 찾아서 열기 누르면 이쪽으로 들어옴.
					// => 인풋스트림 아웃풋스트림 리더 라이터 할수있음.

					// 파일 정보 찍기 // 경로를 가져옴

					if (selectedFile != null) {
						localUrl = selectedFile.toURI().toURL().toString();
					}
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				localImage = new Image(localUrl, false);
				imageViewProduct.setImage(localImage);

				if (selectedFile != null) {
					selectFileName = selectedFile.getName();
				}
				

    			
				
			});
			
	        //새재품등록창에서 저장버튼
	        buttonEdit.setOnAction(e3->{
	        	
	           data.remove(selectedIndex);
	        	try { // 오류가 발생될때를 대비
	    			
	        		File dirMake = new File(dirSave.getAbsolutePath());
	                
	    			// 이미지 저장 폴더 생성
	    			if (!dirMake.exists()) {
	    				dirMake.mkdir();
	    			}

	    			// 이미지 파일 저장
	    			String fileName = imageSave(selectedFile);
	    			
	    			
	    			
    				productionVO svo = new productionVO(tableView.getSelectionModel().getSelectedItem().getNo(), 
    						textFieldProductName.getText(), textFieldItemName.getText()
    						, textFieldInformation.getText(), fileName); 
	    				

    				data.add(svo);
    				
    				btnNewProdStage.close();
    				
    				productionDAO = new ProductionDAO(); // 객체를 부른다.

    				int count = productionDAO.updateProdData(svo);
    		
	    			
	    			if(count != 0) {
	    				// dbArrayListSuper.set(selectedIndex, svo);
	    				alertDisplay(1,"등록 성공","테이블 등록 완료","테이블의 정보를 수정합니다.");
	    				btnNewProdStage.close();
					}
			
	    		} catch (Exception e2) {
	    			e2.printStackTrace();
	    			alertDisplay(1,"등록 실패","등록 오류", e2.toString());
	    			return;
	    		}
	        	
	        });
	        
	        //새재품등록창에서 나가기버튼
	        buttonExit.setOnAction(e4->{
	        	btnNewProdStage.close();
	        });
	        
	        Scene scene=new Scene(BtnNewProdroot);
	        btnNewProdStage.setScene(scene);
	        btnNewProdStage.show();
	        
		} catch (IOException e1) {
			
		}
		
	}
	
	// 4. 삭제 이벤트
	public void handlerBtnDeleteAction(ActionEvent e) {
		
		// alertDisplay(2,"삭제","삭제 경고","정말 삭제 하시겠습니까?"); // 삭제 오류창 띄워보기.
		
		try { // 테이블에서도 삭제, 데이터베이스에서도 삭제
			
			productionDAO = new ProductionDAO();
			
			selectProduction = tableView.getSelectionModel().getSelectedItems();
			
			productionDAO.deleteProdData(selectProduction.get(0).getNo());
			
			data.remove(data);
			EtmtotalList();
			
		} catch (Exception e1) {
			DBUtil.alertDisplay(1, "삭제 오류", "8. 삭제 실패", e1.toString());
		}
		
	}
	
	// 5. 검색 이벤트
	public void handlerBtnSearch(ActionEvent e) {
		
		try {
			
			ArrayList<productionVO> list = new ArrayList<productionVO>();
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.getProdCheck(txtSearchField.getText());
			
			if(list == null) {
				throw new Exception("검색 오류");
			}
			
			data.removeAll(data);
			
			for ( productionVO svo : list ) {
				
				data.add(svo);
			}
		
		} catch (Exception e1) {
			alertDisplay(1,"검색 결과","이름 검색 오류", e1.toString());
		}
		
		txtSearchField.clear();
		
	}
	
	// 6. 종료 이벤트
	public void handlerBtnExitAction(ActionEvent e) {
		
		((Stage) btnExit.getScene().getWindow()).close();
		
	}
	
	// ================== 함수 기능 =================== // 
	
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
	
	// 이미지 파일 저장
	public String imageSave(File file1) {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// 이미지 파일명 생성
			fileName = "compu" + System.currentTimeMillis() + "_" + file1.getName();
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
	
	// 맨 처음의 테이블 뷰에 데이터 베이스 값을 읽어서 테이블 뷰에 가져온다.
	public void EtmtotalList() {
			
			ArrayList<productionVO> list = null;
			productionVO productionVO = null;
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.selectProductionList(s);
			
			if(list == null ) {
				DBUtil.alertDisplay(1, "경고", "DB 가져오기 오류", "점검 필요");
				return;
			}
			
			for ( int i = 0 ; i < list.size() ; i++ ) {
				productionVO = list.get(i);
				data.add(productionVO); // 모든 디비에 있는 내용이 테이블뷰에 연결되어 값이 다 들어감.
			}
			
		}
	
	
	
}
