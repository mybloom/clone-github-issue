package com.example.clonegithubissue.milestone;

import com.example.clonegithubissue.common.dto.ApiResourceType;
import com.example.clonegithubissue.common.dto.DataApiResponse;
import com.example.clonegithubissue.common.dto.ListResourceResponse;
import com.example.clonegithubissue.common.dto.OneResourceResponse;
import com.example.clonegithubissue.common.dto.RelationDataResponse;
import com.example.clonegithubissue.exception.MilestoneNotFoundException;
import com.example.clonegithubissue.issue.Issue;
import com.example.clonegithubissue.member.dto.MemberDetailResponse;
import com.example.clonegithubissue.milestone.dto.MilestoneResponse;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MilestoneService {

	private final MilestoneRepository milestoneRepository;

	@Transactional(readOnly = true)
	public DataApiResponse retrieveList(Long memberId, Integer page, Integer size) {

		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Milestone> milestones = milestoneRepository.findByAuthorId(memberId, pageRequest);

		//entity To DTO
		ListResourceResponse<MilestoneResponse> dataResponse = new ListResourceResponse<>();
		dataResponse.setType(ApiResourceType.MILESTONE.getResourceType());
		dataResponse.setAttributes(milestones.stream()
			.map(milestone -> milestone.getMilestoneResponse())
			.collect(Collectors.toList()));

		return new DataApiResponse(dataResponse);
	}

	@Transactional(readOnly = true)
	public DataApiResponse retrieveDetail(Long memberId, Long milestoneId) {

		Milestone milestone = milestoneRepository.findById(milestoneId)
			.orElseThrow(MilestoneNotFoundException::new);

		OneResourceResponse<MilestoneResponse> resourceResponse = new OneResourceResponse<>();
		resourceResponse.setType(ApiResourceType.MILESTONE.getResourceType());
		resourceResponse.setAttribute(milestone.getMilestoneResponse());

		RelationDataResponse<MemberDetailResponse> relationResponse = new RelationDataResponse<>();
		relationResponse.setType(ApiResourceType.MEMBER.getResourceType());

		Set<Issue> issues = milestone.getIssues();
		// TODO: implement issueListByMilestoneId() in IssueService then set relationResponse.
//		relationResponse.setAttributes();

		return new DataApiResponse(resourceResponse);
	}
}
