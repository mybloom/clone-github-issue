package com.example.clonegithubissue.milestone;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MilestoneController {

	private final MilestoneService milestoneService;
}
