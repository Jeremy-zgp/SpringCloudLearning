package com.springcloud.uaa.service;

import com.springcloud.uaa.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User add(User user);
    List<User> findAll();
}
