package com.example.userData.services;

import com.example.userData.model.Role;
import com.example.userData.model.User;
import com.example.userData.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRoleService userRoleService;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById (Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public Role updateRole(Long id, Role role) {
        role.setId(id);
        return roleRepository.save(role);
    }

    public Role deleteRole(Long id) {
        Role deletedRole = getRoleById(id);
        roleRepository.deleteById(id);
        userRoleService.deleteRole(id);
        return deletedRole;
    }

    public void deleteAllById(List<Long> rolesIdList){
        roleRepository.deleteAllById(rolesIdList);
        userRoleService.deleteAllByRoleId(rolesIdList);
    }

}
