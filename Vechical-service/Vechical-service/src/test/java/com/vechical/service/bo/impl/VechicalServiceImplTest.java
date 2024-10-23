package com.vechical.service.bo.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.vechical.service.bo.dto.VechicalDTO;
import com.vechical.service.bo.mapper.VechicalMapper;
import com.vechical.service.exception.MyCustomException;
import com.vechical.service.repository.VechicalRepository;
import com.vechical.service.repository.Entity.VechicalEntity;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class VechicalServiceImplTest {
	@InjectMocks
	private VechicalServiceImpl vechicalServiceImpl;
	@Mock
	private VechicalRepository vechicalRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@org.junit.Test
	public void insertVchicalTest() {
		VechicalDTO vechicalDTO = VechicalDTO.builder().build();
		VechicalEntity vechicalEntity = VechicalMapper.toEntity(vechicalDTO);
		VechicalEntity savevechicalEntity = VechicalEntity.builder().vechicalId(1l).build();
		when(vechicalRepository.save(vechicalEntity)).thenReturn(Mono.just(savevechicalEntity));
		Mono<Long> vechicalId = vechicalServiceImpl.insertVechical(vechicalDTO);
		StepVerifier.create(vechicalId).expectNext(savevechicalEntity.getVechicalId()).verifyComplete();

	}

	@org.junit.Test
	public void inserstVechicalTest_ErrorHandaling() {
		VechicalDTO vechicalDTO = VechicalDTO.builder().build();
		RuntimeException runTimeError = new RuntimeException("user details not saved");
		when(vechicalRepository.save(any(VechicalEntity.class))).thenReturn(Mono.error(runTimeError));
		Mono<Long> vechicalId = vechicalServiceImpl.insertVechical(vechicalDTO);
		StepVerifier.create(vechicalId).expectErrorMatches(error -> error instanceof MyCustomException).verify();
	}
}
