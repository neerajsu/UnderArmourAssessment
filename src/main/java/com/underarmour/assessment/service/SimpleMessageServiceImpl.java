package com.underarmour.assessment.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.underarmour.assessment.domain.SimpleMessage;
import com.underarmour.assessment.model.RedisMessage;
import com.underarmour.assessment.repo.SimpleMessageColdRepository;
import com.underarmour.assessment.repo.SimpleMessageHotRepository;

@Service
@Transactional
public class SimpleMessageServiceImpl implements SimpleMessageService {

	private SimpleMessageColdRepository coldRepository;
	
	private SimpleMessageHotRepository hotRepository;

	@Autowired
	public SimpleMessageServiceImpl(SimpleMessageColdRepository coldRepository, SimpleMessageHotRepository hotRepository) {
		this.coldRepository = coldRepository;
		this.hotRepository = hotRepository;
	}

	@Override
	public SimpleMessage createSimpleMessage(String userName, String message, Integer timeout) {
		if (timeout == null) {
			timeout = 60;
		}
		
		SimpleMessage simpleMessage = coldRepository.save(new SimpleMessage(userName, message, getExpirationDateFromTimeout(timeout)));
		hotRepository.addMessage(new RedisMessage(simpleMessage.getId(), userName, message, getExpirationDateFromTimeout(timeout)));
		return simpleMessage;
	}

	@Override
	public Optional<SimpleMessage> findMessageById(Integer id) {
		//No need for using redis hot storage here since lookup on id for single row should be performant on cold storage too
		return coldRepository.findById(id);
	}

	@Override
	public List<RedisMessage> findMessageByUserName(String userName) {
		return hotRepository.findAllByUserName(userName);
	}
	
	private Date getExpirationDateFromTimeout(int timeout) {
		return Date.from(Instant.now().plusSeconds(timeout));
	}

	@Override
	public void expireMessage(List<RedisMessage> redisMessages) {
		for (RedisMessage redisMessage : redisMessages) {
			hotRepository.deleteMessage(redisMessage);
		}
	}

}
