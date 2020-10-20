package repositories;

import models.User;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    boolean isUserExist(String username, String password);
    boolean saveUUIDtoExistingUser(String username, String password, String UUID);
    boolean isCurrentUuidExists(String uuid);
    boolean ifUserHasUuid(String username, String password);
    String getUsersUuid(String username, String password);
    List<User> findByName(String name);
}
