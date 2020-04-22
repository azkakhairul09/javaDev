package com.school.administration.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.school.administration.app.shared.dto.AudienceDto;

public interface AudienceService extends UserDetailsService {
	AudienceDto createAudience(String roleId, AudienceDto audience);
	List<AudienceDto> getAudiences(int page, int limit);
	AudienceDto getAudienceByAudienceId(String audienceId);
}
