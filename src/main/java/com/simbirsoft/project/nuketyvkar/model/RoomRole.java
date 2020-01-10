package com.simbirsoft.project.nuketyvkar.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Koloyartsev V.A.
 * <p>
 * RoomRole entity.
 */
@Entity
@Table(name = "room_role")
@Getter
@Setter
public class RoomRole implements GrantedAuthority {

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
     * RoleRoomUsers.
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<RoleRoomUser> roleRoomUsers;

    @Override
    public String getAuthority() {
        return name;
    }
}
