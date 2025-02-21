package com.isi.maven.services.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
