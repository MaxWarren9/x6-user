package com.sds.x6_user.repository.mapper;

import com.sds.x6_user.enums.UserStatus;
import com.sds.x6_user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        Timestamp createdTs = rs.getTimestamp("created_at");
        LocalDate createdAt = createdTs != null
                ? createdTs.toLocalDateTime()
                           .toLocalDate()
                : null;

        Timestamp updatedTs = rs.getTimestamp("updated_at");
        LocalDate updatedAt = updatedTs != null
                ? updatedTs.toLocalDateTime()
                           .toLocalDate()
                : null;
        String statusStr = rs.getString("status");

        return User.builder()
                   .id(rs.getLong("id"))
                   .login(rs.getString("login"))
                   .firstName(rs.getString("first_name"))
                   .lastName(rs.getString("last_name"))
                   .email(rs.getString("email"))
                   .birthday(rs.getDate("birthday")
                               .toLocalDate())
                   .createdAt(createdAt)
                   .status(UserStatus.valueOf(statusStr.toUpperCase()))
                   .updatedAt(updatedAt)
                   .build();
    }
}
