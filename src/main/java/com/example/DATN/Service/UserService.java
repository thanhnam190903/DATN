package com.example.DATN.Service;

import com.example.DATN.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    public void save(User user);
    public User findByUsername(String username);

}
