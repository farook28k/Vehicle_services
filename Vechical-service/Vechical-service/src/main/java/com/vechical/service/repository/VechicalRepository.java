package com.vechical.service.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.vechical.service.repository.Entity.VechicalEntity;

public interface VechicalRepository extends ReactiveCrudRepository<VechicalEntity, Long> {

}
