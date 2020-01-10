package com.simbirsoft.project.nuketyvkar.services;

import com.simbirsoft.project.nuketyvkar.dto.UserDTO;
import com.simbirsoft.project.nuketyvkar.model.Room;
import com.simbirsoft.project.nuketyvkar.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author Koloyartsev V.A.
 * <p>
 * User service.
 */
public interface UserService {

    /**
     * Return user by id.
     *
     * @param id identifier.
     * @return user;
     */
    UserDTO findById(Long id);

    /**
     * Return user by username.
     *
     * @param specification username.
     * @return userDTO.
     */
    UserDTO findUserByUsername(Specification<User> specification);

    /**
     * Return list of users.
     *
     * @return users list.
     */
    List<UserDTO> findAll();

}
