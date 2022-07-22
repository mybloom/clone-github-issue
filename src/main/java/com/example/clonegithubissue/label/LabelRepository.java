package com.example.clonegithubissue.label;

import com.example.clonegithubissue.member.Member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

	@EntityGraph(attributePaths = {"author"})
	Page<Label> findByAuthor(Member author, Pageable pageable);

}
