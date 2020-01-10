package com.simbirsoft.project.nuketyvkar.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Users DTO.
 */
@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    @JsonBackReference
    private String password;

    private List<RoomRoleDTO> roomRoles;

}
