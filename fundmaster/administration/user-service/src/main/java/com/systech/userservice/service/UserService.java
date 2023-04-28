package com.systech.userservice.service;

import com.systech.userservice.dto.SchemeResponseDto;
import com.systech.userservice.dto.UserRequestDto;
import com.systech.userservice.dto.UserResponseDto;
import com.systech.userservice.model.User;
import com.systech.userservice.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;

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

    public UserResponseDto getUserById(Long userId) throws Exception {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent()){
            User user = byId.get();
            var userResponseDto = mapToResponse(user);

            //call the scheme service to get scheme info
            var schemeResponseDto = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/schemes/",
                            uriBuilder -> uriBuilder.path(user.getSchemeId().toString()).build())
                    .retrieve()
                    .bodyToMono(SchemeResponseDto.class)
                    .block();
            userResponseDto.setSchemeResponseDto(schemeResponseDto);
            return userResponseDto;
        }
        throw new Exception("User with ID "+userId +" not found");
    }
}
