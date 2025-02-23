package com.example.userData;

import com.example.userData.model.Role;
import com.example.userData.repository.RoleRepository;
import com.example.userData.services.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class RoleAddUnitTests {
    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService;

    @Test
    public void getAllRolesTest() {

        Role testRole = new Role();
        testRole.setRoleName("Admin");
        testRole.setDescription("Admin role");
        List<Role> expectedRoles = Collections.singletonList(testRole);
        when(roleRepository.findAll()).thenReturn(expectedRoles);
        List<Role> actualRole = roleService.getAllRoles();
        assertEquals(actualRole.size(), 1);
        assertEquals(expectedRoles, actualRole);
    }


}
