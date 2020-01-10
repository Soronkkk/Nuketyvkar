package com.simbirsoft.project.nuketyvkar.repositories;

import com.simbirsoft.project.nuketyvkar.model.Room;
import com.simbirsoft.project.nuketyvkar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Koloyartsev V.A.
 * <p>
 * User jpa repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
