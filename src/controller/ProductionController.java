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

	// =============== ������ â -> ��ǰ ����â =============== //
	
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
	private String localUrl = ""; // �̹��� ���� ���
	private Image localImage;
	
	private boolean editDelete = false;
	
	ArrayList<productionVO> dbArrayListSuper;
	
	private String s;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		selectProduction = FXCollections.observableArrayList();
		
		// 0. ���̺� �÷� �̺�Ʈ
		tableColumeEvent();
		
		// 1. ����Ʈ ��� �̺�Ʈ
		btnList.setOnAction( e -> handlerBtnList(e));
		
		// 2. �� ��ǰ ��� �̺�Ʈ
		btnNewProduction.setOnAction( e -> handlerBtnNewProduction(e));
		
		// 3. ���� �̺�Ʈ
		btnEdit.setOnAction( e -> handlerBtnEditAction(e));
		
		// 4. ���� �̺�Ʈ
		btnDelete.setOnAction( e -> handlerBtnDeleteAction(e));
		
		// 5. �˻� �̺�Ʈ
		btnSearch.setOnAction( e -> handlerBtnSearch(e));
		
		// 6. ���� �̺�Ʈ
		btnExit.setOnAction( e -> handlerBtnExitAction(e));
		

	}

	// 0. ���̺� �÷� �̺�Ʈ
	public void tableColumeEvent() {
		
		TableColumn colPname = new TableColumn("��ǰ��");
		colPname.setPrefWidth(107); // 77
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
		colFilename.setPrefWidth(80); // 77
		colFilename.setStyle("-fx-alignment: CENTER;");
		colFilename.setCellValueFactory(new PropertyValueFactory("filename"));
		
		TableColumn colNo = new TableColumn("��ȣ");
		colNo.setPrefWidth(1); // 77
		colNo.setStyle("-fx-alignment: CENTER;");
		colNo.setCellValueFactory(new PropertyValueFactory("no"));
		
		tableView.setItems(data);
		tableView.getColumns().addAll(colPname, colItem, colInfo, colFilename, colNo);
	}
	
	// 1. ����Ʈ ��� �̺�Ʈ
	public void handlerBtnList(ActionEvent e) {
	
		try {
			
			ArrayList<productionVO> list = new ArrayList<productionVO>();
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.getProdCheck(txtSearchField.getText());
			
			if(list == null) {
				throw new Exception("��ü����Ʈ ����");
			}
			
			data.removeAll(data);
			
			for ( productionVO svo : list ) {
				
				data.add(svo);
			}
		
		} catch (Exception e1) {
			alertDisplay(1,"��ü����Ʈ ����","����Ʈ �ҷ����� ����", e1.toString());
		}
	
	}
	
	// 2. �� ��ǰ ��� �̺�Ʈ
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
       
        // �� ��ǰ ���â���� �̹�����Ϲ�ư
        btnImageLoad.setOnAction(e2 -> {
				// 1. ���� ������ �θ��� ( ��������, �� , ��Ʈ �����Ǿ����� )
				FileChooser fileChooser = new FileChooser();

				// 2. ���� ������ Ȯ���� ����� �ο��Ҽ��ִ�.
				fileChooser.getExtensionFilters().addAll(

						new ExtensionFilter("�̹��� ����", "*.png"), 
						new ExtensionFilter("�̹��� ����", "*.jpg"),
						new ExtensionFilter("�ؽ�Ʈ ����", "*.txt"),  
						new ExtensionFilter("��� ����", "*.*")

				);
				try {
					// 2. ���� ���������� �Ҵ��ؾߵȴ�. ( ���� ���� �������� �Ҵ� )
					selectedFile = fileChooser.showOpenDialog(btnNewProdStage);
					// ���� ã�Ƽ� ���� ������ �������� ����.
					// => ��ǲ��Ʈ�� �ƿ�ǲ��Ʈ�� ���� ������ �Ҽ�����.

					// ���� ���� ��� // ��θ� ������

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
			
	        //����ǰ���â���� �����ư
	        buttonSave.setOnAction(e3->{
	           
	        	try { // ������ �߻��ɶ��� ���
	    			
	    			File dirMake = new File(dirSave.getAbsolutePath());
	                
	    			// �̹��� ���� ���� ����
	    			if (!dirMake.exists()) {
	    				dirMake.mkdir();
	    			}

	    			// �̹��� ���� ����
	    			String fileName = imageSave(selectedFile);
	    			
    				productionVO svo = new productionVO(textFieldProductName.getText(), textFieldItemName.getText()
    						, textFieldInformation.getText(), fileName); // ������ ���⿡ �����ϸ� 
	    				

    				productionDAO = new ProductionDAO(); // ��ü�� �θ���.
	    					
	    					int count = productionDAO.insertProductionData(svo); 
	    								// �����ͺ��̽� ���̺� ���ڵ� ���� �־���� ����
	    					
	    					if(count != 0) {
	    						data.removeAll(data);
	    						EtmtotalList();	
	    					}else {
	    						throw new Exception("�����ͺ��̽� ��� ����");
	    					}
	    				
	    			
	    				alertDisplay(1,"��� ����","���̺� ��� �Ϸ�","���̺� ������ ����մϴ�.");
	    		
	    		} catch (Exception e2) {
	    			alertDisplay(1,"��� ����","��� ����", e2.toString());
	    			return;
	    		}
	        	
	        });
	        
	        //����ǰ���â���� �������ư
	        buttonExit.setOnAction(e4->{
	        	btnNewProdStage.close();
	        });
	        
	        Scene scene=new Scene(BtnNewProdroot);
	        btnNewProdStage.setScene(scene);
	        btnNewProdStage.show();
	        
		} catch (IOException e1) {
			
		}
	        
	}

	// 3. ���� �̺�Ʈ
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
				// 1. ���� ������ �θ��� ( ��������, �� , ��Ʈ �����Ǿ����� )
				FileChooser fileChooser = new FileChooser();

				// 2. ���� ������ Ȯ���� ����� �ο��Ҽ��ִ�.
				fileChooser.getExtensionFilters().addAll(

					new ExtensionFilter("�̹��� ����", "*.png"),new ExtensionFilter("�ؽ�Ʈ ����", "*.txt"),
					new ExtensionFilter("�ѱ� ����", "*.hwp"), new ExtensionFilter("��� ����", "*.*")

				);
				
				try {
					// 2. ���� ���������� �Ҵ��ؾߵȴ�. ( ���� ���� �������� �Ҵ� )
					selectedFile = fileChooser.showOpenDialog(btnNewProdStage);
					// ���� ã�Ƽ� ���� ������ �������� ����.
					// => ��ǲ��Ʈ�� �ƿ�ǲ��Ʈ�� ���� ������ �Ҽ�����.

					// ���� ���� ��� // ��θ� ������

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
			
	        //����ǰ���â���� �����ư
	        buttonEdit.setOnAction(e3->{
	        	
	           data.remove(selectedIndex);
	        	try { // ������ �߻��ɶ��� ���
	    			
	        		File dirMake = new File(dirSave.getAbsolutePath());
	                
	    			// �̹��� ���� ���� ����
	    			if (!dirMake.exists()) {
	    				dirMake.mkdir();
	    			}

	    			// �̹��� ���� ����
	    			String fileName = imageSave(selectedFile);
	    			
	    			
	    			
    				productionVO svo = new productionVO(tableView.getSelectionModel().getSelectedItem().getNo(), 
    						textFieldProductName.getText(), textFieldItemName.getText()
    						, textFieldInformation.getText(), fileName); 
	    				

    				data.add(svo);
    				
    				btnNewProdStage.close();
    				
    				productionDAO = new ProductionDAO(); // ��ü�� �θ���.

    				int count = productionDAO.updateProdData(svo);
    		
	    			
	    			if(count != 0) {
	    				// dbArrayListSuper.set(selectedIndex, svo);
	    				alertDisplay(1,"��� ����","���̺� ��� �Ϸ�","���̺��� ������ �����մϴ�.");
	    				btnNewProdStage.close();
					}
			
	    		} catch (Exception e2) {
	    			e2.printStackTrace();
	    			alertDisplay(1,"��� ����","��� ����", e2.toString());
	    			return;
	    		}
	        	
	        });
	        
	        //����ǰ���â���� �������ư
	        buttonExit.setOnAction(e4->{
	        	btnNewProdStage.close();
	        });
	        
	        Scene scene=new Scene(BtnNewProdroot);
	        btnNewProdStage.setScene(scene);
	        btnNewProdStage.show();
	        
		} catch (IOException e1) {
			
		}
		
	}
	
	// 4. ���� �̺�Ʈ
	public void handlerBtnDeleteAction(ActionEvent e) {
		
		// alertDisplay(2,"����","���� ���","���� ���� �Ͻðڽ��ϱ�?"); // ���� ����â �������.
		
		try { // ���̺����� ����, �����ͺ��̽������� ����
			
			productionDAO = new ProductionDAO();
			
			selectProduction = tableView.getSelectionModel().getSelectedItems();
			
			productionDAO.deleteProdData(selectProduction.get(0).getNo());
			
			data.remove(data);
			EtmtotalList();
			
		} catch (Exception e1) {
			DBUtil.alertDisplay(1, "���� ����", "8. ���� ����", e1.toString());
		}
		
	}
	
	// 5. �˻� �̺�Ʈ
	public void handlerBtnSearch(ActionEvent e) {
		
		try {
			
			ArrayList<productionVO> list = new ArrayList<productionVO>();
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.getProdCheck(txtSearchField.getText());
			
			if(list == null) {
				throw new Exception("�˻� ����");
			}
			
			data.removeAll(data);
			
			for ( productionVO svo : list ) {
				
				data.add(svo);
			}
		
		} catch (Exception e1) {
			alertDisplay(1,"�˻� ���","�̸� �˻� ����", e1.toString());
		}
		
		txtSearchField.clear();
		
	}
	
	// 6. ���� �̺�Ʈ
	public void handlerBtnExitAction(ActionEvent e) {
		
		((Stage) btnExit.getScene().getWindow()).close();
		
	}
	
	// ================== �Լ� ��� =================== // 
	
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
	
	// �̹��� ���� ����
	public String imageSave(File file1) {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// �̹��� ���ϸ� ����
			fileName = "compu" + System.currentTimeMillis() + "_" + file1.getName();
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
	
	// �� ó���� ���̺� �信 ������ ���̽� ���� �о ���̺� �信 �����´�.
	public void EtmtotalList() {
			
			ArrayList<productionVO> list = null;
			productionVO productionVO = null;
			
			ProductionDAO productionDAO = new ProductionDAO();
			list = productionDAO.selectProductionList(s);
			
			if(list == null ) {
				DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
				return;
			}
			
			for ( int i = 0 ; i < list.size() ; i++ ) {
				productionVO = list.get(i);
				data.add(productionVO); // ��� ��� �ִ� ������ ���̺�信 ����Ǿ� ���� �� ��.
			}
			
		}
	
	
	
}
