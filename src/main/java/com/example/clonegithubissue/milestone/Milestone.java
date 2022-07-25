package com.example.clonegithubissue.milestone;

import com.example.clonegithubissue.issue.Issue;
import com.example.clonegithubissue.milestone.dto.MilestoneResponse;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
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
public class Milestone {

	public static final Integer DEFAULT_PAGE = 0;
	public static final Integer DEFAULT_PAGE_SIZE = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "milestone_id")
	private Long id;

	private String title;

	private String description;

	private LocalDate dueDate;

	@Builder.Default
	@OneToMany(mappedBy = "milestone")
	private Set<Issue> issues = new HashSet<>();

	@Column(name = "member_id")
	private Long authorId;

	@Builder.Default
	@Transient
	private int openIssueCount = 0;

	@Builder.Default
	@Transient
	private int closedIssueCount = 0;

	@Builder.Default
	@Transient
	private int completionPercentage = 0;

	private void calculateIssueCountByStatusAndCompletionPercentage() {
		for (Issue issue : issues) {
			if (issue.getOpened() == true) {
				openIssueCount++;
			} else {
				closedIssueCount++;
			}
		}

		for (Issue issue : issues) {
			completionPercentage = (int)(((double) closedIssueCount / (closedIssueCount + openIssueCount)) *100);
		}
	}

	public MilestoneResponse getMilestoneResponse() {
		calculateIssueCountByStatusAndCompletionPercentage();

		return MilestoneResponse.builder()
			.id(id)
			.title(title)
			.description(description)
			.dueDate(dueDate)
			.openIssueCount(openIssueCount)
			.closedIssueCount(closedIssueCount)
			.completionPercentage(completionPercentage)
			.build();
	}
}
