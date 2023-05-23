package com.demo.controller;

import com.demo.dto.UserDto;
import com.demo.service.UserService;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {

    @Autowired
    private UserService userService;
    //http://localhost:8080/api/users
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
    //http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    //http://localhost:8080/api/users
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdUser);
    }
    //http://localhost:8080/api/users/1
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
    //http://localhost:8080/api/users/1
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<User> findByName(@PathVariable String name){
        User name1 = userService.getName(name);
        return new ResponseEntity<>(name1,HttpStatus.OK);
    }
}
