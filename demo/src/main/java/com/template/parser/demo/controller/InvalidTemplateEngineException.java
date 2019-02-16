package com.template.parser.demo.controller;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="InvalidTemplateSelection")
class InvalidTemplateEngineException extends RuntimeException {
	
	InvalidTemplateEngineException() {
		super();
	}
	
}
