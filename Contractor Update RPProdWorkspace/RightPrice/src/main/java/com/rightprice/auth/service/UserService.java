package com.rightprice.auth.service;

import com.rightprice.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
