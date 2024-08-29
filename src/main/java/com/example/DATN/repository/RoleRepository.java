package com.example.DATN.repository;

import com.example.DATN.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role ,Integer> {
    public Role findByName(String name);
}
