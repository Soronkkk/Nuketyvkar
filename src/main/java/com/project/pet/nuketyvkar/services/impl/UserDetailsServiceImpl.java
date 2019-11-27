package com.project.pet.nuketyvkar.services.impl;

import com.project.pet.nuketyvkar.model.Role;
import com.project.pet.nuketyvkar.model.User;
import com.project.pet.nuketyvkar.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER_ROLE"));

        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                    user.get().getPassword(),
                    mapRolesToAuthorities(roles));
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());
    }
}