package ru.itis.util;


import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

public class UserUtil {

    private UsersRepository usersRepository;

    public UserUtil(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<User> getUserById(Long id) {
        return usersRepository.findUserById(id);
    }
}
