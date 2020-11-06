package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;

public interface SignInService {
    UserDto signIn(UserForm userForm);
}
