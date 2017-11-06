package services;

public class UserSingleton {
    //In deze singleton wordt de gebruikersnaam opgeslagen.
    private static UserSingleton ourInstance = new UserSingleton();

    private static String username;

    public static UserSingleton getInstance() {
        return ourInstance;
    }

    public static void setUser(String user) {
        username = user;
    }

    public static String getUser() {
        return username;
    }

    private UserSingleton() {
        //
    }
}
