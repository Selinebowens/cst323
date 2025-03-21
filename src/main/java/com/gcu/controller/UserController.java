package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.User;
import com.gcu.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	 @Autowired
	    private UserService userService;

	    @GetMapping
	    public String getAllUsers(Model model) {
	        List<User> users = userService.getAllUsers();
	        model.addAttribute("users", users);
	        return "user-list";
	    }

	    @GetMapping("/new")
	    public String showUserForm(Model model) {
	        model.addAttribute("user", new User());
	        return "user-form";
	    }

	    @PostMapping
	    public String createUser(@ModelAttribute User user) {
	        userService.saveUser(user);
	        return "redirect:/users";
	    }

	    @GetMapping("/{id}")
	    public String getUserById(@PathVariable Long id, Model model) {
	        User user = userService.getUserById(id);
	        model.addAttribute("user", user);
	        return "user-details";
	    }

	    @PostMapping("/{id}")
	    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
	        user.setId(id);
	        userService.saveUser(user);
	        return "redirect:/users";
	    }

	    @DeleteMapping("/{id}")
	    public String deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	        return "redirect:/users";
	    }
}
