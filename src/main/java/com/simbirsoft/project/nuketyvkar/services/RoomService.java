package com.simbirsoft.project.nuketyvkar.services;

import java.util.List;
import com.simbirsoft.project.nuketyvkar.dto.RoomDTO;
import com.simbirsoft.project.nuketyvkar.model.Room;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Room service.
 */
public interface RoomService {

    /**
     * Return all rooms dto list from data base.
     *
     * @return rooms dto list.
     */
    List<RoomDTO> findAll();

    /**
     * Return room dto by id.
     *
     * @return
     */
    RoomDTO findById(Long id);

    /**
     * Save room in db.
     *
     * @param room
     * @return room dto.
     */
    RoomDTO save(Room room, String username);

    /**
     * Delete first room by name.
     *
     * @param roomName room name.
     * @param username user name.
     */
    void delete(String roomName, String username);


    /**
     * Returned rooms by user and room role.
     *
     * @param username     username.
     * @param roomRoleName roomRoleName.
     * @return rooms dto list.
     */
    List<RoomDTO> findAllByUsernameAndRoleName(String username, String roomRoleName);

    /**
     * Returned room by admin user and room name.
     *
     * @param username username.
     * @param roomName room name.
     * @return room.
     */
    List<RoomDTO> findAllByAdminUsernameAndRoomName(String username, String roomName);

}
