package com.rohit.call.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.rohit.call.Entity.CallDetailsEntity;
import com.rohit.call.mapper.CallDetailsMapper;
import com.rohit.call.model.CallDetails;
import com.rohit.call.repository.CallDetailsRepository;
import com.rohit.call.service.CallDetailsService;
import com.rohit.call.service.exception.CallDetailsNotFoundException;
import com.rohit.call.service.exception.CallDetailsNotValid;

@Service
public class CallDetailsServiceImpl implements CallDetailsService {

	@Autowired
	CallDetailsRepository callRepo;

	@Cacheable(value = "callDetails", key = "#fromNumber", unless = "#result==null")
	@Override
	public List<CallDetails> getCallDetails(Long fromNumber) {

		List<CallDetailsEntity> lst = callRepo.findByFromNumber(fromNumber);
		List<CallDetails> callLst = new ArrayList<>();
		if (!lst.isEmpty()) {
			lst.forEach(entity -> {
				CallDetails cd = new CallDetails();
				BeanUtils.copyProperties(entity, cd);
				callLst.add(cd);
			});
		} else {
			throw new CallDetailsNotFoundException(String.format("For given no %d not callDetails found", fromNumber));
		}

		return callLst;
	}

	@Caching(evict = { @CacheEvict(value = "callDetails", allEntries = true), }, put = {

			@CachePut(value = "callDetails", key = "#callDetails.getFromNumber()") })
	@Override
	public void addCallDetails(CallDetails callDetails) {

		CallDetailsEntity callDetailsEntity = CallDetailsMapper.calltoCallDetailsMapper(callDetails);
		if (callDetailsEntity != null) {
			callRepo.save(callDetailsEntity);
		} else {
			throw new CallDetailsNotValid("Plese provide valid call Details ");
		}
	}

}
