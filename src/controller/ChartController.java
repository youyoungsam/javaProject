package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ChartController implements Initializable {

	// ====================== 차트 창 ====================== // 
	
	public Stage chartStage;
	
	@FXML private PieChart pieChart;
	@FXML private ComboBox<String> ComboBoxChart;
	@FXML private Button btnChartExit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// 1. pie 차트와 콤보박스 이벤트
		pieChartEvent();
		
		// 2. 창 나가기 이벤트
		btnChartExit.setOnAction( e -> handlerbtnChartExit(e));

	}

	// 1. pie 차트와 콤보박스 이벤트
	public void pieChartEvent() {
		
		ObservableList<String> list = FXCollections.observableArrayList();
		
		list.add("케이스");
		list.add("메인보드");
		list.add("파워");
		list.add("RAM");
		list.add("CPU");
		list.add("VGA");
		list.add("HDD");
		list.add("SSD");
		
		ComboBoxChart.setItems(list); 
		
		 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
			
		 pieChartList.add(new PieChart.Data("ABKO NCORE : 32%", 32));
		 pieChartList.add(new PieChart.Data("마이크로닉스 사하라 : 23%", 23));
		 pieChartList.add(new PieChart.Data("아이구주 G50SE : 21%", 21));
		 pieChartList.add(new PieChart.Data("DAVEN 크리스탈 3.0 : 14%", 14));
		 pieChartList.add(new PieChart.Data("DAVEN FT707 : 10%", 10));
			
		 pieChart.setData(pieChartList);
		
		 
		ComboBoxChart.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {

				 int chartData =ComboBoxChart.getSelectionModel().getSelectedIndex();
				 
				// 1. 케이스
				 if ( chartData == 0 ) {
					 	 
					 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
						
					 pieChartList.add(new PieChart.Data("ABKO NCORE : 32%", 32));
					 pieChartList.add(new PieChart.Data("마이크로닉스 사하라 : 23%", 23));
					 pieChartList.add(new PieChart.Data("아이구주 G50SE : 21%", 21));
					 pieChartList.add(new PieChart.Data("DAVEN 크리스탈 3.0 : 14%", 14));
					 pieChartList.add(new PieChart.Data("DAVEN FT707 : 10%", 10));
						
					 pieChart.setData(pieChartList);
						
				// 2. 메인보드
				 }else if( chartData == 1) {
					 
					 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
						
					 pieChartList.add(new PieChart.Data("GIGABYTE B36 : 28%", 28));
					 pieChartList.add(new PieChart.Data("MSI H310M : 27%", 27));
					 pieChartList.add(new PieChart.Data("MSI MPG X570 : 15%", 15));
					 pieChartList.add(new PieChart.Data("ASUS PRIME : 20%", 20));
					 pieChartList.add(new PieChart.Data("MSI MAG B450 : 10%", 10));
						
					 pieChart.setData(pieChartList);
				
				// 3. 파워
				 }else if( chartData == 2) {
					
					 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
						
					 pieChartList.add(new PieChart.Data("마이크로닉스 Classic II : 34%", 34));
					 pieChartList.add(new PieChart.Data("FSP HYPER K 600W : 21%", 21));
					 pieChartList.add(new PieChart.Data("잘만 EcoMax : 21%", 21));
					 pieChartList.add(new PieChart.Data("EVGA 750 : 14%", 14));
					 pieChartList.add(new PieChart.Data("ABKO SUITMASTER : 10%", 10));
						
					 pieChart.setData(pieChartList);
					 
				// 4. RAM
				 }else if( chartData == 3) {
					 
					 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
						
					 pieChartList.add(new PieChart.Data("삼성전자 DDR4 : 50%", 50));
					 pieChartList.add(new PieChart.Data("GeIL DDR4 : 23%", 23));
					 pieChartList.add(new PieChart.Data("ADATA XPG : 17%", 17));
					 pieChartList.add(new PieChart.Data("TeamGroup : 10%", 10));
						
					 pieChart.setData(pieChartList);
					 
				// 5. CPU
				 }else if( chartData == 4) {
					
					 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
						
					 pieChartList.add(new PieChart.Data("AMD 라이젠 5 26 : 32%", 32));
					 pieChartList.add(new PieChart.Data("AMD 라이젠 5 36 : 23%", 23));
					 pieChartList.add(new PieChart.Data("인텔 i7-9세대 : 21%", 21));
					 pieChartList.add(new PieChart.Data("AMD 라이젠 7 : 14%", 14));
					 pieChartList.add(new PieChart.Data("인텔 i5-9세대 : 10%", 10));
						
					 pieChart.setData(pieChartList);
					 
				// 6. VGA
				 }else if( chartData == 5) {
					 
					 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
						
					 pieChartList.add(new PieChart.Data("이엠텍 XENON GTX : 36%", 36));
					 pieChartList.add(new PieChart.Data("이엠텍 HV GTX : 23%", 23));
					 pieChartList.add(new PieChart.Data("갤럭시 GALAX RTX : 21%", 21));
					 pieChartList.add(new PieChart.Data("ZOTAC RTX 2060 : 10%", 10));
					 pieChartList.add(new PieChart.Data("MSI 지포스 RTX : 10%", 10));
						
					 pieChart.setData(pieChartList);
					 
				// 7. HDD	 
				 }else if( chartData == 6) {
					 
					 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
						
					 pieChartList.add(new PieChart.Data("Seagate 2TB : 40%", 40));
					 pieChartList.add(new PieChart.Data("Toshiba 4TB : 32%", 32));
					 pieChartList.add(new PieChart.Data("Western Digital 4TB : 28%", 28));
						
					 pieChart.setData(pieChartList);
					 
				// 8. SSD	 	 
				 }else if( chartData == 7) {
					 
					 ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
						
					 pieChartList.add(new PieChart.Data("마이크론 Crucial : 12%", 12));
					 pieChartList.add(new PieChart.Data("삼성전자 860 : 45%", 45));
					 pieChartList.add(new PieChart.Data("삼성전자 970 EVO : 35%", 35));
					 pieChartList.add(new PieChart.Data("타무즈 GK300 : 8%", 8));
						
					 pieChart.setData(pieChartList);
					 
				 }
					 
			}
		});
		
	}

	// 2. 창 나가기 이벤트
	public void handlerbtnChartExit(ActionEvent e) {
		
		((Stage) btnChartExit.getScene().getWindow()).close();
		
	}
	
	// ===================== 함수 기능 ======================== // 
	
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
	
}
