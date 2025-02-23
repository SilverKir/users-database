package com.example.userData.repository;

import com.example.userData.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {
    public void deleteByUserId(Long userId);
    public void deleteByRoleId(Long roleId);
}
