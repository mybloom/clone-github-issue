package com.example.clonegithubissue.label;

import com.example.clonegithubissue.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LabelController {

	private final LabelService labelService;
	private final static Long MEMBER_ID = 1L;

	@GetMapping("/labels")
	public ResponseEntity<ApiResponse> retrieveList(@RequestParam("page") Integer page,
		@RequestParam("size") Integer size) {
		ApiResponse apiResponse = labelService.retrieveList(MEMBER_ID, page, size);

		return ResponseEntity.ok().body(apiResponse);
	}
}
