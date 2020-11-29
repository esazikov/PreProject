package ru.javamentor.SpringBoot.service;

import ru.javamentor.SpringBoot.model.Role;

import java.util.List;

public interface RoleService {
    void add(Role role);
    Role getRoleById(Long id);
    Role getRoleByName(String roleName);
    List<Role> getAllRoles();
}
