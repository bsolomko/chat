package co.norse.chat;

public class User {
    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUser;
    private String type;

    public User(int id, String username, String email, String firstName, String lastName, String imageUser, String type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUser = imageUser;
        this.type = type;
    }



}