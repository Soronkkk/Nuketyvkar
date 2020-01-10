package com.simbirsoft.project.nuketyvkar.services;

import java.util.List;
import javax.management.relation.Role;
import com.simbirsoft.project.nuketyvkar.dto.RoleRoomUserDTO;
import com.simbirsoft.project.nuketyvkar.model.RoleRoomUser;
import com.simbirsoft.project.nuketyvkar.model.RoomRole;
import com.simbirsoft.project.nuketyvkar.model.User;

public interface RoleRoomUserService {

    /**
     * Returned list role room user by user.
     *
     * @param user user.
     * @return role room user list.
     */
    List<RoleRoomUser> findByUser(User user);

    /**
     * Save roleRoomUser in data base.
     *
     * @param roleRoomUser roleRoomUser.
     * @return roleRoomUser DTO.
     */
    RoleRoomUserDTO save(RoleRoomUser roleRoomUser);


    /**
     * Returned list role user by user and by role.
     *
     * @param user user.
     * @param role role.
     * @return role room user list.
     */
    List<RoleRoomUserDTO> findAllByUserAndRole(User user, RoomRole role);

}
