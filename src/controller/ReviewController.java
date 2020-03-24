package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.productionVO;
import model.reviewVO;

public class ReviewController implements Initializable {
  
	// ======================= ����â ======================== // 
	
	////////////////////////////// 1. ���̽� ����
   @FXML
   private ImageView caseImage;
   @FXML
   private TextField caseId1;
   @FXML
   private TextField caseId2;
   @FXML
   private TextField caseId3;
   @FXML
   private TextField caseId4;
   @FXML
   private TextField caseId5;

   @FXML
   private TextArea caseTxt1;
   @FXML
   private TextArea caseTxt2;
   @FXML
   private TextArea caseTxt3;
   @FXML
   private TextArea caseTxt4;
   @FXML
   private TextArea caseTxt5;

   @FXML
   private TextField caseMyId;
   @FXML
   private TextField caseMyTxt;

   @FXML
   private ComboBox<String> caseComboBox;

   @FXML
   private Button caseSelect;

   @FXML
   private Button caseDelete;
   @FXML
   private Button caseExit;

   /////////////////////////////// 2. ���κ��� ����
   @FXML
   private ImageView mbImage;

   @FXML
   private TextField mbId1;
   @FXML
   private TextField mbId2;
   @FXML
   private TextField mbId3;
   @FXML
   private TextField mbId4;
   @FXML
   private TextField mbId5;

   @FXML
   private TextArea mbTxt1;
   @FXML
   private TextArea mbTxt2;
   @FXML
   private TextArea mbTxt3;
   @FXML
   private TextArea mbTxt4;
   @FXML
   private TextArea mbTxt5;

   @FXML
   private TextField mbMyId;
   @FXML
   private TextField mbMyTxt;

   @FXML
   private ComboBox<String> mbComboBox;

   @FXML
   private Button mbSelect;

   @FXML
   private Button mbDelete;
   @FXML
   private Button mbExit;

   ////////////////////////////// 3. �Ŀ�����
   @FXML
   private ImageView pwImage;

   @FXML
   private TextField pwId1;
   @FXML
   private TextField pwId2;
   @FXML
   private TextField pwId3;
   @FXML
   private TextField pwId4;
   @FXML
   private TextField pwId5;

   @FXML
   private TextArea pwTxt1;
   @FXML
   private TextArea pwTxt2;
   @FXML
   private TextArea pwTxt3;
   @FXML
   private TextArea pwTxt4;
   @FXML
   private TextArea pwTxt5;

   @FXML
   private TextField pwMyId;
   @FXML
   private TextField pwMyTxt;

   @FXML
   private ComboBox<String> pwComboBox;

   @FXML
   private Button pwSelect;

   @FXML
   private Button pwDelete;
   @FXML
   private Button pwExit;

   ///////////////////////// 4.ram ����
   @FXML
   private ImageView ramImage;

   @FXML
   private TextField ramId1;
   @FXML
   private TextField ramId2;
   @FXML
   private TextField ramId3;
   @FXML
   private TextField ramId4;
   @FXML
   private TextField ramId5;

   @FXML
   private TextArea ramTxt1;
   @FXML
   private TextArea ramTxt2;
   @FXML
   private TextArea ramTxt3;
   @FXML
   private TextArea ramTxt4;
   @FXML
   private TextArea ramTxt5;

   @FXML
   private TextField ramMyId;
   @FXML
   private TextField ramMyTxt;

   @FXML
   private ComboBox<String> ramComboBox;

   @FXML
   private Button ramSelect;

   @FXML
   private Button ramDelete;
   @FXML
   private Button ramExit;

   ///////////////////////// 5.cpu ����
   @FXML
   private ImageView cpuImage;

   @FXML
   private TextField cpuId1;
   @FXML
   private TextField cpuId2;
   @FXML
   private TextField cpuId3;
   @FXML
   private TextField cpuId4;
   @FXML
   private TextField cpuId5;

   @FXML
   private TextArea cpuTxt1;
   @FXML
   private TextArea cpuTxt2;
   @FXML
   private TextArea cpuTxt3;
   @FXML
   private TextArea cpuTxt4;
   @FXML
   private TextArea cpuTxt5;

   @FXML
   private TextField cpuMyId;
   @FXML
   private TextField cpuMyTxt;

   @FXML
   private ComboBox<String> cpuComboBox;

   @FXML
   private Button cpuSelect;

   @FXML
   private Button cpuDelete;
   @FXML
   private Button cpuExit;

   ///////////////////////// 6. vga ����
   @FXML
   private ImageView vgaImage;

   @FXML
   private TextField vgaId1;
   @FXML
   private TextField vgaId2;
   @FXML
   private TextField vgaId3;
   @FXML
   private TextField vgaId4;
   @FXML
   private TextField vgaId5;

   @FXML
   private TextArea vgaTxt1;
   @FXML
   private TextArea vgaTxt2;
   @FXML
   private TextArea vgaTxt3;
   @FXML
   private TextArea vgaTxt4;
   @FXML
   private TextArea vgaTxt5;

   @FXML
   private TextField vgaMyId;
   @FXML
   private TextField vgaMyTxt;

   @FXML
   private ComboBox<String> vgaComboBox;

   @FXML
   private Button vgaSelect;

   @FXML
   private Button vgaDelete;
   @FXML
   private Button vgaExit;

   ///////////////////////// 7. hdd ����
   @FXML
   private ImageView hddImage;

   @FXML
   private TextField hddId1;
   @FXML
   private TextField hddId2;
   @FXML
   private TextField hddId3;
   @FXML
   private TextField hddId4;
   @FXML
   private TextField hddId5;

   @FXML
   private TextArea hddTxt1;
   @FXML
   private TextArea hddTxt2;
   @FXML
   private TextArea hddTxt3;
   @FXML
   private TextArea hddTxt4;
   @FXML
   private TextArea hddTxt5;

