package com.simbirsoft.project.nuketyvkar.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simbirsoft.project.nuketyvkar.constants.RoleConstant;
import com.simbirsoft.project.nuketyvkar.dto.RoomDTO;
import com.simbirsoft.project.nuketyvkar.filter.specification.UserSpecification;
import com.simbirsoft.project.nuketyvkar.model.RoleRoomUser;
import com.simbirsoft.project.nuketyvkar.model.Room;
import com.simbirsoft.project.nuketyvkar.model.RoomRole;
import com.simbirsoft.project.nuketyvkar.model.User;
import com.simbirsoft.project.nuketyvkar.repositories.RoomRepository;
import com.simbirsoft.project.nuketyvkar.services.RoleRoomUserService;
import com.simbirsoft.project.nuketyvkar.services.RoomRoleService;
import com.simbirsoft.project.nuketyvkar.services.RoomService;
import com.simbirsoft.project.nuketyvkar.services.UserService;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomRoleService roomRoleService;

    private final ModelMapper modelMapper;

    private final RoleRoomUserService roleRoomUserService;

    private final UserService userService;

    @Transactional
    public List<RoomDTO> findAll() {
        return roomRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public RoomDTO findById(Long id) {
        return roomRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new EntityNotFoundException("No room found with id " + id));
    }

    @Transactional
    public List<RoomDTO> findAllByUsernameAndRoleName(String username, String roomRoleName) {
        return roomRepository.findAllByUsernameAndRoleName(username, roomRoleName).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public List<RoomDTO> findAllByAdminUsernameAndRoomName(String username, String roomName) {
        return roomRepository.findAllByAdminUsernameAndRoomName(username, roomName).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public RoomDTO save(Room room, String username) {

        List<RoleRoomUser> roleRoomUsers = new ArrayList<>();
        User user = modelMapper.map(userService.findUserByUsername(UserSpecification.findByUsername(username)), User.class);
        RoomRole userRole = modelMapper.map(roomRoleService.findByName(RoleConstant.ROLE_USER), RoomRole.class);
        RoomRole moderRole = modelMapper.map(roomRoleService.findByName(RoleConstant.ROLE_MODERATOR), RoomRole.class);
        RoomRole adminRole = modelMapper.map(roomRoleService.findByName(RoleConstant.ROLE_ADMIN), RoomRole.class);

        addRoleRoomUserToList(room, user, userRole, roleRoomUsers);
        addRoleRoomUserToList(room, user, moderRole, roleRoomUsers);
        addRoleRoomUserToList(room, user, adminRole, roleRoomUsers);

        room.setRoleRoomUsers(roleRoomUsers);

        return convertToDTO(roomRepository.save(room));
    }

    @Transactional
    public void delete(String roomName, String username) {
        Room room = roomRepository.findAllByAdminUsernameAndRoomName(username, roomName).get(0);
        room.setDeleted(true);
        roomRepository.save(room);
    }


    private List<RoleRoomUser> addRoleRoomUserToList(Room room, User user, RoomRole roomRole, List<RoleRoomUser> roleRoomUsers) {
        RoleRoomUser roleRoomUserUser = new RoleRoomUser();
        roleRoomUserUser.setRoom(room);
        roleRoomUserUser.setUser(user);
        roleRoomUserUser.setRole(roomRole);
        roleRoomUsers.add(roleRoomUserUser);
        return roleRoomUsers;
    }


    private RoomDTO convertToDTO(Room room) {
        return modelMapper.map(room, RoomDTO.class);
    }

}
