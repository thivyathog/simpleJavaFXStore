package JavaFx;

import connectivity.ConnectionClass;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class Account {
    public TextField userid;
    public TextField userName;
    public TextField address;
    public DatePicker dob;
    public Label satisfy;
    public Label isConnected;
    public PasswordField userPassword;
    Connection connection = null;
    User user;
    private TableView tableview;
    private ObservableList<ObservableList> data;

    public void button(ActionEvent actionEvent) {


        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = ConnectionClass.getConnection();

        if (userName.getText().equals("") || dob.getValue().equals("") || address.getText().equals("") || userid.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("PLEASE FILL ALL FIELDS");
            alert.showAndWait();
        }
        int at = 0;
        int aft = 0;
        String[] mail = userid.getText().split("");
        for (int i = 0; i < mail.length; i++) {
            if ("@".equalsIgnoreCase(mail[i])) {
                at = i;
            }
            if (".".equals(mail[i])) {
                aft = i;
            }
        }
        if (at < 2 || aft < at + 2 || aft + 2 > userid.getText().length()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid");
            alert.setContentText("PLEASE enter a valid email ID");
            alert.showAndWait();
        } else {
            if ((userPassword.getText().length() >= 8) && userPassword.getText().matches(".*\\d.*") && userPassword.getText().matches("[a-zA-z0-9]*")) {
                satisfy.setText("Password Confirmed");
                satisfy.setTextFill(Color.rgb(2, 210, 3));

                Customer customer = new Customer(userName.getText());
                customer.setAddress(address.getText());
                customer.setcName(userName.getText());
                customer.setDob(dob.getValue());

                String sql = "INSERT INTO LOGIN VALUES ('" + 0 + "','" + userName.getText() + "','" + dob.getValue() + "','" + address.getText() + "','" + userid.getText() + "','" + userPassword.getText() + "')";

                Statement statement = null;
                try {
                    statement = connection.createStatement();
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Failure!");
                    alert.setContentText("ACCOUNT ALREADY EXISTS");
                    alert.showAndWait();
                }
                try {
                    statement.executeUpdate(sql);
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Failure!");
                    alert.setContentText("ACCOUNT ALREADY EXISTS");
                    alert.showAndWait();
                }
                System.out.println("Program is running");
                Email email = new Email();
                email.newsletter(userid.getText());


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success!");
                alert.setContentText("ACCOUNT CREATED SUCCESSFULLY");
                alert.showAndWait();


            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid");
                alert.setContentText("PLEASE SATISY PASSWORD CONDITIONS");
                alert.showAndWait();

            }
        }

    }


    public void screen1(ActionEvent actionEvent) throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene sign = new Scene(start);
        sign.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sign);
        window.show();

    }

    public void changescreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public void login(ActionEvent actionEvent) throws SQLException, IOException {
        if (userName.getText().equals(Jeff.jeffId) && userPassword.getText().equals(Jeff.jeffPass)) {
            isConnected.setText("");
            Cart.setCurrentUser(new Jeff());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("WELCOME TO JEFF'S FISHING SHACK " + User.getUId() + "!");
            alert.setContentText("JEFF'S-WHERE THE REAL FISHERMAN SHOP.");
            alert.showAndWait();

            Parent start = FXMLLoader.load(getClass().getResource("Jeff.fxml"));
            Scene sign = new Scene(start);
            sign.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(sign);

            window.show();


        }

        connection = ConnectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql3 = "SELECT * FROM login WHERE user_id = '" + userName.getText() + "' AND password = '" + userPassword.getText() + "';";
        ResultSet resultSet = statement.executeQuery(sql3);

        if (resultSet.next()) {
            isConnected.setText("");
            Cart.setCurrentUser(new Customer(userName.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("WELCOME TO JEFF'S FISHING SHACK " + User.getUId() + "!");
            alert.setContentText("JEFF'S-WHERE THE REAL FISHERMAN SHOP.");
            alert.showAndWait();

            tableview = new TableView();
            buildData();
            Stage stage = new Stage();

            StackPane layout1 = new StackPane();
            Label labelOne = new Label("One");

            layout1.getChildren().add(tableview);


            StackPane layout2 = new StackPane();
            Button btOne = new Button("Order");
            layout2.getChildren().add(labelOne);
            Parent rootss = FXMLLoader.load(getClass().getResource("Order.fxml"));
            //SubScene subSceneTwo = new SubScene(rootss,350,500);

            VBox rootq = new VBox();
            rootq.setAlignment(Pos.CENTER);
            rootq.getChildren().addAll(layout1, rootss);
            Scene mainScene = new Scene(rootq, 600, 600);
            mainScene.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
            //stage.setScene(mainScene);
            // stage.show();

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tableview.prefHeightProperty().bind(window.heightProperty());
            tableview.prefWidthProperty().bind(window.widthProperty());
            window.setScene(mainScene);
            window.setMaximized(true);
            window.show();


        }


        String sql = "SELECT * FROM stafflogin WHERE staffid = '" + userName.getText() + "' AND staffpass = '" + userPassword.getText() + "';";
        ResultSet resultSet2 = statement.executeQuery(sql);

        //jeff.setStaffId(userName.getText());
        // jeff.setStaffId(userPassword.getText());
        if (resultSet2.next()) {
            isConnected.setText("");
            Cart.setCurrentUser(new Staff(userName.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("WELCOME TO JEFF'S FISHING SHACK " + User.getUId() + "!");
            alert.setContentText("JEFF'S-WHERE THE REAL FISHERMAN SHOP.");
            alert.showAndWait();

            Parent staff = FXMLLoader.load(getClass().getResource("Query.fxml"));
            Scene staffs = new Scene(staff);
            staff.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(staffs);

            window.show();


        }
        isConnected.setText("INCORRECT LOGIN DETAILS!");
    }

    @FXML
    private void accountD() throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = ConnectionClass.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM login WHERE user_id = '" + User.getUId() + "';");

        while (resultSet.next()) {

            String userN = resultSet.getString(2);
            String date = resultSet.getString(3);
            String add = resultSet.getString(4);
            String id = resultSet.getString(5);
            String pas = resultSet.getString(6);
            userName.setText(userN);
            address.setText(add);
            dob.setValue(LocalDate.parse(date));
            userid.setText(id);
            userPassword.setText(pas);
        }
    }

    @FXML
    private void modify() throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = ConnectionClass.getConnection();
        if ((userPassword.getText().length() >= 8) && userPassword.getText().matches(".*\\d.*") && userPassword.getText().matches("[a-zA-z0-9]*")) {
            satisfy.setText("Password Confirmed");
            satisfy.setTextFill(Color.rgb(2, 210, 3));
            String sql = "UPDATE login SET name='" + userName.getText() + "',dob='" + dob.getValue() + "',address='" + address.getText() + "',user_id='" + userid.getText() + "',password='" + userPassword.getText() + "' where user_id= '" + User.getUId() + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success!");
            alert.setContentText("ACCOUNT SUCCESSFULLY MODIFIED!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid");
            alert.setContentText("PLEASE SATISY PASSWORD CONDITIONS");
            alert.showAndWait();
        }
    }


    public void buildData() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = ConnectionClass.getConnection();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from item_list";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                col.prefWidthProperty().bind(tableview.widthProperty().multiply(0.25));


            }

            /**
             *
             * Data added to ObservableList

             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }

                data.add(row);

            }

            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

}





