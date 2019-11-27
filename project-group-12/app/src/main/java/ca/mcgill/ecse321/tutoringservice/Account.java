package ca.mcgill.ecse321.tutoringservice;

public class Account {
    private String userName;
    private String password;
    public Account() {

    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
