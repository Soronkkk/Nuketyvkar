package com.simbirsoft.project.nuketyvkar.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simbirsoft.project.nuketyvkar.model.RoleRoomUser;
import com.simbirsoft.project.nuketyvkar.model.RoomRole;
import com.simbirsoft.project.nuketyvkar.model.User;

@Repository
public interface RoleRoomUserRepository extends JpaRepository<RoleRoomUser, Long> {

    /**
     * Returned list role room user by user.
     *
     * @param user user.
     * @return role room user list.
     */
    List<RoleRoomUser> findByUser(User user);

    /**
     * Returned list role user by user and by role.
     *
     * @param user user.
     * @param role role.
     * @return role room user list.
     */
    List<RoleRoomUser> findAllByUserAndRole(User user, RoomRole role);

}
