package com.github.kpossoli.projetopcp.handler;

import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse {

	private final int status;
	private final List<ApiMessage> messages;

	public static ApiResponse of(int status, List<ApiMessage> errors) {
		return new ApiResponse(status, errors);
	}

	public static ApiResponse of(int status, ApiMessage error) {
		return of(status, Collections.singletonList(error));
	}

}
