package com.example.clonegithubissue.milestone;

import com.example.clonegithubissue.common.dto.ApiResourceType;
import com.example.clonegithubissue.common.dto.DataApiResponse;
import com.example.clonegithubissue.common.dto.ListResourceResponse;
import com.example.clonegithubissue.milestone.dto.MilestoneResponse;
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
}
