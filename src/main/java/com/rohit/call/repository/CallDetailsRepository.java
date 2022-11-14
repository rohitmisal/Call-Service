package com.rohit.call.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.call.Entity.CallDetailsEntity;

public interface CallDetailsRepository extends JpaRepository<CallDetailsEntity, Integer> {

	List<CallDetailsEntity> findByFromNumber(Long fromNumber);
}
