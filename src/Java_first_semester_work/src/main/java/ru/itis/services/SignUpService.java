package ru.itis.services;

import ru.itis.dto.UserForm;

public interface SignUpService {
    void signUp(UserForm userForm, String uuid);
    boolean isExistByEmail(String email);
}
