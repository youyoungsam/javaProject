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

		// 1. ��Ʈ �����̳�
		Parent root = FXMLLoader.load(getClass().getResource("/view/login_page.fxml")); // URL 

		// 2. ��
		Scene scene = new Scene(root);
		
		// 2-1. ���� �ܺν�Ÿ���� ����ִ´�. // ��Ÿ�Ͻ�Ʈ.
		scene.getStylesheets().add(getClass().getResource("font.css").toString());
		
		// 3. ��������
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login Page");
		// primaryStage.setResizable(true);
		primaryStage.show();

	}

}
