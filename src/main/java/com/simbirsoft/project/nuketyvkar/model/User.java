package com.simbirsoft.project.nuketyvkar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Koloyartsev V.A.
 * <p>
 * User entity.
 */
@Entity
@Table(name = "person")
@Getter
@Setter
public class User {

    /**
     * Identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Login.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Password.
     */
    @Column(nullable = false)
    private String password;

    /**
     * email.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * User messages.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Message> messages;

    /**
     * RoleRoomUsers.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RoleRoomUser> roleRoomUsers;

}
