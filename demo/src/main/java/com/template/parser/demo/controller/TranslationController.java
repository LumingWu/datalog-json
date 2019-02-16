package com.template.parser.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationController {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@PostMapping("/translate")
	Translation translate(
			@RequestParam final int from,
			@RequestParam final int to,
			@RequestParam final String template) {
		List<String> templateEngines = templateEngine.getTemplateEngines();
		if(from < 0 || from >= templateEngines.size() || to < 0 || to >= templateEngines.size()) {
			throw new InvalidTemplateEngineException();
		}
		return new Translation(template);
	}
	
}
