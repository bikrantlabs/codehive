package com.codehive.repository.ports;

import com.codehive.domain.entity.User;

import java.sql.SQLException;

public interface UserRepoInterface {
    User create(User user) throws SQLException;

    User getById(Integer id);

    User getByEmail(String email) throws SQLException;
}
