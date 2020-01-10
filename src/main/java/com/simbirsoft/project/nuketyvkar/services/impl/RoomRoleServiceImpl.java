package com.simbirsoft.project.nuketyvkar.services.impl;

import com.simbirsoft.project.nuketyvkar.dto.RoomRoleDTO;
import com.simbirsoft.project.nuketyvkar.filter.specification.RoomRoleSpecification;
import com.simbirsoft.project.nuketyvkar.model.RoomRole;
import com.simbirsoft.project.nuketyvkar.repositories.RoomRoleRepository;
import com.simbirsoft.project.nuketyvkar.services.RoomRoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class RoomRoleServiceImpl implements RoomRoleService {

    private final RoomRoleRepository roomRoleRepository;

    private final ModelMapper modelMapper;

    @Override
    public RoomRoleDTO findByName(String name) {
        return convertToDTO(roomRoleRepository.findOne(RoomRoleSpecification.findByName(name))
                .orElseThrow(() -> new EntityNotFoundException("No room found with name ")));
    }


    private RoomRoleDTO convertToDTO(RoomRole roomRole) {
        return modelMapper.map(roomRole, RoomRoleDTO.class);
    }


}
