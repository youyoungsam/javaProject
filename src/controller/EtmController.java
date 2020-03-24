package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.application.HostServicesDelegate;

import javafx.application.HostServices;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.productionVO;

public class EtmController implements Initializable {
	
	// =============== ���� â -> ���� ¥�� â =============== //
	
	@FXML private ComboBox<String> comboBox1;
	@FXML private ComboBox<String> comboBox2;
	@FXML private ComboBox<String> comboBox3;
	@FXML private ComboBox<String> comboBox4;
	@FXML private ComboBox<String> comboBox5;
	@FXML private ComboBox<String> comboBox6;
	@FXML private ComboBox<String> comboBox7;
	@FXML private ComboBox<String> comboBox8;
	
	@FXML private ImageView imgCheck;
	@FXML private ImageView imgCompati;
	
	@FXML private Button btnPmt;
	@FXML private Button btnChase;
	@FXML private Button btnExit;
	
	@FXML private TableView<productionVO> tableView;
	
	private ObservableList<productionVO> selectProdtion;
	
	ObservableList<productionVO> data2  = FXCollections.observableArrayList();;
	
	private String s;
	private String s1;
	private String s2;
	private String s3;
	private String s4;
	private String s5;
	private String s6;
	private String s7;
	private String s8;
	
	private String selectFileName = ""; // �̹��� ���ϸ�
	private String localUrl = ""; // �̹��� ���� ���
	private Image localImage;
	
	ArrayList<productionVO> prodList8;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// 1. ���̺� �÷� ����
		tableColumeEvent();
		btnPmt.setDisable(true);
		
		// 2. �޺��ڽ�, ���̺�� ���
		estimateEvent();
		
		// 3. ���� ��� �̺�Ʈ
		btnChase.setOnAction( e -> handlerBtnChaseAction(e));
		
		// 4. ���̺�� �������� �̺�Ʈ 
		tableView.setOnMousePressed( e -> handlerSetMouseAction(e));
		
		// 5. ȣȯ�� ������ ��� �̺�Ʈ
		btnPmt.setOnAction( e -> handlerBtnPmtAction(e));
		
