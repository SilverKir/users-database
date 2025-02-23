package com.example.userData.services;

import com.example.userData.model.UserRole;
import com.example.userData.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    public boolean hasUserRole(Long userId, Long roleId) {
        return !getAllUserRoles().stream().filter(userRole -> userRole.getUserId() == userId
                && userRole.getRoleId() == roleId).toList().isEmpty();
    }

    public UserRole getUserRole(Long userId, Long roleId) {
        return getAllUserRoles().stream().filter(userRole -> userRole.getUserId() == userId
                && userRole.getRoleId() == roleId).toList().getFirst();
    }

    public UserRole addUserRole(Long userId, Long roleId) {
        if (!hasUserRole(userId, roleId)) {
            userRoleRepository.save(new UserRole(userId, roleId));
        }
        return getUserRole(userId, roleId);
    }

    public List<Long> hasRoles(Long userId) {
        return getAllUserRoles().stream().filter(userRole -> userRole.getUserId() == userId)
                .map(UserRole::getRoleId).collect(Collectors.toList());
    }

    public List<Long> usersWithRole(Long roleId) {
        return getAllUserRoles().stream().filter(userRole -> userRole.getRoleId() == roleId)
                .map(UserRole::getUserId).collect(Collectors.toList());
    }

    public void deleteUserRole(Long userId, Long roleId) {
        if (!hasUserRole(userId, roleId)) {
            userRoleRepository.delete(getUserRole(userId, roleId));
        }
    }

    public void deleteUser(Long userId) {
        if (!hasRoles(userId).isEmpty()) {
            userRoleRepository.deleteByUserId(userId);
        }
    }

    public void deleteRole(Long roleId) {
        if (!usersWithRole(roleId).isEmpty()) {
            userRoleRepository.deleteByRoleId(roleId);
        }
    }

    public void deleteAllByUserId(List<Long> usersIdList) {
        for (Long userId : usersIdList) {
            deleteUser(userId);
        }
    }

    public void deleteAllByRoleId(List<Long> rolesIdList) {
        for (Long roleId : rolesIdList) {
            deleteRole(roleId);
        }
    }

}
