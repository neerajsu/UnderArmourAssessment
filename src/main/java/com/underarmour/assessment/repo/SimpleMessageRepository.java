package com.underarmour.assessment.repo;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import com.underarmour.assessment.domain.SimpleMessage;

public interface SimpleMessageRepository extends CrudRepository<SimpleMessage, Integer> {
	Stream<SimpleMessage> findByUserName(String userName);
}
