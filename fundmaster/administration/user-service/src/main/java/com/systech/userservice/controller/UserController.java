package com.systech.userservice.controller;

import com.systech.userservice.dto.UserRequestDto;
import com.systech.userservice.dto.UserResponseDto;
import com.systech.userservice.dto.toExtResponse;
import com.systech.userservice.service.UserService;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUSer() {
        return userService.getAllUsers();
    }

    @PostMapping
    public boolean createUser(@RequestBody UserRequestDto userRequestDto) {
        //service
        return userService.userService(userRequestDto);

    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "schemeservice", fallbackMethod = "schemeServiceUnavailable")

    public toExtResponse getUserWithSchemeInformation(@PathVariable("userId") Long userId) throws Exception {
        UserResponseDto userById = userService.getUserById(userId);
       return toExtResponse.builder().success(true).msg("Details ok").userResponseDto(userById).build();
    }

    public toExtResponse schemeServiceUnavailable(Long userId,
                                                    RuntimeException runtimeException) {
        return toExtResponse.builder().success(false).msg("Oops! Service taking too long to respond...please try again later").build();

    }


}
