package ru.javamentor.SpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.SpringBoot.model.Role;
import ru.javamentor.SpringBoot.model.User;
import ru.javamentor.SpringBoot.service.RoleService;
import ru.javamentor.SpringBoot.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute(userService.getUserbyId(id));
        return "edit";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "userRole", required = false) boolean userRole,
                          @RequestParam(value = "adminRole", required = false) boolean adminRole) {
        user.setRoles(getRoles(userRole,adminRole));
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/patch")
    public String update(@PathVariable("id") Long id,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "lastname") String lastname,
                         @RequestParam(value = "age") byte age,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "userRole", required = false) boolean userRole,
                         @RequestParam(value = "adminRole", required = false) boolean adminRole) {
        User user = userService.getUserbyId(id);
        user.setName(name);
        user.setLastname(lastname);
        user.setAge(age);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(getRoles(userRole,adminRole));
        userService.edit(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserbyId(id);
        return "redirect:/admin";
    }

    private Set<Role> getRoles(boolean userRole, boolean adminRole) {
        Set<Role> roles = new HashSet<>();
        if (userRole) {
            roles.add(roleService.getRoleByName("ROLE_USER"));
        }
        if (adminRole) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        }
        return roles;
    }

}
