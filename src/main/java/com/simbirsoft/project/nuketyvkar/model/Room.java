package com.simbirsoft.project.nuketyvkar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Room entity.
 */
@Entity
@Table(name = "room")
@Getter
@Setter
public class Room {

    /**
     * Identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Room name.
     */
    @Column(name = "name", nullable = false)
    private String name;


    /**
     * Room messages.
     */
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Message> messages;

    /**
     * RoleRoomUsers.
     */
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoleRoomUser> roleRoomUsers;


    /**
     * Is private or public room.
     */
    @Column(name = "is_private", nullable = false)
    private boolean privateRoom;

    /**
     * Is bot room or not bot room.
     */
    @Column(name = "is_bot", nullable = false)
    private boolean botRoom;

    /**
     * Is deleted room or not deleted.
     */
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

}