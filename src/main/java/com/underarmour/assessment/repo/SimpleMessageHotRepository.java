package com.underarmour.assessment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.underarmour.assessment.model.RedisMessage;

@Repository
public interface SimpleMessageHotRepository extends CrudRepository<RedisMessage, String> {

}
