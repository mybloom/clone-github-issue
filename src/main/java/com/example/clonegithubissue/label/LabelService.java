package com.example.clonegithubissue.label;

import com.example.clonegithubissue.common.dto.ApiResourceType;
import com.example.clonegithubissue.common.dto.CreateResourceResponse;
import com.example.clonegithubissue.common.dto.DataApiResponse;
import com.example.clonegithubissue.common.dto.RelationDataResponse;
import com.example.clonegithubissue.common.dto.GetResourceResponse;
import com.example.clonegithubissue.exception.LabelDuplicateDataException;
import com.example.clonegithubissue.exception.LabelNotFoundException;
import com.example.clonegithubissue.label.dto.LabelResponse;
import com.example.clonegithubissue.label.dto.LabelSaveRequest;
import com.example.clonegithubissue.member.Member;
import com.example.clonegithubissue.member.dto.MemberDetailResponse;
import java.sql.SQLIntegrityConstraintViolationException;
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
	public DataApiResponse retrieveList(Long memberId, Integer page, Integer size) {

		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Label> labels = labelRepository.findByAuthor(Member.of(memberId), pageRequest);

		GetResourceResponse<LabelResponse> dataResponse = new GetResourceResponse<>();
		dataResponse.setType(ApiResourceType.LABEL.getResourceType());
		dataResponse.setAttributes(labels.stream()
			.map(label -> new LabelResponse(label.getId(), label.getTitle(), label.getColor(),
				label.getDescription()))
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

		return new DataApiResponse(dataResponse);
	}

	@Transactional(readOnly = true)
	public DataApiResponse retrieveDetail(Long memberId, Long labelId) {

		Label label = labelRepository.findById(labelId)
			.orElseThrow(LabelNotFoundException::new);

		GetResourceResponse<LabelResponse> resourceResponse = new GetResourceResponse<>();
		resourceResponse.setType(ApiResourceType.LABEL.getResourceType());
		resourceResponse.setAttributes(
			List.of(new LabelResponse(label.getId(), label.getTitle(), label.getColor(),
				label.getDescription())));

		RelationDataResponse<MemberDetailResponse> relationResponse = new RelationDataResponse<>();
		relationResponse.setType(ApiResourceType.MEMBER.getResourceType());

		Member author = label.getAuthor();
		relationResponse.setAttributes(
			List.of(new MemberDetailResponse(author.getOauthId(), author.getOauthName())));
		resourceResponse.setRelationships(relationResponse);

		return new DataApiResponse(resourceResponse);
	}

	@Transactional
	public DataApiResponse createOne(Long memberId, LabelSaveRequest labelSaveRequest) {
		Label label = null;
		try {
			label = labelRepository.save(Label.from(labelSaveRequest, memberId));
		} catch (Exception e) {
			throw new LabelDuplicateDataException();
		}
		CreateResourceResponse<LabelResponse> resourceResponse = new CreateResourceResponse<>();
		resourceResponse.setType(ApiResourceType.LABEL.getResourceType());
		resourceResponse.setAttribute(
			new LabelResponse(label.getId(), label.getTitle(), label.getColor(),
				label.getDescription()));

		return new DataApiResponse(resourceResponse);
	}
}
