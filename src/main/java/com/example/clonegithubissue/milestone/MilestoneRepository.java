package com.example.clonegithubissue.milestone;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

	@EntityGraph(attributePaths = {"issues"})
	Page<Milestone> findByAuthorId(Long authorId, Pageable pageable);

	@EntityGraph(attributePaths = {"issues"})
	@Override
	Optional<Milestone> findById(Long milestoneId);
}
