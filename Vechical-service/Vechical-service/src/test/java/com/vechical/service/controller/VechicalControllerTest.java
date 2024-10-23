package com.vechical.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.vechical.service.bo.VechicalService;
import com.vechical.service.bo.dto.VechicalDTO;
import com.vechical.service.exception.MyCustomException;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class VechicalControllerTest {

	@InjectMocks
	private VechicalController vechicalController;

	@Mock
	private VechicalService vechicalService; 

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveVechicalTest() {
		VechicalDTO vechicalDTO = VechicalDTO.builder().build();
		when(vechicalService.insertVechical(any(VechicalDTO.class))).thenReturn(Mono.just(1l));
		Mono<ResponseEntity<String>> response = vechicalController.saveVechical(vechicalDTO);
		StepVerifier.create(response).expectNextMatches(responseEntity -> {
			HttpStatusCode status = responseEntity.getStatusCode();
			String body = responseEntity.getBody();
			return status == HttpStatus.CREATED && body.contains("vechical saved successfully with id:");
		}).verifyComplete();

	}

	@Test
	public void saveVechicalTestException() {
		VechicalDTO vechicalDtO = VechicalDTO.builder().build();

		when(vechicalService.insertVechical(any(VechicalDTO.class)))
				.thenReturn(Mono.error(new MyCustomException("failed to save VechicalDate")));
		Mono<ResponseEntity<String>> response = vechicalController.saveVechical(vechicalDtO);
		StepVerifier.create(response).expectNextMatches(responseEntity -> {
			HttpStatusCode status = responseEntity.getStatusCode();
			String body = responseEntity.getBody();
			return status == HttpStatus.INTERNAL_SERVER_ERROR && body.contains("failed to save vechical");
		}).verifyComplete();
	}

}
