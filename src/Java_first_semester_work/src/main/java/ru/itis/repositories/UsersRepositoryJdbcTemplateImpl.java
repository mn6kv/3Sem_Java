package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL = "select * from account where email = ?";
    //language=SQL
    private static final String SQL_FIND_ALL = "select * from account";
    //language=SQL
    private static final String SQL_SAVE = "insert into account (email, name, hash_password, uuid) values (?, ?, ?, ?)";

    private RowMapper<User> userRowMapper = (row, rowNumber) -> {
        return User.builder()
                .id(row.getLong("id"))
                .email(row.getString("email"))
                .name(row.getString("name"))
                .hashPassword(row.getString("hash_password"))
                .UUID(row.getString("uuid"))
                .build();
    };

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        //"insert into account (id, email, name, hash_password, uuid) values (?, ?, ?, ?, ?)"
        jdbcTemplate.update(SQL_SAVE,
                entity.getEmail(),
                entity.getName(),
                entity.getHashPassword(),
                entity.getUUID());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }
}
