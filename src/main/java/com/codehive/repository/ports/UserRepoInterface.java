package com.codehive.repository.ports;

import com.codehive.domain.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepoInterface {
    User create(User user) throws SQLException;

    Optional<User> getById(Integer id);
}
