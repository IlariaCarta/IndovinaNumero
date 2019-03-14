package it.polito.tdp.numero;
	
import it.polito.tdp.numero.model.NumeroModel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Numero.fxml")); //lo abbiamo definito come oggetto. FXMLLoader è una classe che permette di creare
													// la nostra scena a partire da un file XML
			BorderPane root = (BorderPane)loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			NumeroModel model= new NumeroModel(); //creiamo il modello
			//definisco il controller
			NumeroController controller = loader.getController();
		
			controller.setModel(model);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
