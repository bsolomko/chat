package co.norse.chat.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "sender_id")
    private long senderId;

    @Column(name = "recipient_id")
    private long recipientId;

    @Column(name = "message")
    private String message;

    @Column(name = "author")
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "create_data_time")
    private LocalDateTime createDataTime;


    public Message() {
        this.createDataTime = LocalDateTime.now();
    }

    public Message(long senderId, String message, long recipientId) {
        this.createDataTime = LocalDateTime.now();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.message = message;
    }

}
