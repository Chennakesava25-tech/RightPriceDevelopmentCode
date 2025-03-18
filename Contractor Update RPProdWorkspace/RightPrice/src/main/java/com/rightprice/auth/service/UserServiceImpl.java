package com.rightprice.auth.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rightprice.auth.model.User;
import com.rightprice.auth.repository.RoleRepository;
import com.rightprice.auth.repository.UserRepository;
import com.rightprice.auth.util.AppLoger;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /*@Autowired
    private SyntelUserRepository syntelUserRepository;*/

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        	AppLoger.APPLOGGER.info("Password encoded,,,,,,");
        	AppLoger.APPLOGGER.info("role setting Started");
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        	AppLoger.APPLOGGER.info("role setting completed");
        	AppLoger.APPLOGGER.info("saving user");
        userRepository.save(user);
        	AppLoger.APPLOGGER.info("saving user completed");
        
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
