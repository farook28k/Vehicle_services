package com.vechical.service.bo.impl;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vechical.service.bo.VechicalService;
import com.vechical.service.bo.dto.VechicalDTO;
import com.vechical.service.bo.mapper.VechicalMapper;
import com.vechical.service.exception.MyCustomException;
import com.vechical.service.repository.VechicalRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

@Service
@Slf4j
public class VechicalServiceImpl implements VechicalService {

	@Autowired
	private VechicalRepository vechicalRepository;

	@Override
	public Mono<Long> insertVechical(VechicalDTO vechicalDTO) {
		log.info("In VechicalServiceImpl insertUser method... executing");

		RetryBackoffSpec retrySpec = Retry.backoff(3, Duration.ofSeconds(2)).onRetryExhaustedThrow((retryBackoffSpec,
				retrySignal) -> new MyCustomException("Failed to save user after retries", retrySignal.failure()));
		return vechicalRepository.save(VechicalMapper.toEntity(vechicalDTO)).map(vechicalEntity -> {
			log.info("in VechicalServiceImpl insertUser method ...vechiclId is {}", vechicalEntity.getVechicalId());
			return vechicalEntity.getVechicalId();
		}).onErrorMap(error -> {
			log.error("in userServiceimpl insertMethod ExceptionRasied -{}", error);
			throw new MyCustomException("failed to vechical data saveing", error);
		}).retryWhen(retrySpec);

	}

	@Override
	public Mono<List<VechicalDTO>> getAllVechical() {

		return vechicalRepository.findAll().map(vechicalEntity -> VechicalMapper.toDTO(vechicalEntity)).collectList()
				.retryWhen(Retry.backoff(3, Duration.ofSeconds(5)));
	}

}
