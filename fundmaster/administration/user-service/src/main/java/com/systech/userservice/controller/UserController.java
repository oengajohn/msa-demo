package com.systech.userservice.controller;

import com.systech.userservice.dto.UserRequestDto;
import com.systech.userservice.dto.UserResponseDto;
import com.systech.userservice.service.UserService;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUSer(){
        return userService.getAllUsers();
    }

    @PostMapping
    public boolean createUser(@RequestBody UserRequestDto userRequestDto){
        //service
        return userService.userService(userRequestDto);

    }
}
