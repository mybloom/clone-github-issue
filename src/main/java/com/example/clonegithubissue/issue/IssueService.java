package com.example.clonegithubissue.issue;

import com.example.clonegithubissue.common.dto.ApiResourceType;
import com.example.clonegithubissue.common.dto.DataApiResponse;
import com.example.clonegithubissue.common.dto.OneResourceResponse;
import com.example.clonegithubissue.exception.IssueNotFoundException;
import com.example.clonegithubissue.issue.dto.IssueListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class IssueService {

	private final IssueRepository issueRepository;

	@Transactional(readOnly = true)
	public DataApiResponse retrieveDetail(Long memberId, Long issueId) {

		Issue issue = issueRepository.findById(issueId)
			.orElseThrow(IssueNotFoundException::new);

		OneResourceResponse<IssueListResponse> resourceResponse = new OneResourceResponse<>();
		resourceResponse.setType(ApiResourceType.ISSUE.getResourceType());
//		resourceResponse.setAttribute();

		return new DataApiResponse(resourceResponse);
	}
}
