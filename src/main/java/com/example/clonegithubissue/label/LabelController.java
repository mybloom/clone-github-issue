package com.example.clonegithubissue.label;

import com.example.clonegithubissue.common.dto.GetApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LabelController {

	private final LabelService labelService;
	private final static Long MEMBER_ID = 1L;

	@GetMapping("/labels")
	public ResponseEntity<GetApiResponse> retrieveList(@RequestParam("page") Integer page,
		@RequestParam("size") Integer size) {
		GetApiResponse apiResponse = labelService.retrieveList(MEMBER_ID, page, size);

		return ResponseEntity.ok().body(apiResponse);
	}

	@GetMapping("/labels/{labelId}")
	public ResponseEntity<GetApiResponse> retrieveDetail(@PathVariable Long labelId) {
		GetApiResponse apiResponse = labelService.retrieveDetail(MEMBER_ID, labelId);

		return ResponseEntity.ok().body(apiResponse);
	}


}
