package com.underarmour.assessment.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.underarmour.assessment.common.CommonConstants;
import com.underarmour.assessment.domain.SimpleMessage;
import com.underarmour.assessment.exception.APIException;
import com.underarmour.assessment.exception.ErrorInfo;
import com.underarmour.assessment.repo.SimpleMessageRepository;
import com.underarmour.assessment.service.SimpleMessageService;
import com.underarmour.assessment.utils.DateUtils;

@RestController
public class SimpleMessageController {

	private static final Logger LOG = LogManager.getLogger(SimpleMessageController.class.getName());

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
	public ChatResponseDto getChatMessage(@PathVariable Integer id) {
		Optional<SimpleMessage> simpleMessageOptional = simpleMessageRepository.findById(id);
		if (simpleMessageOptional.isPresent()) {
			return ToChatReponseDto(simpleMessageOptional.get());
		} else {
			throw new APIException(HttpStatus.NOT_FOUND, "Message for this id: " + id + " does not exist");
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/chats/{userName}")
	public List<ChatsResponseDto> getChatMessages(@PathVariable String userName) {
		Stream<SimpleMessage> messagesStream = simpleMessageRepository.findByUserName(userName);
		return messagesStream.map(message -> toChatsReponseDto(message)).collect(Collectors.toList());
	}

	private ChatResponseDto ToChatReponseDto(SimpleMessage simpleMessage) {
		return new ChatResponseDto(simpleMessage.getUserName(), simpleMessage.getMessage(),
				DateUtils.toDateFormat(simpleMessage.getExpirationDate()));
	}

	private ChatsResponseDto toChatsReponseDto(SimpleMessage simpleMessage) {
		return new ChatsResponseDto(simpleMessage.getId(), simpleMessage.getMessage());
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

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> exceptionHandlerForValidation(MethodArgumentNotValidException exception) {

		LOG.error("MethodArgumentNotValidException occured ::", exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS,
				"Validation of request body failed", HttpStatus.BAD_REQUEST.getReasonPhrase()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorInfo> exceptionHandlerForApiException(APIException exception) {

		LOG.error("APIException occured ::", exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, exception.getMessage(),
				exception.getStatus().getReasonPhrase()), exception.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {

		LOG.error("Exception occured ::", exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
