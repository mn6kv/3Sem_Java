package ru.itis.util;

import ru.itis.dto.SessionDto;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

public class SessionUtil {

    private UsersRepository usersRepository;

    public SessionUtil(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<SessionDto> getSessionById(String sessionId) {
        return usersRepository.findSessionBySessionId(sessionId);
    }
}
