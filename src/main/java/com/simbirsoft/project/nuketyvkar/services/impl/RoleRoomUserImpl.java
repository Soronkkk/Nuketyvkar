package com.simbirsoft.project.nuketyvkar.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.management.relation.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.simbirsoft.project.nuketyvkar.dto.RoleRoomUserDTO;
import com.simbirsoft.project.nuketyvkar.model.RoleRoomUser;
import com.simbirsoft.project.nuketyvkar.model.RoomRole;
import com.simbirsoft.project.nuketyvkar.model.User;
import com.simbirsoft.project.nuketyvkar.repositories.RoleRoomUserRepository;
import com.simbirsoft.project.nuketyvkar.services.RoleRoomUserService;

@Service
@RequiredArgsConstructor
public class RoleRoomUserImpl implements RoleRoomUserService {

    private final RoleRoomUserRepository roleRoomUserRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<RoleRoomUser> findByUser(User user) {
        return roleRoomUserRepository.findByUser(user);
    }

    @Override
    public RoleRoomUserDTO save(RoleRoomUser roleRoomUser) {
        return modelMapper.map(roleRoomUserRepository.save(roleRoomUser), RoleRoomUserDTO.class);
    }

    @Override
    public List<RoleRoomUserDTO> findAllByUserAndRole(User user, RoomRole role) {
        return roleRoomUserRepository.findAllByUserAndRole(user, role).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RoleRoomUserDTO convertToDTO(RoleRoomUser roleRoomUser) {
        return modelMapper.map(roleRoomUser, RoleRoomUserDTO.class);
    }
}
