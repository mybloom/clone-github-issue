package com.example.clonegithubissue.issue;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	Optional<Issue> findById(Long milestoneId);

}
