package com.underarmour.assessment.service;

import java.util.Date;

import com.underarmour.assessment.domain.SimpleMessage;

public interface SimpleMessageService {
	public SimpleMessage createSimpleMessage(String userName, String message, Date expirationDate);
}
