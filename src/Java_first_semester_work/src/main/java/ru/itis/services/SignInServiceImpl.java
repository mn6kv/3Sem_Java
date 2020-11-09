package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(UserForm userForm) {
        Optional<User> userOptional = usersRepository.findByEmail(userForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userForm.getPassword(), user.getHashPassword())) {
                return UserDto.from(user);
            } else return null;
        }
        return null;
    }
}
