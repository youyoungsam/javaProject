package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// 1. 루트 컨테이너
		Parent root = FXMLLoader.load(getClass().getResource("/view/login_page.fxml")); // URL 

		// 2. 씬
		Scene scene = new Scene(root);
		
		// 2-1. 씬에 외부스타일을 집어넣는다. // 스타일시트.
		scene.getStylesheets().add(getClass().getResource("font.css").toString());
		
		// 3. 스테이지
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login Page");
		// primaryStage.setResizable(true);
		primaryStage.show();

	}

}
