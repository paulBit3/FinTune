package com.yetaidev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yetaidev.model.Role;
import com.yetaidev.model.RoleName;


/*
 * Role repository to manage User roles
 */
public interface RoleDao extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}