   @FXML
   private TextField hddMyId;
   @FXML
   private TextField hddMyTxt;

   @FXML
   private ComboBox<String> hddComboBox;

   @FXML
   private Button hddSelect;

   @FXML
   private Button hddDelete;
   @FXML
   private Button hddExit;

   ///////////////////////// 8. ssd ����
   @FXML
   private ImageView ssdImage;

   @FXML
   private TextField ssdId1;
   @FXML
   private TextField ssdId2;
   @FXML
   private TextField ssdId3;
   @FXML
   private TextField ssdId4;
   @FXML
   private TextField ssdId5;

   @FXML
   private TextArea ssdTxt1;
   @FXML
   private TextArea ssdTxt2;
   @FXML
   private TextArea ssdTxt3;
   @FXML
   private TextArea ssdTxt4;
   @FXML
   private TextArea ssdTxt5;

   @FXML
   private TextField ssdMyId;
   @FXML
   private TextField ssdMyTxt;

   @FXML
   private ComboBox<String> ssdComboBox;

   @FXML
   private Button ssdSelect;

   @FXML
   private Button ssdDelete;
   @FXML
   private Button ssdExit;

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      // 1. tabPane ��� �̺�Ʈ ó��
      tabEvent();

      // 2. ���� �̺�Ʈ ó��

   }

   // 1. tabPane ��� �̺�Ʈ ó��
   public void tabEvent() {
      /////////////////////////////////////////////////////////////////////////// ���̽�
      ObservableList<String> list = FXCollections.observableArrayList();

      list.add("ABKO NCORE");
      list.add("DAVEN ũ����Ż");
      list.add("DAVEN FT707");
      list.add("���̱��� G50SE");
      list.add("����ũ�δн� ���϶�");

      caseComboBox.setItems(list);

      caseMyId.setText("mu3386");
      mbMyId.setText("mu3386");
      pwMyId.setText("mu3386");
      ramMyId.setText("mu3386");
      cpuMyId.setText("mu3386");
      vgaMyId.setText("mu3386");
      hddMyId.setText("mu3386");
      ssdMyId.setText("mu3386");
      
      
      // ó�� ������ �ʵ� ���Ƴ���
      initSetting(true);

      caseComboBox.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
        	 
            // �ʵ� Ǯ��
            initcaseSetting(false);
            caseId1.setText("");
            caseTxt1.setText("");
            caseMyId.setText("");
            caseMyTxt.setText("");
            int caseData = caseComboBox.getSelectionModel().getSelectedIndex();
            String selectedcase = caseComboBox.getValue(); // Ȯ��
            ArrayList<reviewVO> list = ReviewDAO.selectReviewList(selectedcase);
//            System.out.println(list.get(0).getReview());
//            System.out.println(list.get(0).getMemberID());

            if (caseData == 0) {

               caseImage.setImage(new Image(getClass().getResource("/comImages/c1.jpg").toString()));
               
               caseMyId.setText("mu3386");
               
               caseId2.setText(list.get(0).getMemberID());
               caseTxt2.setText(list.get(0).getReview());
               caseId3.setText(list.get(1).getMemberID());
               caseTxt3.setText(list.get(1).getReview());
               caseId4.setText(list.get(2).getMemberID());
               caseTxt4.setText(list.get(2).getReview());
               
               caseId5.setText(list.get(3).getMemberID());
               caseTxt5.setText(list.get(3).getReview());

            } else if (caseData == 1) {

               caseImage.setImage(new Image(getClass().getResource("/comImages/c2.jpg").toString()));

               caseMyId.setText("mu3386");
               
               caseId2.setText(list.get(0).getMemberID());
               caseTxt2.setText(list.get(0).getReview());
               caseId3.setText(list.get(1).getMemberID());
               caseTxt3.setText(list.get(1).getReview());
               caseId4.setText(list.get(2).getMemberID());
               caseTxt4.setText(list.get(2).getReview());
               caseId5.setText(list.get(3).getMemberID());
               caseTxt5.setText(list.get(3).getReview());

            } else if (caseData == 2) {

               caseImage.setImage(new Image(getClass().getResource("/comImages/c3.jpg").toString()));
               
               caseMyId.setText("mu3386");
               
               caseId2.setText(list.get(0).getMemberID());
               caseTxt2.setText(list.get(0).getReview());
               caseId3.setText(list.get(1).getMemberID());
               caseTxt3.setText(list.get(1).getReview());
               caseId4.setText(list.get(2).getMemberID());
               caseTxt4.setText(list.get(2).getReview());
               caseId5.setText(list.get(3).getMemberID());
               caseTxt5.setText(list.get(3).getReview());

            } else if (caseData == 3) {

               caseImage.setImage(new Image(getClass().getResource("/comImages/c4.jpg").toString()));
               
               caseMyId.setText("mu3386");
               
               caseId2.setText(list.get(0).getMemberID());
               caseTxt2.setText(list.get(0).getReview());
               caseId3.setText(list.get(1).getMemberID());
               caseTxt3.setText(list.get(1).getReview());
               caseId4.setText(list.get(2).getMemberID());
               caseTxt4.setText(list.get(2).getReview());
               caseId5.setText(list.get(3).getMemberID());
               caseTxt5.setText(list.get(3).getReview());
            } else if (caseData == 4) {

               caseImage.setImage(new Image(getClass().getResource("/comImages/c5.jpg").toString()));
               
               caseMyId.setText("mu3386");
               
               caseId2.setText(list.get(0).getMemberID());
               caseTxt2.setText(list.get(0).getReview());
               caseId3.setText(list.get(1).getMemberID());
               caseTxt3.setText(list.get(1).getReview());
               caseId4.setText(list.get(2).getMemberID());
               caseTxt4.setText(list.get(2).getReview());
               caseId5.setText(list.get(3).getMemberID());
               caseTxt5.setText(list.get(3).getReview());
            }
         }
      });

      ///////////////////////////////////////////////////////////////////////// ���κ���
      ObservableList<String> list2 = FXCollections.observableArrayList();

      list2.add("GIGABYTE B365M");
      list2.add("MSI H310M");
      list2.add("MSI MPG X570");
      list2.add("ASUS PRIME");
      list2.add("MSI MAG B450");

      mbComboBox.setItems(list2);

      mbComboBox.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {

            initMainboardSetting(false);

            mbId1.setText("");
            mbTxt1.setText("");
            mbMyId.setText("");
            mbMyTxt.setText("");

            mbId1.setText("");
            mbTxt1.setText("");
            mbMyId.setText("");
            mbMyTxt.setText("");
            int mbData = mbComboBox.getSelectionModel().getSelectedIndex();
            String selectedboard = mbComboBox.getValue();
            ArrayList<reviewVO> list = ReviewDAO.selectReviewList(selectedboard);
            mbId2.setText(list.get(0).getMemberID());
            mbTxt2.setText(list.get(0).getReview());
            mbId3.setText(list.get(1).getMemberID());
            mbTxt3.setText(list.get(1).getReview());
            mbId4.setText(list.get(2).getMemberID());
            mbTxt4.setText(list.get(2).getReview());
            mbId5.setText(list.get(3).getMemberID());
            mbTxt5.setText(list.get(3).getReview());

            if (mbData == 0) {
               mbImage.setImage(new Image(getClass().getResource("/comImages/m1.jpg").toString()));
               mbMyId.setText("mu3386");
            } else if (mbData == 1) {
               mbImage.setImage(new Image(getClass().getResource("/comImages/m2.jpg").toString()));
               mbMyId.setText("mu3386");
            } else if (mbData == 2) {
               mbImage.setImage(new Image(getClass().getResource("/comImages/m3.jpg").toString()));
               mbMyId.setText("mu3386");
            } else if (mbData == 3) {
               mbImage.setImage(new Image(getClass().getResource("/comImages/m4.jpg").toString()));
               mbMyId.setText("mu3386");
            } else if (mbData == 4) {
               mbImage.setImage(new Image(getClass().getResource("/comImages/m5.jpg").toString()));
               mbMyId.setText("mu3386");
            }

         }
      });

      /////////////////////////////////////////////////////////////////////////// �Ŀ�
      ObservableList<String> list3 = FXCollections.observableArrayList();

      list3.add("����ũ�δн� Classic");
      list3.add("FSP HYPER K");
      list3.add("�߸� EcoMax 500W");
      list3.add("EVGA 750 GQ");
      list3.add("ABKO SUITMASTER");

      pwComboBox.setItems(list3);

      pwComboBox.setOnAction((e) -> {
         power();
      });

      /////////////////////////////////////////////////////////////////////////// ram
      ObservableList<String> list4 = FXCollections.observableArrayList();

      list4.add("�Ｚ���� DDR4 16G");
      list4.add("GeIL DDR4 8G");
      list4.add("ADATA XPG DDR4 16G");
      list4.add("TeamGroup T-Force 16G");

      ramComboBox.setItems(list4);

      ramComboBox.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            initRamSetting(false);

            ramId1.setText("");
            ramTxt1.setText("");
            ramMyId.setText("");
            ramMyTxt.setText("");

            int ramData = ramComboBox.getSelectionModel().getSelectedIndex();
            String selectedram = ramComboBox.getValue();
            ArrayList<reviewVO> list = ReviewDAO.selectReviewList(selectedram);
            ramId2.setText(list.get(0).getMemberID());
            ramTxt2.setText(list.get(0).getReview());
            ramId3.setText(list.get(1).getMemberID());
            ramTxt3.setText(list.get(1).getReview());
            ramId4.setText(list.get(2).getMemberID());
            ramTxt4.setText(list.get(2).getReview());
            ramId5.setText(list.get(3).getMemberID());
            ramTxt5.setText(list.get(3).getReview());

            if (ramData == 0) {
               ramImage.setImage(new Image(getClass().getResource("/comImages/r1.jpg").toString()));
               ramMyId.setText("mu3386");
            } else if (ramData == 1) {
               ramImage.setImage(new Image(getClass().getResource("/comImages/r2.jpg").toString()));
               ramMyId.setText("mu3386");
            } else if (ramData == 2) {
               ramImage.setImage(new Image(getClass().getResource("/comImages/r3.jpg").toString()));
               ramMyId.setText("mu3386");
            } else if (ramData == 3) {
               ramImage.setImage(new Image(getClass().getResource("/comImages/r4.jpg").toString()));
               ramMyId.setText("mu3386");
            }
         }
      });

      /////////////////////////////////////////////////////////////////////////// cpu
      ObservableList<String> list5 = FXCollections.observableArrayList();

      list5.add("AMD ������ 5 2600");
      list5.add("AMD ������ 5 3600");
      list5.add("���� �ھ�i7-9����");
      list5.add("AMD ������ 7 3700X");
      list5.add("���� �ھ�i5-9����");

      cpuComboBox.setItems(list5);

      cpuComboBox.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            initCpuSetting(false);

            cpuId1.setText("");
            cpuTxt1.setText("");
            cpuMyId.setText("");
            cpuMyTxt.setText("");

            int cpuData = cpuComboBox.getSelectionModel().getSelectedIndex();
            String selectedcpu = cpuComboBox.getValue();
            ArrayList<reviewVO> list = ReviewDAO.selectReviewList(selectedcpu);
            cpuId2.setText(list.get(0).getMemberID());
            cpuTxt2.setText(list.get(0).getReview());
            cpuId3.setText(list.get(1).getMemberID());
            cpuTxt3.setText(list.get(1).getReview());
            cpuId4.setText(list.get(2).getMemberID());
            cpuTxt4.setText(list.get(2).getReview());
            cpuId5.setText(list.get(3).getMemberID());
            cpuTxt5.setText(list.get(3).getReview());

            if (cpuData == 0) {
               cpuImage.setImage(new Image(getClass().getResource("/comImages/u1.jpg").toString()));
               cpuMyId.setText("mu3386");
            } else if (cpuData == 1) {
               cpuImage.setImage(new Image(getClass().getResource("/comImages/u2.jpg").toString()));
               cpuMyId.setText("mu3386");
            } else if (cpuData == 2) {
               cpuImage.setImage(new Image(getClass().getResource("/comImages/u3.jpg").toString()));
               cpuMyId.setText("mu3386");
            } else if (cpuData == 3) {
               cpuImage.setImage(new Image(getClass().getResource("/comImages/u4.jpg").toString()));
               cpuMyId.setText("mu3386");
            } else if (cpuData == 4) {
               cpuImage.setImage(new Image(getClass().getResource("/comImages/u5.jpg").toString()));
               cpuMyId.setText("mu3386");
            }
         }
      });

      /////////////////////////////////////////////////////////////////////////// vga
      ObservableList<String> list6 = FXCollections.observableArrayList();

      list6.add("�̿��� XENON GTX 1660 Ti");
      list6.add("�̿��� HV ������ GTX 1660");
      list6.add("������ GALAX RTX 2070");
      list6.add("ZOTAC GAMING RTX 2060");
      list6.add("MSI ������ RTX 2070");

      vgaComboBox.setItems(list6);

      vgaComboBox.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            initVgaSetting(false);

            vgaId1.setText("");
            vgaTxt1.setText("");
            vgaMyId.setText("");
            vgaMyTxt.setText("");

            int vgaData = vgaComboBox.getSelectionModel().getSelectedIndex();
            String selectedvga = vgaComboBox.getValue();
            ArrayList<reviewVO> list = ReviewDAO.selectReviewList(selectedvga);
            vgaId2.setText(list.get(0).getMemberID());
            vgaTxt2.setText(list.get(0).getReview());
            vgaId3.setText(list.get(1).getMemberID());
            vgaTxt3.setText(list.get(1).getReview());
            vgaId4.setText(list.get(2).getMemberID());
            vgaTxt4.setText(list.get(2).getReview());
            vgaId5.setText(list.get(3).getMemberID());
            vgaTxt5.setText(list.get(3).getReview());

            if (vgaData == 0) {
               vgaImage.setImage(new Image(getClass().getResource("/comImages/v1.jpg").toString()));
               vgaMyId.setText("mu3386");
            } else if (vgaData == 1) {
               vgaImage.setImage(new Image(getClass().getResource("/comImages/v2.jpg").toString()));
               vgaMyId.setText("mu3386");
            } else if (vgaData == 2) {
               vgaImage.setImage(new Image(getClass().getResource("/comImages/v3.jpg").toString()));
               vgaMyId.setText("mu3386");
            } else if (vgaData == 3) {
               vgaImage.setImage(new Image(getClass().getResource("/comImages/v4.jpg").toString()));
               vgaMyId.setText("mu3386");
            } else if (vgaData == 4) {
               vgaImage.setImage(new Image(getClass().getResource("/comImages/v5.jpg").toString()));
               vgaMyId.setText("mu3386");
            }
         }
      });

      /////////////////////////////////////////////////////////////////////////// hdd
      ObservableList<String> list7 = FXCollections.observableArrayList();

      list7.add("Seagate 2TB");
      list7.add("Toshiba 4TB");
      list7.add("SWestern Digital 4TB");

      hddComboBox.setItems(list7);

      hddComboBox.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            initHddSetting(false);

            hddId1.setText("");
            hddTxt1.setText("");
            hddMyId.setText("");
            hddMyTxt.setText("");

            int hddData = hddComboBox.getSelectionModel().getSelectedIndex();
            String selectedhdd = hddComboBox.getValue();
            ArrayList<reviewVO> list = ReviewDAO.selectReviewList(selectedhdd);
            hddId2.setText(list.get(0).getMemberID());
            hddTxt2.setText(list.get(0).getReview());
            hddId3.setText(list.get(1).getMemberID());
            hddTxt3.setText(list.get(1).getReview());
            hddId4.setText(list.get(2).getMemberID());
            hddTxt4.setText(list.get(2).getReview());
            hddId5.setText(list.get(3).getMemberID());
            hddTxt5.setText(list.get(3).getReview());

            if (hddData == 0) {
               hddImage.setImage(new Image(getClass().getResource("/comImages/h1.jpg").toString()));
               hddMyId.setText("mu3386");
            } else if (hddData == 1) {
               hddImage.setImage(new Image(getClass().getResource("/comImages/h2.jpg").toString()));
               hddMyId.setText("mu3386");
            } else if (hddData == 2) {
               hddImage.setImage(new Image(getClass().getResource("/comImages/h3.jpg").toString()));
               hddMyId.setText("mu3386");
            }
         }
      });

      /////////////////////////////////////////////////////////////////////////// ssd
      ObservableList<String> list8 = FXCollections.observableArrayList();

      list8.add("����ũ�� Crucial");
      list8.add("�Ｚ���� 860 EVO");
      list8.add("�Ｚ���� 970 EVO");
      list8.add("Ÿ���� GK300");

      ssdComboBox.setItems(list8);
      ssdComboBox.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            initSsdSetting(false);

            ssdId1.setText("");
            ssdTxt1.setText("");
            ssdMyId.setText("");
            ssdMyTxt.setText("");

            int ssdData = ssdComboBox.getSelectionModel().getSelectedIndex();
            String selectedssd = ssdComboBox.getValue();
            ArrayList<reviewVO> list = ReviewDAO.selectReviewList(selectedssd);
            ssdId2.setText(list.get(0).getMemberID());
            ssdTxt2.setText(list.get(0).getReview());
            ssdId3.setText(list.get(1).getMemberID());
            ssdTxt3.setText(list.get(1).getReview());
            ssdId4.setText(list.get(2).getMemberID());
            ssdTxt4.setText(list.get(2).getReview());
            ssdId5.setText(list.get(3).getMemberID());
            ssdTxt5.setText(list.get(3).getReview());

            if (ssdData == 0) {
               ssdImage.setImage(new Image(getClass().getResource("/comImages/s1.jpg").toString()));
               ssdMyId.setText("mu3386");
            } else if (ssdData == 1) {
               ssdImage.setImage(new Image(getClass().getResource("/comImages/s2.jpg").toString()));
               ssdMyId.setText("mu3386");
            } else if (ssdData == 2) {
               ssdImage.setImage(new Image(getClass().getResource("/comImages/s3.jpg").toString()));
               ssdMyId.setText("mu3386");
            } else if (ssdData == 3) {
               ssdImage.setImage(new Image(getClass().getResource("/comImages/s4.jpg").toString()));
               ssdMyId.setText("mu3386");
            }
         }
      });

      //////////////////////////////////////////////////////////////////// ���̽�
      caseSelect.setOnMouseClicked(new EventHandler<Event>() {// ��Ϲ�ư

         @Override
         public void handle(Event event) {
            String id = caseMyId.getText();
            String txt = caseMyTxt.getText();
            String selectedCaseProd = caseComboBox.getValue();
            if (id.equals("")) {
               DBUtil.alertDisplay(1, "���", "���̵����������", "���̵��Է����ּ���");
               return;
            }

            if (txt.equals("")) {
               DBUtil.alertDisplay(1, "���", "���䳻�밡���������", "���븦�Է����ּ���");
               return;
            }

            reviewVO re = new reviewVO(selectedCaseProd, id, txt);

            int count = ReviewDAO.insertReviewData(re); // �����ͺ��̽� ���
            if (count != 0) {
               memManageController.alertDisplay(5, "���", "��� �Ϸ�", "����� �Ϸ��Ͽ����ϴ�.");
            }

            ArrayList<reviewVO> list = ReviewDAO.selectReview(caseMyTxt.getText());
            caseId1.setText(list.get(0).getMemberID());
            caseTxt1.setText(list.get(0).getReview());

         }// end of handle
      });
      caseDelete.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {/////////// ������ư
            ReviewDAO.deleteReviewData(caseMyTxt.getText());
            caseId1.setText("");
            caseTxt1.setText("");
            caseMyId.setText("");
            caseMyTxt.setText("");
         }
      });
      caseExit.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {//////////// �������ư
            ((Stage) caseExit.getScene().getWindow()).close();
         }
      });

      //////////////////////////////////////////////////////////////////// ���κ���
      mbSelect.setOnMouseClicked(new EventHandler<Event>() {// ��Ϲ�ư

         @Override
         public void handle(Event event) {
            String id = mbMyId.getText();
            String txt = mbMyTxt.getText();
            String selectedmbProd = mbComboBox.getValue();
            if (id.equals("")) {
               DBUtil.alertDisplay(1, "���", "���̵����������", "���̵��Է����ּ���");
               return;
            }

            if (txt.equals("")) {
               DBUtil.alertDisplay(1, "���", "���䳻�밡���������", "���븦�Է����ּ���");
               return;
            }

            reviewVO re = new reviewVO(selectedmbProd, id, txt);

            int count = ReviewDAO.insertReviewData(re); // �����ͺ��̽� ���
            if (count != 0) {
               memManageController.alertDisplay(5, "���", "��� �Ϸ�", "����� �Ϸ��Ͽ����ϴ�.");
            }

            ArrayList<reviewVO> list = ReviewDAO.selectReview(mbMyTxt.getText());
            mbId1.setText(list.get(0).getMemberID());
            mbTxt1.setText(list.get(0).getReview());

         }// end of handle
      });
      mbDelete.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {/////////// ������ư
            ReviewDAO.deleteReviewData(mbMyTxt.getText());
            mbId1.setText("");
            mbTxt1.setText("");
            mbMyId.setText("");
            mbMyTxt.setText("");
         }
      });
      mbExit.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {//////////// �������ư
            ((Stage) mbExit.getScene().getWindow()).close();
         }
      });
      //////////////////////////////////////////////////////////////////////////////////// ram
      ramSelect.setOnMouseClicked(new EventHandler<Event>() {// ��Ϲ�ư

         @Override
         public void handle(Event event) {
            String id = ramMyId.getText();
            String txt = ramMyTxt.getText();
            String selectedramProd = ramComboBox.getValue();
            if (id.equals("")) {
               DBUtil.alertDisplay(1, "���", "���̵����������", "���̵��Է����ּ���");
               return;
            }

            if (txt.equals("")) {
               DBUtil.alertDisplay(1, "���", "���䳻�밡���������", "���븦�Է����ּ���");
               return;
            }

            reviewVO re = new reviewVO(selectedramProd, id, txt);

            int count = ReviewDAO.insertReviewData(re); // �����ͺ��̽� ���
            if (count != 0) {
               memManageController.alertDisplay(5, "���", "��� �Ϸ�", "����� �Ϸ��Ͽ����ϴ�.");
            }

            ArrayList<reviewVO> list = ReviewDAO.selectReview(ramMyTxt.getText());
            ramId1.setText(list.get(0).getMemberID());
            ramTxt1.setText(list.get(0).getReview());

         }// end of handle
      });
      ramDelete.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {/////////// ������ư
            ReviewDAO.deleteReviewData(ramMyTxt.getText());
            ramId1.setText("");
            ramTxt1.setText("");
            ramMyId.setText("");
            ramMyTxt.setText("");
         }
      });
      ramExit.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {//////////// �������ư
            ((Stage) ramExit.getScene().getWindow()).close();
         }
      });
      //////////////////////////////////////////////////////////////////// cpu
      
      cpuSelect.setOnMouseClicked(new EventHandler<Event>() {// ��Ϲ�ư

         @Override
         public void handle(Event event) {
            String id = cpuMyId.getText();
            String txt = cpuMyTxt.getText();
            String selectedcpuProd = cpuComboBox.getValue();
            if (id.equals("")) {
               DBUtil.alertDisplay(1, "���", "���̵����������", "���̵��Է����ּ���");
               return;
            }

            if (txt.equals("")) {
               DBUtil.alertDisplay(1, "���", "���䳻�밡���������", "���븦�Է����ּ���");
               return;
            }

            reviewVO re = new reviewVO(selectedcpuProd, id, txt);

            int count = ReviewDAO.insertReviewData(re); // �����ͺ��̽� ���
            if (count != 0) {
               memManageController.alertDisplay(5, "���", "��� �Ϸ�", "����� �Ϸ��Ͽ����ϴ�.");
            }

            ArrayList<reviewVO> list = ReviewDAO.selectReview(cpuMyTxt.getText());
            cpuId1.setText(list.get(0).getMemberID());
            cpuTxt1.setText(list.get(0).getReview());

         }// end of handle
      });
      cpuDelete.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {/////////// ������ư
            ReviewDAO.deleteReviewData(cpuMyTxt.getText());
            cpuId1.setText("");
            cpuTxt1.setText("");
            cpuMyId.setText("");
            cpuMyTxt.setText("");
         }
      });
      cpuExit.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {//////////// �������ư
            ((Stage) cpuExit.getScene().getWindow()).close();
         }
      });
      //////////////////////////////////////////////////////////////////// vga
      vgaSelect.setOnMouseClicked(new EventHandler<Event>() {// ��Ϲ�ư

         @Override
         public void handle(Event event) {
            String id = vgaMyId.getText();
            String txt = vgaMyTxt.getText();
            String selectedvgaProd = vgaComboBox.getValue();
            if (id.equals("")) {
               DBUtil.alertDisplay(1, "���", "���̵����������", "���̵��Է����ּ���");
               return;
            }

            if (txt.equals("")) {
               DBUtil.alertDisplay(1, "���", "���䳻�밡���������", "���븦�Է����ּ���");
               return;
            }

            reviewVO re = new reviewVO(selectedvgaProd, id, txt);

            int count = ReviewDAO.insertReviewData(re); // �����ͺ��̽� ���
            if (count != 0) {
               memManageController.alertDisplay(5, "���", "��� �Ϸ�", "����� �Ϸ��Ͽ����ϴ�.");
            }

            ArrayList<reviewVO> list = ReviewDAO.selectReview(vgaMyTxt.getText());
            vgaId1.setText(list.get(0).getMemberID());
            vgaTxt1.setText(list.get(0).getReview());

         }// end of handle
      });
      vgaDelete.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {/////////// ������ư
            ReviewDAO.deleteReviewData(vgaMyTxt.getText());
            vgaId1.setText("");
            vgaTxt1.setText("");
            vgaMyId.setText("");
            vgaMyTxt.setText("");
         }
      });
      vgaExit.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {//////////// �������ư
            ((Stage) vgaExit.getScene().getWindow()).close();
         }
      });
      //////////////////////////////////////////////////////////////////// hdd
      hddSelect.setOnMouseClicked(new EventHandler<Event>() {// ��Ϲ�ư

         @Override
         public void handle(Event event) {
            String id = hddMyId.getText();
            String txt = hddMyTxt.getText();
            String selectedhddProd = hddComboBox.getValue();
            if (id.equals("")) {
               DBUtil.alertDisplay(1, "���", "���̵����������", "���̵��Է����ּ���");
               return;
            }

            if (txt.equals("")) {
               DBUtil.alertDisplay(1, "���", "���䳻�밡���������", "���븦�Է����ּ���");
               return;
            }

            reviewVO re = new reviewVO(selectedhddProd, id, txt);

            int count = ReviewDAO.insertReviewData(re); // �����ͺ��̽� ���
            if (count != 0) {
               memManageController.alertDisplay(5, "���", "��� �Ϸ�", "����� �Ϸ��Ͽ����ϴ�.");
            }

            ArrayList<reviewVO> list = ReviewDAO.selectReview(hddMyTxt.getText());
            hddId1.setText(list.get(0).getMemberID());
            hddTxt1.setText(list.get(0).getReview());

         }// end of handle
      });
      hddDelete.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {/////////// ������ư
            ReviewDAO.deleteReviewData(hddMyTxt.getText());
            hddId1.setText("");
            hddTxt1.setText("");
            hddMyId.setText("");
            hddMyTxt.setText("");
         }
      });
      hddExit.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {//////////// �������ư
            ((Stage) hddExit.getScene().getWindow()).close();
         }
      });
      //////////////////////////////////////////////////////////////////// ssd
      ssdSelect.setOnMouseClicked(new EventHandler<Event>() {// ��Ϲ�ư

         @Override
         public void handle(Event event) {
            String id = ssdMyId.getText();
            String txt = ssdMyTxt.getText();
            String selectedssdProd = ssdComboBox.getValue();
            if (id.equals("")) {
               DBUtil.alertDisplay(1, "���", "���̵����������", "���̵��Է����ּ���");
               return;
            }

            if (txt.equals("")) {
               DBUtil.alertDisplay(1, "���", "���䳻�밡���������", "���븦�Է����ּ���");
               return;
            }

            reviewVO re = new reviewVO(selectedssdProd, id, txt);

            int count = ReviewDAO.insertReviewData(re); // �����ͺ��̽� ���
            if (count != 0) {
               memManageController.alertDisplay(5, "���", "��� �Ϸ�", "����� �Ϸ��Ͽ����ϴ�.");
            }

            ArrayList<reviewVO> list = ReviewDAO.selectReview(ssdMyTxt.getText());
            ssdId1.setText(list.get(0).getMemberID());
            ssdTxt1.setText(list.get(0).getReview());

         }// end of handle
      });
      ssdDelete.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {/////////// ������ư
            ReviewDAO.deleteReviewData(ssdMyTxt.getText());
            ssdId1.setText("");
            ssdTxt1.setText("");
            ssdMyId.setText("");
            ssdMyTxt.setText("");
         }
      });
      ssdExit.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {//////////// �������ư
            ((Stage) ssdExit.getScene().getWindow()).close();
         }
      });

   }// end of tabCaseEvent

   // ó�� ������ �ʵ� ���Ƴ���
   public boolean initSetting(boolean t) {
      ///////////////////////////// ���̽�
      caseId1.setDisable(true);
      caseTxt1.setDisable(true);
      caseId2.setDisable(true);
      caseTxt2.setDisable(true);
      caseId3.setDisable(true);
      caseTxt3.setDisable(true);
      caseId4.setDisable(true);
      caseTxt4.setDisable(true);
      caseId5.setDisable(true);
      caseTxt5.setDisable(true);
      ///////////////////////////// ���κ���
      mbId1.setDisable(true);
      mbTxt1.setDisable(true);
      mbId2.setDisable(true);
      mbTxt2.setDisable(true);
      mbId3.setDisable(true);
      mbTxt3.setDisable(true);
      mbId4.setDisable(true);
      mbTxt4.setDisable(true);
      mbId5.setDisable(true);
      mbTxt5.setDisable(true);
      ///////////////////////////// �Ŀ�
      pwId1.setDisable(true);
      pwTxt1.setDisable(true);
      pwId2.setDisable(true);
      pwTxt2.setDisable(true);
      pwId3.setDisable(true);
      pwTxt3.setDisable(true);
      pwId4.setDisable(true);
      pwTxt4.setDisable(true);
      pwId5.setDisable(true);
      pwTxt5.setDisable(true);
      ///////////////////////////// ��
      ramId1.setDisable(true);
      ramTxt1.setDisable(true);
      ramId2.setDisable(true);
      ramTxt2.setDisable(true);
      ramId3.setDisable(true);
      ramTxt3.setDisable(true);
      ramId4.setDisable(true);
      ramTxt4.setDisable(true);
      ramId5.setDisable(true);
      ramTxt5.setDisable(true);
      ///////////////////////////// cpu
      cpuId1.setDisable(true);
      cpuTxt1.setDisable(true);
      cpuId2.setDisable(true);
      cpuTxt2.setDisable(true);
      cpuId3.setDisable(true);
      cpuTxt3.setDisable(true);
      cpuId4.setDisable(true);
      cpuTxt4.setDisable(true);
      cpuId5.setDisable(true);
      cpuTxt5.setDisable(true);
      ///////////////////////////// vga
      vgaId1.setDisable(true);
      vgaTxt1.setDisable(true);
      vgaId2.setDisable(true);
      vgaTxt2.setDisable(true);
      vgaId3.setDisable(true);
      vgaTxt3.setDisable(true);
      vgaId4.setDisable(true);
      vgaTxt4.setDisable(true);
      vgaId5.setDisable(true);
      vgaTxt5.setDisable(true);
      ///////////////////////////// hdd
      hddId1.setDisable(true);
      hddTxt1.setDisable(true);
      hddId2.setDisable(true);
      hddTxt2.setDisable(true);
      hddId3.setDisable(true);
      hddTxt3.setDisable(true);
      hddId4.setDisable(true);
      hddTxt4.setDisable(true);
      hddId5.setDisable(true);
      hddTxt5.setDisable(true);
      ///////////////////////////// ssd
      ssdId1.setDisable(true);
      ssdTxt1.setDisable(true);
      ssdId2.setDisable(true);
      ssdTxt2.setDisable(true);
      ssdId3.setDisable(true);
      ssdTxt3.setDisable(true);
      ssdId4.setDisable(true);
      ssdTxt4.setDisable(true);
      ssdId5.setDisable(true);
      ssdTxt5.setDisable(true);

      return t;
   }

   // ���̽� �ʵ� Ǯ��
   public boolean initcaseSetting(boolean f) {

      caseId1.setDisable(false);
      caseTxt1.setDisable(false);
      caseId2.setDisable(false);
      caseTxt2.setDisable(false);
      caseId3.setDisable(false);
      caseTxt3.setDisable(false);
      caseId4.setDisable(false);
      caseTxt4.setDisable(false);
      caseId5.setDisable(false);
      caseTxt5.setDisable(false);

      return f;
   }

   // ���κ��� �ʵ� Ǯ��
   public boolean initMainboardSetting(boolean f) {

      mbId1.setDisable(false);
      mbTxt1.setDisable(false);
      mbId2.setDisable(false);
      mbTxt2.setDisable(false);
      mbId3.setDisable(false);
      mbTxt3.setDisable(false);
      mbId4.setDisable(false);
      mbTxt4.setDisable(false);
      mbId5.setDisable(false);
      mbTxt5.setDisable(false);

      return f;
   }

   // �Ŀ� �ʵ� Ǯ��
   public boolean initPowerSetting(boolean f) {

      pwId1.setDisable(false);
      pwTxt1.setDisable(false);
      pwId2.setDisable(false);
      pwTxt2.setDisable(false);
      pwId3.setDisable(false);
      pwTxt3.setDisable(false);
      pwId4.setDisable(false);
      pwTxt4.setDisable(false);
      pwId5.setDisable(false);
      pwTxt5.setDisable(false);

      return f;
   }

   // �� �ʵ� Ǯ��
   public boolean initRamSetting(boolean f) {

      ramId1.setDisable(false);
      ramTxt1.setDisable(false);
      ramId2.setDisable(false);
      ramTxt2.setDisable(false);
      ramId3.setDisable(false);
      ramTxt3.setDisable(false);
      ramId4.setDisable(false);
      ramTxt4.setDisable(false);
      ramId5.setDisable(false);
      ramTxt5.setDisable(false);

      return f;
   }

   // ������ �ʵ� Ǯ��
   public boolean initCpuSetting(boolean f) {

      cpuId1.setDisable(false);
      cpuTxt1.setDisable(false);
      cpuId2.setDisable(false);
      cpuTxt2.setDisable(false);
      cpuId3.setDisable(false);
      cpuTxt3.setDisable(false);
      cpuId4.setDisable(false);
      cpuTxt4.setDisable(false);
      cpuId5.setDisable(false);
      cpuTxt5.setDisable(false);

      return f;
   }

   // �׷���ī�� �ʵ� Ǯ��
   public boolean initVgaSetting(boolean f) {

      vgaId1.setDisable(false);
      vgaTxt1.setDisable(false);
      vgaId2.setDisable(false);
      vgaTxt2.setDisable(false);
      vgaId3.setDisable(false);
      vgaTxt3.setDisable(false);
      vgaId4.setDisable(false);
      vgaTxt4.setDisable(false);
      vgaId5.setDisable(false);
      vgaTxt5.setDisable(false);

      return f;
   }

   // �ϵ� ��ũ �ʵ� Ǯ��
   public boolean initHddSetting(boolean f) {

      hddId1.setDisable(false);
      hddTxt1.setDisable(false);
      hddId2.setDisable(false);
      hddTxt2.setDisable(false);
      hddId3.setDisable(false);
      hddTxt3.setDisable(false);
      hddId4.setDisable(false);
      hddTxt4.setDisable(false);
      hddId5.setDisable(false);
      hddTxt5.setDisable(false);

      return f;
   }

   // ssd �ʵ� Ǯ��
   public boolean initSsdSetting(boolean f) {

      ssdId1.setDisable(false);
      ssdTxt1.setDisable(false);
      ssdId2.setDisable(false);
      ssdTxt2.setDisable(false);
      ssdId3.setDisable(false);
      ssdTxt3.setDisable(false);
      ssdId4.setDisable(false);
      ssdTxt4.setDisable(false);
      ssdId5.setDisable(false);
      ssdTxt5.setDisable(false);

      return f;
   }

   // �Ŀ����ٽľ��Լ�
   public void power() {
	   
      initPowerSetting(false);

      pwId1.setText("");
      pwTxt1.setText("");
      pwMyId.setText("");
      pwMyTxt.setText("");

      int pwData = pwComboBox.getSelectionModel().getSelectedIndex();
      String selectedpower = pwComboBox.getValue();
      ArrayList<reviewVO> a = ReviewDAO.selectReviewList(selectedpower);

      pwId2.setText(a.get(0).getMemberID());
      pwTxt2.setText(a.get(0).getReview());
      pwId3.setText(a.get(1).getMemberID());
      pwTxt3.setText(a.get(1).getReview());
      pwId4.setText(a.get(2).getMemberID());
      pwTxt4.setText(a.get(2).getReview());
      pwId5.setText(a.get(3).getMemberID());
      pwTxt5.setText(a.get(3).getReview());

      if (pwData == 0) {
         pwImage.setImage(new Image(getClass().getResource("/comImages/p1.jpg").toString()));
         pwMyId.setText("mu3386");
      } else if (pwData == 1) {
         pwImage.setImage(new Image(getClass().getResource("/comImages/p2.jpg").toString()));
         pwMyId.setText("mu3386");
      } else if (pwData == 2) {
         pwImage.setImage(new Image(getClass().getResource("/comImages/p3.jpg").toString()));
         pwMyId.setText("mu3386");
      } else if (pwData == 3) {
         pwImage.setImage(new Image(getClass().getResource("/comImages/p4.jpg").toString()));
         pwMyId.setText("mu3386");
      } else if (pwData == 4) {
         pwImage.setImage(new Image(getClass().getResource("/comImages/p5.jpg").toString()));
         pwMyId.setText("mu3386");
      }
////////////////////////////////////////////////////////////////////�Ŀ�
      pwSelect.setOnMouseClicked(new EventHandler<Event>() {// ��Ϲ�ư

         @Override
         public void handle(Event event) {
            String id = pwMyId.getText();
            String txt = pwMyTxt.getText();
            String selectedpwProd = pwComboBox.getValue();
            if (id.equals("")) {
               DBUtil.alertDisplay(1, "���", "���̵����������", "���̵��Է����ּ���");
               return;
            }

            if (txt.equals("")) {
               DBUtil.alertDisplay(1, "���", "���䳻�밡���������", "���븦�Է����ּ���");
               return;
            }

            reviewVO re = new reviewVO(selectedpwProd, id, txt);

            int count = ReviewDAO.insertReviewData(re); // �����ͺ��̽� ���
            if (count != 0) {
               memManageController.alertDisplay(5, "���", "��� �Ϸ�", "����� �Ϸ��Ͽ����ϴ�.");
            }

            ArrayList<reviewVO> list = ReviewDAO.selectReview(pwMyTxt.getText());
            pwId1.setText(list.get(0).getMemberID());
            pwTxt1.setText(list.get(0).getReview());

         }// end of handle
      });
      pwDelete.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {/////////// ������ư
            ReviewDAO.deleteReviewData(pwMyTxt.getText());
            pwId1.setText("");
            pwTxt1.setText("");
            pwMyId.setText("");
            pwMyTxt.setText("");
         }
      });
      pwExit.setOnMouseClicked(new EventHandler<Event>() {

         @Override
         public void handle(Event event) {//////////// �������ư
            ((Stage) pwExit.getScene().getWindow()).close();
         }
      });
   }
}