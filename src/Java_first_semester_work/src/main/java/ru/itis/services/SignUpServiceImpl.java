package ru.itis.services;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

@Data
public class SignUpServiceImpl implements SignUpService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserForm userForm, String uuid) {
        User user = User.builder()
                .email(userForm.getEmail())
                .name(userForm.getName())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .UUID(uuid)
                .build();
        usersRepository.save(user);
    }

    @Override
    public boolean isExistByEmail(String email) {
        if (usersRepository.findByEmail(email).isEmpty())
            return false;
        return true;
    }
}
