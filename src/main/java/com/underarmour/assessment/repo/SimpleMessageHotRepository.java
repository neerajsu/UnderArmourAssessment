package com.underarmour.assessment.repo;

import java.util.List;

import com.underarmour.assessment.model.RedisMessage;


public interface SimpleMessageHotRepository {
	void addMessage(final RedisMessage message);
	List<RedisMessage> findAllByUserName(String userName);
	void deleteMessage(final RedisMessage message);
}
