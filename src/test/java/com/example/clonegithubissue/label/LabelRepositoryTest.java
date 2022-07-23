package com.example.clonegithubissue.label;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.clonegithubissue.exception.LabelNotFoundException;
import com.example.clonegithubissue.label.dto.LabelSaveRequest;
import com.example.clonegithubissue.member.Member;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class LabelRepositoryTest {

	@Autowired
	LabelRepository labelRepository;
	@Autowired
	EntityManager entityManager;


	final Long authorId = 1L;
	final Long notAuthorId = 2L;
	final Long labelId = 1L;

	@Test
	@DisplayName("check if the labelId is a label created by the logged-in user")
	void findByIdAndAuthor() {
		//given

		//when
		Label label = labelRepository.findByIdAndAuthor(labelId, Member.of(authorId))
			.orElse(null);

		//then
		assertThat(label).isNotNull();
	}

	@Test
	@DisplayName("the labelId is a label created by the another user and not assignees")
	void findByIdAndNotAuthorAndNotAssignees() {
		//given

		//when
		Label label = labelRepository.findByIdAndAuthor(labelId, Member.of(notAuthorId))
			.orElse(null);

		//then
		assertThat(label).isNull();
	}

	@Test
	@DisplayName("check the delete() query method operation")
	void deleteLabel() {
		//given
		LabelSaveRequest labelSaveRequest = new LabelSaveRequest();
		labelSaveRequest.setTitle("테스트라벨");
		labelSaveRequest.setColor("#3D2FA6");
		labelSaveRequest.setDescription("");

		Label label = Label.from(labelSaveRequest, authorId);
		Label savedLabel = labelRepository.save(label);

		//when
		labelRepository.delete(savedLabel);
		entityManager.flush();
		entityManager.clear();

		//then
		assertThatExceptionOfType(LabelNotFoundException.class)
			.isThrownBy(() -> labelRepository.findById(savedLabel.getId())
				.orElseThrow(LabelNotFoundException::new));
	}
}