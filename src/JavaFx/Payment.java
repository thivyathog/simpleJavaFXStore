package JavaFx;


import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;

public class Payment {
    protected String creditNo;
    protected String uName;
    protected LocalDate exp;
    protected int pin;

    public TextField creditNum;
    public TextField usName;
    public DatePicker expi;
    public TextField pinNo;

    public void confirmPayment() {
        if (creditNum.getText().equals("") || expi.getValue().equals("") || usName.getText().equals("") || pinNo.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("PLEASE FILL ALL FIELDS");
            alert.showAndWait();
        }
        if(creditNum.getText().length()<19 &&creditNum.getText().length()>19){
            Alert alert=new Alert(Alert.AlertType.WARNING) ;
            alert.setContentText("THE CARD NUMBER IS INCORRECT");
            alert.showAndWait();}

            creditNo=creditNum.getText();
        uName=usName.getText();
        exp=expi.getValue();
        pin= Integer.parseInt(pinNo.getText());

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Success!!");
        alert.setContentText("Billing info Successfully Added!");
        alert.showAndWait();

    }


    public void screen1(ActionEvent actionEvent) throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene sign = new Scene(start);
sign.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sign);
        window.show();

    }


    }