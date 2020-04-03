package com.school.administration.app.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.AudienceEntity;

@Repository
public interface AudienceRepositories extends PagingAndSortingRepository<AudienceEntity, Long> {
	AudienceEntity findByAudienceName(String audienceName);
	AudienceEntity findAudienceByAudienceId(String audienceId);
}
