package com.vechical.service.bo;

import java.util.List;

import com.vechical.service.bo.dto.VechicalDTO;

import reactor.core.publisher.Mono;

public interface VechicalService {
	public Mono<Long> insertVechical(VechicalDTO vechicalDTO);

	public Mono<List<VechicalDTO>> getAllVechical();

}
