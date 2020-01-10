package com.simbirsoft.project.nuketyvkar.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Koloyartsev V.A.
 * <p>
 * RoleRoomUser DTO.
 */
@Getter
@Setter
public class RoleRoomUserDTO {


    private Long id;

    private RoomRoleDTO role;

    private RoomDTO room;

    private UserDTO user;

}
