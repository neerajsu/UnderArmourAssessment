package com.underarmour.assessment.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class SimpleMessage {

	protected SimpleMessage() {
	}

	public SimpleMessage(String userName, String message, Date expirationDate) {
		super();
		this.userName = userName;
		this.message = message;
		this.expirationDate = expirationDate;
	}

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String userName;
	
	@Column
	private String message;

	@Column
	private Date expirationDate;

	@Column
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@Column
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public Integer getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getMessage() {
		return message;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

}
