package com.dxc.dxconlinebanking.common;

import java.util.Map;

public record ApiError(String message, Map<String, String> fieldErrors) {

	public ApiError(String message) {
		this(message, Map.of());
	}
}
