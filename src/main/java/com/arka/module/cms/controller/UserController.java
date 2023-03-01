package com.arka.module.cms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.LoginHistory;
import com.arka.module.cms.entity.UserMaster;
import com.arka.module.cms.repo.UserMasterRepo;
import com.arka.module.cms.service.UserService;



/**
 * @author Surekha
 *
 */


@SuppressWarnings({"unused"})
@RestController
@CrossOrigin
public class UserController {
	private static final Logger logger = LogManager.getLogger(NumberRangeController.class);

	@Autowired
	UserMasterRepo umRepo;
	
	@Autowired
	UserService uSer;
	
	/**
	 * This Api is to genarate Otp and sends the otp to the user's registered email
	 * @param email
	 * @return message
	 * @throws Exception
	 */
	@PostMapping("getOtp")
	public ResponseEntity<?> getOtp(String email) throws Exception{
		String res = uSer.sendOtpToUser(email);
		return ResponseEntity.ok().body(res);
		}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody UserMaster user ) throws Exception{
//		Object verifyOtpAndLogin = uSer.verifyOtpAndLogin(email, otp);
		Object verifyOtpAndLogin = uSer.verifyUserEmailAndPassword(user.getEmailId(), user.getPassword());
		return ResponseEntity.ok(verifyOtpAndLogin);
	}
	@PostMapping("logout")
	public ResponseEntity<?> logout(@RequestBody LoginHistory login ) throws Exception{
//		Object verifyOtpAndLogin = uSer.verifyOtpAndLogin(email, otp);
		Object logout = uSer.verifySessionLogout(login.getSessionId());
		return ResponseEntity.ok(logout);
	}
	
	
}
