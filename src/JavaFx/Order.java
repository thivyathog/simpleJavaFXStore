package JavaFx;

import java.sql.Date;
import java.time.LocalDate;

public class Order {
    private Date orderDate;
    private String pName;
    private String userId;
    private double pTotal;
    private String pSize;
    private String code;
    private double quantity;

    public Order(Date orderDate, String userId, String code, String pName, String pSize, double quantity, double pTotal) {
        this.pName = pName;
        this.orderDate = orderDate;
        this.userId = userId;
        this.pTotal = pTotal;
        this.pSize = pSize;
        this.code = code;
        this.quantity = quantity;
    }

    public Order(String userId, String code, String pName, String pSize, double quantity, double pTotal) {
        this.pName = pName;

        this.userId = userId;
        this.pTotal = pTotal;
        this.pSize = pSize;
        this.code = code;
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getpTotal() {
        return pTotal;
    }

    public void setpTotal(double pTotal) {
        this.pTotal = pTotal;
    }

    public String getpSize() {
        return pSize;
    }

    public void setpSize(String pSize) {
        this.pSize = pSize;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }


    public String toString() {
        return "Order Date : " + getOrderDate() + "User ID: " + getUserId() + "code " + getCode() + "Product Name: " + getpName() + "Size: " + getpSize() + "Quantity: " + getQuantity() + "price: " + getpTotal() + "\n";
    }
}
