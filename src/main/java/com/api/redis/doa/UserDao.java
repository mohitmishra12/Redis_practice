package com.api.redis.doa;

import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.Map;

@Repository
public class UserDao {

    private static final String KEY = "USER";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, User> hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public User save(User user) {
        hashOperations.put(KEY, user.getUserId(), user);
        return user;
    }

    public User get(String userId) {
        return hashOperations.get(KEY, userId);
    }

    public Map<Object, Object> findAll() {
        return redisTemplate.opsForHash().entries(KEY);
    }

    public void delete(String userId) {
        hashOperations.delete(KEY, userId);
    }
}
