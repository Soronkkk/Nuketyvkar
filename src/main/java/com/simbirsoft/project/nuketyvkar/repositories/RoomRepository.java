package com.simbirsoft.project.nuketyvkar.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.simbirsoft.project.nuketyvkar.model.Room;

/**
 * @author Koloyartsev V.A.
 * <p>r
 * Room jpa repository.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    /**
     * Returned rooms by user and room role.
     *
     * @param username     username.
     * @param roomRoleName roomRoleName.
     * @return rooms list.
     */
    @Query("select roleRoomUser.room from RoleRoomUser roleRoomUser " +
            "where roleRoomUser.role.name = :roomRoleName " +
            "and roleRoomUser.user.username = :username " +
            "and roleRoomUser.room.deleted = false ")
    List<Room> findAllByUsernameAndRoleName(String username, String roomRoleName);

    /**
     * Returned rooms by admin user and room name.
     *
     * @param username username.
     * @param roomName room name.
     * @return room.
     */
    @Query("select  roleRoomUser.room from RoleRoomUser roleRoomUser  " +
            "where roleRoomUser.role.name = 'ROLE_ADMIN' " +
            "and roleRoomUser.user.username = :username " +
            "and roleRoomUser.room.name = :roomName " +
            "and roleRoomUser.room.deleted = false")
    List<Room> findAllByAdminUsernameAndRoomName(String username, String roomName);

}
