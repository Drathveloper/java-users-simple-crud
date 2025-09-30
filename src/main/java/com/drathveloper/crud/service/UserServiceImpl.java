package com.drathveloper.crud.service;

import com.drathveloper.crud.model.User;
import com.drathveloper.crud.repository.UserRepository;
import com.drathveloper.crud.repository.exception.ItemNotFound;
import com.drathveloper.crud.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User getUser(final long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("user " + id + " not found"));
    }

    public long registerUser(final User user) {
        return userRepository.save(user).getId();
    }

    public void deleteUser(final long id) {
        try {
            userRepository.delete(id);
        } catch (final ItemNotFound ex) {
            log.info("user {} not found", id);
        }
    }

    public void updateUser(final User user) {
        try {
            userRepository.update(user);
        } catch (final ItemNotFound ex) {
            throw new UserNotFoundException("user " + user.getId() + " not found");
        }
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
