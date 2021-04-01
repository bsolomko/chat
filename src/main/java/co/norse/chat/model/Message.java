package co.norse.chat.model;

public class Message {

    private String message;
    private String author;



    public Message() {
    }

    public Message(String  author, String  message) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
