package com.noman.ems.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;


@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {
    private Map<String, String> errors;
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(Map<String, String> errors) {
        super("Validation failed");
        this.errors = errors;
    }
}
