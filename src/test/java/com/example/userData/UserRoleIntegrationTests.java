package com.example.userData;

import com.example.userData.model.Role;
import com.example.userData.model.User;
import com.example.userData.repository.RoleRepository;
import com.example.userData.repository.UserRepository;
import com.example.userData.repository.UserRoleRepository;
import com.example.userData.services.RoleService;
import com.example.userData.services.UserRoleService;
import com.example.userData.services.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRoleIntegrationTests {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    private List<Long> setUsers() {
        List<Long> usersId = new ArrayList<>();
        User user1 = new User();
        user1.setName("Bill");
        userService.addUser(user1);
        usersId.add(user1.getId());
        User user2 = new User();
        user2.setName("John");
        userService.addUser(user2);
        usersId.add(user2.getId());
        return usersId;
    }

    private List<Long> setRoles() {
        List<Long> rolesID = new ArrayList<>();
        Role role1 = new Role("Admin", "Administrator");
        roleService.addRole(role1);
        rolesID.add(role1.getId());

        Role role2 = new Role("User", "User");
        roleService.addRole(role2);
        rolesID.add(role2.getId());
        return rolesID;
    }

    @Test
    public void addDeleteUsersTest() {
        int countUsersBefore = userService.getAllUsers().size();
        List<Long> usersIdList = setUsers();
        assertEquals(userService.getAllUsers().size(), countUsersBefore + 2);
        userService.deleteAllById(usersIdList);
        assertEquals(userService.getAllUsers().size(), countUsersBefore);
    }

    @Test
    public void addDeleteRolesTest() {
        int countRolesBefore = roleService.getAllRoles().size();
        List<Long> rolesIdList = setRoles();
        assertEquals(roleService.getAllRoles().size(), countRolesBefore + 2);
        roleService.deleteAllById(rolesIdList);
        assertEquals(roleService.getAllRoles().size(), countRolesBefore);
    }

    @Transactional
    @Test
    public void UserRolesTest() {
        List<Long> usersIdList = setUsers();
        List<Long> rolesIdList = setRoles();
        int initialSize = userRoleService.getAllUserRoles().size();

        System.out.println(initialSize);

        userRoleService.addUserRole(usersIdList.getFirst(), rolesIdList.getFirst());
        userRoleService.addUserRole(usersIdList.getFirst(), rolesIdList.getFirst());

        assertEquals(userRoleService.getAllUserRoles().size(), initialSize + 1);

        userService.deleteAllById(usersIdList);
        assertEquals(userRoleService.getAllUserRoles().size(), initialSize);
        roleService.deleteAllById(rolesIdList);
    }

}

