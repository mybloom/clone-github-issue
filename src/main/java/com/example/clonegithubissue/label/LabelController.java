package com.example.clonegithubissue.label;

import com.example.clonegithubissue.common.dto.DataApiResponse;
import com.example.clonegithubissue.exception.LabelDuplicateDataException;
import com.example.clonegithubissue.label.dto.LabelSaveRequest;
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
public class LabelController {

	private final LabelService labelService;
	private final static Long MEMBER_ID = 1L;

	@GetMapping("/labels")
	public ResponseEntity<DataApiResponse> retrieveList(
		@RequestParam("page") Optional<Integer> page,
		@RequestParam("size") Optional<Integer> size) {

		DataApiResponse apiResponse = labelService.retrieveList(MEMBER_ID,
			page.orElse(Label.DEFAULT_PAGE), size.orElse(Label.DEFAULT_PAGE_SIZE));

		return ResponseEntity.ok().body(apiResponse);
	}

	@GetMapping("/labels/{labelId}")
	public ResponseEntity<DataApiResponse> retrieveDetail(@PathVariable Long labelId) {
		DataApiResponse apiResponse = labelService.retrieveDetail(MEMBER_ID, labelId);

		return ResponseEntity.ok().body(apiResponse);
	}

	@PostMapping("/labels")
	public ResponseEntity<DataApiResponse> createOne(
		@RequestBody LabelSaveRequest labelSaveRequest) {
		DataApiResponse apiResponse = labelService.createOne(MEMBER_ID, labelSaveRequest);

		return ResponseEntity.ok().body(apiResponse);
	}

	@PatchMapping("/labels/{labelId}")
	public ResponseEntity<DataApiResponse> modifyOne(@PathVariable Long labelId,
		@RequestBody LabelSaveRequest labelSaveRequest) {

		DataApiResponse apiResponse = null;
		try {
			apiResponse = labelService.modifyOne(MEMBER_ID, labelId,
				labelSaveRequest);
		} catch (RuntimeException e) {
			throw new LabelDuplicateDataException();
		}

		return ResponseEntity.ok().body(apiResponse);
	}

	@DeleteMapping("/labels/{labelId}")
	public ResponseEntity<DataApiResponse> deleteOne(@PathVariable Long labelId) {
		labelService.deleteOne(MEMBER_ID, labelId);

		return ResponseEntity.noContent().build();
	}
}
