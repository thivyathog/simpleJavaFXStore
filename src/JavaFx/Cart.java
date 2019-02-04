package JavaFx;

import connectivity.ConnectionClass;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import linkedList.Node;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class Cart implements Initializable {


    private static User currentUser;
    private final String SenderId = "thivya1998@gmail.com";
    private final String Senderpass = "aiyaamma";
    private final String emailSMTPserver = "smtp.gmail.com";
    private final String emailServerPort = "465";
    public TextField pQuantity;
    public TextField pSize;
    public ComboBox productName;
    public ObservableList<String> combo = FXCollections.observableArrayList("Fishing Hooks", "Fishing Rod", "Fishing Reel", "Fishing line", "swivels", "sinkers");
    public TextArea txtArea;
    public TextField search;
    Node<Product> dataN = null;
    String emailSubject = null;
    String emailBody = null;
    User user;
    int x = 1;
    int receipt = 1;
    private List<Product> sortedList;
    private String code = null;
    private double price = 0;
    private double total = 0;
    private double totalAmount = 0;
    public TextArea txtEnquiry;
    public static void setCurrentUser(User currentUser) {

        Cart.currentUser = currentUser;
    }

    public static int performBinarySearch(List<Product> input, int number) {
        int low = 0;
        int high = input.size();
        while (high >= low) {
            int middle = (low + high) / 2;
            if (input.get(middle).getiPrice() == (number)) {
                return middle;
            } else if (input.get(middle).getiPrice() < number) {
                low = middle + 1;
            } else if (input.get(middle).getiPrice() > number) {
                high = middle - 1;
            }


        }
        return -1;
    }

    public void setSize() {

        if (productName.getValue().equals("Fishing Hooks")) {
            pSize.setText("#6");
        } else if (productName.getValue().equals("Fishing Rod")) {
            pSize.setText("3m");

        } else if (productName.getValue().equals("Fishing Reel")) {
            pSize.setText("M");

        }
    }

    @FXML
    protected void cart() {
        if (pQuantity.getText().equals("") || pSize.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("PLEASE FILL ALL FIELDS");
            alert.showAndWait();
        }

        if (productName.getValue().equals("Fishing Hooks")) {

            code = "FH";
            price = 20;
            total = Double.parseDouble(pQuantity.getText()) * price;
        } else if (productName.getValue().equals("Fishing Rod")) {
            pSize.setText("3m");
            price = 100;
            code = "RO";
            total = Double.parseDouble(pQuantity.getText()) * price;
        } else if (productName.getValue().equals("Fishing Reel")) {
            pSize.setText("M");
            code = "FR";
            price = 25;
            total = Double.parseDouble(pQuantity.getText()) * price;
        } else if (productName.getValue().equals("Fishing line")) {
            code = "FL";
            switch (pSize.getText()) {
                case "100m":
                    price = 75;
                    break;
                case "200m":
                    price = 100;
                    break;
                default:
                    price = 125;
                    break;
            }

            total = Double.parseDouble(pQuantity.getText()) * price;
        } else if (productName.getValue().equals("swivels")) {
            code = "SW";
            switch (pSize.getText()) {
                case "S":
                    price = 90;
                    break;
                case "M":
                    price = 110;
                    break;
                default:
                    price = 120;
                    break;
            }

            total = Double.parseDouble(pQuantity.getText()) * price;
        } else if (productName.getValue().equals("sinkers")) {
            code = "SI";
            price = 50;
            total = Double.parseDouble(pQuantity.getText()) * price;
        } else {
            System.out.println("ENter Valid product");
        }
        //  plist = new Product[10];

        ///

        if (dataN == null) {
            dataN = new Node<>(
                    new Product(String.valueOf(productName.getValue()), code, pSize.getText(), Integer.parseInt(pQuantity.getText()), price, total)
            );


        } else {

            Node<Product> newNode = new Node<>(
                    new Product(String.valueOf(productName.getValue()), code, pSize.getText(), Integer.parseInt(pQuantity.getText()), price, total)
            );
            newNode.setPrevious(dataN);
            dataN.setNext(newNode);
            dataN = newNode;
            newNode = null;
        }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Success!");
        alert.setContentText("Product successfully added to the cart!");
        alert.showAndWait();
        refresh();


    }

    private List<Product> sortProducts(List<Product> list) {

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getpTotal() < list.get(minIndex).getpTotal()) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Product temp = list.get(minIndex);
                list.set(minIndex, list.get(i));
                list.set(i, temp);
            }
        }
        return list;
    }

    public void price_sort() {
        txtArea.appendText("");
        Node<Product> currentNode = getFirstNode(dataN);

        List<Product> productList = new ArrayList<>();
        if (currentNode.getNext() == null) {

            productList.add(currentNode.getData());

        } else {
            while (currentNode.getNext() != null) {

                productList.add(currentNode.getData());
                currentNode = currentNode.getNext();

            }
            productList.add(currentNode.getData());
        }

        sortedList = sortProducts(productList);
        print(sortedList);


    }

    public void searchPrice() {
        txtArea.clear();
        price_sort();
        txtArea.clear();
        int key = Integer.parseInt(search.getText());
        int index = performBinarySearch(sortedList, key);
        if (index == -1) {

            txtArea.appendText("Sorry Product priced at Rs." + key + " is not found in the cart");

        } else {

            txtArea.appendText("Product priced at Rs." + key + " is  found in the cart as Product Number" + (index + 1));

        }

    }

    public void print(List<Product> price) {

        for (Product product : price) {
            txtArea.appendText(String.valueOf(product));
        }


    }

    public void print() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Node<Product> currentNode = getFirstNode(dataN);
        Statement statement = connection.createStatement();
        if (currentNode.getNext() == null) {

            String sql = "INSERT INTO product_order VALUES ('" + java.time.LocalDate.now() + "','" + currentUser.getUId() + "','" + dataN.getData().getiCode() + "','" + dataN.getData().getiName() + "','" + dataN.getData().getiSize() + "','" + dataN.getData().getQuantity() + "','" + dataN.getData().getiPrice() + "','" + dataN.getData().getpTotal() + "')";
            System.out.println("print w");

            statement.executeUpdate(sql);
        } else {
            while (currentNode.getNext() != null) {
                String sql1 = "INSERT INTO product_order VALUES ('" + java.time.LocalDate.now() + "','" + currentUser.getUId() + "','" + currentNode.getData().getiCode() + "','" + currentNode.getData().getiName() + "','" + currentNode.getData().getiSize() + "','" + currentNode.getData().getQuantity() + "','" + currentNode.getData().getiPrice() + "','" + currentNode.getData().getpTotal() + "')";

                statement.executeUpdate(sql1);
                System.out.println("print 1");
                currentNode = currentNode.getNext();

            }
            String sql2 = "INSERT INTO product_order VALUES ('" + java.time.LocalDate.now() + "','" + currentUser.getUId() + "','" + currentNode.getData().getiCode() + "','" + currentNode.getData().getiName() + "','" + currentNode.getData().getiSize() + "','" + currentNode.getData().getQuantity() + "','" + currentNode.getData().getiPrice() + "','" + currentNode.getData().getpTotal() + "')";
            statement.executeUpdate(sql2);
            System.out.println("printed all");
        }

    }

    public Node<Product> getFirstNode(Node<Product> dataNode) {
        Node<Product> currentNode = dataNode;
        while (currentNode.getPrevious() != null) {
            currentNode = currentNode.getPrevious();
        }
        return currentNode;
    }

    private void refresh() {
        productName.setValue("Fishing Hooks");
        pQuantity.setText("");
        pSize.setText("");
    }

    public void openModify(ActionEvent actionEvent) throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("Modify.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(start, 600, 600);
        scene.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void removeCart() {
        txtArea.clear();
        Node<Product> del = dataN.getPrevious();
        dataN.setPrevious(null);
        del.setNext(null);
        dataN = del;
        //cNode.setPrevious(null);

        // deleteNode(getFirstNode(DataNode),getLastNode((DataNode)));
    }

    public void open(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("BillingForm.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 600, 450);
        scene.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public void screen1(ActionEvent actionEvent) throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene sign = new Scene(start);
        sign.getStylesheets().add(Main.class.getResource("fx.css").toExternalForm());
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sign);
        window.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productName.setValue("Fishing Hooks");
        productName.setItems(combo);
    }

    public void invoice() throws SQLException {
        if (dataN == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please Enter products");
            alert.setHeaderText("product Payment unavailable,enter products");
            alert.showAndWait();
        }

        print();

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql3 = "SELECT * FROM login WHERE user_id = '" + currentUser.getUId() + "';";
        ResultSet resultSet = statement.executeQuery(sql3);
        String name = null;
        String add = null;
        String email = null;
        String cid = null;
        if (resultSet.next()) {
            cid = resultSet.getString(1);
            name = resultSet.getString(2);
            add = resultSet.getString(4);
            email = resultSet.getString(5);
        }

        emailSubject = " Thank you for your purchase with Jeff";
        emailBody = "";


        Properties properties = System.getProperties();
        properties.put("mail.smtp.user", SenderId);
        properties.put("mail.smtp.host", emailSMTPserver);
        properties.put("mail.smtp.port", emailServerPort);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", emailServerPort);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        SecurityManager security = System.getSecurityManager();


        try {

            Authenticator auth = new Email.SMTP();
            // getPasswordAuthentication();
            Session session = Session.getInstance(properties, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject(emailSubject);

            msg.setContent("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +

                    "    <title>Invoice</title>\n" +
                    "\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h2 align=\"center\">Jeff's Fishing Shack</h2>\n" +
                    "<h1 align=\"center\">Tax Invoice</h1>\n" +
                    "\n" +
                    "Jeff's Fishing Shack\n" +
                    "Trading as:Octopus Ptv Ltd\n" +
                    "<BR><BR>\n" +
                    "\n" +
                    "Shop 4,Hillarys Boat Harbour\n" +
                    "<br><BR>\n" +
                    "\n" +
                    "Hillarys,WA,6025\n" +
                    "<br><BR>\n" +
                    "\n" +
                    "T:<a href=\"08 9402 2222\" >08 9402 2222</a><BR>\n" +
                    "E:<a href= \"Sales@JFS.com.au\" >Sales@JFS.com.au</a>\n" + "<BR>\n" +
                    "<p align=\"right\">Date:\n" + LocalDate.now() + "</p>\n" +
                    "Receipt#:" + receipt + "\n" +
                    "\n" +
                    "<Br>\n" +
                    "<Br>\n" +
                    "<Br>\n" +
                    "Customer:" + name + "<BR>\n" +
                    "<Br>\n" +
                    add +
                    "<Br>\n" +
                    "<Br>\n" +
                    "Customer#:" + cid + "<Br>\n" +
                    "Customer Email: " + currentUser.getUId() + "\n" +
                    "<Br><Br><Br>\n" +
                    "Purchases<Br><Br>\n" +
                    "\n" +
                    getInvoiceTable(dataN) +
                    "<br>\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "<h2 align=\"right\">Total Paid:" + totalAmount + "</h2>\n" +
                    "<br>\n" +
                    "<br>\n" + " <p align=\"center\" style=\"font-size: 20px;\">Thank you for your business. </p>" +
                    "<p align=\"center\"style=\"font-size: 20px;\">Jeff's-Where the real fisherman shop</p>" +
                    "</body>\n" +
                    "</html>", "text/html; charset=utf-8");
            msg.setFrom(new InternetAddress(SenderId));
            msg.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            Transport.send(msg);
            System.out.println("Message Sent");
            receipt++;

        } catch (Exception e) {
            e.printStackTrace();

        }

        receipt++;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("THANYOU FOR PURCHASING WITH JEFF'S FISHING SHACK");
        alert.setHeaderText("Payment Confirmed");
        alert.showAndWait();
    }

    private String getInvoiceTable(Node<Product> productsLinkedList) {

        String table = "<table>";
        table += "<tr> <TH>No.</TH>\n" +
                "        <TH>Desc.</TH>\n" +
                "        <TH>Code.</TH>\n" +
                "        <TH>Size.</TH>\n" +
                "        <TH>Cost.</TH>\n" +
                "        <TH>Qty.</TH>\n" +
                "        <TH>Amount.</TH></tr>"; // TODO add other column titles
        Node<Product> currentNode = getFirstNode(dataN);
        if (currentNode.getNext() == null) {
            table += getProductRow(currentNode.getData());
        } else {
            while (currentNode.getNext() != null) {
                totalAmount = currentNode.getData().getpTotal() + totalAmount;

                table += getProductRow(currentNode.getData());
                currentNode = currentNode.getNext();
                x++;

                totalAmount = currentNode.getData().getpTotal() + totalAmount;

            }
            table += getProductRow(currentNode.getData());
            x++;
        }
        table += "</table>";
        return table;
    }
public void enqu(){
    Email email=new Email();
    String content=txtArea.getText();
    email.enquiry(currentUser.getUId(),content);

}
    private String getProductRow(Product product) {

        return "<tr><td>" + x + "</td><td>" + product.getiName() + "</td>" + "<td>" + product.getiCode() + "</td>" + "<td>" + product.getiSize() + "</td>" + "<td>" + product.getiPrice() + "</td>" + "<td>" + product.getQuantity() + "</td>" + "<td>" + product.getpTotal() + "</td>";
        // TODO concatenate others too

    }


}
