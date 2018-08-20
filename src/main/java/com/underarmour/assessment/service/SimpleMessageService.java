package com.underarmour.assessment.service;

import java.util.List;
import java.util.Optional;

import com.underarmour.assessment.domain.SimpleMessage;
import com.underarmour.assessment.model.RedisMessage;

public interface SimpleMessageService {
	public SimpleMessage createSimpleMessage(String userName, String message, Integer timeout);
	public Optional<SimpleMessage> findMessageById(Integer id);
	public void expireMessage(List<RedisMessage> redisMessages);
	public List<RedisMessage> findMessageByUserName(String userName);
}
