package JavaFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        root.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        primaryStage.setTitle("Jeff's Fishing Shack");
        primaryStage.setScene(new Scene(root));

        primaryStage.show();

        /*Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }


    public static void main(String[] args) throws SQLException {
        launch(args);


//Controller controller=new Controller();
//controller.showData();



    }

}
