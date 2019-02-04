package JavaFx;


import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;

public class Jeff extends Staff {
    protected static final String jeffId = "Jeff";
    protected static final String jeffPass = "Jeff123";
    public TextField staffId;
    public TextArea logFile;
    public TextField staffName;
    public PasswordField staffPass;
    public DatePicker log;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public Jeff() {
        super(jeffId);
    }

    @Override
    public void screen1(ActionEvent actionEvent) throws IOException {
        super.screen1(actionEvent);
    }

    public void open(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Query.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 600, 450);
        scene.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void addStaff() throws SQLException {


        String sql = "INSERT INTO stafflogin VALUES ('" + staffId.getText() + "','" + staffName.getText() + "','" + staffPass.getText() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Success!!");
        alert.setContentText("Staff added successfully");
        alert.showAndWait();


    }

    public void logFile(ActionEvent actionEvent) throws IOException, SQLException {

        LocalDate logDay = log.getValue();


        BufferedWriter bw = null;
        try {

            FileWriter filew = new FileWriter(new File("logFile.txt"), false);
            PrintWriter printw = new PrintWriter(filew, true);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product_order where orderDate='" + logDay + "';");

            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                String userId = resultSet.getString(2);
                String code = resultSet.getString(3);
                String name = resultSet.getString(4);
                String pSize = resultSet.getString(5);
                double quantity = resultSet.getDouble(6);
                double pTotal = resultSet.getDouble(7);
                printw.println(String.valueOf(new Order(date, userId, code, name, pSize, quantity, pTotal)));
            }


        } catch (IOException e) {
            e.printStackTrace();
            // pw.close();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Success!!");
        alert.setContentText("LOG FILE CREATED");
        alert.showAndWait();

    }


    public void correctRun(ActionEvent actionEvent) throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("Order.fxml"));
        Scene sign = new Scene(start);
        Stage primaryStage = new Stage();
        primaryStage.setScene(sign);
        primaryStage.show();

    }
}

