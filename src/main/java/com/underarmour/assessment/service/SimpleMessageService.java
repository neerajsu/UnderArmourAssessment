package com.underarmour.assessment.service;

import java.util.List;
import java.util.Optional;

import com.underarmour.assessment.domain.SimpleMessage;

public interface SimpleMessageService {
	public SimpleMessage createSimpleMessage(String userName, String message, Integer timeout);
	public Optional<SimpleMessage> findMessageById(Integer id);
	List<SimpleMessage> findMessageByUserName(String userName);
}
