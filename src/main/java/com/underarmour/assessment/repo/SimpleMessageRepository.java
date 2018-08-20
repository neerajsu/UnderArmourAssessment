package com.underarmour.assessment.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.underarmour.assessment.domain.SimpleMessage;

public interface SimpleMessageRepository extends CrudRepository<SimpleMessage, Integer>{
	List<SimpleMessage> findByUserName(String userName);
}
