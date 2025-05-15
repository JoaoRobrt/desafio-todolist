package com.joao.demo.controller;

import com.joao.demo.dto.UserDTO;
import com.joao.demo.entity.User;
import com.joao.demo.mappers.UserMapper;
import com.joao.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDTO dto){
        var user = userMapper.ToEntity(dto);
        System.out.println("DTO: " + dto);
        System.out.println("Mapped User: " + user);
        userService.save(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.list();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        return userService.findById(id).map(ResponseEntity :: ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user){
        return userService.update(id, user).map(updatedUser -> ResponseEntity.ok().body(updatedUser))
                .orElse(ResponseEntity.notFound().build());
    }

}
