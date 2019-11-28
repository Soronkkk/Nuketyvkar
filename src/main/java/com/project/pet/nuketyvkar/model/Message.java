package com.project.pet.nuketyvkar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Koloyartsev V.A.
 *
 * Сущность сообщения.
 */
@Entity
@Table(name = "messages")
public class Message {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Техт сообщения.
     */
    @Column(name = "message_text", nullable = false)
    private String messageText;

    /**
     * Дата и время создания комментария.
     */
    @Column(nullable = false, name = "created_date_time")
    private LocalDateTime createdDateTime;

    /**
     * Связь многие к одному.
     * Несколько комментариев может быть у одного пользователя.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    @JsonBackReference
    private User author;

    public Message() {
    }

    public Message(String messageText) {
        this.messageText = messageText;
    }

    public Message(String messageText, LocalDateTime createdDateTime, User author) {
        this.messageText = messageText;
        this.createdDateTime = createdDateTime;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
