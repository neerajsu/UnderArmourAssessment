package com.underarmour.assessment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.underarmour.assessment.domain.SimpleMessage;

public interface SimpleMessageColdRepository extends JpaRepository<SimpleMessage, Integer> {
	List<SimpleMessage> findByUsername(String userName);
}
