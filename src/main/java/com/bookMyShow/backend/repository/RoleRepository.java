package com.bookMyShow.backend.repository;

import com.bookMyShow.backend.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
