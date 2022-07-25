package com.example.clonegithubissue.milestone;

import com.example.clonegithubissue.common.dto.DataApiResponse;
import com.example.clonegithubissue.exception.LabelDuplicateDataException;
import com.example.clonegithubissue.exception.MilestoneDuplicateDataException;
import com.example.clonegithubissue.label.dto.LabelSaveRequest;
import com.example.clonegithubissue.milestone.dto.MilestoneSaveRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/milestones/{milestoneId}")
	public ResponseEntity<DataApiResponse> retrieveDetail(@PathVariable Long milestoneId) {
		DataApiResponse apiResponse = milestoneService.retrieveDetail(MEMBER_ID, milestoneId);

		return ResponseEntity.ok().body(apiResponse);
	}

	@PostMapping("/milestones")
	public ResponseEntity<DataApiResponse> createOne(
		@RequestBody MilestoneSaveRequest milestoneSaveRequest) {
		DataApiResponse apiResponse = milestoneService.createOne(MEMBER_ID, milestoneSaveRequest);

		return ResponseEntity.ok().body(apiResponse);
	}

	@PatchMapping("/milestones/{milestoneId}")
	public ResponseEntity<DataApiResponse> modifyOne(@PathVariable Long milestoneId,
		@RequestBody MilestoneSaveRequest milestoneSaveRequest) {

		DataApiResponse apiResponse = null;
		try {
			apiResponse = milestoneService.modifyOne(MEMBER_ID, milestoneId,
				milestoneSaveRequest);
		} catch (RuntimeException e) {
			throw new MilestoneDuplicateDataException();
		}

		return ResponseEntity.ok().body(apiResponse);
	}

	@DeleteMapping("/milestones/{milestoneId}")
	public ResponseEntity<DataApiResponse> deleteOne(@PathVariable Long milestoneId) {
		milestoneService.deleteOne(MEMBER_ID, milestoneId);

		return ResponseEntity.noContent().build();
	}
}
