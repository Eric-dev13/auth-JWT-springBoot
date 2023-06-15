package com.ricou.api.todolist.controller;

import com.ricou.api.todolist.Model.UserEntity;
import com.ricou.api.todolist.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    // Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final UserService userService;

    /*
    @GetMapping("/detail")
    public UserDetails loadUserByUsername(String username) {
        return userService.loadUserByUsername("admin");
    }
     */


    @GetMapping("/")
    @Secured("ROLE_USER")
    public Iterable<UserEntity> getUsers() {  // iterable or collection
        // Retourne la liste des taches à réaliser
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable("id") Long id) {
        // Retourne une taches à réaliser par son identifiant
        return userService.getById(id);
    }

    @PostMapping("/")
    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return userService.add(userEntity);
    }

    @PutMapping("/{id}")
    public void editUser(@PathVariable("id") final String id, @RequestBody final UserEntity userEntity) {
        userService.edit(userEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
    }
}
