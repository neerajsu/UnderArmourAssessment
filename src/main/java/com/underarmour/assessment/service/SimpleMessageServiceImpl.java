package com.underarmour.assessment.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.underarmour.assessment.domain.SimpleMessage;
import com.underarmour.assessment.repo.SimpleMessageRepository;

@Service
public class SimpleMessageServiceImpl implements SimpleMessageService {

	private SimpleMessageRepository messageRepository;

	@Autowired
	public SimpleMessageServiceImpl(SimpleMessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public SimpleMessage createSimpleMessage(String userName, String message, Date expirationDate) {
		if (userName != null) {
			return messageRepository.save(new SimpleMessage(userName, message, expirationDate));
		} else {
			return null; // I would throw custom exception that could be handled based on rest api contract - Not provided in requirement
		}
	}

}
