package com.rohit.call.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.call.model.CallDetails;
import com.rohit.call.service.CallDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
public class CallDetailsAPI {

	@Autowired
	CallDetailsService callService;
	
	@GetMapping("/calldetails/{fromNumber}")
	@ApiOperation(value = "Return the call Details of a number")
	public ResponseEntity<?> getCallDetailsforNumber(@PathVariable Long fromNumber){
		List<CallDetails> lst= callService.getCallDetails(fromNumber);
		if(!lst.isEmpty()) {
			
			return new ResponseEntity<List<CallDetails>>(lst,HttpStatus.OK);
		}
		else {
			
			return  new ResponseEntity<String>("NO record Found",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@PostMapping("/calldetails/add")
	@ApiResponse(code = 200,message = "Call Details added Successfully")
	public ResponseEntity<String>addCallDetails(@RequestBody CallDetails callDetails){
		callService.addCallDetails(callDetails);
		return new ResponseEntity<String>("CallDetails Added succesfully",HttpStatus.CREATED);
	}
}
