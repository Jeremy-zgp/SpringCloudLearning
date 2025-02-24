package com.springcloud.uaa.service;

import com.springcloud.uaa.entity.Permission;
import com.springcloud.uaa.entity.Role;
import com.springcloud.uaa.entity.User;
import com.springcloud.uaa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }
}