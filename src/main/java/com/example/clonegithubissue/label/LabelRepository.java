package com.example.clonegithubissue.label;

import com.example.clonegithubissue.member.Member;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

	@EntityGraph(attributePaths = {"author"})
	List<Label> findByAuthor(Member author);

}
