package ru.mail.park.services;

import ru.mail.park.models.User;

/**
 * Created by kirrok on 21.10.16.
 */
public interface AccountService {
    Long addUser(User user);

    void deleteUser(Long user);

    void updateUser(User user);

    User getUserByLogin(String email);

    User getUserById(long id);

}
