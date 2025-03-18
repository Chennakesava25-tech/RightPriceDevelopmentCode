package com.rightprice.auth.repository;

import com.rightprice.auth.model.User;

public interface UserRepository /*extends JpaRepository<User, Long>*/{
    User findByUsername(String username);

	void save(User user);
}
