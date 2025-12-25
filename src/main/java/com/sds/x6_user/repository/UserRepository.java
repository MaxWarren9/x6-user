package com.sds.x6_user.repository;

import com.sds.x6_user.model.User;
import com.sds.x6_user.repository.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {

    private static final String INSERT = """
            INSERT INTO x6.user (login, first_name, last_name, email, birthday, created_at, status)
            VALUES (:login, :first_name, :last_name, :email, :birthday, NOW(), 'ACTIVE')
            RETURNING *;
            """;

    private static final String GET_ALL = """
            SELECT * FROM x6.user;
            """;

    private static final String GET_BY_ID = """
            SELECT * FROM x6.user
            WHERE id = :id;
            """;

    private static final String UPDATE = """
            UPDATE x6.user 
            SET login = :login, first_name = :first_name, last_name = :last_name, email = :email, birthday = :birthday, updated_at = NOW()
            WHERE id = :id
            RETURNING *;
            """;
    private static final String EXISTS = """
            SELECT EXISTS(SELECT 1 FROM x6.user WHERE id = :id);
            """;

    private NamedParameterJdbcTemplate jdbcTemplate;
    private final UserMapper userMapper = new UserMapper();

    public User insert(final User user) {
        return jdbcTemplate.queryForObject(INSERT, userToSql(user), userMapper);
    }

    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL, userMapper);
    }

    public User getById(final long id) {
        return jdbcTemplate.queryForObject(GET_BY_ID, idParam(id), userMapper);
    }

    public boolean isUserAvailable(final long id) {
        return jdbcTemplate.queryForObject(EXISTS, idParam(id), Boolean.class);
    }

    public User update(final User user) {
        MapSqlParameterSource params = userToSql(user)
                .addValue("id", user.getId());
        return jdbcTemplate.queryForObject(UPDATE, params, userMapper);
    }

    public MapSqlParameterSource userToSql(final User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return params
                .addValue("login", user.getLogin())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("birthday", user.getBirthday())
                ;
    }

    private MapSqlParameterSource idParam(long id) {
        return new MapSqlParameterSource().addValue("id", id);
    }
}
