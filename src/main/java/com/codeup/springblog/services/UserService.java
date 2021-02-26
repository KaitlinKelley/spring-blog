package com.codeup.springblog.services;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userDao;

    public UserService(UserRepository userDao) {
        this.userDao = userDao;
    }

    public User getLoggedInUser() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loggedInUser;
    }
}
