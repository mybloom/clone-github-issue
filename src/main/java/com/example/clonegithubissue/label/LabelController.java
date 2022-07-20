package com.example.clonegithubissue.label;

import com.example.clonegithubissue.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LabelController {

	private final LabelService labelService;
	private final static Long MEMBER_ID = 1L;

	@GetMapping("/labels")
	public ResponseEntity<ApiResponse> retrieveList() {
		ApiResponse apiResponse = labelService.retrieveList(MEMBER_ID);

		return ResponseEntity.ok().body(apiResponse);
	}
}
