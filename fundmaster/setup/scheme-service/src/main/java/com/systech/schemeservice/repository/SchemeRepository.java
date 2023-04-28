package com.systech.schemeservice.repository;

import com.systech.schemeservice.model.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemeRepository extends JpaRepository<Scheme, Long> {
}