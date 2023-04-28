package com.systech.schemeservice.service;

import com.systech.schemeservice.dto.SchemeRequestDto;
import com.systech.schemeservice.dto.SchemeResponseDto;
import com.systech.schemeservice.model.Scheme;
import com.systech.schemeservice.repository.SchemeRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchemeService {
    private final SchemeRepository schemeRepository;

    public boolean userService(SchemeRequestDto userRequestDto) {
        Scheme scheme = this.mapToScheme(userRequestDto);
        schemeRepository.save(scheme);
        //TODO: send notification
        return true;
    }

    private Scheme mapToScheme(SchemeRequestDto userRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRequestDto, Scheme.class);

    }

    public List<SchemeResponseDto> getAllUsers() {
        return schemeRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    private SchemeResponseDto mapToResponse(Scheme scheme) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(scheme, SchemeResponseDto.class);
    }

}
