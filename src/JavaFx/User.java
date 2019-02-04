package JavaFx;

public abstract class User {
    private static String uId;
public User(){}
    public User(String uId) {

        this.uId = uId;
    }

    public static String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }
}
