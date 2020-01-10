package com.simbirsoft.project.nuketyvkar.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Messages entity.
 */
@Entity
@Table(name = "message")
@Getter
@Setter
public class Message {

    /**
     * Identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Message author.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    private User author;


    /**
     * Message room.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", referencedColumnName = "ID")
    private Room room;

    /**
     * Message text.
     */
    @Column(name = "message_text", nullable = false)
    private String messageText;

    /**
     * Created date time.
     */
    @Column(nullable = false, name = "created_date_time")
    private ZonedDateTime createdDateTime;

}
