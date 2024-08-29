package com.example.DATN.repository;

import com.example.DATN.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    public User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.fullName LIKE %?1% " +
            "OR u.sdt LIKE %?1% " +
            "OR u.email LIKE %?1%")
    List<User> searchUser(String key);

}
