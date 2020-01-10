package com.simbirsoft.project.nuketyvkar.services.impl;

import com.simbirsoft.project.nuketyvkar.filter.specification.UserSpecification;
import com.simbirsoft.project.nuketyvkar.model.User;
import com.simbirsoft.project.nuketyvkar.services.RoleRoomUserService;
import com.simbirsoft.project.nuketyvkar.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Koloyartsev V.A.
 * <p>
 * User details service implementation.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final ModelMapper modelMapper;

    private final RoleRoomUserService roleRoomUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = modelMapper.map(userService.findUserByUsername(UserSpecification.findByUsername(username)), User.class);

        if (Objects.nonNull(user)) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    mapRolesToAuthorities(user));
        }
        throw new UsernameNotFoundException("Invalid username or password.");
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(User user) {
        return roleRoomUserService
                .findByUser(user)
                .stream()
                .map(roleRoomUser -> new SimpleGrantedAuthority(roleRoomUser.getRole().getAuthority()))
                .collect(Collectors.toSet());
    }

}
