package com.springcloud.uaa.controller;

import com.springcloud.uaa.entity.User;
import com.springcloud.uaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String userName, @RequestParam String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userName, password);
            // 3. 调用 AuthenticationManager 进行认证
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // 4. 将认证信息存入安全上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login failed");
            return ResponseEntity.status(401).body(response);
        }
    }

    @GetMapping("/addUser")
    public void addUser(@RequestParam String userName, @RequestParam String password) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        userService.add(user);
    }

    @GetMapping("/list")
    public List<User> findAll() {
        return userService.findAll();
    }
}
