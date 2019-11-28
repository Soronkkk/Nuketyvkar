package com.project.pet.nuketyvkar.services;

import com.project.pet.nuketyvkar.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Возвращает пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    Optional<User> findById(Long id);

    /**
     * Возвращает пользователя по имени.
     *
     * @param username имя пользователя.
     * @return пользователь.
     */
    Optional<User> findByUsername(String username);

    /**
     * Возвращает список всех пользователей.
     *
     * @return список пользователей.
     */
    List<User> findAll();
}
