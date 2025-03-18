package com.rightprice.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rightprice.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
