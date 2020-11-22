package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

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

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "userRole", required = false) boolean userRole,
                         @RequestParam(value = "adminRole", required = false) boolean adminRole) {
        user.setRoles(getRoles(userRole,adminRole));
        userService.edit(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
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
