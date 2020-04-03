package com.school.administration.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.school.administration.app.shared.dto.AudienceDto;

public interface AudienceService extends UserDetailsService {
	AudienceDto createAudience(AudienceDto audience);
}
