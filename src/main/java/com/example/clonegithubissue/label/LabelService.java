package com.example.clonegithubissue.label;

import com.example.clonegithubissue.common.dto.ApiResourceType;
import com.example.clonegithubissue.common.dto.ApiResponse;
import com.example.clonegithubissue.common.dto.RelationDataResponse;
import com.example.clonegithubissue.common.dto.RootDataResponse;
import com.example.clonegithubissue.label.dto.LabelListResponse;
import com.example.clonegithubissue.member.Member;
import com.example.clonegithubissue.member.dto.MemberDetailResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LabelService {

	private final LabelRepository labelRepository;

	public ApiResponse retrieveList(Long memberId) {
		List<Label> labels = labelRepository.findByAuthor(Member.of(memberId));

		RootDataResponse<LabelListResponse> dataResponse = new RootDataResponse<>();
		dataResponse.setType(ApiResourceType.LABEL.getResourceType());
		dataResponse.setAttributes(labels.stream()
			.map(label -> new LabelListResponse(label.getId(), label.getTitle(), label.getColor()))
			.collect(Collectors.toList()));

		RelationDataResponse<MemberDetailResponse> relationResponse = new RelationDataResponse<>();
		relationResponse.setType(ApiResourceType.MEMBER.getResourceType());
		Member author = labels.get(0).getAuthor();
		relationResponse.setAttributes(List.of(new MemberDetailResponse(author.getOauthId(),author.getOauthName())));

		dataResponse.setRelationships(relationResponse);

		return new ApiResponse(dataResponse);
	}
}
