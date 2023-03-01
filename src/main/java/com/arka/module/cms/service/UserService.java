package com.arka.module.cms.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arka.module.cms.entity.LoginHistory;
import com.arka.module.cms.entity.OtpVerification;
import com.arka.module.cms.entity.UserMaster;
import com.arka.module.cms.exception.ResourceNotFoundException;
import com.arka.module.cms.repo.LoginHistoryRepo;
import com.arka.module.cms.repo.OtpVerificationRepo;
import com.arka.module.cms.repo.UserMasterRepo;
import com.arka.module.cms.utils.Response;
import com.arka.module.cms.utils.Utility;



/**
 * @author Surekha
 *
 */

@Service
public class UserService {


	@Autowired
	UserMasterRepo userRepo;

	@Autowired
	OtpVerificationRepo otpRepo;

	@Autowired
	LoginHistoryRepo lhRepo;

	Response res;

	private  Logger log = LoggerFactory.getLogger(UserService.class);

	/**
	 * This method is used to generate OTP to user
	 */

	public String sendOtpToUser(String email) throws Exception {
		log.debug("Entered Email..."+email);
		OtpVerification otpVerify = new OtpVerification();
		String response = "";
		UserMaster user = userRepo.findByEmailId(email);
		if(user != null) {
			String OTP = Utility.genrateOTP();
			String organizationId = user.getOrganizationId();
			//			JSONObject jsonObject = new JSONObject();

			Long userId = user.getUserId();
			//			String message = "Hello " + email + ""
			//		            + "you're required to use the following "
			//		            + "One Time Password to login:"
			//		            + "" + OTP + "";
			//			Utility.sendMailtoUser(email, message, "OTP");
			otpVerify.setEmail(email);
			otpVerify.setOtp(OTP);
			otpRepo.save(otpVerify);
			response =  "Your One Time Password has been sent to the registered email";
		}else {
			UserMaster userM = new UserMaster();
			userM.setEmailId(email);
			userRepo.save(userM);
			String OTP = Utility.genrateOTP();
			String message = "Hello " + email + ""
					+ "For security reason, you're required to use the following "
					+ "One Time Password to login:"
					+ "" + OTP + "";
			Utility.sendMailtoUser(email, message, "Reset Password");
			otpVerify.setEmail(email);
			otpVerify.setOtp(OTP);
			otpRepo.save(otpVerify);
			response =  "Your One Time Password has been sent to the registered email";
		}		
		return response;
	}

	public Object verifyOtpAndLogin(String email,String otp) throws Exception {	

		OtpVerification verify = otpRepo.findByEmailAndOtp(email, otp);

		Optional.ofNullable(verify).orElseThrow(()-> new ResourceNotFoundException("List is Empty"));
		System.out.println("verify---->"+ verify);	 	 
		UserMaster um = userRepo.findByEmailId(email);
		LoginHistory history =  new LoginHistory();
		history.setStatus("Success");
		history.setUserId(um.getUserId());
		history.setLogin(true);
		final LoginHistory lh = lhRepo.save(history);			
		return lh;
	}

	public Object verifyUserEmailAndPassword(String email,String password) throws Exception {	

		Object verify = userRepo.findByEmailIdAndPassword(email, password);
		if(verify == null) {
			res = new Response(204,"Failed to Login");
		}else {		System.out.println("verify---->"+ verify);	 	 
		LoginHistory history =  new LoginHistory();
		history.setStatus("Success");
		history.setUserId(((UserMaster) verify).getUserId());
		history.setLogin(true);
		final LoginHistory lh = lhRepo.save(history);
			res = new Response(200,"Logged In Successfully",verify);
		}
		return res;
	}
	
	public Object verifySessionLogout(String sessionId) throws Exception {	

		LoginHistory findBySessionId = lhRepo.findBySessionId(sessionId);
		if(findBySessionId == null) {
			res = new Response(204,"Failed to Login");
		}else {
			findBySessionId.setLogin(false);
			findBySessionId.setStatus("LoggedOut");
			final LoginHistory lh = lhRepo.save(findBySessionId);
			res = new Response(200,"Logged Out Successfully",lh);

		}
		return res;

	}
	
	

}
