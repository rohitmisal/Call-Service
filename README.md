**Call Details Service API
  It has two end points**
  
    1) url= http://localhost:2001/api/calldetails/add
   
    2) url= http://localhost:2001/api/calldetails/{fromNumber}
    
  **Swagger Ui **
    swagger url =http://localhost:2001/swagger-ui/index.html

**Redis Cache added:**

**1) to adding cache while addig calldetails and removing previous cache for the given from number**

 	 @Caching(evict = { @CacheEvict(value = "callDetails", allEntries = true), }, put = {
			@CachePut(value = "callDetails", key = "#callDetails.getFromNumber()") })
      @Override
      public void addCallDetails(CallDetails callDetails)
      
     
**2) to get cache for callDetails**

 	 @Cacheable(value = "callDetails", key = "#fromNumber", unless = "#result==null")
	 @Override
	 public List<CallDetails> getCallDetails(Long fromNumber)
 
 
  
  
