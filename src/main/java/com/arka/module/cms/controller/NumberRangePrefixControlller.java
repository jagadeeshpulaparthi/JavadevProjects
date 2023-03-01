package com.arka.module.cms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.NumberRangePrefix;
import com.arka.module.cms.repo.NumberRangePrefixRepo;
import com.arka.module.cms.service.NumberRangePrefixService;

@SuppressWarnings({"unused"})
@RestController
@CrossOrigin
public class NumberRangePrefixControlller {
	private static final Logger logger = LogManager.getLogger(NumberRangePrefixControlller.class);

	
	@Autowired
	NumberRangePrefixRepo preRepo;
	
	@Autowired
	NumberRangePrefixService preSer;
	
	@PostMapping("/addingPrefix")
	public ResponseEntity<?> addingPrefix(@RequestBody  NumberRangePrefix Prefix) throws Exception  {
		boolean exists = preRepo.existsByPrefix(Prefix.getPrefix());
		if(exists) {
			throw new Exception();
		}else {
		NumberRangePrefix save = preRepo.save(Prefix);
		return new ResponseEntity<>(save, HttpStatus.OK);
		}
	}
	@GetMapping("/getPrefixes")
	public ResponseEntity<?> getPrefixes(@RequestParam Long userId) throws Exception{
		List <NumberRangePrefix> prefixList = preRepo.findByUserId(userId);
		if(prefixList.isEmpty()) {
			throw new Exception();
		}else {
			return new ResponseEntity<>(prefixList, HttpStatus.OK);

		}

	}

}
