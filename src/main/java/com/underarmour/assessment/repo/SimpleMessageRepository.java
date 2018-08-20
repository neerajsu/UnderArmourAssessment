package com.underarmour.assessment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.underarmour.assessment.domain.SimpleMessage;

public interface SimpleMessageRepository extends JpaRepository<SimpleMessage, Integer> {
	List<SimpleMessage> findByUserName(String userName);
}
