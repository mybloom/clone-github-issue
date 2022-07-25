package com.example.clonegithubissue.milestone;


import com.example.clonegithubissue.common.dto.ApiResourceType;
import com.example.clonegithubissue.common.dto.DataApiResponse;
import com.example.clonegithubissue.common.dto.ListResourceResponse;
import com.example.clonegithubissue.common.dto.OneResourceResponse;
import com.example.clonegithubissue.common.dto.RelationDataResponse;
import com.example.clonegithubissue.common.dto.SaveResourceResponse;
import com.example.clonegithubissue.exception.LabelNoPermissionException;
import com.example.clonegithubissue.exception.MilestoneDuplicateDataException;
import com.example.clonegithubissue.exception.MilestoneNotFoundException;
import com.example.clonegithubissue.issue.Issue;
import com.example.clonegithubissue.issue.dto.IssueListResponse;
import com.example.clonegithubissue.milestone.dto.MilestoneGetResponse;
import com.example.clonegithubissue.milestone.dto.MilestoneSaveRequest;
import com.example.clonegithubissue.milestone.dto.MilestoneSaveResponse;
import java.util.Comparator;
import java.util.List;
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
		ListResourceResponse<MilestoneGetResponse> dataResponse = new ListResourceResponse<>();
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

		OneResourceResponse<MilestoneGetResponse> resourceResponse = new OneResourceResponse<>();
		resourceResponse.setType(ApiResourceType.MILESTONE.getResourceType());
		resourceResponse.setAttribute(milestone.getMilestoneResponse());

		RelationDataResponse<IssueListResponse> relationResponse = new RelationDataResponse<>();
		relationResponse.setType(ApiResourceType.ISSUE.getResourceType());

		Set<Issue> issues = milestone.getIssues();
		List<IssueListResponse> issueListResponses = issues.stream()
			.map(IssueListResponse::from)
			.collect(Collectors.toList());
		issueListResponses.sort(Comparator.comparing(IssueListResponse::getOpened));
		relationResponse.setAttributes(issueListResponses);

		resourceResponse.setRelationships(relationResponse);

		return new DataApiResponse(resourceResponse);
	}

	@Transactional
	public DataApiResponse createOne(Long memberId, MilestoneSaveRequest milestoneSaveRequest) {
		Milestone milestone = null;
		try {
			milestone = milestoneRepository.save(Milestone.from(milestoneSaveRequest));
		} catch (RuntimeException e) {
			throw new MilestoneDuplicateDataException();
		}

		return convertEntityToResponse(milestone);
	}

	private DataApiResponse convertEntityToResponse(Milestone milestone) {
		SaveResourceResponse<MilestoneSaveResponse> resourceResponse = new SaveResourceResponse<>();
		resourceResponse.setType(ApiResourceType.MILESTONE.getResourceType());
		resourceResponse.setAttribute(MilestoneSaveResponse.from(milestone));

		return new DataApiResponse(resourceResponse);
	}

	@Transactional
	public DataApiResponse modifyOne(Long memberId, Long milestoneId,
		MilestoneSaveRequest milestoneSaveRequest) throws RuntimeException {
		Milestone milestone = milestoneRepository.findByIdAndAuthorId(milestoneId, memberId)
			.orElseThrow(LabelNoPermissionException::new);

		milestone.modify(milestoneSaveRequest);
		milestone = milestoneRepository.save(milestone);

		return convertEntityToResponse(milestone);
	}

	public void deleteOne(Long memberId, Long milestoneId) {

	}
}
