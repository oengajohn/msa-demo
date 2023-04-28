package com.systech.userservice.dto;

import com.systech.userservice.model.User;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the {@link User} entity
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto implements Serializable {
    private  Long id;

    private  String firstName;

    private  String lastName;

    private  String username;

    private  String email;

    private  Long  schemeId;

    private SchemeResponseDto schemeResponseDto;
}