package com.ryan.miaosha.service;

import com.ryan.miaosha.dao.UserDao;
import com.ryan.miaosha.domain.User;
import com.ryan.miaosha.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUser(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public boolean insertTransaction(User... user) {
        for (User u : user) {
            userDao.insertIntoUser(u);
        }
        return true;
    }
}
