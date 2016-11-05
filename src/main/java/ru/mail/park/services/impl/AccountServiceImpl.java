package ru.mail.park.services.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.park.models.User;
import ru.mail.park.repositories.UserDAO;
import ru.mail.park.services.AccountService;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    UserDAO userDao;

    private static final Logger logger = LogManager.getLogger("main");

    @Autowired
    public AccountServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Nullable
    @Override
    public Long addUser(User user) {
        try {
            return userDao.addUser(user.getLogin(), user.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userDao.deleteUser(userId);
        } catch (DataAccessException e) {
            logger.info(e);
        }
    }

    @Nullable
    @Override
    public User getUserByLogin(String login) {
        try {
            return userDao.getUserByLogin(login);
        } catch (EmptyResultDataAccessException e) {
            logger.info(e);
            return null;
        }
    }

    @Nullable
    @Override
    public User getUserById(long id) {
        try {
            return userDao.getUserById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.info(e);
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Nullable
    @Override
    public List<Map<String, Object>> score(String limit) {
        try {
            return userDao.getUsersScore(limit);
        } catch (DataAccessException e) {
            logger.info(e);
            return null;
        }

    }
}