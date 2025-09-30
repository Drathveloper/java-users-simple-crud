package com.drathveloper.crud.service;

import com.drathveloper.crud.model.User;

public interface UserService {
    User getUser(final long id);
    long registerUser(final User user);
    void deleteUser(final long id);
    void updateUser(final User user);
    long count();
}
