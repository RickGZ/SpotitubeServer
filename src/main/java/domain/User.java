package domain;

public class User {
    private String username;
    private String password;

    public User() {
        //
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
