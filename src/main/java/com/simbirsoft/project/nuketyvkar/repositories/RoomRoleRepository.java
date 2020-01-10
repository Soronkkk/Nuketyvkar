package com.simbirsoft.project.nuketyvkar.repositories;

import com.simbirsoft.project.nuketyvkar.model.RoleRoomUser;
import com.simbirsoft.project.nuketyvkar.model.RoomRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Room role jpa repository.
 */
@Repository
public interface RoomRoleRepository extends JpaRepository<RoomRole, Long>, JpaSpecificationExecutor<RoomRole> {
}
