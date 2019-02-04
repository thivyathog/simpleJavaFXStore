package JavaFx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Comparator;

public class Product {

    private String iName;
    private String iCode;
    private double iPrice;
    private String iSize;
    private int quantity;
    private double pTotal;

    public Product(String iName, String code, String iSize, int quantity, Double iPrice, Double ptotal) {
        this.iName = iName;
        this.iPrice = iPrice;
        this.iSize = iSize;
        this.quantity = quantity;
        this.pTotal = ptotal;
        this.iCode = code;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public double getiPrice() {
        return iPrice;
    }

    public void setiPrice(double iPrice) {
        this.iPrice = iPrice;
    }

    public String getiSize() {
        return iSize;
    }

    public void setiSize(String iSize) {
        this.iSize = iSize;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getpTotal() {
        return pTotal;
    }

    public void setpTotal(double pTotal) {
        this.pTotal = pTotal;
    }

    public String toString() {
        return "Name: " + getiName() + "" + "Code: " + getiCode()  + ""+ "Size: " + getiSize()  + ""+ "Quantity: " + getQuantity() + "" + "Unit-price: " + getiPrice() + "" + "Total: " + getpTotal() + "\n";
    }


    public String getiCode() {
        return iCode;
    }

}



