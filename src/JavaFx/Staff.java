package JavaFx;

import com.sun.org.apache.xpath.internal.operations.Or;
import connectivity.ConnectionClass;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff extends User {

    public DatePicker datePicker;
    public TextArea textA1;

    public TextField customerid;
    List<Order> list = new ArrayList<>();
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();


    public Staff(String uId) {

        super(uId);
    }

    public Staff() {
    }

    public void screen1(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene sign = new Scene(start);
        sign.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sign);
        window.show();

    }

    public void cusQ(javafx.event.ActionEvent actionEvent) throws SQLException {

        textA1.clear();
        LocalDate day = datePicker.getValue();

        String query = "select * from product_order where orderDate='" + day + "'OR user_id='" + customerid.getText() + "';";

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            textA1.appendText("No transactions made");
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {

            textA1.appendText("No transactions made");
        }

        while (resultSet.next()) {
            Date date = resultSet.getDate(1);
            String userId = resultSet.getString(2);
            String code = resultSet.getString(3);
            String name = resultSet.getString(4);
            String pSize = resultSet.getString(5);
            double quantity = resultSet.getDouble(6);
            double pTotal = resultSet.getDouble(7);
            list.add(new Order(date, userId, code, name, pSize, quantity, pTotal));

        }

        for (Order order : list) {

            textA1.appendText(String.valueOf(order));
        }
list.clear();
    }
}
