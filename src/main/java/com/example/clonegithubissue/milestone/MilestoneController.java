package com.example.clonegithubissue.milestone;

import com.example.clonegithubissue.common.dto.DataApiResponse;
import com.example.clonegithubissue.common.dto.ListResourceResponse;
import com.example.clonegithubissue.label.Label;
import com.example.clonegithubissue.milestone.dto.MilestoneResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MilestoneController {

	private final MilestoneService milestoneService;
	private final static Long MEMBER_ID = 1L;

	@GetMapping("/milestones")
	public ResponseEntity<DataApiResponse> retrieveList(
		@RequestParam("page") Optional<Integer> page,
		@RequestParam("size") Optional<Integer> size) {

		DataApiResponse apiResponse = milestoneService.retrieveList(MEMBER_ID,
			page.orElse(Milestone.DEFAULT_PAGE), size.orElse(Milestone.DEFAULT_PAGE_SIZE));

		return ResponseEntity.ok().body(apiResponse);
	}
}
