package com.simbirsoft.project.nuketyvkar.services;

import com.simbirsoft.project.nuketyvkar.dto.RoomRoleDTO;
import com.simbirsoft.project.nuketyvkar.model.RoomRole;
import org.springframework.data.jpa.domain.Specification;

public interface RoomRoleService {

    /**
     * Return room role by room name.
     *
     * @param name
     * @return roomRole.
     */
    RoomRoleDTO findByName(String  name);

}
