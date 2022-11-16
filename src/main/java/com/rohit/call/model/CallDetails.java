package com.rohit.call.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CallDetails implements Serializable{

	private Integer id;
	private Long fromNumber;
	private Long toNumber;
	private Integer duration;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime calledOn;
	
}
