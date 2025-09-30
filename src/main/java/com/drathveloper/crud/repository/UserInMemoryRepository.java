package com.drathveloper.crud.repository;

import com.drathveloper.crud.model.User;
import com.drathveloper.crud.repository.entity.UserEntity;
import com.drathveloper.crud.repository.exception.ItemNotFound;
import com.drathveloper.crud.repository.mapper.UserEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Repository
public class UserInMemoryRepository implements UserRepository {

    private final UserEntityMapper userMapper;

    private final ReentrantLock mutex = new ReentrantLock();

    private final Map<Long, UserEntity> users = new HashMap<>();

    public UserInMemoryRepository(UserEntityMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findById(final long id) {
        try {
            mutex.lock();
            final UserEntity entity = users.get(id);
            if (entity == null) {
                return Optional.empty();
            }
            return Optional.of(userMapper.userEntityToUser(entity));
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public User save(final User user) {
        try {
            mutex.lock();
            final long nextId = users.size() + 1;
            final UserEntity entity = userMapper.userToUserEntity(nextId, user);
            users.put(nextId, entity);
            return userMapper.userEntityToUser(entity);
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void delete(final long id) {
        try {
            mutex.lock();
            if (!users.containsKey(id)) {
                throw new ItemNotFound("item not found");
            }
            users.remove(id);
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void update(final User user) {
        try {
            mutex.lock();
            if (!users.containsKey(user.getId())) {
                throw new ItemNotFound("item not found");
            }
            final UserEntity entity = userMapper.userToUserEntity(user);
            users.put(user.getId(), entity);
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public long count() {
        try {
            mutex.lock();
            return users.size();
        } finally {
            mutex.unlock();
        }
    }
}
