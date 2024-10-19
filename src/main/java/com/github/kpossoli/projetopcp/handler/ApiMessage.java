package com.github.kpossoli.projetopcp.handler;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiMessage {

	private final String code;
	private final String message;

}