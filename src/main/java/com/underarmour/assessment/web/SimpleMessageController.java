package com.underarmour.assessment.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.underarmour.assessment.domain.SimpleMessage;
import com.underarmour.assessment.exception.APIException;
import com.underarmour.assessment.repo.SimpleMessageRepository;
import com.underarmour.assessment.service.SimpleMessageService;

@RestController
public class SimpleMessageController {

	@Autowired
	SimpleMessageService simpleMessageService;
	
	@Autowired
	SimpleMessageRepository simpleMessageRepository;

	@RequestMapping(method = RequestMethod.POST, path = "/chat")
	@ResponseStatus(HttpStatus.CREATED)
	public IdReturnType createChatMessage(@RequestBody @Validated ChatRequestDto messageDto) {
		SimpleMessage simpleMessage = simpleMessageService.createSimpleMessage(messageDto.getUsername(),
				messageDto.getText(), getExpirationDateFromTimeout(messageDto.getTimeout()));
		return new IdReturnType(simpleMessage.getId());
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/chat/{id}")
	public ChatResponseDto getChatMessage(@PathVariable Integer id){
		Optional<SimpleMessage> simpleMessageOptional = simpleMessageRepository.findById(id);
		if(simpleMessageOptional.isPresent()) {
			return toReponseDto(simpleMessageOptional.get());
		} else {
			throw new APIException(HttpStatus.NOT_FOUND, "Message for this id: " +id+" does not exist");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/chats/{userName}")
	public List<ChatsResponseDto>getChatMessages(@PathVariable String userName){
		
	}
	
	private ChatResponseDto toReponseDto(SimpleMessage simpleMessage) {
		return null;
	}

	private Date getExpirationDateFromTimeout(int timeout) {
		return new Date();
	}

	class IdReturnType {
		private Integer id;

		public IdReturnType(Integer id) {
			id = this.id;
		}
	}
}
