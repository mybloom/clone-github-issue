package com.example.clonegithubissue.label;

import com.example.clonegithubissue.common.dto.ApiResourceType;
import com.example.clonegithubissue.common.dto.GetApiResponse;
import com.example.clonegithubissue.common.dto.RelationDataResponse;
import com.example.clonegithubissue.common.dto.ResourceDataResponse;
import com.example.clonegithubissue.exception.LabelNotFoundException;
import com.example.clonegithubissue.label.dto.LabelListResponse;
import com.example.clonegithubissue.member.Member;
import com.example.clonegithubissue.member.dto.MemberDetailResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LabelService {

	private final LabelRepository labelRepository;

	@Transactional(readOnly = true)
	public GetApiResponse retrieveList(Long memberId, Integer page, Integer size) {

		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Label> labels = labelRepository.findByAuthor(Member.of(memberId), pageRequest);

		ResourceDataResponse<LabelListResponse> dataResponse = new ResourceDataResponse<>();
		dataResponse.setType(ApiResourceType.LABEL.getResourceType());
		dataResponse.setAttributes(labels.stream()
			.map(label -> new LabelListResponse(label.getId(), label.getTitle(), label.getColor()))
			.collect(Collectors.toList()));

		RelationDataResponse<MemberDetailResponse> relationResponse = new RelationDataResponse<>();
		relationResponse.setType(ApiResourceType.MEMBER.getResourceType());

		Member author = null;
		if (!labels.getContent().isEmpty()) {
			author = labels.getContent().get(0).getAuthor();
		}

		if (author == null) {
			relationResponse.setAttributes(Collections.emptyList());
		} else {
			relationResponse.setAttributes(
				List.of(new MemberDetailResponse(author.getOauthId(), author.getOauthName())));
		}

		dataResponse.setRelationships(relationResponse);

		return new GetApiResponse(dataResponse);
	}

	@Transactional(readOnly = true)
	public GetApiResponse retrieveDetail(Long memberId, Long labelId) {

		Label label = labelRepository.findById(labelId)
			.orElseThrow(LabelNotFoundException::new);

		ResourceDataResponse<LabelListResponse> dataResponse = new ResourceDataResponse<>();
		dataResponse.setType(ApiResourceType.LABEL.getResourceType());
		dataResponse.setAttributes(
			List.of(new LabelListResponse(label.getId(), label.getTitle(), label.getColor())));

		RelationDataResponse<MemberDetailResponse> relationResponse = new RelationDataResponse<>();
		relationResponse.setType(ApiResourceType.MEMBER.getResourceType());

		Member author = label.getAuthor();
		relationResponse.setAttributes(
			List.of(new MemberDetailResponse(author.getOauthId(), author.getOauthName())));

		return new GetApiResponse(dataResponse);
	}
}
