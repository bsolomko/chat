package co.norse.chat.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Message {

    private static AtomicLong atomicLong = new AtomicLong();
    private long id;
    private String message;
    private String author;
    private LocalDateTime createDataTime;


    public Message() {
        this.createDataTime = LocalDateTime.now();
        this.id = atomicLong.incrementAndGet();
    }

    public Message(String author, String message) {

    }

    public LocalDateTime getCreateDataTime() {
        return createDataTime;
    }

    public void setCreateDataTime(LocalDateTime createDataTime) {
        this.createDataTime = createDataTime;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTime(){
        return createDataTime.toLocalTime().withNano(0).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id && Objects.equals(message, message1.message) && Objects.equals(author, message1.author) && Objects.equals(createDataTime, message1.createDataTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, author, createDataTime);
    }
}
