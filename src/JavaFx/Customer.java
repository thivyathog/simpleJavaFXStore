package JavaFx;

import java.time.LocalDate;

public class Customer extends User{

    private String cName;
    private String Address;

    private LocalDate dob;

    public Customer(String uId) {

        super(uId);
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }



}
