package com.vechical.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vechical.service.bo.VechicalService;
import com.vechical.service.bo.dto.VechicalDTO;

import reactor.core.publisher.Mono;

@RestController
public class VechicalController {

	@Autowired
	private VechicalService vechicalService;

	@PostMapping("/saveVechical")
	public Mono<ResponseEntity<String>> saveVechical(@RequestBody VechicalDTO vechicalDTO) {
		return vechicalService.insertVechical(vechicalDTO)
				.map(vechicalId -> ResponseEntity.status(HttpStatus.CREATED)
						.body("vechical saved successfully with id:" + vechicalId))
				.onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("failed to save vechical" + error.getMessage())));

	}
}
