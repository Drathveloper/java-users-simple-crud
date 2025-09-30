package com.drathveloper.crud.repository;

import com.drathveloper.crud.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(final long id);
    User save(final User user);
    void delete(final long id);
    void update(final User user);
    long count();
}
