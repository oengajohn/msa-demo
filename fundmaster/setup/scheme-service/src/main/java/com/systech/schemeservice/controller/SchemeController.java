package com.systech.schemeservice.controller;

import com.systech.schemeservice.dto.SchemeRequestDto;
import com.systech.schemeservice.dto.SchemeResponseDto;
import com.systech.schemeservice.service.SchemeService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schemes")
public class SchemeController {
    private final SchemeService schemeService;
    @GetMapping
    public List<SchemeResponseDto> getAllUSer(){
        return schemeService.getAllSchemes();
    }

    @PostMapping
    public boolean createUser(@RequestBody SchemeRequestDto schemeRequestDto){
        //service
        return schemeService.saveScheme(schemeRequestDto);

    }
    @GetMapping("/{schemeId}")
    public SchemeResponseDto getSchemeById(@PathVariable("schemeId") Long schemeId) throws Exception {
        return schemeService.getSchemeById(schemeId);
    }
}
