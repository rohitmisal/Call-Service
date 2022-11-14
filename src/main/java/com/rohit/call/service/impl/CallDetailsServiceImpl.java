package com.rohit.call.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohit.call.Entity.CallDetailsEntity;
import com.rohit.call.mapper.CallDetailsMapper;
import com.rohit.call.model.CallDetails;
import com.rohit.call.repository.CallDetailsRepository;
import com.rohit.call.service.CallDetailsService;

@Service
public class CallDetailsServiceImpl implements CallDetailsService {

	@Autowired
	CallDetailsRepository callRepo;
	
	@Override
	public List<CallDetails> getCallDetails(Long fromNumber) {

		List<CallDetailsEntity>lst=callRepo.findByFromNumber(fromNumber);
		List<CallDetails> callLst=new ArrayList<>();
		lst.forEach(entity->{
			CallDetails cd=new CallDetails();
			BeanUtils.copyProperties(entity,cd);
			callLst.add(cd);
		});
		
		return callLst	;
	}

	@Override
	public void addCallDetails(CallDetails callDetails) {

		CallDetailsEntity callDetailsEntity=CallDetailsMapper.calltoCallDetailsMapper(callDetails);
		callRepo.save(callDetailsEntity);
	}

}
