package com.rohit.call.mapper;

import org.springframework.beans.BeanUtils;

import com.rohit.call.Entity.CallDetailsEntity;
import com.rohit.call.model.CallDetails;

public class CallDetailsMapper {

	public static CallDetails callEntitytoCallDetailsMapper(CallDetailsEntity callDetailsEntity) {
		
		CallDetails callDetails=new CallDetails();
		BeanUtils.copyProperties(callDetailsEntity, callDetails);
		return callDetails;
		
	}
	
	public static CallDetailsEntity calltoCallDetailsMapper(CallDetails callDetails) {
		
		CallDetailsEntity callDetailsEntity=new CallDetailsEntity();
		BeanUtils.copyProperties( callDetails,callDetailsEntity);
		return callDetailsEntity;
		
	}
}
