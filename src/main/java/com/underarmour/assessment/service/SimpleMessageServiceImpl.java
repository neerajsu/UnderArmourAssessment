package com.underarmour.assessment.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.underarmour.assessment.domain.SimpleMessage;
import com.underarmour.assessment.repo.SimpleMessageColdRepository;

@Service
public class SimpleMessageServiceImpl implements SimpleMessageService {

	private SimpleMessageColdRepository coldRepository;

	@Autowired
	public SimpleMessageServiceImpl(SimpleMessageColdRepository coldRepository) {
		this.coldRepository = coldRepository;
	}

	@Override
	public SimpleMessage createSimpleMessage(String userName, String message, Integer timeout) {
		if (timeout == null) {
			timeout = 60;
		}
		return coldRepository.save(new SimpleMessage(userName, message, getExpirationDateFromTimeout(timeout)));
	}

	@Override
	public Optional<SimpleMessage> findMessageById(Integer id) {
		return coldRepository.findById(id);
	}

	@Override
	public List<SimpleMessage> findMessageByUserName(String userName) {
		return coldRepository.findByUserName(userName);
	}
	
	private Date getExpirationDateFromTimeout(int timeout) {
		Instant instant = Instant.now();
		instant.plusSeconds(timeout);
		return Date.from(instant);
	}

}
