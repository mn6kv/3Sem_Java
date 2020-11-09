package ru.itis.repositories;

import ru.itis.dto.SessionDto;
import ru.itis.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findUserById(Long id);
    Optional<User> findByEmail(String email);
    Optional<SessionDto> findSessionByUserId(Long id);
    Optional<SessionDto> findSessionBySessionId(String sessionId);
    void addSessionToUser(Long id, String session);
}
