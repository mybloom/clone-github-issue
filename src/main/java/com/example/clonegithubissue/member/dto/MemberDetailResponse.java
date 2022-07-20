package com.example.clonegithubissue.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class MemberDetailResponse {

	/**
	 * id : Member.oauthId와 매핑되는 속성
	 */
	private Long id;
	private String name;

}
