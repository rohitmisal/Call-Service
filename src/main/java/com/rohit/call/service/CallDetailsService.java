package com.rohit.call.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rohit.call.model.CallDetails;


public interface CallDetailsService {

	List<CallDetails> getCallDetails(Long fromNumber);
	void addCallDetails(CallDetails callDetails);
}
