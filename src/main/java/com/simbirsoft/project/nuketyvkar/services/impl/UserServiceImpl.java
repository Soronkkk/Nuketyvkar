package com.simbirsoft.project.nuketyvkar.services.impl;

import com.simbirsoft.project.nuketyvkar.dto.UserDTO;
import com.simbirsoft.project.nuketyvkar.model.User;
import com.simbirsoft.project.nuketyvkar.repositories.UserRepository;
import com.simbirsoft.project.nuketyvkar.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Koloyartsev V.A.
 * <p>
 * User service implementation.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public UserDTO findById(Long id) {
        return convertToDTO(userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("No user found with id " + id)));
    }

    @Transactional
    public UserDTO findUserByUsername(Specification<User> specification) {
        return convertToDTO(userRepository.findOne(specification).orElseThrow(() -> new EntityNotFoundException("No user found with username ")));
    }

    @Transactional
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

}
