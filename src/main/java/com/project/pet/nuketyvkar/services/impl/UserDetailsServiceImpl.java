package com.project.pet.nuketyvkar.services.impl;

import com.project.pet.nuketyvkar.model.Role;
import com.project.pet.nuketyvkar.model.User;
import com.project.pet.nuketyvkar.repositories.RoleRepository;
import com.project.pet.nuketyvkar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static String ADMIN = "ROLE_ADMIN";
    private final static String USER = "ROLE_USER";
    private final static String MODERATOR = "ROLE_MODERATOR";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                    user.get().getPassword(),
                    mapRolesToAuthorities(user.get()));
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(User user) {
        return user
                .getRoleList()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());
    }

    public User save(User user) {
        Role currentRole = roleRepository.findByName(USER).get();
        List<Role> currentList = new ArrayList<>();
        currentList.add(currentRole);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setRoleList(currentList);

        return userRepository.save(user);
    }
}