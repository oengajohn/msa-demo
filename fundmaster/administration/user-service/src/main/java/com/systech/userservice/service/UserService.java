package com.systech.userservice.service;

import com.systech.userservice.dto.UserRequestDto;
import com.systech.userservice.dto.UserResponseDto;
import com.systech.userservice.model.User;
import com.systech.userservice.repository.UserRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public boolean userService(UserRequestDto userRequestDto) {
        User user = this.mapToUSer(userRequestDto);
        userRepository.save(user);
        //TODO: send notification
        return true;
    }

    private User mapToUSer(UserRequestDto userRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRequestDto, User.class);

    }

    public List<UserResponseDto> getAllUsers() {
      return  userRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    private UserResponseDto mapToResponse(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserResponseDto.class);
    }
}
