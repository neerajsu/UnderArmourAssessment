package com.underarmour.assessment.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.underarmour.assessment.model.RedisMessage;

@Repository
public class SimpleMessageHotRepositoryImpl implements SimpleMessageHotRepository {

	private RedisTemplate<String, String> redisTemplate;
	ValueOperations<String, String> valueOperations;
	
	@PostConstruct
    private void init(){
        this.valueOperations = redisTemplate.opsForValue();
    }

	@Autowired
	public SimpleMessageHotRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public void addMessage(final RedisMessage message) {
		String key = buildKey(message);
		valueOperations= redisTemplate.opsForValue();
		valueOperations.set(key, message.getText());
		valueOperations.getOperations().expireAt(key, message.getExpirationDate());
    }
	
	private String buildKey(final RedisMessage message) {
		return message.getUsername() + ":"+ message.getId();
	}
	
	@Override
	public void deleteMessage(final RedisMessage message) {
		redisTemplate.delete(buildKey(message));
    }
	
	@Override
	public List<RedisMessage> findAllByUserName(String userName) {
		Set<String> keys = redisTemplate.keys(userName+"*");
		List<RedisMessage> redisMessages = new ArrayList<RedisMessage>();
		keys.stream().forEach(key -> {
			String text = valueOperations.get(key);
			String username = userName.split(":")[0];
			Integer id = Integer.parseInt(username.split(":")[1]);
			RedisMessage redisMessage = new RedisMessage(id, username, text, new Date());
			redisMessages.add(redisMessage);
		});
		return redisMessages;
		
	}

}
