package com.example.userData;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRoleIntegrationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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

    private void setRole() {

    }

    @Test
    public void addUsersTest() {
        int countUsersBefore = userService.getAllUsers().size();
        List<Long> usersIdList = setUsers();
        assertEquals(userService.getAllUsers().size(), countUsersBefore + 2);
        for (Long userId : usersIdList) {
            userService.deleteUser(userId);
        }
        assertEquals(userService.getAllUsers().size(), countUsersBefore);
    }
}

