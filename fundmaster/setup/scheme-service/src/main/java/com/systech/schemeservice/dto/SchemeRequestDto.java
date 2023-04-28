package com.systech.schemeservice.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the {@link com.systech.schemeservice.model.Scheme} entity
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchemeRequestDto implements Serializable {

    private  String schemeName;

    private  String contactEmail;
}