		// 6. ������ ��ư �̺�Ʈ
		btnExit.setOnAction( e-> handlerBtnExitAction(e));
		

	}


	// 1. ���̺� �÷� ����
	public void tableColumeEvent() {
	
		TableColumn colPname = new TableColumn("��ǰ��");
		colPname.setPrefWidth(150); // 77
		colPname.setStyle("-fx-alignment: CENTER;");
		colPname.setCellValueFactory(new PropertyValueFactory("productName"));
		
		TableColumn colItem = new TableColumn("ǰ��");
		colItem.setPrefWidth(60); // 108
		colItem.setStyle("-fx-alignment: CENTER;");
		colItem.setCellValueFactory(new PropertyValueFactory("item"));
		
		TableColumn colInfo = new TableColumn("����");
		colInfo.setPrefWidth(245); // 195
		colInfo.setStyle("-fx-alignment: CENTER;");
		colInfo.setCellValueFactory(new PropertyValueFactory("info"));
		
		TableColumn colFilename = new TableColumn("�̹���");
		colFilename.setPrefWidth(100); // 77
		colFilename.setStyle("-fx-alignment: CENTER;");
		colFilename.setCellValueFactory(new PropertyValueFactory("filename"));
		
		tableView.setItems(data2);
		tableView.getColumns().addAll(colPname, colItem, colInfo, colFilename);
		
		
	}
	
	// 2. �޺��ڽ�, ���̺�� ���
	public void estimateEvent() {
		
		ObservableList<String> list = FXCollections.observableArrayList();
		
		list.add("ABKO NCORE");
		list.add("DAVEN ũ����Ż");                // +35,500��"
		list.add("DAVEN FT707"); // +36,900��"
		list.add("���̱��� G50SE");//                      +36,900��\"
		list.add("����ũ�δн� ���϶�");//                +19,100��"
		
		comboBox1.setItems(list); 
		
		comboBox1.setOnAction(new EventHandler<ActionEvent>() {
			
	         public void handle(ActionEvent event) {
	        	 
	        	s1 = comboBox1.getValue();
	        	ArrayList<productionVO> prodList1 = ProductionDAO.selectProductionList(comboBox1.getValue());
	        	 
	            int a = comboBox1.getSelectionModel().getSelectedIndex();
	            
	            if(a==0) { 
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/c1.jpg").toString()));
	               totalList1();
	               comboBox1.setDisable(true);
	            }else if(a==1) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/c2.jpg").toString()));
	               totalList1();
	               comboBox1.setDisable(true);
	            }else if(a==2) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/c3.jpg").toString()));
		           totalList1();
		           comboBox1.setDisable(true);
	            }else if(a==3) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/c4.jpg").toString()));
		           totalList1();
		           comboBox1.setDisable(true);
	            }else if(a==4) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/c5.jpg").toString()));
		           totalList1();
		           comboBox1.setDisable(true);
	            }
	         }	            
	      });
		

		// ���κ���
		ObservableList<String> list2 = FXCollections.observableArrayList(); // = ArrayList<String> list = new ArrayList<String>();
		
		list2.add("GIGABYTE B365M"); // +83,400��
		list2.add("MSI H310M");
		list2.add("MSI MPG X570");
		list2.add("ASUS PRIME");
		list2.add("MSI MAG B450");
		
		comboBox2.setItems(list2); 
		
		comboBox2.setOnAction(new EventHandler<ActionEvent>() {
			
	         public void handle(ActionEvent event) {
			
	        	 s2 = comboBox2.getValue();
		        ArrayList<productionVO> prodList2 = ProductionDAO.selectProductionList(comboBox2.getValue());
			
	            int a=comboBox2.getSelectionModel().getSelectedIndex();
	            if(a==0) {       	
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/m1.jpg").toString()));
	               totalList2();
	               comboBox2.setDisable(true);
	            }else if(a==1) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/m2.jpg").toString()));
	               totalList2();
	               comboBox2.setDisable(true);
	            }else if(a==2) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/m3.jpg").toString()));
		           totalList2();
		           comboBox2.setDisable(true);
	            }else if(a==3) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/m4.jpg").toString()));
		           totalList2();
		           comboBox2.setDisable(true);
	            }else if(a==4) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/m5.jpg").toString()));
		           totalList2();
		           comboBox2.setDisable(true);
	            }
	         }	            
	      });
		
		// PW
		ObservableList<String> list3 = FXCollections.observableArrayList(); // = ArrayList<String> list = new ArrayList<String>();
		
		list3.add("����ũ�δн� Classic");
		list3.add("FSP HYPER K");
		list3.add("�߸� EcoMax 500W");
		list3.add("EVGA 750 GQ");
		list3.add("ABKO SUITMASTER");
		
		comboBox3.setItems(list3); 
		
		comboBox3.setOnAction(new EventHandler<ActionEvent>() {
	         
	         public void handle(ActionEvent event) {
	        	 
	        	 s3 = comboBox3.getValue();
			     ArrayList<productionVO> prodList3 = ProductionDAO.selectProductionList(comboBox3.getValue());
	        	 
	            int a=comboBox3.getSelectionModel().getSelectedIndex();
	            if(a==0) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/p1.jpg").toString()));
	               totalList3();
	               imgCompati.setImage(new Image(getClass().getResource("/comImages/pmt2.png").toString()));
	               comboBox3.setDisable(true);
	            }else if(a==1) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/p2.jpg").toString()));
	               totalList3();
	               imgCompati.setImage(new Image(getClass().getResource("/comImages/pmt2.png").toString()));
	               comboBox3.setDisable(true);
	            }else if(a==2) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/p3.jpg").toString()));
		           totalList3();
		           imgCompati.setImage(new Image(getClass().getResource("/comImages/pmt2.png").toString()));
		           comboBox3.setDisable(true);
	            }else if(a==3) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/p4.jpg").toString()));
		           totalList3();
		           imgCompati.setImage(new Image(getClass().getResource("/comImages/pmt2.png").toString()));
		           comboBox3.setDisable(true);
	            }else if(a==4) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/p5.jpg").toString()));
		           totalList3();
		           comboBox3.setDisable(true);
	            }
	         }	            
	      });
		
		// RAM
		ObservableList<String> list4 = FXCollections.observableArrayList(); // = ArrayList<String> list = new ArrayList<String>();
		
		list4.add("�Ｚ���� DDR4 16G");
		list4.add("GeIL DDR4 8G");
		list4.add("ADATA XPG DDR4 16G");
		list4.add("TeamGroup 16G");
		
		comboBox4.setItems(list4); 
		
		comboBox4.setOnAction(new EventHandler<ActionEvent>() {
	         
	         public void handle(ActionEvent event) {
	        	 
	        	 s4 = comboBox4.getValue();
			     ArrayList<productionVO> prodList4 = ProductionDAO.selectProductionList(comboBox4.getValue());
	        	 
	            int a=comboBox4.getSelectionModel().getSelectedIndex();
	            if(a==0) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/r1.jpg").toString()));
	               totalList4();
	               comboBox4.setDisable(true);
	            }else if(a==1) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/r2.jpg").toString()));
	               totalList4();
	               comboBox4.setDisable(true);
	            }else if(a==2) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/r3.jpg").toString()));
		           totalList4();
		           comboBox4.setDisable(true);
	            }else if(a==3) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/r4.jpg").toString()));
		           totalList4();
		           comboBox4.setDisable(true);
	            }else if(a==4) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/r5.jpg").toString()));
		           totalList4();
		           comboBox4.setDisable(true);
	            }
	         }	            
	      });
		
		// CPU
		ObservableList<String> list5 = FXCollections.observableArrayList(); // = ArrayList<String> list = new ArrayList<String>();
		
		list5.add("AMD ������ 5 2600");
		list5.add("AMD ������ 5 3600");
		list5.add("���� �ھ� i7-9����");
		list5.add("AMD ������ 7 3700X");
		list5.add("���� �ھ� i5-9����");
		
		comboBox5.setItems(list5); 
		
		comboBox5.setOnAction(new EventHandler<ActionEvent>() {
	         
	         public void handle(ActionEvent event) {
	        	 
	        	 s5 = comboBox5.getValue();
			     ArrayList<productionVO> prodList5 = ProductionDAO.selectProductionList(comboBox5.getValue());
	        	 
	            int a=comboBox5.getSelectionModel().getSelectedIndex();
	            if(a==0) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/u1.jpg").toString()));
	               totalList5();
	               comboBox5.setDisable(true);
	            }else if(a==1) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/u2.jpg").toString()));
	               totalList5();
	               comboBox5.setDisable(true);
	            }else if(a==2) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/u3.jpg").toString()));
		           totalList5();
		           comboBox5.setDisable(true);
	            }else if(a==3) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/u4.jpg").toString()));
		           totalList5();
		           comboBox5.setDisable(true);
	            }else if(a==4) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/u5.jpg").toString()));
		           totalList5();
		           comboBox5.setDisable(true);
	            }
	         }	            
	      });
		
		// VGA
		ObservableList<String> list6 = FXCollections.observableArrayList(); // = ArrayList<String> list = new ArrayList<String>();
		
		list6.add("�̿��� XENON GTX 1660 Ti");
		list6.add("�̿��� HV ������ GTX 1660");
		list6.add("������ GALAX RTX 2070");
		list6.add("ZOTAC GAMING RTX 2060");
		list6.add("MSI ������ RTX 2070");
		
		comboBox6.setItems(list6); 
		
		comboBox6.setOnAction(new EventHandler<ActionEvent>() {
	         
	         public void handle(ActionEvent event) {
	        	 
	        	 s6 = comboBox6.getValue();
			     ArrayList<productionVO> prodList6 = ProductionDAO.selectProductionList(comboBox6.getValue());
	        	 
	            int a=comboBox6.getSelectionModel().getSelectedIndex();
	            if(a==0) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/v1.jpg").toString()));
	               totalList6();
	               comboBox6.setDisable(true);
	            }else if(a==1) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/v2.jpg").toString()));
	               totalList6();
	               comboBox6.setDisable(true);
	            }else if(a==2) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/v3.jpg").toString()));
		           totalList6();
		           comboBox6.setDisable(true);
	            }else if(a==3) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/v4.jpg").toString()));
		           totalList6();
		           comboBox6.setDisable(true);
	            }else if(a==4) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/v5.jpg").toString()));
		           totalList6();
		           comboBox6.setDisable(true);
	            }
	         }	            
	      });
		
		// HDD
		ObservableList<String> list7 = FXCollections.observableArrayList(); // = ArrayList<String> list = new ArrayList<String>();
		
		list7.add("Seagate 2TB");
		list7.add("Toshiba 4TB");
		list7.add("Western Digital 4TB");
		list7.add("���� ����");
		
		comboBox7.setItems(list7); 
		
		comboBox7.setOnAction(new EventHandler<ActionEvent>() {
	         
	         public void handle(ActionEvent event) {
	        	 
	        	 s7 = comboBox7.getValue();
			     ArrayList<productionVO> prodList7 = ProductionDAO.selectProductionList(comboBox7.getValue());
	        	 
	            int a=comboBox7.getSelectionModel().getSelectedIndex();
	            if(a==0) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/h1.jpg").toString()));
	               totalList7();
	               comboBox7.setDisable(true);
	            }else if(a==1) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/h2.jpg").toString()));
	               totalList7();
	               comboBox7.setDisable(true);
	            }else if(a==2) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/h3.jpg").toString()));
		           totalList7();
		           comboBox7.setDisable(true);
	            }else if (a == 3) {
			       imgCheck.setImage(new Image(getClass().getResource("/comImages/noselect.png").toString()));
	            }
	         }	            
	      });
		
		// SSD
		ObservableList<String> list8 = FXCollections.observableArrayList(); // = ArrayList<String> list = new ArrayList<String>();
		
		list8.add("����ũ�� Crucial");
		list8.add("�Ｚ���� 860 EVO");
		list8.add("�Ｚ���� 970 EVO");
		list8.add("Ÿ���� GK300");
		
		comboBox8.setItems(list8); 
		
		comboBox8.setOnAction(new EventHandler<ActionEvent>() {
	         
	         public void handle(ActionEvent event) {
	        	 
	        	 s8 = comboBox8.getValue();
			     ArrayList<productionVO> prodList8 = ProductionDAO.selectProductionList(comboBox8.getValue());
	        	 
	            int a=comboBox8.getSelectionModel().getSelectedIndex();
	            if(a==0) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/s1.jpg").toString()));
	               totalList8();
	               imgCompati.setImage(new Image(getClass().getResource("/comImages/pmt2.png").toString()));
	               comboBox8.setDisable(true);
	               btnPmt.setDisable(false);
	               btnChase.setDisable(true);
	            }else if(a==1) {
	               imgCheck.setImage(new Image(getClass().getResource("/comImages/s2.jpg").toString()));
	               totalList8();
	               imgCompati.setImage(new Image(getClass().getResource("/comImages/pmt3.png").toString()));
	               comboBox8.setDisable(true);
	               btnPmt.setDisable(false);
	            }else if(a==2) {
		           imgCheck.setImage(new Image(getClass().getResource("/comImages/s3.jpg").toString()));
		           totalList8();
		           imgCompati.setImage(new Image(getClass().getResource("/comImages/pmt4.png").toString()));
		           comboBox8.setDisable(true);
		           btnPmt.setDisable(false);
	            }else if(a==3) {
			       imgCheck.setImage(new Image(getClass().getResource("/comImages/s4.jpg").toString()));
			       totalList8();
			       imgCompati.setImage(new Image(getClass().getResource("/comImages/pmt5.png").toString()));
			       comboBox8.setDisable(true);
			       btnPmt.setDisable(false);
		        }
	         }	            
	      });
		
		
	}
	
	// 3. ���� ��� �̺�Ʈ 
	public void handlerBtnChaseAction(ActionEvent e) {
	
		try {
			// ���� ȭ���� �θ���. �˾�â.
			Parent chaseRoot = FXMLLoader.load(getClass().getResource("/view/chase.fxml"));
		
			Stage chaseStage = new Stage();
			chaseStage.initModality(Modality.WINDOW_MODAL);
			chaseStage.initOwner(btnChase.getScene().getWindow());
			chaseStage.setTitle("PurChase Page");
			
			TableView<productionVO> chaseTableView = (TableView<productionVO>) chaseRoot.lookup("#chaseTableView");
			TextField txtPrediction1 = (TextField) chaseRoot.lookup("#txtPrediction1");
			TextField txtPrediction2 = (TextField) chaseRoot.lookup("#txtPrediction2");
			
			Hyperlink hyp1 = (Hyperlink) chaseRoot.lookup("#hyp1");
			Hyperlink hyp2 = (Hyperlink) chaseRoot.lookup("#hyp2");
			Hyperlink hyp3 = (Hyperlink) chaseRoot.lookup("#hyp3");
			
			Button btnChaseExit = (Button) chaseRoot.lookup("#btnChaseExit");
			
			txtPrediction1.setText("1,049,999");
			txtPrediction2.setText("1,299,999");
			
			TableColumn colPname = new TableColumn("��ǰ��");
			colPname.setPrefWidth(100); // 77
			colPname.setStyle("-fx-alignment: CENTER;");
			colPname.setCellValueFactory(new PropertyValueFactory("productName"));
			
			TableColumn colItem = new TableColumn("ǰ��");
			colItem.setPrefWidth(68); // 108
			colItem.setStyle("-fx-alignment: CENTER;");
			colItem.setCellValueFactory(new PropertyValueFactory("item"));
			
			TableColumn colInfo = new TableColumn("����");
			colInfo.setPrefWidth(200); // 195
			colInfo.setStyle("-fx-alignment: CENTER;");
			colInfo.setCellValueFactory(new PropertyValueFactory("info"));
			
			TableColumn colFilename = new TableColumn("�̹���");
			colFilename.setPrefWidth(70); // 77
			colFilename.setStyle("-fx-alignment: CENTER;");
			colFilename.setCellValueFactory(new PropertyValueFactory("filename"));
			
			chaseTableView.setItems(data2);
			chaseTableView.getColumns().addAll(colPname, colItem, colInfo, colFilename);
			
			hyp1 = new Hyperlink();

			hyp1.setOnAction(e1 -> {
			  
			});
			
			// ����â ��� �̺�Ʈ ó��
			btnChaseExit.setOnAction( e1 -> {
				chaseStage.close();
			});
			
			Scene scene = new Scene(chaseRoot);
			chaseStage.setScene(scene);
			chaseStage.setResizable(false);
			chaseStage.show();
			
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}
	
	// 4. ���̺�� �������� �̺�Ʈ 
	public void handlerSetMouseAction(MouseEvent e) {
		
		String imageFile1;
		
		imageFile1 = ProductionDAO
				.getSelectSkin1(tableView.getSelectionModel().getSelectedItem().getProductName());

		localImage = new Image("/comImages/" + imageFile1, false);
		imgCheck.setImage(localImage);
		
	}
	
	// 5. ȣȯ�� ������ ��� �̺�Ʈ
	public void handlerBtnPmtAction(ActionEvent e) {
		
		try {
			
			Parent compatiRoot = FXMLLoader.load(getClass().getResource("/view/compatibility.fxml"));
		
			Stage compatiStage = new Stage();
			compatiStage.initModality(Modality.WINDOW_MODAL);
			compatiStage.initOwner(btnChase.getScene().getWindow());
			compatiStage.setTitle("Compatibility Page");
		
			ImageView imageView1 = (ImageView) compatiRoot.lookup("#imageView1");
			ImageView imageView2 = (ImageView) compatiRoot.lookup("#imageView2");
			ImageView imageView3 = (ImageView) compatiRoot.lookup("#imageView3");
			ImageView imageView4 = (ImageView) compatiRoot.lookup("#imageView4");
			ImageView imageView5 = (ImageView) compatiRoot.lookup("#imageView5");
			ImageView imageView6 = (ImageView) compatiRoot.lookup("#imageView6");
			
			TextField txt1 = (TextField) compatiRoot.lookup("#txt1");
			TextField txt2 = (TextField) compatiRoot.lookup("#txt2");
			TextField txt3 = (TextField) compatiRoot.lookup("#txt3");
			TextField txt4 = (TextField) compatiRoot.lookup("#txt4");
			TextField txt5 = (TextField) compatiRoot.lookup("#txt5");
			TextField txt6 = (TextField) compatiRoot.lookup("#txt6");
			TextField txt7 = (TextField) compatiRoot.lookup("#txt7");
			TextField txt8 = (TextField) compatiRoot.lookup("#txt8");
			TextField txt9 = (TextField) compatiRoot.lookup("#txt9");
			TextField txt10 = (TextField) compatiRoot.lookup("#txt10");
			TextField txt11 = (TextField) compatiRoot.lookup("#txt11");
			TextField txt12 = (TextField) compatiRoot.lookup("#txt12");
			TextField txt13 = (TextField) compatiRoot.lookup("#txt13");
			TextField txt14 = (TextField) compatiRoot.lookup("#txt14");
			TextField txt15 = (TextField) compatiRoot.lookup("#txt15");
			TextField txt16 = (TextField) compatiRoot.lookup("#txt16");
			TextField txt17 = (TextField) compatiRoot.lookup("#txt17");
			TextField txt18 = (TextField) compatiRoot.lookup("#txt18");
			TextField txt19 = (TextField) compatiRoot.lookup("#txt19");
			
			Button btnExit = (Button) compatiRoot.lookup("#btnExit");
			

			// ������ �̺�Ʈ ó��
			btnExit.setOnAction( e1 -> {
				compatiStage.close();
			});
			
			Scene scene = new Scene(compatiRoot);
			compatiStage.setScene(scene);
			compatiStage.setResizable(false);
			compatiStage.show();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	// 6. ������ ��ư �̺�Ʈ
	public void handlerBtnExitAction(ActionEvent e) {
		
		((Stage) btnExit.getScene().getWindow()).close();
		
	}
	
	
	public void totalList() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.getProductTotal();
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); // ��� ��� �ִ� ������ ���̺�信 ����Ǿ� ���� �� ��.
		}
	}
	
	// case ����Ʈ ����
	public void totalList1() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.selectProductionList(s1);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); 
		}
	}
	
	// ���κ��� ����Ʈ ����
	public void totalList2() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.selectProductionList(s2);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); 
		}
	}
	
	// �Ŀ� ����Ʈ ����
	public void totalList3() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.selectProductionList(s3);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); 
		}
	}
	
	// RAM ����Ʈ ����
	public void totalList4() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.selectProductionList(s4);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); 
		}
	}
	
	// CPU ����Ʈ ����
	public void totalList5() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.selectProductionList(s5);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); 
		}
	}
	
	// VGA ����Ʈ ����
	public void totalList6() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.selectProductionList(s6);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); 
		}
	}
	
	// HDD ����Ʈ ����
	public void totalList7() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.selectProductionList(s7);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); 
		}
	}
	
	// SSD ����Ʈ ����
	public void totalList8() {
		
		ArrayList<productionVO> list = null;
		productionVO prodVO = null;
		
		ProductionDAO prodDAO = new ProductionDAO();
		list = prodDAO.selectProductionList(s8);
		
		if(list == null ) {
			DBUtil.alertDisplay(1, "���", "DB �������� ����", "���� �ʿ�");
			return;
		}
		
		for ( int i = 0 ; i < list.size() ; i++ ) {
			prodVO = list.get(i);
			data2.add(prodVO); 
		}
	}
	
	// ���â ���÷���
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
			alert.setContentText(contentText);
			alert.setResizable(false);
			alert.showAndWait();
			
		}
}
