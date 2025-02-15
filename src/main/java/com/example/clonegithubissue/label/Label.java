package com.example.clonegithubissue.label;

import com.example.clonegithubissue.common.domain.BaseTime;
import com.example.clonegithubissue.label.dto.LabelSaveRequest;
import com.example.clonegithubissue.member.Member;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Label extends BaseTime {

	public static final Integer DEFAULT_PAGE = 0;
	public static final Integer DEFAULT_PAGE_SIZE = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private String color;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member author;

	public static Label from(LabelSaveRequest labelSaveRequest, Long memberId) {
		return Label.builder()
			.title(labelSaveRequest.getTitle())
			.description(labelSaveRequest.getDescription())
			.color(labelSaveRequest.getColor())
			.author(Member.of(memberId))
			.build();
	}

	public static Label of(Long labelId) {
		return Label.builder()
			.id(labelId)
			.build();
	}

	public void modify(LabelSaveRequest labelSaveRequest) {
		title = labelSaveRequest.getTitle();
		color = labelSaveRequest.getColor();
		description = labelSaveRequest.getDescription();
	}
}
